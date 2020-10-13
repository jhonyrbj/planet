package com.planet.api.controller;

import com.planet.api.entity.Planet;
import com.planet.api.model.PlanetRequest;
import com.planet.api.model.PlanetResponse;
import com.planet.api.model.PlanetResponseSWAPIPage;
import com.planet.api.model.PlanetsResponsePage;
import com.planet.api.service.PlanetService;
import com.planet.api.service.SWAPIService;
import com.planet.api.service.impl.SWAPIServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Log4j2
@AllArgsConstructor
public class PlanetController implements BaseController, PlanetApi{

    private PlanetService service;
    private ModelMapper modelMapper;
    private SWAPIService swapiService;

    @Override
    public ResponseEntity<PlanetResponse> addPlanet(@Valid @RequestBody PlanetRequest body) {
        log.info("Start method addPlanet");
        Planet planet = modelMapper.map(body,Planet.class);
        PlanetResponse planetResponse = modelMapper.map(service.save(planet),PlanetResponse.class);
        log.info("Finish method addPlanet");
        return new ResponseEntity<>(planetResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deletePlanet(String planetId) {
        log.info("Start method deletePlanet");
        service.delete(planetId);
        log.info("Finish method deletePlanet");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PlanetResponse> getPlanetById(String planetId) {
        log.info("Start method getPlanetById");
        PlanetResponse planetResponse = modelMapper.map(service.findById(planetId),PlanetResponse.class);
        log.info("Finish method getPlanetById");
        return new ResponseEntity<>(planetResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlanetResponse> findPlanetByName(@NotNull @Valid String name) {
        log.info("Start method findPlanetByName");
        PlanetResponse planetResponse = modelMapper.map(service.findOneByName(name),PlanetResponse.class);
        log.info("Finish method findPlanetByName");
        return new ResponseEntity<>(planetResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlanetsResponsePage> listAllPlanets(@Valid Integer page) {
        log.info("Start method listAllPlanets");
        PlanetsResponsePage planetsResponsePage = modelMapper.map(service.findAll(page),PlanetsResponsePage.class);
        log.info("Finish method listAllPlanets");
        return new ResponseEntity<>(planetsResponsePage,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlanetResponseSWAPIPage> listAllPlanetsSWAPI(@Valid Integer page) {
        log.info("Start method listAllPlanetsSWAPI");
        PlanetResponseSWAPIPage planetResponseSWAPIPage = modelMapper.map(swapiService.findAllPlanet(page),PlanetResponseSWAPIPage.class);
        log.info("Finish method listAllPlanetsSWAPI");
        return new ResponseEntity<>(planetResponseSWAPIPage,HttpStatus.OK);
    }
}
