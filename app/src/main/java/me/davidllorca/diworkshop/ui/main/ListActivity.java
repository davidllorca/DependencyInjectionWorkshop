package me.davidllorca.diworkshop.ui.main;

import android.os.Bundle;

import java.util.List;

import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.dialogs.ServerErrorDialogFragment;
import me.davidllorca.diworkshop.ui.common.mvcviews.ViewMvcFactory;
import me.davidllorca.diworkshop.ui.detail.DetailActivity;

public class ListActivity extends BaseActivity
        implements ListViewMvc.Listener, GetCharactersUseCase.Listener {

    public ViewMvcFactory mViewMvcFactory;
    public GetCharactersUseCase mGetCharactersUseCase;
    public DialogsManager mDialogsManager;

    private ListViewMvc mViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInjector().inject(this);

        mViewMvc = mViewMvcFactory.newInstance(ListViewMvc.class, null);
        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mGetCharactersUseCase.registerListener(this);
        mGetCharactersUseCase.execute();
    }

    @Override
    protected void onStop() {
        mViewMvc.unregisterListener(this);
        mGetCharactersUseCase.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onCharacterClicked(Character character) {
        DetailActivity.start(this, character.getId());
    }

    @Override
    public void onSuccess(List<Character> characters) {
        mViewMvc.bindCharacters(characters);
    }

    @Override
    public void onError() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(),"");
    }
}
