package com.nils27.lastfmmusicsearchapp.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nils27.lastfmmusicsearchapp.R;
import com.nils27.lastfmmusicsearchapp.databinding.ViewholderArtistsearchBinding;
import com.nils27.lastfmmusicsearchapp.model.Artist;
import com.nils27.lastfmmusicsearchapp.model.Artists;

import java.util.ArrayList;
import java.util.List;

public class AdapterArtistSearch extends RecyclerView.Adapter<AdapterArtistSearch.SearchedArtistsViewHolder> {

    private static final String TAG = AdapterArtistSearch.class.getSimpleName();
    private Artists mArtists;
    private List<Artist> mArtistList = new ArrayList<>();
    private Context mContext;

    final private SearchedArtistOnClickHandler mClickHandler;

    public interface SearchedArtistOnClickHandler {
        void onArtistClick(Artist artist);
    }


    public AdapterArtistSearch(@NonNull Context context, Artists chartArtists, SearchedArtistOnClickHandler clickHandler) {
        mContext = context;
        mArtists = chartArtists;
        mClickHandler = clickHandler;
        setArtistList();
    }


    @Override
    public SearchedArtistsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        ViewholderArtistsearchBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.viewholder_artistsearch, parent, false);
        return new SearchedArtistsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SearchedArtistsViewHolder holder, int position) {
        holder.binding.setArtist(mArtistList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (mArtistList == null) {
            return 0;
        }
        return mArtistList.size();
    }

    private void setArtistList() {
        if (mArtists != null) {
            mArtistList = mArtists.getArtist(); //gets List of Artists
        } else {
            mArtistList = null;
        }
    }

    public void changeArtistList(Artists newArtists) {
        mArtists = newArtists;
        setArtistList();
        notifyDataSetChanged();
    }


    public class SearchedArtistsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ViewholderArtistsearchBinding binding;

        SearchedArtistsViewHolder(ViewholderArtistsearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setListener(this);
        }

        @Override
        public void onClick(View v) {
            Artist artist = mArtistList.get(getAdapterPosition());
            mClickHandler.onArtistClick(artist);
        }

    }
}
