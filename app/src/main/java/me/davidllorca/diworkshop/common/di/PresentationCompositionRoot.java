package me.davidllorca.diworkshop.common.di;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.mvcviews.ViewMvcFactory;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentManager mFragmentManager;
    private final LayoutInflater mLayoutInflater;

    public PresentationCompositionRoot(CompositionRoot compositionRoot, FragmentManager fragmentManager, LayoutInflater layoutInflater) {
        this.mCompositionRoot = compositionRoot;
        this.mFragmentManager = fragmentManager;
        this.mLayoutInflater = layoutInflater;
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(mFragmentManager);
    }

    public ViewMvcFactory getMvcFactory() {
        return new ViewMvcFactory(mLayoutInflater);
    }

    public GetCharactersUseCase getGetCharactersUseCase(){
        return mCompositionRoot.getCharactersUseCase();
    }

    public GetCharacterDetailUseCase getGetCharacterDetailUseCase() {
        return mCompositionRoot.getCharacterDetailUseCase();
    }
}
