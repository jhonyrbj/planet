package com.planet.api.service;

import com.planet.api.entity.Planet;
import com.planet.api.exception.NotFoundException;
import com.planet.api.repository.PlanetRepository;
import com.planet.api.service.impl.PlanetServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static com.planet.api.constant.MessageErrorPlanetApi.PLANET_API_PLANETS_NOT_FOUND_CODE;
import static com.planet.api.constant.MessageErrorPlanetApi.PLANET_API_PLANET_NOT_FOUND_CODE;
import static com.planet.api.constant.Pagination.PAGE_SIZE;
import static com.planet.api.model.Error.singleError;
import static com.planet.api.util.MockBuilder.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PlanetServiceImplTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private PlanetServiceImpl planetService;

    @MockBean
    private PlanetRepository repository;

    @MockBean
    private SWAPIService swapiService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveSuccessfully() {
        Planet planet = createPlanetToMapper();
        when(swapiService.findPlanetByName(anyString())).thenReturn(createPlanetResponseSWAPI());
        when(repository.saveAndFlush(any(Planet.class))).thenReturn(createPlanet());
        Assert.assertNotNull(planetService.save(planet));
    }

    @Test
    public void shouldDeleteSuccessfully() {
        doNothing().when(repository).deleteById(anyString());
        planetService.delete("1");
        verify(repository).deleteById(anyString());
    }

    @Test
    public void shouldFindOneByNameSuccessfully() {
        when(repository.findOneByName(anyString())).thenReturn(Optional.of(createPlanet()));
        Assert.assertNotNull(planetService.findOneByName("planet"));
    }

    @Test
    public void shouldFindOneByNameNotFoundException() {
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(PLANET_API_PLANET_NOT_FOUND_CODE));
        when(repository.findOneByName(anyString())).thenReturn(Optional.empty());
        planetService.findOneByName("planet");
    }

    @Test
    public void shouldFindByIdSuccessfully() {
        when(repository.findById(anyString())).thenReturn(Optional.of(createPlanet()));
        Assert.assertNotNull(planetService.findById("id"));
    }

    @Test
    public void shouldFindByIdNotFoundException() {
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(PLANET_API_PLANET_NOT_FOUND_CODE));
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        planetService.findById("id");
    }

    @Test
    public void shouldFindAllSuccessfully() {
        PageRequest pageReq
                = PageRequest.of(1, PAGE_SIZE);
        when(repository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(createPlanet()),pageReq, 60));
        Assert.assertNotNull(planetService.findAll(1));
    }

    @Test
    public void shouldFindAllNotFoundException() {
        exception.expect(NotFoundException.class);
        exception.expectMessage(singleError(PLANET_API_PLANETS_NOT_FOUND_CODE));
        PageRequest pageReq
                = PageRequest.of(1, PAGE_SIZE);
        when(repository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<Planet>(new ArrayList<>(),pageReq, 0));
        planetService.findAll(1);
    }
}
