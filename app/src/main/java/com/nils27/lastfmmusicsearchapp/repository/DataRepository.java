package com.nils27.lastfmmusicsearchapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.BuildConfig;
import com.nils27.lastfmmusicsearchapp.model.Artist;
import com.nils27.lastfmmusicsearchapp.model.Artists;
import com.nils27.lastfmmusicsearchapp.model.TopChartArtists;
import com.nils27.lastfmmusicsearchapp.rest.Client;
import com.nils27.lastfmmusicsearchapp.rest.LastFMService;

import java.io.IOException;

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

    private DataRepository(Context context) {
        mArtists = new MutableLiveData<>();
    }

    public static DataRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DataRepository(context);
        }
        return instance;
    }

    private void fetchChartArtists() {
//        client.getTopChartArtistsJson(API_KEY_VALUE).enqueue(new Callback<Artists>() {
//        try {
//            Artists artists = client.getTopChartArtistsJson().execute().body();
//            Log.d(TAG, "onResponse: response recieved");
//            Log.d(TAG, "onResponse: response - " + artists.toString());
//            mArtists.postValue(artists);
//        } catch (IOException e) {
//            Log.d(TAG, "fetchElements: error - " + e.getMessage());
//        }



        client.getTopChartArtistsJson().enqueue(new Callback<TopChartArtists>() {
            @Override
            public void onResponse(Call<TopChartArtists> call, Response<TopChartArtists> response) {
                Log.d(TAG, "onResponse: response recieved");
                Log.d(TAG, "onResponse: response - " + response.body().toString());
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
        Log.d(TAG, "getArtistsData: pre fetch artist");
        fetchChartArtists(); //call refresh of liveData
        Log.d(TAG, "getArtistsData: after fetch called");
        return mArtists;
    }


}
