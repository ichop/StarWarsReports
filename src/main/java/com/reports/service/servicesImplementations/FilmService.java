package com.reports.service.servicesImplementations;

import com.reports.model.Film;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class FilmService {

    @Resource(name = "getBaseUrl")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Film getFilm(String url){
        Film film = restTemplate.getForObject(url, Film.class);
        setFilmId(film);
        return film;
    }

    private void setFilmId(Film film) {
        film.setFilmId(
                Integer.parseInt(film.getUrl().replace(baseUrl + "api/films/", "").replace("/", ""))
        );
    }
}
