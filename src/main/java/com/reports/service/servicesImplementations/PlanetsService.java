package com.reports.service.servicesImplementations;

import com.reports.model.Planet;
import com.reports.model.PlanetSearchResponsePage;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlanetsService {

    @Resource(name = "getBaseUrl")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Planet getByUrl(String url) {
        Planet planet = restTemplate.getForObject(url, Planet.class);
        setPlanetId(planet);
        return planet;
    }

    public Planet getByName(String name) throws NotFoundException {

        String basePlanetSearchUrl = baseUrl + "/api/planets/?search=";

        List<Planet> planets = restTemplate.getForObject(basePlanetSearchUrl + name, PlanetSearchResponsePage.class).getResults();
        if (planets.size() == 1 && planets.get(0).getPlanetName().equals(name)) {
            setPlanetId(planets.get(0));
            return planets.get(0);
        }
        throw new NotFoundException("No such planet name found");
    }

    private void setPlanetId(Planet planet) {
        planet.setPlanetId(
                Integer.parseInt(planet.getUrl().replace(baseUrl + "/api/planets/", "").replace("/", ""))
        );
    }
}
