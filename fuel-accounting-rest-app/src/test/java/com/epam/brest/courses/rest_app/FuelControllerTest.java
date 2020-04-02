package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Fuel;
import com.epam.brest.courses.model.dto.FuelDto;
import com.epam.brest.courses.rest_app.exception.FuelNotFoundException;
import com.epam.brest.courses.service.FuelDtoService;
import com.epam.brest.courses.service.FuelService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class FuelControllerTest {
    @InjectMocks
    private FuelController fuelController;

    @Mock
    private FuelDtoService fuelDtoService;

    @Mock
    private FuelService fuelService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(fuelController)
                .build();
    }

    @AfterEach
    public void end() {
        Mockito.verifyNoMoreInteractions(fuelDtoService, fuelService);
    }

    @Test
    public void shouldGetFuels() throws Exception {
        Mockito.when(fuelDtoService.findAllWithFuelSum()).thenReturn(Arrays.asList(createFuelDto(0), createFuelDto(1)));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuels")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fuelId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fuelName", Matchers.is("test0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sumFuel", Matchers.is(100.d)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fuelId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fuelName", Matchers.is("test1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sumFuel", Matchers.is(101.d)));
        Mockito.verify(fuelDtoService).findAllWithFuelSum();
    }

    @Test
    public void shouldGetFuelById() throws Exception{
        Mockito.when(fuelService.findById(1)).thenReturn(createFuel(1));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("fuelId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("fuelName", Matchers.is("test1")));
        Mockito.verify(fuelService).findById(1);
    }

    @Test
    public void shouldNotGetFuelById() throws Exception{
        Mockito.when(fuelService.findById(99)).thenReturn(createEmptyFuel());
        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/fuel/99")).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
        });
        String expectedMessage = "Fuel is not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Create mock FuelDto with data by index.
     * @param index int.
     * @return fuelDto.
     */
    private FuelDto createFuelDto(int index) {
        FuelDto fuelDto = new FuelDto()
                .setFuelId(index)
                .setFuelName("test"+index)
                .setSumFuel((double) (100+index));
        return fuelDto;
    }

    /**
     * Create mock Optional<Fuel> with data by index.
     * @param index int.
     * @return Optional<Fuel>.
     */
    private Optional<Fuel> createFuel(int index) {
        Fuel fuel = new Fuel()
                .setFuelId(index)
                .setFuelName("test"+index);
        List<Fuel> fuels = new ArrayList<Fuel>();
        fuels.add(fuel);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(fuels));
    }

    /**
     * Create mock Optional<Fuel> with data by index.
     * @return Optional<Fuel>.
     */
    private Optional<Fuel> createEmptyFuel() {
        List<Fuel> fuels = new ArrayList<Fuel>();
        return Optional.ofNullable(DataAccessUtils.uniqueResult(fuels));
    }
}
