package me.davidllorca.diworkshop.data.usecase;

import me.davidllorca.diworkshop.common.BaseObservable;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCharacterDetailUseCase extends BaseObservable<GetCharacterDetailUseCase.Listener> {

    public interface Listener {
        void onSuccess(Character character);
        void onError();
    }

    private RickAndMortyApi mRickAndMortyApi;
    private Call<Character> mCall;

    public GetCharacterDetailUseCase(RickAndMortyApi rickAndMortyApi) {
        mRickAndMortyApi = rickAndMortyApi;
    }

    public void execute(int characterId) {
        cancelCurrentCallIfActive();
        mCall = mRickAndMortyApi.getDetailCharacter(characterId);
        mCall.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if(response.isSuccessful()){
                    notifySuccess(response.body());
                } else {
                    notifyFail();
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                notifyFail();
            }
        });
    }

    private void notifySuccess(Character characters) {
        for(GetCharacterDetailUseCase.Listener listener: getListeners()) {
            listener.onSuccess(characters);
        }
    }

    private void notifyFail() {
        for(GetCharacterDetailUseCase.Listener listener: getListeners()) {
            listener.onError();
        }
    }

    private void cancelCurrentCallIfActive() {
        if(mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }

}
