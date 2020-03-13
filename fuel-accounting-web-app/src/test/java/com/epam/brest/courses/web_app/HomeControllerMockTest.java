package com.epam.brest.courses.web_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ExtendWith(MockitoExtension.class)
public class HomeControllerMockTest {
    private HomeController homeController = new HomeController();
    private MockMvc mockMVC;

    @BeforeEach
    private void setup(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        mockMVC = MockMvcBuilders.standaloneSetup(homeController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldGetHelloWord() throws Exception {
        mockMVC.perform(MockMvcRequestBuilders.get("/")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

}
