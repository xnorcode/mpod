package com.mpod.data.source;

import android.support.annotation.NonNull;

import com.mpod.data.Artist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xnorcode on 13/07/2018.
 */
public interface ArtistDataSource {

    Observable<List<Artist>> searchArtist(String name);

    Observable<Artist> getArtistInfo(@NonNull String mbID);

    void refreshArtists();
}
