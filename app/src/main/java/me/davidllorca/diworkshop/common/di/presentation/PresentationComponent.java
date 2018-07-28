package me.davidllorca.diworkshop.common.di.presentation;

import dagger.Subcomponent;
import me.davidllorca.diworkshop.ui.detail.DetailActivity;
import me.davidllorca.diworkshop.ui.main.ListActivity;

@Subcomponent(
        modules = PresentationModule.class)
public interface PresentationComponent {
    void inject(ListActivity activity);
    void inject(DetailActivity activity);
}
