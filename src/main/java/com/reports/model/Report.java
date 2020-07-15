package com.reports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Report {
    @Id
    @JsonProperty("report_id")
    private Integer reportId;
    @JsonProperty("query_criteria_character_phrase")
    private String queryCriteriaCharacterPhrase;
    @JsonProperty("query_criteria_planet_name")
    private String queryCriteriaPlanetName;

    @ElementCollection(fetch = FetchType.LAZY)
    @JsonProperty("result")
    private Set<Result> results = new HashSet<>();

    public Report(Integer reportId, String queryCriteriaCharacterPhrase, String queryCriteriaPlanetName) {
        this.reportId = reportId;
        this.queryCriteriaCharacterPhrase = queryCriteriaCharacterPhrase;
        this.queryCriteriaPlanetName = queryCriteriaPlanetName;
    }

}
