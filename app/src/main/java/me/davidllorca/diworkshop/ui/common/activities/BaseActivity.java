package me.davidllorca.diworkshop.ui.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.common.di.CompositionRoot;
import me.davidllorca.diworkshop.common.di.Injector;
import me.davidllorca.diworkshop.common.di.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private PresentationCompositionRoot mPresentationCompositionRoot;

    @UiThread
    protected Injector getInjector(){
        return new Injector(getCompositionRoot());
    }

    private PresentationCompositionRoot getCompositionRoot() {
        if(mPresentationCompositionRoot == null){
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    this
            );
        }
        return mPresentationCompositionRoot;
    }

    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
