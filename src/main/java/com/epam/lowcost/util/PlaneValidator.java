package com.epam.lowcost.util;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.services.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PlaneValidator implements Validator{
    private final PlaneService planeService;

    @Autowired
    public PlaneValidator(PlaneService planeService) {
        this.planeService = planeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Plane.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Plane plane = (Plane) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessPlacesNumber", "NotEmpty");
        if (plane.getBusinessPlacesNumber() < 0) {
            errors.rejectValue("businessPlacesNumber", "Size.planeForm.Number");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "economPlacesNumber", "NotEmpty");
        if (plane.getEconomPlacesNumber() < 0) {
            errors.rejectValue("economPlacesNumber", "Size.planeForm.Number");
        }
    }
}
