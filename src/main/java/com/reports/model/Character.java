package com.reports.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Character {

    private Integer characterId;
    private String characterName;
    private Planet homeworld;
    private List<Film> films;
}
