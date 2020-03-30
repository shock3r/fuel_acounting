package com.epam.brest.courses.web_app.validators;

import com.epam.brest.courses.model.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.courses.constants.TransportConstants.*;

@Component
public class TransportNameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Transport.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, TRANSPORT_NAME, "transportName.empty");
        Transport transport = (Transport) target;
        if (StringUtils.hasLength(transport.getTransportName())
                && transport.getTransportName().length() > TRANSPORT_NAME_SIZE) {
            errors.rejectValue(TRANSPORT_NAME, "transportName.maxSize");
        }
    }
}
