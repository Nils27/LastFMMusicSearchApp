package com.nils27.lastfmmusicsearchapp.rest;

import com.nils27.lastfmmusicsearchapp.model.TopChartArtists;
import com.nils27.lastfmmusicsearchapp.model.artist_details.ArtistDetails;
import com.nils27.lastfmmusicsearchapp.model.artist_search.ArtistResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMService {

    //Get the Top 50 artists
    @GET("2.0/?method=chart.gettopartists")
    Call<TopChartArtists> getTopChartArtistsJson(@Query("api_key") String API_KEY_VALUE, @Query("format") String json);

    //Get Artist Details
    @GET("2.0/?method=artist.getinfo")
    Call<ArtistDetails> getArtistDetailsJson(@Query("artist") String artistName, @Query("api_key") String API_KEY_VALUE, @Query("format") String json);

    //Get the Top 50 artists
    @GET("2.0/?method=artist.search")
    Call<ArtistResult> getSearchedArtistJson(@Query("artist") String artistName, @Query("api_key") String API_KEY_VALUE, @Query("format") String json);

}
