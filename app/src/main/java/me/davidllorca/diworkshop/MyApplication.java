package me.davidllorca.diworkshop;

import android.app.Application;
import android.support.annotation.UiThread;

import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private Retrofit mRetrofit;
    private RickAndMortyApi mRickAndMortyApi;

    @UiThread
    private Retrofit getRetrofit() {
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    @UiThread
    private RickAndMortyApi getRickAndMortyApi(){
        if(mRickAndMortyApi == null){
            mRickAndMortyApi = getRetrofit().create(RickAndMortyApi.class);
        }
        return mRickAndMortyApi;
    }

    @UiThread
    public GetCharactersUseCase getCharactersUseCase() {
        return new GetCharactersUseCase(getRickAndMortyApi());
    }

    @UiThread
    public GetCharacterDetailUseCase getCharacterDetailUseCase() {
        return new GetCharacterDetailUseCase(getRickAndMortyApi());
    }

}
