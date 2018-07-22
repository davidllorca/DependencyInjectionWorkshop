package me.davidllorca.diworkshop.common.di;

import android.support.annotation.UiThread;
import android.support.v4.app.FragmentManager;

import me.davidllorca.diworkshop.Constants;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@UiThread
public class CompositionRoot {

    private Retrofit mRetrofit;
    private RickAndMortyApi mRickAndMortyApi;

    private Retrofit getRetrofit() {
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private RickAndMortyApi getRickAndMortyApi(){
        if(mRickAndMortyApi == null){
            mRickAndMortyApi = getRetrofit().create(RickAndMortyApi.class);
        }
        return mRickAndMortyApi;
    }

    public GetCharactersUseCase getCharactersUseCase() {
        return new GetCharactersUseCase(getRickAndMortyApi());
    }

    public GetCharacterDetailUseCase getCharacterDetailUseCase() {
        return new GetCharacterDetailUseCase(getRickAndMortyApi());
    }

    public DialogsManager getDialogsManager(FragmentManager fragmentManager) {
        return new DialogsManager(fragmentManager);
    }
}
