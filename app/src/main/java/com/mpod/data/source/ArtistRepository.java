package com.mpod.data.source;

import android.support.annotation.NonNull;

import com.mpod.data.Artist;
import com.mpod.data.source.local.DbHelper;
import com.mpod.data.source.remote.ApiHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by xnorcode on 13/07/2018.
 */
@Singleton
public class ArtistRepository implements ArtistDataSource {

    private DbHelper mArtistLocalDataSource;

    private ApiHelper mArtistRemoteDataSource;

    // allowing package local visibility in tests only
    Map<String, Artist> mCachedArtists;
    boolean mCacheInvalid;


    @Inject
    public ArtistRepository(DbHelper artistLocalDataSource, ApiHelper artistRemoteDataSource) {
        this.mArtistLocalDataSource = artistLocalDataSource;
        this.mArtistRemoteDataSource = artistRemoteDataSource;
        mCacheInvalid = false;
    }


    /**
     * Search for an artist in the local db if not found get from API
     * Check CACHE -> Check Local -> Check Remote
     *
     * @param name The name of the artist to search for
     * @return A list with the possible results wrapped in an Observable
     */
    @Override
    public Flowable<List<Artist>> searchArtist(String name) {

        // get results directly from cache
        if (mCachedArtists != null && !mCacheInvalid) {
            return Flowable.just(new ArrayList<Artist>(mCachedArtists.values()));
        }

        if (mCacheInvalid) {
            // cache is invalid download from remote data source
            return downloadArtistFromRemoteDataSource(name);
        } else {
            // check in the local database
            return Flowable.<List<Artist>>create(emitter -> {
                // get artists from local db
                List<Artist> artists = mArtistLocalDataSource.getArtists();

                // if data not valid download from remote API
                if (artists == null || artists.isEmpty()) {
                    // will download artists from remote API at onErrorResumeNext()
                    emitter.onError(new Exception("Could not load artists..."));
                    return;
                }

                // cache
                refreshCache(artists);
                // proceed
                emitter.onNext(artists);
                emitter.onComplete();
            }, BackpressureStrategy.ERROR)
                    .onErrorResumeNext(downloadArtistFromRemoteDataSource(name));
        }
    }


    /**
     * Get more information of a specific artist from the local db if not found query the API
     *
     * @param mbID The mbid of the artist
     * @return The queried artist wrapped in an Observable
     */
    @Override
    public Flowable<Artist> getArtistInfo(@NonNull String mbID) {

        Artist cachedArtist = getCachedArtist(mbID);
        // check cache
        if (hasInfo(cachedArtist)) return Flowable.just(cachedArtist);

        // check in local db
        return Flowable.<Artist>create(emitter -> {
            // get artist from local db
            Artist artist = mArtistLocalDataSource.getArtistInfo(mbID);

            // check data if not valid
            if (!hasInfo(artist)) {
                // will download artist info from remote API at onErrorResumeNext()
                emitter.onError(new Exception("Artist info was not found..."));
                return;
            }

            // cache
            refreshCachedArtist(artist);
            // proceed
            emitter.onNext(artist);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR)
                .onErrorResumeNext(downloadArtistInfoFromRemoteDataSource(mbID));
    }


    /**
     * Notify the data repository that artists in the local cache are out of data and force update
     * by changing the flag to True
     */
    @Override
    public void refreshArtists() {
        mCacheInvalid = true;
    }


    @Override
    public void deleteAllArtists() {
        // clear all previous results from db
        mArtistLocalDataSource.deleteAllArtists();
    }


    /**
     * Private method for searching and download a specific artist from the API
     *
     * @param name The name of the artist to search for
     * @return The possible list of artists as a result
     */
    private Flowable<List<Artist>> downloadArtistFromRemoteDataSource(String name) {
        return Flowable.create(emitter -> {
            // get artists from API
            List<Artist> artists = mArtistRemoteDataSource.searchArtist(name);

            // complete if data not valid
            if (artists == null || artists.isEmpty()) {
                emitter.onError(new Exception("Could not download artists..."));
                return;
            }

            // refresh artist in the cache
            refreshCache(artists);
            // refresh local data source
            refreshLocalDataSource(artists);
            // proceed
            emitter.onNext(artists);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }


    /**
     * Private method to for downloading an artists information from the Remote Dta Source
     *
     * @param mbid The mbid of the artist
     * @return The artist info
     */
    private Flowable<Artist> downloadArtistInfoFromRemoteDataSource(String mbid) {
        return Flowable.create(emitter -> {
            // get artist from API
            Artist artist = mArtistRemoteDataSource.getArtistInfo(mbid);

            // complete if data not valid
            if (artist == null) {
                emitter.onError(new Exception("Could not download Artist info..."));
                return;
            }

            // save to cache
            refreshCachedArtist(artist);
            // update to local db
            mArtistLocalDataSource.updateArtist(artist);
            // proceed
            emitter.onNext(artist);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }


    /**
     * Private method to refresh the cache
     *
     * @param artists The list of artists to cache
     */
    private void refreshCache(List<Artist> artists) {
        if (mCachedArtists == null) mCachedArtists = new LinkedHashMap<>();
        // clear
        mCachedArtists.clear();
        // add to cache
        for (Artist artist : artists) {
            mCachedArtists.put(artist.getMbID(), artist);
        }
        // mark as valid
        mCacheInvalid = false;
    }


    /**
     * Private method to refresh an artist info in cache
     *
     * @param artist The artist to be updated in cache
     */
    private void refreshCachedArtist(Artist artist) {
        if (mCachedArtists != null)
            // add to cache
            mCachedArtists.put(artist.getMbID(), artist);
    }


    /**
     * Private method to refresh the local data source
     *
     * @param artists The list of artist to store in local db
     */
    private void refreshLocalDataSource(List<Artist> artists) {
        // clear all previous results from db
        mArtistLocalDataSource.deleteAllArtists();
        // save places to db
        mArtistLocalDataSource.saveArtists(artists);
    }


    /**
     * Retrieve artist info from cache
     *
     * @param mbid The mbid of the artist
     * @return the requested artist
     */
    private Artist getCachedArtist(String mbid) {
        if (mCachedArtists == null || mCacheInvalid) return null;
        return mCachedArtists.get(mbid);
    }


    /**
     * Check if user given artist has full info
     *
     * @param artist The artist to check if has full info
     * @return
     */
    private boolean hasInfo(Artist artist) {
        return artist != null && artist.getBioSummary() != null && !artist.getBioSummary().isEmpty();
    }
}
