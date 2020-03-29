package com.epam.brest.courses.web_app.validators;

import com.epam.brest.courses.model.Transport;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.courses.constants.TransportConstants.*;

@Component
public class TransportDateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Transport.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Transport transport = (Transport) target;
        ValidationUtils.rejectIfEmpty(errors, TRANSPORT_DATE, "transportDate.empty");

    }
}
