package com.nils27.lastfmmusicsearchapp;

import com.nils27.lastfmmusicsearchapp.viewmodels.ArtistDetailsViewModel;
import com.nils27.lastfmmusicsearchapp.viewmodels.ArtistSearchViewModel;

import org.junit.Assert;
import org.junit.Test;

public class ViewModelTests {
    ArtistSearchViewModel viewModel;

    @Test
    public void updateUI(){
        // Trigger
        viewModel.setSearchQuery("dummy text");
        // Validation

//        Mockito.verify(viewModel.variable1).set("dummy text");
    }

}
