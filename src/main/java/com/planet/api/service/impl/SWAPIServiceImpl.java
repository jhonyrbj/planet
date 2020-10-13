package com.planet.api.service.impl;

import com.planet.api.exception.NotFoundException;
import com.planet.api.integration.SWAPIClient;
import com.planet.api.model.PlanetResponsePageSWAPI;
import com.planet.api.model.PlanetResponseSWAPI;
import com.planet.api.service.SWAPIService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.planet.api.constant.MessageErrorSWAPI.SWAPI_PLANETS_NOT_FOUND_CODE;
import static com.planet.api.constant.MessageErrorSWAPI.SWAPI_PLANET_NOT_FOUND_CODE;
import static com.planet.api.constant.Pagination.PAGE_SIZE;
import static com.planet.api.model.Error.singleError;

@Service
@Slf4j
@AllArgsConstructor
public class SWAPIServiceImpl implements SWAPIService {

    private SWAPIClient swapiClient;

    @Override
    public PlanetResponseSWAPI findPlanetByName(String name){
        ResponseEntity<PlanetResponsePageSWAPI> responseEntity = swapiClient.findPlanetByName(name);
        if (responseEntity == null){
            throw new NotFoundException(singleError(SWAPI_PLANET_NOT_FOUND_CODE));
        }
        PlanetResponsePageSWAPI planetResponsePageSWAPI = responseEntity.getBody();
        if (planetResponsePageSWAPI == null ||
                planetResponsePageSWAPI.getResults() == null ||
                planetResponsePageSWAPI.getResults().isEmpty()){
            throw new NotFoundException(singleError(SWAPI_PLANET_NOT_FOUND_CODE));
        }
        return planetResponsePageSWAPI.getResults().get(0);
    }

    @Override
    public Page<PlanetResponseSWAPI> findAllPlanet(Integer page){
        ResponseEntity<PlanetResponsePageSWAPI> responseEntity =
                swapiClient.findAllPlanet(page == null || page < 1 ? 1: page);
        if (responseEntity == null){
            throw new NotFoundException(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        }
        PlanetResponsePageSWAPI planetResponsePageSWAPI = responseEntity.getBody();
        if (planetResponsePageSWAPI == null ||
                planetResponsePageSWAPI.getResults() == null ||
                planetResponsePageSWAPI.getResults().isEmpty()){
            throw new NotFoundException(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        }
        PageRequest pageReq
                = PageRequest.of(page == null || page < 1 ? 0: page - 1, PAGE_SIZE);
        return new PageImpl<>(planetResponsePageSWAPI.getResults(),pageReq, planetResponsePageSWAPI.getCount());
    }
}
