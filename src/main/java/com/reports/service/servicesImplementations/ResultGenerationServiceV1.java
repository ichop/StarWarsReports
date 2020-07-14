package com.reports.service.servicesImplementations;

import com.reports.model.*;
import com.reports.model.Character;
import com.reports.service.ResultsGenerationService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ResultGenerationServiceV1 implements ResultsGenerationService {

    private final PlanetsService planetsService;
    private final FilmService filmService;
    private final CharacterService characterService;
    private final Set<Result> results = new HashSet<>();

    public ResultGenerationServiceV1(PlanetsService planetsService, FilmService filmService, CharacterService characterService) {
        this.planetsService = planetsService;
        this.filmService = filmService;
        this.characterService = characterService;
    }

    public Set<Result> generateResults(String characterPhrase, String searchingPlanetPhrase){
        results.clear();
        List<Character> characters = characterService.searchByNamePhrase(characterPhrase);
        filterByPlanetName(searchingPlanetPhrase, characters);
        return results;
    }


    private void filterByPlanetName(String searchingPlanetName, List<Character> characterList) {
        for (Character character : characterList) {
            Planet homeworld = getHomeworld(character);
            if (homeworld.getPlanetName().contains(searchingPlanetName) && !homeworld.getPlanetName().equals("unknown")) {  //or homeworld.getName().equals() to search by exact planet name
                generateResult(character, homeworld);
            }
        }
    }

    private void generateResult(Character character, Planet planet) {
        for (String url : character.getFilms()) {
            Film film = filmService.getFilm(url);
            Result result = Result.newBuilder()
                    .setFilmId(film.getFilmId())
                    .setFilmName(film.getTitle())
                    .setCharacterId(character.getCharacterId())
                    .setCharacterName(character.getCharacterName())
                    .setPlanetId(planet.getPlanetId())
                    .setPlanetName(planet.getPlanetName())
                    .build();
            results.add(result);
        }
    }

    private Planet getHomeworld(Character character) {
        return planetsService.getByUrl(character.getHomeworld());
    }
}
