package me.davidllorca.diworkshop.common.di.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.davidllorca.diworkshop.Constants;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

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

    @Provides
    GetCharactersUseCase getCharactersUseCase(RickAndMortyApi api) {
        return new GetCharactersUseCase(api);
    }

    @Provides
    GetCharacterDetailUseCase getCharacterDetailUseCase(RickAndMortyApi api) {
        return new GetCharacterDetailUseCase(api);
    }
}
