package com.planet.api.controller;

import com.google.gson.Gson;
import com.planet.api.entity.Planet;
import com.planet.api.model.PlanetRequest;
import com.planet.api.service.impl.PlanetServiceImpl;
import com.planet.api.service.impl.SWAPIServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.planet.api.util.MockBuilder.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class PlanetControllerTest {

    private static final String BASE_PATH = "/planet-api/v1";
    private static final String PLANET_ENDPOINT = "/planet";
    private static final String FIND_BY_NAME_ENDPOINT = "/planet/find-by-name";
    private static final String PLANET_SWAPI_ENDPOINT = "/planet/list-planets-swapi";

    @InjectMocks
    private PlanetController planetController;

    @MockBean
    private PlanetServiceImpl planetService;

    @MockBean
    private SWAPIServiceImpl swapiService;

    @MockBean
    private ModelMapper modelMapper;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(planetController).build();
    }

    @Test
    public void shouldAddPlanetSuccessfully() throws Exception {
        PlanetRequest planetRequest = createPlanetRequest();
        when(planetService.save(any(Planet.class))).thenReturn(createPlanet());
        when(modelMapper.map(any(PlanetRequest.class), Matchers.<Class<Planet>>any())).thenReturn(createPlanetToMapper());
        MockHttpServletResponse response = mockMvc
                .perform(post(BASE_PATH + PLANET_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(planetRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void shouldDeletePlanetSuccessfully() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(delete(BASE_PATH + PLANET_ENDPOINT + "/" + createPlanet().getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse();
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void shouldGetPlanetByIdSuccessfully() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get(BASE_PATH + PLANET_ENDPOINT + "/" + createPlanet().getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void shouldFindPlanetByNameSuccessfully() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get(BASE_PATH + FIND_BY_NAME_ENDPOINT)
                        .param("name","Geonosis"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void shouldListAllPlanetsSuccessfully() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get(BASE_PATH + PLANET_ENDPOINT ))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void shouldListAllPlanetsSWAPISuccessfully() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get(BASE_PATH + PLANET_SWAPI_ENDPOINT ))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        assertNotNull(response.getContentAsString());
    }


}
