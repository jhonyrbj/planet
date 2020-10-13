package com.planet.api.service;

import com.planet.api.exception.NotFoundException;
import com.planet.api.integration.SWAPIClient;
import com.planet.api.model.PlanetResponsePageSWAPI;
import com.planet.api.model.PlanetResponseSWAPI;
import com.planet.api.service.impl.SWAPIServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.planet.api.constant.MessageErrorSWAPI.SWAPI_PLANETS_NOT_FOUND_CODE;
import static com.planet.api.constant.MessageErrorSWAPI.SWAPI_PLANET_NOT_FOUND_CODE;
import static com.planet.api.constant.Pagination.PAGE_SIZE;
import static com.planet.api.model.Error.singleError;
import static com.planet.api.util.MockBuilder.createPlanetResponsePageSWAPI;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SWAPIServiceImplTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private SWAPIServiceImpl service;

    @MockBean
    private SWAPIClient swapiClient;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindPlanetByNameSuccessfully(){
        when(swapiClient.findPlanetByName(anyString())).thenReturn(ResponseEntity.ok(createPlanetResponsePageSWAPI()));
        Assert.assertNotNull(service.findPlanetByName("name"));
    }

    @Test
    public void shouldFindPlanetByNameNotFoundExceptionWithResponseEntityIsNull(){
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(SWAPI_PLANET_NOT_FOUND_CODE));
        when(swapiClient.findPlanetByName(anyString())).thenReturn(null);
        service.findPlanetByName("name");
    }

    @Test
    public void shouldFindPlanetByNameNotFoundExceptionWithResponseBodyIsNull(){
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(SWAPI_PLANET_NOT_FOUND_CODE));
        when(swapiClient.findPlanetByName(anyString())).thenReturn(ResponseEntity.ok(null));
        service.findPlanetByName("name");
    }

    @Test
    public void shouldFindPlanetByNameNotFoundExceptionWithResultsIsEmpty(){
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(SWAPI_PLANET_NOT_FOUND_CODE));
        when(swapiClient.findPlanetByName(anyString()))
                .thenReturn(ResponseEntity.ok(new PlanetResponsePageSWAPI(List.of(),0)));
        service.findPlanetByName("name");
    }



    public Page<PlanetResponseSWAPI> findAllPlanet(Integer page){
        ResponseEntity<PlanetResponsePageSWAPI> responseEntity =
                swapiClient.findAllPlanet(page == null || page < 1 ? 1: page);
        if (responseEntity == null){
            throw new NotFoundException(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        }
        PlanetResponsePageSWAPI planetResponsePageSWAPI = responseEntity.getBody();
        if (planetResponsePageSWAPI == null){
            throw new NotFoundException(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        }
        PageRequest pageReq
                = PageRequest.of(page == null || page < 1 ? 0: page - 1, PAGE_SIZE);
        return new PageImpl<>(planetResponsePageSWAPI.getResults(),pageReq, planetResponsePageSWAPI.getCount());
    }

    @Test
    public void shouldFindAllPlanetSuccessfully(){
        when(swapiClient.findAllPlanet(anyInt())).thenReturn(ResponseEntity.ok(createPlanetResponsePageSWAPI()));
        Assert.assertNotNull(service.findAllPlanet(1));
    }

    @Test
    public void shouldFindAllPlanetNotFoundExceptionWithResponseEntityIsNull(){
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        when(swapiClient.findAllPlanet(anyInt())).thenReturn(null);
        service.findAllPlanet(1);
    }

    @Test
    public void shouldFindAllPlanetNotFoundExceptionWithResponseBodyIsNull(){
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        when(swapiClient.findAllPlanet(anyInt())).thenReturn(ResponseEntity.ok(null));
        service.findAllPlanet(1);
    }

    @Test
    public void shouldFindAllPlanetNotFoundExceptionWithResultsIsEmpty(){
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        when(swapiClient.findAllPlanet(anyInt()))
                .thenReturn(ResponseEntity.ok(new PlanetResponsePageSWAPI(List.of(),0)));
        service.findAllPlanet(1);
    }
}
