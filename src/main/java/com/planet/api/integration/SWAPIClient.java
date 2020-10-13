package com.planet.api.integration;

import com.planet.api.model.PlanetResponsePageSWAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SWAPIClient", url = "https://swapi.dev/api/", configuration = ApiErrorDecoder.class)
public interface SWAPIClient {

    @GetMapping(value = "/planets/", produces = "application/json")
    ResponseEntity<PlanetResponsePageSWAPI> findPlanetByName(@RequestParam("search") String search);

    @GetMapping(value = "/planets/", produces = "application/json")
    ResponseEntity<PlanetResponsePageSWAPI> findAllPlanet(@RequestParam("page") Integer page);
}
