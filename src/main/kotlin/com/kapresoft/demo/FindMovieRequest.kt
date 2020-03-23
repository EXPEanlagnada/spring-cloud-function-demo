package com.kapresoft.demo;

public class FindMovieRequest {

    private String characterName;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FindMovieRequest{");
        sb.append("characterName='").append(characterName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
