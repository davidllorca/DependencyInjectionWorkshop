package me.davidllorca.diworkshop.ui.common.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.common.di.CompositionRoot;
import me.davidllorca.diworkshop.common.di.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private PresentationCompositionRoot mPresentationCompositionRoot;

    protected PresentationCompositionRoot getCompositionRoot() {
        if(mPresentationCompositionRoot == null){
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    getSupportFragmentManager(),
                    LayoutInflater.from(this)
            );
        }
        return mPresentationCompositionRoot;
    }

    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}