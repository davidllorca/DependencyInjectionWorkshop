package me.davidllorca.diworkshop.common.di.application;

import dagger.Module;
import dagger.Provides;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;

@Module
public class ApplicationModule {

    @Provides
    GetCharactersUseCase getCharactersUseCase(RickAndMortyApi api) {
        return new GetCharactersUseCase(api);
    }

    @Provides
    GetCharacterDetailUseCase getCharacterDetailUseCase(RickAndMortyApi api) {
        return new GetCharacterDetailUseCase(api);
    }
}
