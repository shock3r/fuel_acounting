package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Fuel;
import com.epam.brest.courses.rest_app.exception.CustomExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.constants.FuelConstants.FUEL_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class FuelControllerIT {

    private static Logger LOGGER = LoggerFactory.getLogger(FuelControllerIT.class);

    public static final String FUELS_ENDPOINT = "/fuels";

    @Autowired
    private FuelController fuelController;
    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    MockMvcFuelService fuelService = new MockMvcFuelService();

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(fuelController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void shouldFindAllFuels() throws Exception {
        List<Fuel> fuels = fuelService.findAll();
        assertNotNull(fuels);
        assertTrue(fuels.size() > 0);
    }

    @Test
    public void shouldCreateFuel() throws Exception {
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);
        assertNotNull(id);
    }

    @Test
    public void shouldfindFuelById() throws Exception {
        // given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);
        assertNotNull(id);

        // when
        Optional<Fuel> fuelOptional = fuelService.findById(id);

        // then
        assertTrue(fuelOptional.isPresent());
        assertEquals(fuelOptional.get().getFuelId(), id);
        assertEquals(fuelOptional.get().getFuelName(), fuel.getFuelName());
    }

    @Test
    public void shouldUpdateFuel() throws Exception {
        // given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);
        assertNotNull(id);

        Optional<Fuel> fuelOptional = fuelService.findById(id);
        assertTrue(fuelOptional.isPresent());

        fuelOptional.get()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));

        // when
        int result = fuelService.update(fuelOptional.get());

        // then
        assertTrue(1 == result);
        Optional<Fuel> updatedFuelOptional = fuelService.findById(id);
        assertTrue(updatedFuelOptional.isPresent());
        assertEquals(updatedFuelOptional.get().getFuelId(), id);
        assertEquals(updatedFuelOptional.get().getFuelName(), fuelOptional.get().getFuelName());
    }

    @Test
    public void shouldDeleteFuel() throws Exception {
        //given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);
        assertNotNull(id);

        List<Fuel> fuels = fuelService.findAll();
        assertNotNull(fuels);

        // when
        int result = fuelService.delete(id);

        //then
        assertTrue(1 == result);

        List<Fuel> currentFuels = fuelService.findAll();
        assertNotNull(currentFuels);

        assertTrue(fuels.size() - 1 == currentFuels.size());
    }

    class MockMvcFuelService {

        public List<Fuel> findAll() throws Exception {
            LOGGER.debug("findAll()");
            MockHttpServletResponse response = mockMvc.perform(get(FUELS_ENDPOINT)
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Fuel>>() {});
        }

        public Optional<Fuel> findById(Integer fuelId) throws Exception {
            LOGGER.debug("findById({})", fuelId);
            MockHttpServletResponse response = mockMvc.perform(get(FUELS_ENDPOINT + "/" + fuelId)
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Fuel.class));
        }

        public Integer create(Fuel fuel) throws Exception {
            LOGGER.debug("create({})", fuel);
            String json = objectMapper.writeValueAsString(fuel);
            MockHttpServletResponse response =
                    mockMvc.perform(post(FUELS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        public int update(Fuel fuel) throws Exception {
            LOGGER.debug("update({})", fuel);
            String updateFuelJson = objectMapper.writeValueAsString(fuel);
            MockHttpServletResponse response =
                    mockMvc.perform(put(FUELS_ENDPOINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updateFuelJson)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        public int delete(Integer fuelId) throws Exception {
            LOGGER.debug("Delete({})", fuelId);
            MockHttpServletResponse response =
                   mockMvc.perform(MockMvcRequestBuilders.delete(FUELS_ENDPOINT + "/" + fuelId)
                   .accept(MediaType.APPLICATION_JSON)
                   ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }

}
