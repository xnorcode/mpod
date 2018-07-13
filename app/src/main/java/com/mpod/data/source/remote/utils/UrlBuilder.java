package com.mpod.data.source.remote.utils;

import java.net.URL;

import okhttp3.HttpUrl;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class UrlBuilder {

    class Parameters {
        static final String PROTOCOL = "http";
        static final String HOST_URL = "ws.audioscrobbler.com";
        static final String API_VERSION_PATH = "2.0";
        static final String METHOD = "method";
        static final String QUERY_SEARCH_ARTIST = "artist.search";
        static final String QUERY_ARTIST_INFO = "artist.getinfo";
        static final String ARTIST_NAME = "artist";
        static final String ARTIST_MBID = "mbid";
        static final String KEY = "api_key";
        static final String FORMAT = "format";
        static final String FORMAT_TYPE = "json";
    }


    /**
     * Build URL to search for a given artist
     *
     * @param artist the name of the artist to search for
     * @param apiKey last.fm api key
     * @return The url to connect to
     */
    public static URL searchArtistsUrl(String artist, String apiKey) {
        return new HttpUrl.Builder()
                .scheme(Parameters.PROTOCOL)
                .host(Parameters.HOST_URL)
                .addPathSegment(Parameters.API_VERSION_PATH)
                .addQueryParameter(Parameters.METHOD, Parameters.QUERY_SEARCH_ARTIST)
                .addQueryParameter(Parameters.ARTIST_NAME, artist)
                .addQueryParameter(Parameters.KEY, apiKey)
                .addQueryParameter(Parameters.FORMAT, Parameters.FORMAT_TYPE)
                .build()
                .url();
    }


    /**
     * Build URL to get the info of an artist
     *
     * @param mbID   mbID of an artist
     * @param apiKey last.fm api key
     * @return The url to connect to
     */
    public static URL artistInfo(String mbID, String apiKey) {
        return new HttpUrl.Builder()
                .scheme(Parameters.PROTOCOL)
                .host(Parameters.HOST_URL)
                .addPathSegment(Parameters.API_VERSION_PATH)
                .addQueryParameter(Parameters.METHOD, Parameters.QUERY_ARTIST_INFO)
                .addQueryParameter(Parameters.ARTIST_MBID, mbID)
                .addQueryParameter(Parameters.KEY, apiKey)
                .addQueryParameter(Parameters.FORMAT, Parameters.FORMAT_TYPE)
                .build()
                .url();
    }
}
