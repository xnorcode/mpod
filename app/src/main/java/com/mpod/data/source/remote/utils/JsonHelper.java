package com.mpod.data.source.remote.utils;

import com.mpod.data.Artist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class JsonHelper {

    /**
     * Extract search result from JSON response
     *
     * @param data JSON response data from the API
     * @return The list of artist resulted from the search
     * @throws JSONException
     */
    public static List<Artist> extractArtists(String data) throws JSONException {

        List<Artist> output = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(data);

        if (!jsonObject.has("results")) return output;

        jsonObject = jsonObject.getJSONObject("results");

        if (!jsonObject.has("artistmatches")) return output;

        jsonObject = jsonObject.getJSONObject("artistmatches");

        if (!jsonObject.has("artist")) return output;

        JSONArray array = jsonObject.getJSONArray("artist");

        for (int i = 0; i < array.length(); i++) {

            JSONObject obj = array.getJSONObject(i);

            Artist artist = new Artist(obj.getString("mbid"), obj.getString("name"), obj.getString("url"));

            JSONArray imageArray = obj.getJSONArray("image");

            if (imageArray.length() >= 3) {
                // get large sized image
                artist.setImageUrl(imageArray.getJSONObject(2).getString("#text"));
            } else if (imageArray.length() > 0) {
                // get small sized image
                artist.setImageUrl(imageArray.getJSONObject(0).getString("#text"));
            }

            artist.setListeners(obj.getString("listeners"));

            output.add(artist);
        }

        return output;
    }


    /**
     * Extract artist information from the JSON response
     *
     * @param data JSON response data from the API
     * @return The artist data downloaded from the API
     * @throws JSONException
     */
    public static Artist extractArtistInfo(String data) throws JSONException {

        JSONObject obj = new JSONObject(data);

        if (!obj.has("artist")) return null;

        obj = obj.getJSONObject("artist");

        Artist artist = new Artist(obj.getString("mbid"), obj.getString("name"), obj.getString("url"));

        JSONArray imageArray = obj.getJSONArray("image");

        if (imageArray.length() >= 3) {
            // get large sized image
            artist.setImageUrl(imageArray.getJSONObject(2).getString("#text"));
        } else if (imageArray.length() > 0) {
            // get small sized image
            artist.setImageUrl(imageArray.getJSONObject(0).getString("#text"));
        }

        artist.setListeners(obj.getJSONObject("stats").getString("listeners"));

        artist.setPlaycount(obj.getJSONObject("stats").getString("playcount"));

        artist.setBioSummary(obj.getJSONObject("bio").getString("summary"));

        return artist;
    }
}
