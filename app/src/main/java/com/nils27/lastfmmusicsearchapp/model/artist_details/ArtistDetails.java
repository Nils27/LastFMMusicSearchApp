package com.nils27.lastfmmusicsearchapp.model.artist_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistDetails {

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}

