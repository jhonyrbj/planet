package com.planet.api.util;

import com.planet.api.entity.Planet;
import com.planet.api.model.PlanetRequest;
import com.planet.api.model.PlanetResponsePageSWAPI;
import com.planet.api.model.PlanetResponseSWAPI;

import java.util.ArrayList;
import java.util.Arrays;

public final class MockBuilder {

    private MockBuilder() {
    }

    public static PlanetRequest createPlanetRequest(){
        return new PlanetRequest()
                .name("Geonosis")
                .climate("temperate, arid")
                .terrain("rock, desert, mountain, barren");
    }

    public static Planet createPlanet(){
        Planet planet = new Planet();
        planet.setId("74d66be2-77a3-4ca6-b8aa-fc9e00d694b3");
        planet.setName("Geonosis");
        planet.setClimate("temperate, arid");
        planet.setTerrain("rock, desert, mountain, barren");
        planet.setQttMovies(1);
        return planet;
    }

    public static Planet createPlanetToMapper(){
        Planet planet = new Planet();
        planet.setName("Geonosis");
        planet.setClimate("temperate, arid");
        planet.setTerrain("rock, desert, mountain, barren");
        return planet;
    }

    public static PlanetResponseSWAPI createPlanetResponseSWAPI(){
        return new PlanetResponseSWAPI()
                .name("Geonosis")
                .climate("temperate, arid")
                .terrain("rock, desert, mountain, barren")
                .films(new ArrayList<>(Arrays.asList("url")));
    }

    public static PlanetResponsePageSWAPI createPlanetResponsePageSWAPI(){
        PlanetResponsePageSWAPI planetResponsePageSWAPI = new PlanetResponsePageSWAPI(
                Arrays.asList(createPlanetResponseSWAPI()),1
        );
        return planetResponsePageSWAPI;
    }
}
