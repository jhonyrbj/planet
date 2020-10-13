package com.planet.api.service;

import com.planet.api.entity.Planet;
import org.springframework.data.domain.Page;

public interface PlanetService {

    Planet save(Planet planet);

    void delete(String planetId);

    Planet findOneByName(String name);

    Planet findById(String planetId);

    Page<Planet> findAll(Integer page);
}
