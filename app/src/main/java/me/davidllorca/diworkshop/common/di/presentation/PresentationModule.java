package me.davidllorca.diworkshop.common.di.presentation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import me.davidllorca.diworkshop.ui.common.ImageLoader;
import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;

@Module
public class PresentationModule {

    private final BaseActivity mActivity;

    public PresentationModule(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    Context getContext(Activity activity) {
        return activity;
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

}
