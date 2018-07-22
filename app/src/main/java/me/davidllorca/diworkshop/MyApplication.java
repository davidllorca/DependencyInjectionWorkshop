package me.davidllorca.diworkshop;

import android.app.Application;

import me.davidllorca.diworkshop.common.di.CompositionRoot;

public class MyApplication extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
