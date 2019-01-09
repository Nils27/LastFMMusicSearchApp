package com.nils27.lastfmmusicsearchapp.ui;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nils27.lastfmmusicsearchapp.R;

import com.nils27.lastfmmusicsearchapp.adapters.AdapterArtistSearch;
import com.nils27.lastfmmusicsearchapp.databinding.ActivityMainBinding;
import com.nils27.lastfmmusicsearchapp.model.Artist;
import com.nils27.lastfmmusicsearchapp.model.Artists;
import com.nils27.lastfmmusicsearchapp.model.artist_search.ArtistResult;
import com.nils27.lastfmmusicsearchapp.viewmodels.ArtistSearchViewModel;



public class MainActivity extends AppCompatActivity implements AdapterArtistSearch.SearchedArtistOnClickHandler {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_ARTIST_NAME = "ArtistName";

    private ActivityMainBinding binding;
    private ArtistSearchViewModel artistSearchViewModel;
    private AdapterArtistSearch adapterArtistSearch;

    private Artists mChartArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //view model
        artistSearchViewModel = ViewModelProviders.of(this).get(ArtistSearchViewModel.class);
        artistSearchViewModel.init(this);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Top Artists");
        }


        //AdapterArtistSearch
        adapterArtistSearch = new AdapterArtistSearch(this, null, this);


        //get Chart Top Artists data
        artistSearchViewModel.getChartArtists().observe(this, new Observer<Artists>() {
            @Override
            public void onChanged(@Nullable Artists chartArtists) {
                if (chartArtists == null) {
                    Log.d(TAG, "onChanged: char artists is null");
                }
                Log.d(TAG, "onChanged: artists data changed");
                adapterArtistSearch.changeArtistList(chartArtists.getArtist());
                mChartArtists = chartArtists;
            }
        });

        //get Searched Artist data
        artistSearchViewModel.getSearchedArtists().observe(this, new Observer<ArtistResult>() {
            @Override
            public void onChanged(@Nullable ArtistResult artistResult) {
                adapterArtistSearch.changeArtistList(artistResult.getResults().getArtistmatches().getArtist());
            }
        });


        binding.rvListArtists.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListArtists.setAdapter(adapterArtistSearch);

    }


    @Override
    public void onArtistClick(Artist artist) {
        Log.d(TAG, "onArtistClick: artist clicked - " + artist.getName());
        Intent goToArtistDetails = new Intent(this, ArtistDetails.class);
        goToArtistDetails.putExtra(KEY_ARTIST_NAME, artist.getName());
        startActivity(goToArtistDetails);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        final SearchView searchView = (SearchView) searchItem.getActionView();

        //hint
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);

        //get the search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: query - " + query);
                //do the search here
                artistSearchViewModel.setSearchQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                getSupportActionBar().setDisplayShowTitleEnabled(false);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        });

        //listener to show no search - default to Top Artist
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                adapterArtistSearch.changeArtistList(mChartArtists.getArtist());
                Log.d(TAG, "onClose: Search Cancelled");
                return false;
            }
        });

        return true;

    }


}



 /*
  *
  * TODO - Unit Tests
  *         - Pojos for 3 results that will come back (top 50, search results, details of Artist)
  *         - Search toolbar values: word(s) are valid (are numbers allowed, are special chars allowed, do they affect the search results - "+" or "*", etc)
  * TODO    - Repository Exceptions that may come about if user searches using "word" which has no result, special chars ("±", etc), null/epmty should return top 50, etc
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
  * Done - Rest - get data
  * Done - Repository - get data via rest
  * Done - POJOs - 3x POJO: Top 50, search results, artist details
  * Done - ViewModel(s) to provide data from Repo to UI
  * Done - Add RecyclerView, Adapter, DataBinding to show results
  * Done - Use Glide for Images and BindingAdapter
  * Done - Detail Screen to show detail view
  *
  * Done - above for searchedArtist
  * Done - replace the data in the adapter with searchedArtist list of Artists
  *
  * */