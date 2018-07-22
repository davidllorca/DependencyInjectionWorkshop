package me.davidllorca.diworkshop.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import me.davidllorca.diworkshop.MyApplication;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.usecase.GetCharacterDetailUseCase;
import me.davidllorca.diworkshop.ui.common.dialogs.DialogsManager;
import me.davidllorca.diworkshop.ui.common.dialogs.ServerErrorDialogFragment;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity
        implements DetailViewMvc.Listener, GetCharacterDetailUseCase.Listener {

    public static final String EXTRA_CHARACTER_ID = "EXTRA_CHARACTER_ID";

    public static void start(Context context, int characterId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_CHARACTER_ID, characterId);
        context.startActivity(intent);
    }

    private DetailViewMvc mViewMvc;
    private GetCharacterDetailUseCase mGetCharacterDetailUseCase;
    private DialogsManager mDialogsManager;

    private int mCharacterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewMvc = new DetailViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());

        Retrofit retrofit = ((MyApplication) getApplication()).getRetrofit();
        mGetCharacterDetailUseCase = new GetCharacterDetailUseCase(retrofit);

        mDialogsManager = new DialogsManager(getSupportFragmentManager());

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
