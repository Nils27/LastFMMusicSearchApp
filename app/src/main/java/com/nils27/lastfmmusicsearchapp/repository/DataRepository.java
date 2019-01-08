package com.nils27.lastfmmusicsearchapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.BuildConfig;
import com.nils27.lastfmmusicsearchapp.model.TopChartArtists;
import com.nils27.lastfmmusicsearchapp.rest.Client;
import com.nils27.lastfmmusicsearchapp.rest.LastFMService;

import java.util.List;

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

    private MutableLiveData<List<TopChartArtists>> mTopChartArtists;

    private DataRepository(Context context) {
    }

    public static DataRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DataRepository(context);
        }
        return instance;
    }

    private void fetchTopChartArtists() {
        client.getTopChartArtistsJson(API_KEY_VALUE).enqueue(new Callback<List<TopChartArtists>>() {
            @Override
            public void onResponse(Call<List<TopChartArtists>> call, Response<List<TopChartArtists>> response) {
                mTopChartArtists.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TopChartArtists>> call, Throwable t) {
                Log.d(TAG, "onFailure: Top Chart Artists Data Fetch has failed");
            }
        });
    }

    public LiveData<List<TopChartArtists>> getTopChartArtistsData() {
        fetchTopChartArtists(); //call refresh of liveData
        return mTopChartArtists;
    }


}
