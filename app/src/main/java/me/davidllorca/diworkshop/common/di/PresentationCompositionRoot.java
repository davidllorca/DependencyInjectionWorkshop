package me.davidllorca.diworkshop.common.di;

import android.support.v4.app.FragmentManager;

import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentManager mFragmentManager;

    public PresentationCompositionRoot(CompositionRoot compositionRoot, FragmentManager fragmentManager) {
        this.mCompositionRoot = compositionRoot;
        this.mFragmentManager = fragmentManager;
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(mFragmentManager);
    }

    public GetCharactersUseCase getGetCharactersUseCase(){
        return mCompositionRoot.getCharactersUseCase();
    }

    public GetCharacterDetailUseCase getGetCharacterDetailUseCase() {
        return mCompositionRoot.getCharacterDetailUseCase();
    }
}
