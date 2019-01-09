package com.nils27.lastfmmusicsearchapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.R;
import com.nils27.lastfmmusicsearchapp.databinding.ActivityArtistDetailsBinding;
import com.nils27.lastfmmusicsearchapp.viewmodels.ArtistDetailsViewModel;

public class ArtistDetails extends AppCompatActivity {

    public static final String TAG = ArtistDetails.class.getSimpleName();
    private ActivityArtistDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist_details);

        String artistName = getIntent().getStringExtra(MainActivity.KEY_ARTIST_NAME);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(artistName);
        }

        //view model
        ArtistDetailsViewModel artistDetailsViewModel = ViewModelProviders.of(this).get(ArtistDetailsViewModel.class);
        artistDetailsViewModel.init(this, artistName);


        artistDetailsViewModel.getArtistDetails().observe(this, new Observer<com.nils27.lastfmmusicsearchapp.model.artist_details.ArtistDetails>() {
            @Override
            public void onChanged(@Nullable com.nils27.lastfmmusicsearchapp.model.artist_details.ArtistDetails artistDetails) {
                Log.d(TAG, "onChanged: response - " + artistDetails.getArtist().getBio().getSummary());
                //TODO - only showing bio for now as app is not being judged on ui and data it shows - for future I would include all other meta data for the users
                binding.tvBio.setText(artistDetails.getArtist().getBio().getSummary());
            }
        });

    }


}
