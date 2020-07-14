package com.reports.model;

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
public class Planet {
    @JsonProperty("planet_id")
    private Integer planetId;
    @JsonProperty("name")
    private String planetName;
    @JsonProperty("residents")
    private List<String> residents;
    private String url;
}
