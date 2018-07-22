package me.davidllorca.diworkshop.common.di;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.mvcviews.ViewMvcFactory;

public class Injector {

    private final PresentationCompositionRoot mPresentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot){
        mPresentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client){
        Class clazz = client.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if(isAnnotatedForInjection(field)){
                injectField(client, field);
            }
        }

    }

    private boolean isAnnotatedForInjection(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for(Annotation annotation: annotations){
            if(annotation instanceof Service){
                return true;
            }
        }
        return false;
    }

    private void injectField(Object client, Field field) {
        try {
            // Make modifier public
            boolean isAccessibleInitially = field.isAccessible();
            field.setAccessible(true);
            field.set(client, getServiceForClass(field.getType()));
            // Make modifier like initial state
            field.setAccessible(isAccessibleInitially);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getServiceForClass(Class<?> type) {
        if(type.equals(DialogsManager.class)){
            return mPresentationCompositionRoot.getDialogsManager();
        } else if(type.equals(ViewMvcFactory.class)) {
            return mPresentationCompositionRoot.getMvcFactory();
        } else if(type.equals(GetCharactersUseCase.class)){
            return mPresentationCompositionRoot.getGetCharactersUseCase();
        } else if(type.equals(GetCharacterDetailUseCase.class)) {
            return mPresentationCompositionRoot.getGetCharacterDetailUseCase();
        } else {
            return new RuntimeException("Unsupported service type class: " + type);
        }
    }

}
