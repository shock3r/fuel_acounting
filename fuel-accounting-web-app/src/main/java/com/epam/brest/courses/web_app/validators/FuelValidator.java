package com.epam.brest.courses.web_app.validators;

import com.epam.brest.courses.model.Fuel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.courses.constants.FuelConstants.FUEL_NAME;
import static com.epam.brest.courses.constants.FuelConstants.FUEL_NAME_SIZE;

@Component
public class FuelValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Fuel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, FUEL_NAME, "fuelName.empty");
        Fuel fuel = (Fuel) target;
        if (StringUtils.hasLength(fuel.getFuelName()) && fuel.getFuelName().length() > FUEL_NAME_SIZE) {
            errors.rejectValue(FUEL_NAME, "fuelName.maxSize");
        }
    }
}
