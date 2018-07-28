package me.davidllorca.diworkshop.ui.common.dialogs;

import android.support.annotation.UiThread;
import android.support.v4.app.DialogFragment;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.common.di.application.ApplicationComponent;
import me.davidllorca.diworkshop.common.di.presentation.PresentationComponent;
import me.davidllorca.diworkshop.common.di.presentation.PresentationModule;
import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;

public abstract class BaseDialog extends DialogFragment {
    private boolean mIsInjectionDone;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectionDone) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectionDone = true;
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule((BaseActivity) getActivity()));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getActivity().getApplication()).getApplicationComponent();
    }

}
