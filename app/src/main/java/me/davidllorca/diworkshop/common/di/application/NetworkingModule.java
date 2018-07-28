package me.davidllorca.diworkshop.common.di.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.davidllorca.diworkshop.Constants;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkingModule {

    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    RickAndMortyApi getRickAndMortyApi(Retrofit retrofit) {
        return retrofit.create(RickAndMortyApi.class);
    }
}
