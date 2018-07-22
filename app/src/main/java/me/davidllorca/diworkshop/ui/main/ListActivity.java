package me.davidllorca.diworkshop.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import me.davidllorca.diworkshop.Constants;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.remote.CharacterListResponse;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.ui.common.ServerErrorDialogFragment;
import me.davidllorca.diworkshop.ui.detail.DetailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity  implements
        Callback<CharacterListResponse>, ListViewMvc.Listener {

    private ListViewMvc mViewMvc;
    private RickAndMortyApi mRickAndMortyApi;

    private Call<CharacterListResponse> mCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = new ListViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());

        // init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRickAndMortyApi = retrofit.create(RickAndMortyApi.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mCall = mRickAndMortyApi.getAllCharacters();
        mCall.enqueue(this);
    }

    @Override
    protected void onStop() {
        mViewMvc.unregisterListener(this);
        super.onStop();
        if (mCall != null) {
            mCall.cancel();
        }
    }

    @Override
    public void onResponse(Call<CharacterListResponse> call, Response<CharacterListResponse> response) {
        CharacterListResponse responseSchema;
        if (response.isSuccessful() && (responseSchema = response.body()) != null) {
            mViewMvc.bindCharacters(responseSchema.getCharacter());
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<CharacterListResponse> call, Throwable t) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onCharacterClicked(Character character) {
        DetailActivity.start(this, character.getId());
    }

}
