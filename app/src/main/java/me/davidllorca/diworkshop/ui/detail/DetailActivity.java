package me.davidllorca.diworkshop.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import me.davidllorca.diworkshop.common.di.Service;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.ui.common.activities.BaseActivity;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.dialogs.ServerErrorDialogFragment;
import me.davidllorca.diworkshop.ui.common.mvcviews.ViewMvcFactory;

public class DetailActivity extends BaseActivity
        implements DetailViewMvc.Listener, GetCharacterDetailUseCase.Listener {

    public static final String EXTRA_CHARACTER_ID = "EXTRA_CHARACTER_ID";

    public static void start(Context context, int characterId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_CHARACTER_ID, characterId);
        context.startActivity(intent);
    }

    @Service private ViewMvcFactory mViewMvcFactory;
    @Service private GetCharacterDetailUseCase mGetCharacterDetailUseCase;
    @Service private DialogsManager mDialogsManager;

    private DetailViewMvc mViewMvc;

    private int mCharacterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInjector().inject(this);

        mViewMvc = mViewMvcFactory.newInstance(DetailViewMvc.class, null);
        setContentView(mViewMvc.getRootView());

        //noinspection ConstantConditions
        mCharacterId = getIntent().getExtras().getInt(EXTRA_CHARACTER_ID);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mGetCharacterDetailUseCase.registerListener(this);
        mGetCharacterDetailUseCase.execute(mCharacterId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mGetCharacterDetailUseCase.unregisterListener(this);
    }

    @Override
    public void onSuccess(Character character) {
        mViewMvc.bindCharacter(character);
    }

    @Override
    public void onError() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(),"");
    }
}
