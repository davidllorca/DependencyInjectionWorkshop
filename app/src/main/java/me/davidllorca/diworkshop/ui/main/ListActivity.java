package me.davidllorca.diworkshop.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.List;

import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.usecase.GetCharactersUseCase;
import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.dialogs.ServerErrorDialogFragment;
import me.davidllorca.diworkshop.ui.detail.DetailActivity;

public class ListActivity extends BaseActivity
        implements ListViewMvc.Listener, GetCharactersUseCase.Listener {

    private ListViewMvc mViewMvc;
    private GetCharactersUseCase mGetCharactersUseCase;
    private DialogsManager mDialogsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = new ListViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());

        mGetCharactersUseCase = getCompositionRoot().getCharactersUseCase();

        mDialogsManager = getCompositionRoot().getDialogsManager(getSupportFragmentManager());
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
