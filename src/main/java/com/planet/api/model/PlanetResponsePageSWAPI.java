package com.planet.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Getter
@Setter
public class PlanetResponsePageSWAPI {

    private List<PlanetResponseSWAPI> results;
    private Integer count;
}
