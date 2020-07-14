package com.reports.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CharacterDTO {
    @JsonProperty("character_id")
    private Integer characterId;
    @JsonProperty("name")
    private String characterName;
    private String homeworld;
    private List<String> films;
    private String url;
}