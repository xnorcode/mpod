package com.mpod.data.source.remote;

import com.mpod.data.source.remote.utils.UrlBuilder;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class ArtistRemoteDataSource implements ApiHelper {

    private String apiKey;


    public ArtistRemoteDataSource(String apiKey) {
        this.apiKey = apiKey;
    }


    /**
     * Create a call to the last.fm API with the artist.search query
     *
     * @param name The name of the artist to search for
     * @return Network call to API
     */
    @Override
    public Call searchArtist(String name) {
        // get url
        String url = UrlBuilder.searchArtistsUrl(name, apiKey).toString();
        // init okhttp client
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }


    /**
     * Create a call to the last.fm API with the artist.getinfo query
     *
     * @param mbid The mbid of the artist
     * @return Network call to API
     */
    @Override
    public Call getArtistInfo(String mbid) {
        // get url
        String url = UrlBuilder.artistInfo(mbid, apiKey).toString();
        // init okhttp client
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }
}
