package me.davidllorca.diworkshop.data.usecase;

import android.support.annotation.Nullable;

import java.util.List;

import me.davidllorca.diworkshop.common.BaseObservable;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.remote.CharacterListResponse;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetCharactersUseCase extends BaseObservable<GetCharactersUseCase.Listener> {

    public interface Listener {
        void onSuccess(List<Character> characters);
        void onError();
    }

    private RickAndMortyApi mRickAndMortyApi;
    @Nullable private Call<CharacterListResponse> mCall;

    public GetCharactersUseCase(Retrofit retrofit) {
        mRickAndMortyApi = retrofit.create(RickAndMortyApi.class);
    }

    public void execute() {
        cancelCurrentCallIfActive();
        mCall = mRickAndMortyApi.getAllCharacters();
        mCall.enqueue(new Callback<CharacterListResponse>() {
            @Override
            public void onResponse(Call<CharacterListResponse> call, Response<CharacterListResponse> response) {
                if(response.isSuccessful()){
                    notifySuccess(response.body().getCharacters());
                } else {
                    notifyFail();
                }
            }

            @Override
            public void onFailure(Call<CharacterListResponse> call, Throwable t) {
                notifyFail();
            }
        });
    }

    private void notifySuccess(List<Character> characters) {
        for(Listener listener: getListeners()) {
            listener.onSuccess(characters);
        }
    }

    private void notifyFail() {
        for(Listener listener: getListeners()) {
            listener.onError();
        }
    }

    private void cancelCurrentCallIfActive() {
        if(mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }

}
