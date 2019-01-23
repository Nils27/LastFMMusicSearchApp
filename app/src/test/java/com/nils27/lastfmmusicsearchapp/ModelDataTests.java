package com.nils27.lastfmmusicsearchapp;

import com.nils27.lastfmmusicsearchapp.model.Artist;

import org.junit.Test;
import static org.junit.Assert.*;

public class ModelDataTests {

    //test data
    static final Artist MJ = new Artist("Michael Jackson", "1234567", "12345678", "", "", "0", null);

    @Test
    public void testArtistData_Name() {
        assertEquals("Michael Jackson", MJ.getName());
    }

    @Test
    public void testArtistData_PlayCount() {
        assertEquals("1234567", MJ.getPlaycount());
    }

    @Test
    public void testArtistData_Listeners() {
        assertEquals("12345678", MJ.getListeners());
    }

    @Test
    public void testArtistData_Url_Blank() {
        assertEquals("", MJ.getUrl());
    }

    @Test
    public void testArtistData_Url_Streamable() {
        assertEquals("0", MJ.getStreamable());
    }

    @Test
    public void testArtistData_Url_Images() {
        assertNull(MJ.getImage());
    }


}
