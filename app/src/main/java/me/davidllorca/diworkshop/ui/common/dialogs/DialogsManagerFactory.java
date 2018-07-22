package me.davidllorca.diworkshop.ui.common.dialogs;

import android.support.v4.app.FragmentManager;

public class DialogsManagerFactory {

    public DialogsManager newDialogsManager(FragmentManager fragmentManager){
        return new DialogsManager(fragmentManager);
    }
}
