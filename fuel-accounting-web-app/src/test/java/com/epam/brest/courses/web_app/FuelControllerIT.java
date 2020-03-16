package com.epam.brest.courses.web_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static com.epam.brest.courses.constants.FuelConstants.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:app-context-test.xml"})
public class FuelControllerIT {
    public static final String FUELS_VIEW_NAME = "fuels";
    public static final String FUELS_MODEL_ATRIBUTE = "fuels";
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    public void shouldReturnFuelsPage() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuels")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name(FUELS_VIEW_NAME))
                .andExpect(model().attribute(FUELS_MODEL_ATRIBUTE, hasItem(
                        allOf(
                                hasProperty(FUEL_ID, is(1)),
                                hasProperty(FUEL_NAME, is("Gasoline")),
                                hasProperty(FUEL_SUM_FUEL, is(45.d))
                        )
                )))
                .andExpect(model().attribute(FUELS_MODEL_ATRIBUTE, hasItem(
                        allOf(
                                hasProperty(FUEL_ID, is(2)),
                                hasProperty(FUEL_NAME, is("Disel")),
                                hasProperty(FUEL_SUM_FUEL, is(114.d))
                        )
                )));
    }

    @Test
    public void shoudOpenEditFuelPageById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("fuel"))
                .andExpect(model().attribute("fuel", hasProperty("fuelId", is(1))))
                .andExpect(model().attribute("fuel", hasProperty("fuelName", is("Gasoline"))));
    }

    @Test
    public void shoudReturnToFuelsPageIfFuelIsNotFoundById() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel/9999999")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("fuels"));
    }
}
