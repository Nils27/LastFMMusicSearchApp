package com.nils27.lastfmmusicsearchapp.rest;

import com.nils27.lastfmmusicsearchapp.model.Artists;
import com.nils27.lastfmmusicsearchapp.model.TopChartArtists;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LastFMService {

    //Get the Top 50 artists
    @GET("?method=chart.gettopartists&api_key={api_key}&format=json")
    Call<Artists> getTopChartArtistsJson2(@Path("api_key") String API_KEY_VALUE);

    @GET("2.0/?method=chart.gettopartists&api_key=6c5b54cf5ef1d377a3bdf2aeb5086c3d&format=json")
    Call<TopChartArtists> getTopChartArtistsJson();
}
