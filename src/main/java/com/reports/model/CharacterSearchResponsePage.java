package com.reports.model;

import com.reports.model.DTO.CharacterDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CharacterSearchResponsePage<T> {
    private int count;
    private String next;
    private String previous;
    private List<CharacterDTO> results;

    public List<CharacterDTO> getResults(){
        return results;
    }
}
