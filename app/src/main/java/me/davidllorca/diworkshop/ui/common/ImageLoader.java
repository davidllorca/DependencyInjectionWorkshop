package me.davidllorca.diworkshop.ui.common;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;

public class ImageLoader {

    private final BaseActivity mActivity;
    private final RequestOptions mDefaultRequestOptions;

    public ImageLoader(BaseActivity activity){
        this.mActivity = activity;
        mDefaultRequestOptions = new RequestOptions()
                .centerCrop();
    }

    public void loadImage(String uri, ImageView target) {
        Glide
                .with(mActivity)
                .load(uri)
                .apply(mDefaultRequestOptions)
                .into(target);
    }

}
