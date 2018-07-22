package me.davidllorca.diworkshop.ui.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.davidllorca.diworkshop.R;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.ui.common.ImageLoader;
import me.davidllorca.diworkshop.ui.common.mvcviews.BaseViewMvc;

public class DetailViewMvcImpl extends BaseViewMvc<DetailViewMvc.Listener> implements DetailViewMvc {

    private final ImageLoader mImageLoader;
    private ImageView mImageIv;
    private TextView mNameTv;
    private TextView mStatusTv;
    private TextView mSpeciesTv;
    private TextView mTypeTv;
    private TextView mGenderTv;

    public DetailViewMvcImpl(LayoutInflater inflater, ViewGroup container, ImageLoader imageLoader) {
        mImageLoader = imageLoader;
        setRootView(inflater.inflate(R.layout.activity_detail, container, false));

        mImageIv = findViewById(R.id.iv_image_item);
        mNameTv = findViewById(R.id.tv_name_item);
        mStatusTv = findViewById(R.id.tv_status_item);
        mSpeciesTv = findViewById(R.id.tv_species_item);
        mTypeTv = findViewById(R.id.tv_type_item);
        mGenderTv = findViewById(R.id.tv_gender_item);
    }


    @Override
    public void bindCharacter(Character character) {
        mImageLoader.loadImage(character.getImage(), mImageIv);
        mNameTv.setText(character.getName());
        mStatusTv.setText(character.getStatus());
        mSpeciesTv.setText(character.getSpecies());
        mTypeTv.setText(character.getType());
        mGenderTv.setText(character.getGender());
    }
}
