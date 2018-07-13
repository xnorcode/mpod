package com.mpod.data.source.remote.utils;

import junit.framework.Assert;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class UrlBuilderTest {

    @Test
    public void searchArtistsUrl() throws MalformedURLException {
        URL url = new URL("http://ws.audioscrobbler.com/2.0?method=artist.search&artist=Cher&api_key=1234&format=json");
        Assert.assertEquals(url, UrlBuilder.searchArtistsUrl("Cher", "1234"));
    }

    @Test
    public void artistInfo() throws MalformedURLException {
        URL url = new URL("http://ws.audioscrobbler.com/2.0?method=artist.getinfo&mbid=bfcc6d75-a6a5-4bc6-8282-47aec8531818&api_key=1234&format=json");
        Assert.assertEquals(url, UrlBuilder.artistInfo("bfcc6d75-a6a5-4bc6-8282-47aec8531818", "1234"));
    }
}
