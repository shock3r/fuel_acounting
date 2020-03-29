package com.epam.brest.courses.web_app.validators;

import com.epam.brest.courses.model.Transport;
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

        Transport transport = (Transport) target;

        ValidationUtils.rejectIfEmpty(errors, TRANSPORT_NAME, "transportName.empty");
//        ValidationUtils.rejectIfEmpty(errors, TRANSPORT_TANK_CAPASITY, "transportTankCapasity.empty");
//        ValidationUtils.rejectIfEmpty(errors, TRANSPORT_DATE, "transportDate.empty");

        if (StringUtils.hasLength(transport.getTransportName())
                && transport.getTransportName().length() > TRANSPORT_NAME_SIZE) {
            errors.rejectValue(TRANSPORT_NAME, "transportName.maxSize");
        }

//        if (transport.getTransportTankCapasity() < 0
//                && transport.getTransportTankCapasity() > TRANSPORT_TANK_CAPASITY_SIZE
//                && transport.getTransportTankCapasity() == null) {
//            errors.rejectValue(TRANSPORT_TANK_CAPASITY, "transportTankCapasity.maxSize");
//        }

    }
}
