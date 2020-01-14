package com.ms.base.controller.DB;

import com.fasterxml.jackson.databind.JsonNode;
import com.ms.base.beam.User;
import com.ms.base.throwable.validator.UserValidator;
import com.ms.base.workspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ms.base.controller.route.USER_CONTROLLER;

@RestController
@RequestMapping(value = USER_CONTROLLER)
public class UserDBController extends BaseDBController<UserService, User, UserValidator> {

    @Autowired
    UserValidator userValidator;

    @Override
    Optional<User> requestCast(JsonNode jsonNode) {
        return Optional.of(jackson.requestCast(User.class, jsonNode));
    }

    @Override
    BindingResult applyValidator(String rule, User item, BindingResult errors) {
        return userValidator.applyValidate(rule, item, errors);
    }
}
