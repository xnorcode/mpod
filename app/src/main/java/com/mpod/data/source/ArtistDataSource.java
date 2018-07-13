package com.mpod.data.source;

import android.support.annotation.NonNull;

import com.mpod.data.Artist;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by xnorcode on 13/07/2018.
 */
public interface ArtistDataSource {

    Flowable<List<Artist>> searchArtist(String name);

    Flowable<Artist> getArtistInfo(@NonNull String mbID);

    void refreshArtists();
}
