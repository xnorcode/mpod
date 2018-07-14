package com.mpod.data.source.local;

import android.support.annotation.NonNull;

import com.mpod.data.Artist;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by xnorcode on 13/07/2018.
 */
@Singleton
public class ArtistLocalDataSource implements DbHelper {


    private ArtistDao mArtistDao;


    @Inject
    public ArtistLocalDataSource(@NonNull ArtistDao mArtistDao) {
        this.mArtistDao = mArtistDao;
    }


    /**
     * Get all artist stored in the db
     *
     * @return The list of artist
     */
    @Override
    public List<Artist> getArtists() {
        return mArtistDao.getArtists();
    }


    /**
     * Get an artist from the db based on its mbid
     *
     * @param mbID The artist's mbid
     * @return Return the artist
     */
    @Override
    public Artist getArtistInfo(@NonNull String mbID) {
        return mArtistDao.getArtistById(mbID);
    }


    /**
     * Save a list of artist in the db
     *
     * @param artists The list of artists to save
     */
    @Override
    public void saveArtists(@NonNull List<Artist> artists) {
        mArtistDao.insertArtists(artists);
    }


    /**
     * Update an artist in the db
     *
     * @param artist The artist to be updated
     */
    @Override
    public void updateArtist(@NonNull Artist artist) {
        mArtistDao.updateArtist(artist);
    }


    /**
     * Delete all artists in the db
     */
    @Override
    public void deleteAllArtists() {
        mArtistDao.deleteArtists();
    }
}
