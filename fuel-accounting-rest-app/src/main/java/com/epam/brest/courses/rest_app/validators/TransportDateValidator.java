package com.epam.brest.courses.rest_app.validators;

import com.epam.brest.courses.model.Transport;
import com.epam.brest.courses.util.DateUtilites;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.courses.constants.TransportConstants.TRANSPORT_DATE;

@Component
public class TransportDateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Transport.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, TRANSPORT_DATE, "transportDate.empty");
        Transport transport = (Transport) target;
        try {
            String dateAsString = DateUtilites.getStringByDate(transport.getTransportDate());
        } catch (Exception ex){
            errors.rejectValue(TRANSPORT_DATE, "transportDate.datePattern");
        }

    }
}
