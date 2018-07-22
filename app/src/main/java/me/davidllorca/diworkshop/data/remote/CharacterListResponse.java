package me.davidllorca.diworkshop.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.davidllorca.diworkshop.data.model.Character;

public class CharacterListResponse {

    @SerializedName("results")
    private final List<Character> mCharacters;

    public CharacterListResponse(List<Character> characters) {
        mCharacters = characters;
    }

    public List<Character> getCharacter() {
        return mCharacters;
    }
}
