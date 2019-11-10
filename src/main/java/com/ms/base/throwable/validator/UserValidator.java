package com.ms.base.throwable.validator;


import com.ms.base.beam.UserModel;
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
public class UserValidator<T> extends BaseValidator implements Validator {


    private javax.validation.Validator validator;


    /**
     * If you want to create new rule accourding to te below
     */
    // name;authId;dateOfBirth;phone;isoCode;type;
    // rewardCode;address;image;email;password;
    // referralCode;gender;userRoles;


    private HashMap<String, Map<String, String>> rules;

    public UserValidator() {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
//                "name", "required.name", "Field name is required.");
    }

    public BindingResult rulefilter(String[] rules, UserModel item, BindingResult errors) {

        Set<ConstraintViolation<UserModel>> violations = validator.validate(item);

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


    public BindingResult applyValidate(String action, UserModel item, BindingResult errors) {

        if (action.equalsIgnoreCase("userAdmin")) {
            String[] rules = {"name", "phone", "isoCode", "email", "password"};
            return this.rulefilter(rules, item, errors);
        } else if (action.equalsIgnoreCase("userAdmin")){
            String[] rules = {"name", "email", "password"};
            return this.rulefilter(rules, item, errors);
        }else {
            return errors;
        }
//
//        List<Object> params = Arrays.asList("k1", "v1", "k2", "v2");
//        Map<String, Object> map =
//                IntStream.range(0, params.size() / 2)
//                        .collect(HashMap::new,
//                                (m, i) -> m.put((String) params.get(i * 2), params.get(i * 2 + 1)),
//                                HashMap::putAll);

//         schemaFileJson = ValidationUtils.getSchemaNode(matchesJsonSchemaInClasspath("schemas/users_schema.json").using());
//
//        schemaFileJson.

//        Response response =  RestAssured.given().get("http://localhost:8080/endpoint")
//                .andReturn();
//
//        com.github.fge.jsonschema.main.JsonSchema schemaFileJson = ValidationUtils.getSchemaNode(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"JsonSchemas"+File.separator+"SampleSchema.json");
//        ObjectMapper mapper = new ObjectMapper();
//        com.fasterxml.jackson.databind.JsonNode responseBodyJsonObject = mapper.readTree(response.getBody().asString());


//        if(schemaFileJson.validate(responseBodyJsonObject).isSuccess()) {
//            System.out.println("Schema is valid");
//        }else {
//            System.out.println("Schema is not valid");
//        }

//
//        Set<ConstraintViolation<UserModel>> violations = validator.validate(item);
//
//        if (!this.rules.containsKey(rule)) {
//            return errors;
//        }
////                violations.stream().fi
//
//        Object dd = this.rules.get(rule).stream()
//                .forEach(one -> {
//                    violations.stream()
//                            .filter(two -> {
//                                return two.getPropertyPath().toString().equalsIgnoreCase(one);
//                            })
//                            .limit(1);
//                });
//
//        this.rules.get(rule).stream().filter((x) -> {
//
//            violations.stream().filter(y ->
//                    {
//                        if (y.getPropertyPath().toString().equalsIgnoreCase(x))
//                    }
//            )
//
//        })
//        for (ConstraintViolation<UserModel> violation : violations) {
//            String propertyPath = violation.getPropertyPath().toString();
//            String message = violation.getMessage();
//            // Add JSR-303 errors to BindingResult
//            // This allows Spring to display them in view via a FieldError
//            errors.addError(new FieldError(item.getClass().toString(), propertyPath,
//
//                    "Invalid " + propertyPath + "(" + message + ")"));
//        }
//
//        return errors;

    }
}