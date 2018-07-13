package com.mpod.data.source.local;

import com.mpod.data.Artist;

import java.util.List;

/**
 * Created by xnorcode on 13/07/2018.
 */
public interface DbHelper {

    List<Artist> getArtists();

    Artist getArtistInfo(String mbid);

    void saveArtists(List<Artist> artists);

    void updateArtist(Artist artist);

    void deleteAllArtists();
}
