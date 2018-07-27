package me.davidllorca.diworkshop.common.di.presentation;

import dagger.Component;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.mvcviews.ViewMvcFactory;

@Component(modules = PresentationModule.class)
public interface PresentationComponent {

    DialogsManager getDialogsManager();
    ViewMvcFactory getMvcFactory();
    GetCharactersUseCase getGetCharactersUseCase();
    GetCharacterDetailUseCase getGetCharacterDetailUseCase();

}
