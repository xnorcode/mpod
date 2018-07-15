package com.mpod.data.source.remote;

import com.mpod.data.Artist;
import com.mpod.data.source.LastfmApiKey;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by xnorcode on 16/07/2018.
 * <p>
 * This is a fake remote data source to make testing the screens more convenient
 */
@Singleton
public class FakeArtistRemoteDataSource implements ApiHelper {

    private String apiKey;

    @Inject
    public FakeArtistRemoteDataSource(@LastfmApiKey String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * return fake list of artists
     *
     * @param name The name of the artist to search for
     * @return The list of artists
     */
    @Override
    public List<Artist> searchArtist(String name) {
        List<Artist> artists = null;
        if (name.equals("name")) {
            artists = new ArrayList<>();
            artists.add(new Artist("mbid", "name", "url"));
        }
        return artists;
    }


    /**
     * return fake artist
     *
     * @param mbid The mbid of the artist
     * @return More details of an artist
     */
    @Override
    public Artist getArtistInfo(String mbid) {
        Artist artist = null;
        if (mbid.equals("mbid")) {
            artist = new Artist("mbid", "name", "url");
            artist.setListeners("10");
        }
        return artist;
    }
}
