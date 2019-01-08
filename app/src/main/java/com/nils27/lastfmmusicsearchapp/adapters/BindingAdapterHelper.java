package com.nils27.lastfmmusicsearchapp.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nils27.lastfmmusicsearchapp.model.Image;

import java.util.List;

public class BindingAdapterHelper {
    public BindingAdapterHelper() {}

    @BindingAdapter({"bind:setImage"})
    public static void setImage(ImageView view, List<Image> imagesList) {
        String mediumImageString = "";
        for (Image i : imagesList) {
            if (i.getSize().equals("medium")) {
                mediumImageString = i.getText();
            }
        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(android.R.drawable.ic_menu_gallery);
        requestOptions.error(android.R.drawable.ic_menu_gallery);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(mediumImageString)
                .into(view);
    }
}
