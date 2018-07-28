package me.davidllorca.diworkshop;

import android.app.Application;

import me.davidllorca.diworkshop.common.di.application.ApplicationComponent;
import me.davidllorca.diworkshop.common.di.application.ApplicationModule;
import me.davidllorca.diworkshop.common.di.application.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
