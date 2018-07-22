package me.davidllorca.diworkshop.ui.common.activities;

import android.support.v7.app.AppCompatActivity;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.common.di.CompositionRoot;
import me.davidllorca.diworkshop.common.di.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private PresentationCompositionRoot mPresentationCompositionRoot;

    protected PresentationCompositionRoot getCompositionRoot() {
        if(mPresentationCompositionRoot == null){
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    getSupportFragmentManager()
            );
        }
        return mPresentationCompositionRoot;
    }

    protected CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
