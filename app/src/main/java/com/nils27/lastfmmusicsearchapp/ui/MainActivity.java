package com.nils27.lastfmmusicsearchapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nils27.lastfmmusicsearchapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
  * TODO - Add API_Key
  * TODO - Sort out folder structure of app
  *             - rest
  *             - model
  *             - repository
  *             - viewmodels
  *             - ui
  *             - adapters
  *             - utils
  *
  * TODO - add all dependencies (Retrofit, Glide, etc)
  *
  * TODO - Rest - get data
  * TODO - Repository - get data via rest
  * TODO - POJOs - 3x POJO: Top 50, search results, artist details
  * TODO - ViewModel(s) to provide data from Repo to UI
  * TODO - Add RecyclerView, Adapter, DataBinding to show results
  * TODO - Use Glide for Images and BindingAdapter
  * TODO - Detail Screen to show detail view
  *
  *
  *
  *
  *
  *
  * */