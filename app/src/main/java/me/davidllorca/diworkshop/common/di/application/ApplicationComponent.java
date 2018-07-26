package me.davidllorca.diworkshop.common.di.application;

import javax.inject.Singleton;

import dagger.Component;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    GetCharactersUseCase getGetCharactersUseCase();
    GetCharacterDetailUseCase getGetCharacterDetailUseCase();

}
