package me.davidllorca.diworkshop.common.di.application;

import javax.inject.Singleton;

import dagger.Component;
import me.davidllorca.diworkshop.common.di.presentation.PresentationComponent;
import me.davidllorca.diworkshop.common.di.presentation.PresentationModule;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {

    PresentationComponent newPresentationComponent(PresentationModule presentationModule);

}
