package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Transport;
import com.epam.brest.courses.service.TransportService;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TransportControllerTest {
    @InjectMocks
    private TransportController transportController;

    @Mock
    private TransportService transportService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(transportController)
                .build();
    }

    @AfterEach
    public void end() {
        Mockito.verifyNoMoreInteractions(transportService);
    }

    @Test
    public void shouldNotGetTransportById() throws Exception{
        Mockito.when(transportService.findById(99)).thenReturn(createEmptyTransport());
        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/transports/99")).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
        });
        String expectedMessage = "Transport not found for id";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Create mock Optional<Transport> with data by index.
     * @return Optional<Transport>.
     */
    private Optional<Transport> createEmptyTransport() {
        List<Transport> fuels = new ArrayList<Transport>();
        return Optional.ofNullable(DataAccessUtils.uniqueResult(fuels));
    }
}
