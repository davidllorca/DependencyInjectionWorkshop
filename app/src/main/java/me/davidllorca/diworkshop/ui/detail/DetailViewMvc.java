package me.davidllorca.diworkshop.ui.detail;

import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.ui.common.mvcviews.ObservableViewMvc;

public interface DetailViewMvc extends ObservableViewMvc<DetailViewMvc.Listener> {

    interface Listener {
        // Events have not listened yet
    }

    void bindCharacter(Character character);

}
