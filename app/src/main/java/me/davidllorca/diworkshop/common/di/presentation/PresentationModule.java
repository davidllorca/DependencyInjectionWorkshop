package me.davidllorca.diworkshop.common.di.presentation;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import me.davidllorca.diworkshop.common.di.application.ApplicationComponent;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.ImageLoader;
import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.mvcviews.ViewMvcFactory;

@Module
public class PresentationModule {

    private final BaseActivity mActivity;
    private final ApplicationComponent mApplicationComponent;

    public PresentationModule(BaseActivity activity, ApplicationComponent applicationComponent) {
        this.mActivity = activity;
        this.mApplicationComponent = applicationComponent;
    }

    @Provides
    FragmentManager getFragmentManager(){
        return mActivity.getSupportFragmentManager();
    }
    
    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    ImageLoader getImageLoader() {
        return new ImageLoader(mActivity);
    }

    @Provides
    DialogsManager getDialogsManager() {
        return new DialogsManager(getFragmentManager());
    }

    @Provides
    ViewMvcFactory getMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getImageLoader());
    }

    @Provides
    GetCharactersUseCase getGetCharactersUseCase(){
        return mApplicationComponent.getGetCharactersUseCase();
    }

    @Provides
    GetCharacterDetailUseCase getGetCharacterDetailUseCase() {
        return mApplicationComponent.getGetCharacterDetailUseCase();
    }
}
