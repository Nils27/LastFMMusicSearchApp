package com.nils27.lastfmmusicsearchapp.rest;

import com.nils27.lastfmmusicsearchapp.BuildConfig;
import com.nils27.lastfmmusicsearchapp.model.TopChartArtists;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LastFMService {

    //Get the Top 50 artists
    @GET("?method=chart.gettopartists&api_key={api_key}&format=json")
    Call<List<TopChartArtists>> getTopChartArtistsJson(@Path("api_key") String API_KEY);

}
