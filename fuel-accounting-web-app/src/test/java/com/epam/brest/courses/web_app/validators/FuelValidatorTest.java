package com.epam.brest.courses.web_app.validators;

import com.epam.brest.courses.model.Fuel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static com.epam.brest.courses.constants.FuelConstants.FUEL_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FuelValidatorTest {

    private Fuel fuel;
    private FuelValidator fuelValidator = new FuelValidator();
    private BindingResult result;

    @BeforeEach
    private void setup() {
        fuel = Mockito.mock(Fuel.class);
        result = new BeanPropertyBindingResult(fuel, "fuel");
    }

    @Test
    public void shouldRejectNullFuelName() {
        // given
        Mockito.when(fuel.getFuelName()).thenReturn(null);

        // when
        fuelValidator.validate(fuel, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    public void shouldRejectEmptyFuelName() {
        // given
        Mockito.when(fuel.getFuelName()).thenReturn("");

        // when
        fuelValidator.validate(fuel, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    public void shouldRejectLargeFuelName() {
        // given
        String filled = StringUtils.repeat("*", FUEL_NAME_SIZE + 1);
        Mockito.when(fuel.getFuelName())
                .thenReturn(filled);

        // when
        fuelValidator.validate(fuel,
                result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    public void shoudValidateFuelName() {
        // given
        String filed = StringUtils.repeat("*", FUEL_NAME_SIZE);
        Mockito.when(fuel.getFuelName()).thenReturn(filed);

        // when
        fuelValidator.validate(fuel,
                result);

        //then
        assertFalse(result.hasErrors());
    }


}
