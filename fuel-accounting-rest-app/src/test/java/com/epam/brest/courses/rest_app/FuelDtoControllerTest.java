package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Fuel;
import com.epam.brest.courses.model.dto.FuelDto;
import com.epam.brest.courses.service.FuelDtoService;
import com.epam.brest.courses.service.FuelService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class FuelDtoControllerTest {
    @InjectMocks
    private FuelDtoController fuelDtoController;

    @Mock
    private FuelDtoService fuelDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(fuelDtoController)
                .build();
    }

    @AfterEach
    public void end() {
        Mockito.verifyNoMoreInteractions(fuelDtoService);
    }

    @Test
    public void shouldGetFuelsDtos() throws Exception {
        Mockito.when(fuelDtoService.findAllWithFuelSum()).thenReturn(Arrays.asList(createFuelDto(0), createFuelDto(1)));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel_dtos")
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

}
