package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.dto.FuelDto;
import com.epam.brest.courses.service.FuelDtoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static com.epam.brest.courses.constants.FuelConstants.*;
import static com.epam.brest.courses.constants.FuelConstants.FUEL_SUM_FUEL;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class FuelControllerMockTest {
    public static final String FIRST_FUEL_NAME = "name0";
    public static final String SECOND_FUEL_NAME = "name1";
    @InjectMocks
    private FuelController fuelController;
    @Mock
    FuelDtoService fuelDtoService;

    private MockMvc mockMvc;


    @BeforeEach
    private void setup(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(fuelController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @AfterEach
    public void end(){
        Mockito.verifyNoMoreInteractions(fuelDtoService);
    }

    @Test
    public void shouldGetfuelsView() throws Exception {
        Mockito.when(fuelDtoService.findAllWithFuelSum()).thenReturn(Arrays.asList(createFuelDto(0), createFuelDto(1)));
        mockMvc.perform(MockMvcRequestBuilders.get("/fuels")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/fuels.html"))
                .andExpect(view().name("fuels"))
                .andExpect(model().attribute("fuels", hasItem(
                        allOf(
                                hasProperty(FUEL_ID, is(0)),
                                hasProperty(FUEL_NAME, is(FIRST_FUEL_NAME)),
                                hasProperty(FUEL_SUM_FUEL, is(100.d))
                        )
                )))
                .andExpect(model().attribute("fuels", hasItem(
                        allOf(
                                hasProperty(FUEL_ID, is(1)),
                                hasProperty(FUEL_NAME, is(SECOND_FUEL_NAME)),
                                hasProperty(FUEL_SUM_FUEL, is(101.d))
                        )
                )));
    }

    /**
     * Create test Fuel DTO Object.
     * @param index index.
     * @return Fuel DTO.
     */
    private FuelDto createFuelDto(int index) {
        FuelDto fuelDto = new FuelDto();
        fuelDto.setFuelId(index);
        fuelDto.setFuelName("name" + index);
        fuelDto.setSumFuel(Double.valueOf(100 + index));
        return fuelDto;
    }

    @Test
    public void shouldGetfuelView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fuel")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/fuel.html"))
        ;
    }

}
