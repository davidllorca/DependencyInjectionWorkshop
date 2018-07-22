package me.davidllorca.diworkshop.common.di;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.ImageLoader;
import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.mvcviews.ViewMvcFactory;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final BaseActivity mActivity;

    public PresentationCompositionRoot(CompositionRoot compositionRoot, BaseActivity activity) {
        this.mCompositionRoot = compositionRoot;
        this.mActivity = activity;
    }

    private FragmentManager getFragmentManager(){
        return mActivity.getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    private ImageLoader getImageLoader() {
        return new ImageLoader(mActivity);
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(getFragmentManager());
    }

    public ViewMvcFactory getMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getImageLoader());
    }

    public GetCharactersUseCase getGetCharactersUseCase(){
        return mCompositionRoot.getCharactersUseCase();
    }

    public GetCharacterDetailUseCase getGetCharacterDetailUseCase() {
        return mCompositionRoot.getCharacterDetailUseCase();
    }
}
