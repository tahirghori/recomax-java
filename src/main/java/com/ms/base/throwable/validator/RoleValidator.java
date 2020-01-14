package com.ms.base.throwable.validator;


import com.ms.base.beam.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component

public class RoleValidator extends BaseValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
//                "name", "required.name", "Field name is required.");
    }
}