package com.mpod.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by xnorcode on 13/07/2018.
 * <p>
 * Artist model class
 */
@Entity(tableName = "artists")
public class Artist {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "mbID")
    private String mbID;

    @Nullable
    @ColumnInfo(name = "name")
    private String name;

    @Nullable
    @ColumnInfo(name = "lastfmProfileUrl")
    private String lastfmProfileUrl;

    @Nullable
    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @Nullable
    @ColumnInfo(name = "listeners")
    private String listeners;

    @Nullable
    @ColumnInfo(name = "playcount")
    private String playcount;

    @Nullable
    @ColumnInfo(name = "bioSummary")
    private String bioSummary;


    /**
     * Constructor to create an artist based on:
     *
     * @param mbID             The musicbrainz id for the artist
     * @param name             The artist name
     * @param lastfmProfileUrl The last.fm profile URL for the artist
     */
    public Artist(String mbID, String name, String lastfmProfileUrl) {
        this.mbID = mbID;
        this.name = name;
        this.lastfmProfileUrl = lastfmProfileUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public void setBioSummary(String bioSummary) {
        this.bioSummary = bioSummary;
    }


    @NonNull
    public String getMbID() {
        return mbID;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getLastfmProfileUrl() {
        return lastfmProfileUrl;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @Nullable
    public String getListeners() {
        return listeners;
    }

    @Nullable
    public String getPlaycount() {
        return playcount;
    }

    @Nullable
    public String getBioSummary() {
        return bioSummary;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return mbID.equals(artist.getMbID()) &&
                name.equals(artist.getName()) &&
                lastfmProfileUrl.equals(artist.getLastfmProfileUrl());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mbID.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastfmProfileUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Artist with name: " + name;
    }
}
