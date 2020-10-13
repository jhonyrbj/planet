package com.planet.api.service;

import com.planet.api.model.PlanetResponseSWAPI;
import org.springframework.data.domain.Page;

public interface SWAPIService {

    PlanetResponseSWAPI findPlanetByName(String name);
    Page<PlanetResponseSWAPI> findAllPlanet(Integer page);
}
