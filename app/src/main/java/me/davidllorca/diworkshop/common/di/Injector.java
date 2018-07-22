package me.davidllorca.diworkshop.common.di;

import me.davidllorca.diworkshop.ui.detail.DetailActivity;
import me.davidllorca.diworkshop.ui.main.ListActivity;

public class Injector {

    private final PresentationCompositionRoot mPresentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot){
        mPresentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client){
        if(client instanceof ListActivity){
            injectListActivity(((ListActivity) client));
        } else if(client instanceof DetailActivity){
            injectDetailActivity(((DetailActivity) client));
        } else {
            throw new IllegalArgumentException("Invalid client: " + client);
        }
    }

    private void injectListActivity(ListActivity client) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getMvcFactory();
        client.mGetCharactersUseCase = mPresentationCompositionRoot.getGetCharactersUseCase();
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager();
    }


    private void injectDetailActivity(DetailActivity client) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getMvcFactory();
        client.mGetCharacterDetailUseCase = mPresentationCompositionRoot.getGetCharacterDetailUseCase();
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager();
    }

}
