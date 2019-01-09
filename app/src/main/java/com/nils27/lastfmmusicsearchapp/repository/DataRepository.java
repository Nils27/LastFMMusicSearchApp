package com.nils27.lastfmmusicsearchapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.BuildConfig;
import com.nils27.lastfmmusicsearchapp.model.Artists;
import com.nils27.lastfmmusicsearchapp.model.TopChartArtists;
import com.nils27.lastfmmusicsearchapp.model.artist_details.ArtistDetails;
import com.nils27.lastfmmusicsearchapp.model.artist_search.ArtistResult;
import com.nils27.lastfmmusicsearchapp.rest.Client;
import com.nils27.lastfmmusicsearchapp.rest.LastFMService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataRepository {
    private static final String TAG = DataRepository.class.getSimpleName();
    private static final String API_KEY_VALUE = BuildConfig.LAST_FM_API_KEY;
    private static DataRepository instance;

    private Retrofit restClient = Client.getClient();
    private LastFMService client = restClient.create(LastFMService.class);

    private MutableLiveData<Artists> mArtists;
    private MutableLiveData<ArtistDetails> mArtistDetails;
    private MutableLiveData<ArtistResult> mArtistSearched;

    private DataRepository(Context context) {
        mArtists = new MutableLiveData<>();
        mArtistDetails = new MutableLiveData<>();
        mArtistSearched = new MutableLiveData<>();
    }

    public static DataRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DataRepository(context);
        }
        return instance;
    }

    private void fetchChartArtists() {
        client.getTopChartArtistsJson(API_KEY_VALUE, "json").enqueue(new Callback<TopChartArtists>() {
            @Override
            public void onResponse(Call<TopChartArtists> call, Response<TopChartArtists> response) {
                mArtists.postValue(response.body().getArtists());
            }

            @Override
            public void onFailure(Call<TopChartArtists> call, Throwable t) {
                Log.d(TAG, "onFailure: Top Chart Artists Data Fetch has failed");
                Log.d(TAG, "onFailure: call request - " + call.request().toString());
                Log.d(TAG, "onFailure: t - " + t.getMessage());
                Log.d(TAG, "onFailure: t - " + t.toString());
            }
        });
    }

    public LiveData<Artists> getArtistsData() {
        fetchChartArtists(); //call refresh of liveData
        return mArtists;
    }


    private void fetchArtistDetails(String artistName) {
    client.getArtistDetailsJson(artistName, API_KEY_VALUE, "json").enqueue(new Callback<ArtistDetails>() {
            @Override
            public void onResponse(Call<ArtistDetails> call, Response<ArtistDetails> response) {
                mArtistDetails.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArtistDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: Top Chart Artists Data Fetch has failed");
                Log.d(TAG, "onFailure: call request - " + call.request().toString());
                Log.d(TAG, "onFailure: t - " + t.getMessage());
                Log.d(TAG, "onFailure: t - " + t.toString());
            }
        });
    }

    public LiveData<ArtistDetails> getArtistDetailsData(String artistName) {
        fetchArtistDetails(artistName); //call refresh of liveData
        return mArtistDetails;
    }


    public LiveData<ArtistResult> getSearchedArtistData(String query) {
        fetchSearchedArtist(query); //call refresh of liveData
        return mArtistSearched;
    }

    private void fetchSearchedArtist(String artistName) {
        client.getSearchedArtistJson(artistName, API_KEY_VALUE, "json").enqueue(new Callback<ArtistResult>() {
            @Override
            public void onResponse(Call<ArtistResult> call, Response<ArtistResult> response) {
                mArtistSearched.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArtistResult> call, Throwable t) {
                Log.d(TAG, "onFailure: Searched Artist Data Fetch has failed");
                Log.d(TAG, "onFailure: call request - " + call.request().toString());
                Log.d(TAG, "onFailure: t - " + t.getMessage());
                Log.d(TAG, "onFailure: t - " + t.toString());
            }
        });

    }



    }
