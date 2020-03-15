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
public class FuelControllerMockTest {
    @InjectMocks
    private FuelController fuelController;
    private MockMvc mockMVC;

    @BeforeEach
    private void setup(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        mockMVC = MockMvcBuilders.standaloneSetup(fuelController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldGetfuelsView() throws Exception {
        mockMVC.perform(MockMvcRequestBuilders.get("/fuels")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/fuels.html"));
    }

    @Test
    public void shouldGetfuelView() throws Exception {
        mockMVC.perform(MockMvcRequestBuilders.get("/fuel")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/fuel.html"));
    }

}
