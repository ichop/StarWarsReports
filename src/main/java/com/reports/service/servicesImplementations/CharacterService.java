package com.reports.service.servicesImplementations;

import com.reports.model.Character;
import com.reports.model.CharacterSearchResponsePage;
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

    public Character getByUrl(String url) {
        Character character = restTemplate.getForObject(url, Character.class);
        setCharacterId(character);
        return character;
    }

    public List<Character> searchByNamePhrase(String characterPhrase) {
        List<Character> characters = new ArrayList<>();
        String basePeopleSearchUrl = baseUrl + "/api/people/?search=";
        String nextUrl = basePeopleSearchUrl + characterPhrase;
        CharacterSearchResponsePage<Character> characterSearchResponsePage;
        do {
            characterSearchResponsePage = restTemplate.getForObject(nextUrl, CharacterSearchResponsePage.class);
            for (Character character : Objects.requireNonNull(characterSearchResponsePage).getResults()) {
                setCharacterId(character);
                characters.add(character);
                nextUrl = characterSearchResponsePage.getNext();
            }
        } while (characterSearchResponsePage.getNext() != null);
        return characters;
    }

    private void setCharacterId(Character character) {
        character.setCharacterId(
                Integer.parseInt(character.getUrl().replace(baseUrl + "/api/people/", "").replace("/", ""))
        );
    }

}
