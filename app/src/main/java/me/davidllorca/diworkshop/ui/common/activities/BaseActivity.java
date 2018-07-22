package me.davidllorca.diworkshop.ui.common.activities;

import android.support.v7.app.AppCompatActivity;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.common.di.CompositionRoot;

public class BaseActivity extends AppCompatActivity {

    protected CompositionRoot getCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
