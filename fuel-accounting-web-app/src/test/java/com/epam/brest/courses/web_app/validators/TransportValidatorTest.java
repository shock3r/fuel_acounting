package com.epam.brest.courses.web_app.validators;

import com.epam.brest.courses.model.Transport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static com.epam.brest.courses.constants.TransportConstants.TRANSPORT_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransportValidatorTest {
    private Transport transport;
    private TransportValidator transportValidator;
    private BindingResult result;

    @BeforeEach
    private  void setup(){
        transport = Mockito.mock(Transport.class);
        result = new BeanPropertyBindingResult(transport, "transport");
        transportValidator = new TransportValidator();
    }

    @Test
    public void shouldRejectNullTransportName(){
        // given
        Mockito.when(transport.getTransportName()).thenReturn(null);

        // when
        transportValidator.validate(transport, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    public void shouldRejectLargeTransportName(){
        // given
        Mockito.when(transport.getTransportName())
                .thenReturn(StringUtils.repeat("*", TRANSPORT_NAME_SIZE+1));

        // when
       transportValidator.validate(transport, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    public void shouldValidateTransportName(){
        // given
        Mockito.when(transport.getTransportName())
                .thenReturn(StringUtils.repeat("*", TRANSPORT_NAME_SIZE));

        // when
        transportValidator.validate(transport, result);

        // then
        assertFalse(result.hasErrors());
    }
}
