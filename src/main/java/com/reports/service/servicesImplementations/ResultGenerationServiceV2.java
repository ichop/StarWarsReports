package com.reports.service.servicesImplementations;

import com.reports.model.Character;
import com.reports.model.Film;
import com.reports.model.Planet;
import com.reports.model.Result;
import com.reports.service.ResultsGenerationService;
import javassist.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Primary
public class ResultGenerationServiceV2 implements ResultsGenerationService {


    private final PlanetsService planetsService;
    private final CharacterService characterService;
    private final FilmService filmService;
    private Planet planet;
    private Set<Result> results = new HashSet<>();

    public ResultGenerationServiceV2(PlanetsService planetsService, CharacterService characterService, FilmService filmService) {
        this.planetsService = planetsService;
        this.characterService = characterService;
        this.filmService = filmService;
    }

    @Override
    public Set<Result> generateResults(String characterPhrase, String searchingPlanetName) throws NotFoundException {
        results.clear();
        List<Character> characters = new ArrayList<>();

            planet = planetsService.getByName(searchingPlanetName);
            planet.getResidents().stream()
                    .forEach(url -> {
                  characters.add(characterService.getByUrl(url));
            });

            filterByCharacterName(characters, characterPhrase);

        return results;
    }

    private void filterByCharacterName(List<Character> characters, String characterPhrase) {
        for (Character character: characters) {
            if(character.getCharacterName().contains(characterPhrase)) {
                generateResult(character);
            }
        }
    }

    private void generateResult(Character character) {
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

}
