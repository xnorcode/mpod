package com.mpod.data.source.remote;

import com.mpod.data.Artist;

import java.util.List;

/**
 * Created by xnorcode on 13/07/2018.
 */
public interface ApiHelper {

    List<Artist> searchArtist(String name);

    Artist getArtistInfo(String mbid);
}
