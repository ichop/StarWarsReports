package com.reports.service.servicesImplementations;

import com.reports.model.*;
import com.reports.model.Character;
import com.reports.model.DTO.CharacterDTO;
import com.reports.service.ResultsGenerationService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ResultGenerationServiceV1 implements ResultsGenerationService {

    private final PlanetsService planetsService;
    private final CharacterService characterService;
    private final Set<Result> results = new HashSet<>();

    public ResultGenerationServiceV1(PlanetsService planetsService, CharacterService characterService) {
        this.planetsService = planetsService;
        this.characterService = characterService;
    }

    public Set<Result> generateResults(String characterPhrase, String searchingPlanetPhrase){
        results.clear();
        List<CharacterDTO> characters = characterService.searchByNamePhrase(characterPhrase);
        filterByPlanetName(searchingPlanetPhrase, characters);
        return results;
    }


    private void filterByPlanetName(String searchingPlanetName, List<CharacterDTO> characterList) {
        for (CharacterDTO characterDTO : characterList) {
            Planet homeworld = getHomeworld(characterDTO);
            if (homeworld.getPlanetName().contains(searchingPlanetName) && !homeworld.getPlanetName().equals("unknown")) {
               generateResult(characterService.mapToCharacter(characterDTO, homeworld));
            }
        }
    }

    private void generateResult(Character character) {
        for (Film film : character.getFilms()) {
            Result result = Result.newBuilder()
                    .setFilmId(film.getFilmId())
                    .setFilmName(film.getTitle())
                    .setCharacterId(character.getCharacterId())
                    .setCharacterName(character.getCharacterName())
                    .setPlanetId(character.getHomeworld().getPlanetId())
                    .setPlanetName(character.getHomeworld().getPlanetName())
                    .build();
            results.add(result);
        }
    }

    private Planet getHomeworld(CharacterDTO character) {
        return planetsService.getByUrl(character.getHomeworld());
    }
}
