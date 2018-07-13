package com.mpod.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mpod.data.Artist;

import java.util.List;

/**
 * Created by xnorcode on 13/07/2018.
 * <p>
 * Data Access Object for the artist table
 */
@Dao
public interface ArtistDao {


    /**
     * Select all the artists from the db
     *
     * @return all artists
     */
    @Query("SELECT * FROM Artists")
    List<Artist> getArtists();


    /**
     * Select artist by its mbid
     *
     * @param mbID The mbid of the artist
     * @return The artist with the given mbid
     */
    @Query("SELECT * FROM Artists WHERE mbID = :mbID")
    Artist getArtistById(String mbID);


    /**
     * Save all artists found in the db. If any artist already exist then replace it.
     *
     * @param artist the artist to be saved
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArtists(List<Artist> artist);


    /**
     * Update an artist in the db
     *
     * @param artist the artist to be updated
     * @return the number of artists updated
     */
    @Update
    int updateArtist(Artist artist);


    /**
     * Delete all artists from db
     */
    @Query("DELETE FROM Artists")
    void deleteArtists();
}
