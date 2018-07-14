package com.mpod.data.source.remote;

import com.mpod.data.Artist;
import com.mpod.data.source.LastfmApiKey;
import com.mpod.data.source.remote.utils.JsonHelper;
import com.mpod.data.source.remote.utils.UrlBuilder;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xnorcode on 13/07/2018.
 */
@Singleton
public class ArtistRemoteDataSource implements ApiHelper {

    private String apiKey;


    @Inject
    public ArtistRemoteDataSource(@LastfmApiKey String apiKey) {
        this.apiKey = apiKey;
    }


    /**
     * Perform a call to the last.fm API with the artist.search query
     *
     * @param name The name of the artist to search for
     * @return The list of artists
     */
    @Override
    public List<Artist> searchArtist(String name) {
        try {
            // get url
            String url = UrlBuilder.searchArtistsUrl(name, apiKey).toString();
            // init okhttp client
            OkHttpClient client = new OkHttpClient();
            // create request
            Request request = new Request.Builder().url(url).build();
            // execute network call
            Response response = client.newCall(request).execute();
            // status check
            if (response.code() != 200) return null;
            // get response json data
            String json = response.body().string();
            // extract data from JSON and return list of artists
            return JsonHelper.extractArtists(json);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Perform a call to the last.fm API with the artist.getinfo query
     *
     * @param mbid The mbid of the artist
     * @return More details of an artist
     */
    @Override
    public Artist getArtistInfo(String mbid) {
        try {
            // get url
            String url = UrlBuilder.artistInfo(mbid, apiKey).toString();
            // init okhttp client
            OkHttpClient client = new OkHttpClient();
            // create request
            Request request = new Request.Builder().url(url).build();
            // execute network call
            Response response = client.newCall(request).execute();
            // status check
            if (response.code() != 200) return null;
            // get response json data
            String json = response.body().string();
            // extract data from JSON and return artist info
            return JsonHelper.extractArtistInfo(json);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
