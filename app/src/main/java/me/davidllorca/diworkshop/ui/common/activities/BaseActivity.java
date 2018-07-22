package me.davidllorca.diworkshop.ui.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.common.di.CompositionRoot;
import me.davidllorca.diworkshop.common.di.Injector;
import me.davidllorca.diworkshop.common.di.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectionDone;

    @UiThread
    protected Injector getInjector() {
        if (mIsInjectionDone) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectionDone = true;
        return new Injector(getCompositionRoot());
    }

    private PresentationCompositionRoot getCompositionRoot() {
        return new PresentationCompositionRoot(getAppCompositionRoot(),this);
    }

    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
