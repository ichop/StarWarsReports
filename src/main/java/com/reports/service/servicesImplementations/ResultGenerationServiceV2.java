package com.reports.service.servicesImplementations;

import com.reports.model.Character;
import com.reports.model.DTO.CharacterDTO;
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
    private Planet planet;

    private Set<Result> results = new HashSet<>();

    public ResultGenerationServiceV2(PlanetsService planetsService, CharacterService characterService) {
        this.planetsService = planetsService;
        this.characterService = characterService;
    }

    @Override
    public Set<Result> generateResults(String characterPhrase, String searchingPlanetName) throws NotFoundException {
        results.clear();
        List<CharacterDTO> characterDTOS = getCharacterListByHomeworldPlanet(searchingPlanetName);
        filterByCharacterName(characterDTOS, characterPhrase);
        return results;
    }

    private List<CharacterDTO> getCharacterListByHomeworldPlanet(String searchingPlanetName) throws NotFoundException {
        List<CharacterDTO> characterDTOS = new ArrayList<>();
        planet =  planetsService.getByName(searchingPlanetName);
        planet.getResidents()
                .forEach(url -> {
                    characterDTOS.add(characterService.getByUrl(url));
                });
        return characterDTOS;
    }

    private void filterByCharacterName(List<CharacterDTO> charactersDTOS, String characterPhrase) {

        for (CharacterDTO characterDTO: charactersDTOS) {
            if(characterDTO.getCharacterName().contains(characterPhrase)) {
                System.out.println(planet);
                generateResult(characterService.mapToCharacter(characterDTO, planet));
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
                    .setPlanetId(planet.getPlanetId())
                    .setPlanetName(planet.getPlanetName())
                    .build();
            results.add(result);
        }
    }

}
