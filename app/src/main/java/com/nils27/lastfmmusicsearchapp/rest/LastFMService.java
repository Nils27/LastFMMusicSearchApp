package com.nils27.lastfmmusicsearchapp.rest;

import com.nils27.lastfmmusicsearchapp.BuildConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class LastFMService {

    private static final String API_KEY_VALUE = BuildConfig.LAST_FM_API_KEY;

    //Get the Top 50 artists
//    @GET("?method=chart.gettopartists&api_key={api_key}&format=json")
//    Call<List<ArtistChart>> getEventsJson(@Path("api_key") API_KEY_VALUE);

}
