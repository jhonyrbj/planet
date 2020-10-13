package com.planet.api.service.impl;

import com.planet.api.entity.Planet;
import com.planet.api.exception.NotFoundException;
import com.planet.api.repository.PlanetRepository;
import com.planet.api.service.PlanetService;
import com.planet.api.service.SWAPIService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.planet.api.constant.MessageErrorPlanetApi.PLANET_API_PLANETS_NOT_FOUND_CODE;
import static com.planet.api.constant.MessageErrorPlanetApi.PLANET_API_PLANET_NOT_FOUND_CODE;
import static com.planet.api.constant.Pagination.PAGE_SIZE;
import static com.planet.api.model.Error.singleError;

@Service
@Slf4j
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private PlanetRepository repository;
    private SWAPIService swapiService;

    @Override
    public Planet save(Planet planet) {
        List<String> films = swapiService.findPlanetByName(planet.getName()).getFilms();
        planet.setQttMovies(films == null || films.isEmpty() ? 0 : films.size());
        return repository.saveAndFlush(planet);
    }

    @Override
    public void delete(String planetId){
        repository.deleteById(planetId);
    }

    @Override
    public Planet findOneByName(String name) {
        return repository.findOneByName(name).orElseThrow(() -> new NotFoundException(singleError(PLANET_API_PLANET_NOT_FOUND_CODE)));
    }

    @Override
    public Planet findById(String planetId){
        return repository.findById(planetId).orElseThrow(() -> new NotFoundException(singleError(PLANET_API_PLANET_NOT_FOUND_CODE)));
    }

    @Override
    public Page<Planet> findAll(Integer page) {
        PageRequest pageReq
                = PageRequest.of(page == null || page < 1 ? 0: page - 1, PAGE_SIZE);
        Page<Planet> planetPage = repository.findAll(pageReq);
        if (!planetPage.hasContent()){
            throw new NotFoundException(singleError(PLANET_API_PLANETS_NOT_FOUND_CODE));
        }
        return planetPage;
    }
}
