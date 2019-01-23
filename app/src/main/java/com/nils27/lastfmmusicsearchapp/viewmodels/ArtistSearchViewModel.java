package com.nils27.lastfmmusicsearchapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.model.Artists;
import com.nils27.lastfmmusicsearchapp.model.artist_search.ArtistResult;
import com.nils27.lastfmmusicsearchapp.repository.DataRepository;

import java.util.List;

public class ArtistSearchViewModel extends AndroidViewModel {
    private static final String TAG = ArtistSearchViewModel.class.getSimpleName();
//    private LiveData<Integer> entryCount;
    private DataRepository dataRepository;
    private LiveData<Artists> chartArtists;
    private LiveData<ArtistResult> searchedArtists;
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();


    public ArtistSearchViewModel(Application application) {
        super(application);
        Log.d(TAG, "init: Pre Init");
        dataRepository = DataRepository.getInstance(application.getApplicationContext());
        chartArtists = dataRepository.getArtistsData();

        searchedArtists = Transformations.switchMap(searchQuery, input -> dataRepository.getSearchedArtistData(input));

        Log.d(TAG, "init: Post Init");
    }

//    public void init(Context context) {
//    }

    public LiveData<Artists> getChartArtists() {
        return chartArtists;
    }

    public LiveData<ArtistResult> getSearchedArtists() {
        return searchedArtists;
    }

    public void setSearchQuery(String query) {
        searchQuery.setValue(query);
    }


}
