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
public class PlanetSearchResponsePage {
    private int count;
    private String next;
    private String previous;
    private List<Planet> results;

    public List<Planet> getResults(){
        return results;
    }
}
