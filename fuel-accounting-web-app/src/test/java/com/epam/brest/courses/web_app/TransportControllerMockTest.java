package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Transport;
import com.epam.brest.courses.service.TransportService;
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
import static com.epam.brest.courses.constants.TransportConstants.*;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class TransportControllerMockTest {
    @InjectMocks
    private TransportController transportController;
    @Mock
    private TransportService transportService;
    private MockMvc mockMvc;

    @BeforeEach
    private void setup(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(transportController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @AfterEach
    private void end() {
        Mockito.verifyNoMoreInteractions(transportService);
    }

    @Test
    public void shouldGetTransports() throws Exception {
        Mockito.when(transportService.findAll())
                .thenReturn(Arrays.asList(
                        createTransport(0),
                        createTransport(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/transports")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/transports.html"))
                .andExpect(MockMvcResultMatchers.view().name("transports"))
                .andExpect(MockMvcResultMatchers.model().attribute("transports", org.hamcrest.Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty(TRANSPORT_ID, Matchers.is(0)),
                                Matchers.hasProperty(TRANSPORT_NAME, Matchers.is("name0")),
                                Matchers.hasProperty(TRANSPORT_DATE, Matchers.is(getDateByString("0" + 1 + "/01/2020"))),
                                Matchers.hasProperty(TRANSPORT_TANK_CAPASITY, Matchers.is(100.d))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("transports", org.hamcrest.Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty(TRANSPORT_ID, Matchers.is(1)),
                                Matchers.hasProperty(TRANSPORT_NAME, Matchers.is("name1")),
                                Matchers.hasProperty(TRANSPORT_DATE, Matchers.is(getDateByString("0" + 2 + "/01/2020"))),
                                Matchers.hasProperty(TRANSPORT_TANK_CAPASITY, Matchers.is(101.d))
                        )
                )));
    }

    private Transport createTransport(int index){
        if (index > 31) {
            return null;
        }
        Transport transport = new Transport()
                .setTransportId(index)
                .setFuelId(index)
                .setTransportName("name" + index)
                .setTransportDate(getDateByString("0" + (index + 1) + "/01/2020"))
                .setTransportTankCapasity(100.d + index);
        return transport;
    }

    private Date getDateByString(String dateAsString) {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateformat.parse(dateAsString);
        } catch (Exception ex) {
            return null;
        }
    }
}
