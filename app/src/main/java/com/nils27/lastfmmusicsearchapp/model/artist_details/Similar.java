package com.nils27.lastfmmusicsearchapp.model.artist_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Similar {
    @SerializedName("artist")
    @Expose
    private List<SimilarArtist> artist = null;

    public List<SimilarArtist> getArtist() {
        return artist;
    }

    public void setArtist(List<SimilarArtist> artist) {
        this.artist = artist;
    }

}