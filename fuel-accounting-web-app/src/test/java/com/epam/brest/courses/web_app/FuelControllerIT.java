package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Fuel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.epam.brest.courses.constants.FuelConstants.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:app-context-test.xml"})
@Transactional
public class FuelControllerIT {
    public static final String FUELS_VIEW_NAME = "fuels";
    public static final String FUEL_VIEW_NAME = "fuel";
    public static final String FUELS_MODEL_ATRIBUTE = "fuels";
    public static final String FUEL_MODEL_ATRIBUTE = "fuel";
    public static final String FUEL_SESSION_ATRIBUTE = "fuel";
    public static final String TEST = "test";
    public static final String IS_NEW = "isNew";
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
                .andExpect(view().name(FUEL_VIEW_NAME))
                .andExpect(model().attribute(IS_NEW,is(false)))
                .andExpect(model().attribute(FUEL_MODEL_ATRIBUTE, hasProperty(FUEL_ID, is(1))))
                .andExpect(model().attribute(FUEL_MODEL_ATRIBUTE, hasProperty(FUEL_NAME, is("Gasoline"))));
    }

    @Test
    public void shoudReturnToFuelsPageIfFuelIsNotFoundById() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel/9999999")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/fuels"));
    }

    @Test
    public void shouldUpdateFuelAfterEdit() throws Exception{
        Fuel fuel = new Fuel()
                .setFuelId(1)
                .setFuelName(TEST);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/fuel/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(FUEL_ID, String.valueOf(fuel.getFuelId()))
                        .param(FUEL_NAME, fuel.getFuelName())
                .sessionAttr(FUEL_SESSION_ATRIBUTE, fuel)
        ).andExpect(status().isFound())
            .andExpect(view().name("redirect:/fuels"))
            .andExpect(redirectedUrl("/fuels"));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name(FUEL_VIEW_NAME))
                .andExpect(model().attribute(FUEL_MODEL_ATRIBUTE, hasProperty(FUEL_ID, is(1))))
                .andExpect(model().attribute(FUEL_MODEL_ATRIBUTE, hasProperty(FUEL_NAME, is(TEST))));
    }

    @Test
    public void shoudRejectUpdateFuelOnLargeFuelName() throws Exception{
        Fuel fuel = new Fuel()
                .setFuelId(1)
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE+1));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/fuel/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(FUEL_ID, String.valueOf(fuel.getFuelId()))
                .param(FUEL_NAME, fuel.getFuelName())
                .sessionAttr(FUEL_SESSION_ATRIBUTE, fuel)
        ).andExpect(status().isOk())
                .andExpect(view().name("fuel"));
    }

    @Test
    public void shouldOpenNewFuelPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name(FUEL_VIEW_NAME))
                .andExpect(model().attribute(IS_NEW, is(true)))
                .andExpect(model().attribute(FUEL_MODEL_ATRIBUTE, isA(Fuel.class)));
    }

    @Test
    public void shouldAddNewFuel() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/fuel")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(FUEL_NAME, TEST)
        ).andExpect(status().isFound())
        .andExpect(view().name("redirect:/fuels"))
        .andExpect(redirectedUrl("/fuels"));
    }

    @Test
    public void shouldRejectAddNewFuelOnLargeFuelName() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/fuel")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(FUEL_NAME, RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE+1))
        ).andExpect(status().isOk())
                .andExpect(view().name("fuel"));
    }

    @Test
    public void shouldDeleteFuel() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/fuel/3/delete")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(view().name("redirect:/fuels"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/fuels"));

    }
}
