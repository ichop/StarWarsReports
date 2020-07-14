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
public class Planet {
    private Integer planetId;
    private String planetName;
    private List<String> residents;
    private String url;
}
