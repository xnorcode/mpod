package com.mpod.data.source;

import android.support.annotation.NonNull;

import com.mpod.data.Artist;
import com.mpod.data.source.local.DbHelper;
import com.mpod.data.source.remote.ApiHelper;
import com.mpod.data.source.remote.utils.JsonHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Response;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class ArtistRepository implements ArtistDataSource {

    private DbHelper mArtistLocalDataSource;

    private ApiHelper mArtistRemoteLocalDataSource;

    private Map<String, Artist> mCachedArtists;

    private boolean mCacheInvalid;


    @Inject
    public ArtistRepository(DbHelper artistLocalDataSource, ApiHelper artistRemoteLocalDataSource) {
        this.mArtistLocalDataSource = artistLocalDataSource;
        this.mArtistRemoteLocalDataSource = artistRemoteLocalDataSource;
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
    public Observable<List<Artist>> searchArtist(String name) {

        // get results directly from cache
        if (mCachedArtists != null && !mCacheInvalid) {
            return Observable.just(new ArrayList<Artist>(mCachedArtists.values()));
        }

        if (mCacheInvalid) {
            // cache is invalid download from remote data source
            return downloadArtistFromRemoteDataSource(name);
        } else {
            // check in the local database
            return Observable.just(mArtistLocalDataSource.getArtists())
                    .flatMap(artists -> {
                        // cache
                        if (artists != null && !artists.isEmpty())
                            refreshCache(artists);
                        // if not in local db download from remote
                        if (artists == null || artists.isEmpty())
                            return downloadArtistFromRemoteDataSource(name);
                        return Observable.just(artists);
                    });
        }
    }


    /**
     * Get more information of a specific artist from the local db if not found query the API
     *
     * @param mbID The mbid of the artist
     * @return The queried artist wrapped in an Observable
     */
    @Override
    public Observable<Artist> getArtistInfo(@NonNull String mbID) {

        Artist cachedArtist = getCachedArtist(mbID);
        // check cache
        if (hasInfo(cachedArtist)) return Observable.just(cachedArtist);

        // check in local
        return Observable.just(mArtistLocalDataSource.getArtistInfo(mbID))
                .flatMap(artist -> {
                    // download from remote if does not have info
                    if (!hasInfo(artist)) return downloadArtistInfoFromApi(mbID);
                    return Observable.just(artist);
                });
    }


    /**
     * Notify the data repository that artists in the local cache are out of data and force update
     * by changing the flag to True
     */
    @Override
    public void refreshArtists() {
        mCacheInvalid = true;
    }


    /**
     * Private method for searching and download a specific artist from the API
     *
     * @param name The name of the artist to search for
     * @return The possible list of artists as a result
     */
    private Observable<List<Artist>> downloadArtistFromRemoteDataSource(String name) {
        return Observable.just(mArtistRemoteLocalDataSource.searchArtist(name))
                .map(call -> {
                    // execute network call
                    Response response = call.execute();
                    // get response json data
                    String json = response.body().string();
                    // extract data from JSON and return list of artists
                    return JsonHelper.extractArtists(json);
                })
                .flatMap(artists -> {
                    if (artists != null && !artists.isEmpty()) {
                        // refresh artist in the cache
                        refreshCache(artists);
                        // refresh local data source
                        refreshLocalDataSource(artists);
                    }
                    // return artists
                    return Observable.just(artists);
                });
    }


    /**
     * Private method to for downloading an artists information from the Remote Dta Source
     *
     * @param mbid The mbid of the artist
     * @return The artist info
     */
    private Observable<Artist> downloadArtistInfoFromApi(String mbid) {
        return Observable.just(mArtistRemoteLocalDataSource.getArtistInfo(mbid))
                .map(call -> {
                    // execute network call
                    Response response = call.execute();
                    // get response json data
                    String json = response.body().string();
                    // extract data from JSON and return artist info
                    return JsonHelper.extractArtistInfo(json);
                })
                .flatMap(artist -> {
                    if (artist != null) {
                        // save to cache
                        mCachedArtists.put(artist.getMbID(), artist);
                        // update to local db
                        mArtistLocalDataSource.updateArtist(artist);
                    }
                    // return artist
                    return Observable.just(artist);
                });
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
