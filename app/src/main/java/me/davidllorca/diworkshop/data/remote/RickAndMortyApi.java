package me.davidllorca.diworkshop.data.remote;

import me.davidllorca.diworkshop.data.model.Character;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickAndMortyApi {

    @GET("/api/character/")
    Call<CharacterListResponse> getAllCharacters();

    @GET("/api/character/{characterId}")
    Call<Character> getDetailCharacter(@Path("characterId") int characterId);

}
