package com.reports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Embeddable
public class Result {
    @JsonProperty("film_id")
    private Integer filmId;
    @JsonProperty("film_name")
    private String filmName;
    @JsonProperty("character_id")
    private Integer characterId;
    @JsonProperty("character_name")
    private String characterName;
    @JsonProperty("planet_id")
    private Integer planetId;
    @JsonProperty("planet_name")
    private String planetName;


    public Result(Integer filmId, String filmName, Integer characterId, String characterName, Integer planetId, String planetName) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.characterId = characterId;
        this.characterName = characterName;
        this.planetId = planetId;
        this.planetName = planetName;
    }

    public static Builder newBuilder() {
        return new Result().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setFilmId(Integer filmId) {
            Result.this.filmId = filmId;
            return this;
        }

        public Builder setFilmName(String filmName) {
            Result.this.filmName = filmName;
            return this;
        }

        public Builder setCharacterId(Integer characterId) {
            Result.this.characterId = characterId;
            return this;
        }

        public Builder setCharacterName(String characterName) {
            Result.this.characterName = characterName;
            return this;
        }

        public Builder setPlanetId(Integer planetId) {
            Result.this.planetId = planetId;
            return this;
        }

        public Builder setPlanetName(String planetName) {
            Result.this.planetName = planetName;
            return this;
        }

        public Result build() {
            return Result.this;
        }
    }
}
