package me.davidllorca.diworkshop.common.di.presentation;

import dagger.Component;
import me.davidllorca.diworkshop.common.di.PresentationScope;
import me.davidllorca.diworkshop.common.di.application.ApplicationComponent;
import me.davidllorca.diworkshop.ui.detail.DetailActivity;
import me.davidllorca.diworkshop.ui.main.ListActivity;

@PresentationScope
@Component(dependencies = ApplicationComponent.class,
        modules = PresentationModule.class)
public interface PresentationComponent {
    void inject(ListActivity activity);
    void inject(DetailActivity activity);
}
