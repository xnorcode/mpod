package com.mpod.data.source.remote;

import okhttp3.Call;

/**
 * Created by xnorcode on 13/07/2018.
 */
public interface ApiHelper {

    Call searchArtist(String name);

    Call getArtistInfo(String mbid);
}
