package me.davidllorca.diworkshop.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import me.davidllorca.diworkshop.Constants;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.ui.common.ServerErrorDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements Callback<Character>, DetailViewMvc.Listener {

    public static final String EXTRA_CHARACTER_ID = "EXTRA_CHARACTER_ID";

    public static void start(Context context, int characterId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_CHARACTER_ID, characterId);
        context.startActivity(intent);
    }

    private DetailViewMvc mViewMvc;

    private RickAndMortyApi mRickAndMortyApi;

    private Call<Character> mCall;

    private int mCharacterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewMvc = new DetailViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRickAndMortyApi = retrofit.create(RickAndMortyApi.class);

        //noinspection ConstantConditions
        mCharacterId = getIntent().getExtras().getInt(EXTRA_CHARACTER_ID);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mCall = mRickAndMortyApi.getDetailCharacter(mCharacterId);
        mCall.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        if (mCall != null) {
            mCall.cancel();
        }
    }


    @Override
    public void onResponse(Call<Character> call, Response<Character> response) {
        if (response.isSuccessful() && (response.body()) != null) {
            Character character = response.body();
            mViewMvc.bindCharacter(character);
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<Character> call, Throwable t) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }
}
