package com.nils27.lastfmmusicsearchapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.nils27.lastfmmusicsearchapp.R;

import com.nils27.lastfmmusicsearchapp.adapters.AdapterArtistSearch;
import com.nils27.lastfmmusicsearchapp.databinding.ActivityMainBinding;
import com.nils27.lastfmmusicsearchapp.model.Artist;
import com.nils27.lastfmmusicsearchapp.model.Artists;
import com.nils27.lastfmmusicsearchapp.viewmodels.ArtistSearchViewModel;



public class MainActivity extends AppCompatActivity implements AdapterArtistSearch.SearchedArtistOnClickHandler {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private AdapterArtistSearch adapterArtistSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //view model
        ArtistSearchViewModel artistSearchViewModel = ViewModelProviders.of(this).get(ArtistSearchViewModel.class);
        artistSearchViewModel.init(this);


        //AdapterArtistSearch
        adapterArtistSearch = new AdapterArtistSearch(this, null, this);


        //get data
        artistSearchViewModel.getChartArtists().observe(this, new Observer<Artists>() {
            @Override
            public void onChanged(@Nullable Artists chartArtists) {
                if (chartArtists == null) {
                    Log.d(TAG, "onChanged: char artists is null");
                }
                Log.d(TAG, "onChanged: artists data changed");
                adapterArtistSearch.changeArtistList(chartArtists);
                if (chartArtists.getArtist() == null) {
                    Log.d(TAG, "onChanged: null list");
                }
            }
        });

        binding.rvListArtists.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListArtists.setAdapter(adapterArtistSearch);

    }


    @Override
    public void onArtistClick(Artist artist) {
        Log.d(TAG, "onArtistClick: artist clicked - " + artist.getName());
    }
}



 /*
  *
  * TODO - Unit Tests
  *         - Pojos for 3 results that will come back (top 50, search results, details of Artist)
  *         - Search toolbar values: word(s) are valid (are numbers allowed, are special chars allowed, do they affect the search results - "+" or "*", etc)
  *         - Repository Exceptions that may come about if user searches using "word" which has no result, special chars ("±", etc), null/epmty should return top 50, etc
  *         - Does the phone have a network connection
  *
  * Done - Add API_Key
  * Done - Sort out folder structure of app
  *             - rest
  *             - model
  *             - repository
  *             - viewmodels
  *             - ui
  *             - adapters
  *             - utils - needed?
  *
  * Done - add all dependencies (Retrofit, Glide, etc)
  *
  * TODO - Rest - get data
  * TODO - Repository - get data via rest
  * TODO - POJOs - 3x POJO: Top 50, search results, artist details
  * TODO - ViewModel(s) to provide data from Repo to UI
  * Done - Add RecyclerView, Adapter, DataBinding to show results
  * TODO - Use Glide for Images and BindingAdapter
  * TODO - Detail Screen to show detail view
  *
  *
  *
  *
  *
  *
  * */