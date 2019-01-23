package com.nils27.lastfmmusicsearchapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class ArtistDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application mApplication;
    private String mArtistName;


    public ArtistDetailsViewModelFactory(Application application, String artistName) {
        mApplication = application;
        mArtistName = artistName;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ArtistDetailsViewModel(mApplication, mArtistName);
    }

}
