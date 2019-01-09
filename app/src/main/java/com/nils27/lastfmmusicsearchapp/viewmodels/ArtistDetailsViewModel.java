package com.nils27.lastfmmusicsearchapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.model.artist_details.ArtistDetails;
import com.nils27.lastfmmusicsearchapp.repository.DataRepository;

public class ArtistDetailsViewModel extends ViewModel {
    private static final String TAG = ArtistDetailsViewModel.class.getSimpleName();
//    private LiveData<Integer> entryCount;
    private DataRepository dataRepository;
    private LiveData<ArtistDetails> artistDetails;


    public ArtistDetailsViewModel() {
    }

    public void init(Context context, String artistName) {
        Log.d(TAG, "init: Pre Init");
        dataRepository = DataRepository.getInstance(context);
        Log.d(TAG, "init: Pre getArtist");
        artistDetails = dataRepository.getArtistDetailsData(artistName);
        Log.d(TAG, "init: Post Init");
    }

    public LiveData<ArtistDetails> getArtistDetails() {
        return artistDetails;
    }


}
