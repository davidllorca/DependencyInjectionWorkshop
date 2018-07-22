package me.davidllorca.diworkshop.ui.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import me.davidllorca.diworkshop.R;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.ui.common.mvcviews.BaseViewMvc;

class DetailViewMvcImpl extends BaseViewMvc<DetailViewMvc.Listener> implements DetailViewMvc {

    private TextView mNameTv;
    private TextView mStatusTv;
    private TextView mSpeciesTv;
    private TextView mTypeTv;
    private TextView mGenderTv;

    public DetailViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.activity_detail, container, false));

        mNameTv = findViewById(R.id.tv_name_item);
        mStatusTv = findViewById(R.id.tv_status_item);
        mSpeciesTv = findViewById(R.id.tv_species_item);
        mTypeTv = findViewById(R.id.tv_type_item);
        mGenderTv = findViewById(R.id.tv_gender_item);
    }


    @Override
    public void bindCharacter(Character character) {
        mNameTv.setText(character.getName());
        mStatusTv.setText(character.getStatus());
        mSpeciesTv.setText(character.getSpecies());
        mTypeTv.setText(character.getType());
        mGenderTv.setText(character.getGender());
    }
}