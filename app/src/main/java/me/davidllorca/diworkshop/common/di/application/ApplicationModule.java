package me.davidllorca.diworkshop.common.di.application;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
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
