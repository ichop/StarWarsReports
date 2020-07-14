package com.reports.service.servicesImplementations;

import com.reports.model.Character;
import com.reports.model.CharacterSearchResponsePage;
import com.reports.model.DTO.CharacterDTO;
import com.reports.model.Film;
import com.reports.model.Planet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CharacterService {

    @Resource(name = "getBaseUrl")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final FilmService filmService;

    public CharacterService(FilmService filmService) {
        this.filmService = filmService;
    }

    public CharacterDTO getByUrl(String url) {
        CharacterDTO characterDto = restTemplate.getForObject(url, CharacterDTO.class);
        setCharacterId(characterDto);
        return characterDto;
    }

    public List<CharacterDTO> searchByNamePhrase(String characterPhrase) {
        List<CharacterDTO> characters = new ArrayList<>();
        String basePeopleSearchUrl = baseUrl + "/api/people/?search=";
        String nextUrl = basePeopleSearchUrl + characterPhrase;
        CharacterSearchResponsePage<Character> characterSearchResponsePage;
        do {
            characterSearchResponsePage = restTemplate.getForObject(nextUrl, CharacterSearchResponsePage.class);
            for (CharacterDTO characterDTO : Objects.requireNonNull(characterSearchResponsePage).getResults()) {
                setCharacterId(characterDTO);
                characters.add(characterDTO);
                nextUrl = characterSearchResponsePage.getNext();
            }
        } while (characterSearchResponsePage.getNext() != null);
        return characters;
    }

    private void setCharacterId(CharacterDTO character) {
        character.setCharacterId(
                Integer.parseInt(character.getUrl().replace(baseUrl + "/api/people/", "").replace("/", ""))
        );
    }


    public Character mapToCharacter(CharacterDTO characterDTO, Planet planet) {
        Character character = new Character();
        character.setCharacterId(characterDTO.getCharacterId());
        character.setCharacterName(characterDTO.getCharacterName());
        character.setHomeworld(planet);
        List<Film> films = new ArrayList<>();
        for (String filmUrl: characterDTO.getFilms()) {
            films.add(filmService.getFilm(filmUrl));
        }
        character.setFilms(films);
        return character;
    }

}
