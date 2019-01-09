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
    private List<Artist> mArtistList = new ArrayList<>();
    private Context mContext;

    final private SearchedArtistOnClickHandler mClickHandler;

    public interface SearchedArtistOnClickHandler {
        void onArtistClick(Artist artist);
    }


    public AdapterArtistSearch(@NonNull Context context, List<Artist> chartArtists, SearchedArtistOnClickHandler clickHandler) {
        mContext = context;
        mArtistList = chartArtists;
        mClickHandler = clickHandler;
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

    public void changeArtistList(List<Artist> newArtistList) {
        mArtistList = newArtistList;
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
