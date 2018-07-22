package me.davidllorca.diworkshop.common.di;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
            if(isPublicNonStaticNonFinal(field)){
                injectField(client, field);
            }
        }

    }

    private boolean isPublicNonStaticNonFinal(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isPublic(modifiers) &&
                !Modifier.isStatic(modifiers) &&
                !Modifier.isFinal(modifiers);
    }

    private void injectField(Object client, Field field) {
        try {
            field.set(client, getServiceForClass(field.getType()));
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
