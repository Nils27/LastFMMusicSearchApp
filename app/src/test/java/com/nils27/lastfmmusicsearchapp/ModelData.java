package com.nils27.lastfmmusicsearchapp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ModelData {

    //test data
    static final Artist MJ = new Artist(1, "Michael Jackson", 1234567, 12345678, "", 0, null);

    @Test
    public void testArtistData_Name() {
        assertEquals("Britney Spears", MJ.getName());
    }

    @Test
    public void testArtistData_PlayCount() {
        assertEquals(1234567, MJ.getPlayCount());
    }

    @Test
    public void testArtistData_Listeners() {
        assertEquals(12345678, MJ.getListeners());
    }

    @Test
    public void testArtistData_Url_Blank() {
        assertEquals("", MJ.getUrl());
    }

    @Test
    public void testArtistData_Url_Streamable() {
        assertEquals(0, MJ.getStreamable());
    }

    @Test
    public void testArtistData_Url_Images() {
        assertArrayEquals(null, MJ.getImages());
    }


}
