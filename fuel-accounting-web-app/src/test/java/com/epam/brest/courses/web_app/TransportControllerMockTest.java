package com.epam.brest.courses.web_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ExtendWith(MockitoExtension.class)
public class TransportControllerMockTest {
    @InjectMocks
    private TransportController transportController;
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

    @Test
    public void shouldGetTransports() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transports")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/transports.html"));
    }

    @Test
    public void shouldGetTransport() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transport")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/transport.html"));
    }

}
