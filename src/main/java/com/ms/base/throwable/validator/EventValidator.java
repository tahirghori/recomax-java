package com.ms.base.throwable.validator;


import com.ms.base.beam.Event;
import com.ms.base.beam.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component

public class EventValidator extends BaseValidator implements Validator {


    private javax.validation.Validator validator;


    /**
     * If you want to create new rule accourding to te below
     */
    // name;authId;dateOfBirth;phone;isoCode;type;
    // rewardCode;address;image;email;password;
    // referralCode;gender;userRoles;


    private HashMap<String, Map<String, String>> rules;

    public EventValidator() {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
//                "name", "required.name", "Field name is required.");
    }

    public BindingResult rulefilter(String[] rules, Event item, BindingResult errors) {

        Set<ConstraintViolation<Event>> violations = validator.validate(item);

        violations.stream().map(x -> {
            for (String rule : rules) {
                if (rule.equalsIgnoreCase(x.getPropertyPath().toString())) {
                    String propertyPath = x.getPropertyPath().toString();
                    String message = x.getMessage();
                    // Add JSR-303 errors to BindingResult
                    // This allows Spring to display them in view via a FieldError
                    errors.addError(new FieldError(item.getClass().toString(), propertyPath,

                            "Invalid " + propertyPath + "(" + message + ")"));
                }
            }
            return x;
        });
        return errors;
    }


    public BindingResult applyValidate(String action, Event item, BindingResult errors) {

        if (action.equalsIgnoreCase("userAdmin")) {
            String[] rules = {"name", "phone", "isoCode", "email", "password"};
            return this.rulefilter(rules, item, errors);
        } else if (action.equalsIgnoreCase("userAdmin")) {
            String[] rules = {"name", "email", "password"};
            return this.rulefilter(rules, item, errors);
        } else {
            return errors;
        }
    }
}