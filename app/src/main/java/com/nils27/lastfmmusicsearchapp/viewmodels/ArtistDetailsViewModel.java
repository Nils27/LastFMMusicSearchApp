package com.nils27.lastfmmusicsearchapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.model.artist_details.ArtistDetails;
import com.nils27.lastfmmusicsearchapp.repository.DataRepository;

public class ArtistDetailsViewModel extends AndroidViewModel {
    private static final String TAG = ArtistDetailsViewModel.class.getSimpleName();
//    private LiveData<Integer> entryCount;
    private DataRepository dataRepository;
    private LiveData<ArtistDetails> artistDetails;


    public ArtistDetailsViewModel(Application application, String artistName) {
        super(application);
        Log.d(TAG, "ArtistDetailsViewModel: Pre Init");
        dataRepository = DataRepository.getInstance(application);
        Log.d(TAG, "ArtistDetailsViewModel: Pre getArtist");
        artistDetails = dataRepository.getArtistDetailsData(artistName);
        Log.d(TAG, "ArtistDetailsViewModel: Post Init");

    }

    public LiveData<ArtistDetails> getArtistDetails() {
        return artistDetails;
    }


}
