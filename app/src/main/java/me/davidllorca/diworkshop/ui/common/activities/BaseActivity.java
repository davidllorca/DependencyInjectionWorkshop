package me.davidllorca.diworkshop.ui.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.common.di.application.ApplicationComponent;
import me.davidllorca.diworkshop.common.di.presentation.DaggerPresentationComponent;
import me.davidllorca.diworkshop.common.di.presentation.PresentationComponent;
import me.davidllorca.diworkshop.common.di.presentation.PresentationModule;

public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectionDone;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectionDone) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectionDone = true;
        return DaggerPresentationComponent.builder()
                .presentationModule(new PresentationModule(this))
                .applicationComponent(getApplicationComponent())
                .build();
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();

    }

}
