package me.davidllorca.diworkshop.ui.main;

import java.util.List;

import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.ui.common.mvcviews.ObservableViewMvc;

public interface ListViewMvc extends ObservableViewMvc<ListViewMvc.Listener> {

    interface Listener {
        void onCharacterClicked(Character character);
    }

    void bindCharacters(List<Character> characters);

}
