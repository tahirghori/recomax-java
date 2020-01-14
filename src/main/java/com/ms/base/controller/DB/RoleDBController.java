package com.ms.base.controller.DB;

import com.fasterxml.jackson.databind.JsonNode;
import com.ms.base.beam.Role;
import com.ms.base.throwable.validator.RoleValidator;
import com.ms.base.workspace.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ms.base.controller.route.ROLE_CONTROLLER;

@RestController
@RequestMapping(value = ROLE_CONTROLLER)
public class RoleDBController extends BaseDBController<RoleService, Role, RoleValidator> {


    @Autowired
    RoleValidator userValidator;

    @Override
    Optional<Role> requestCast(JsonNode jsonNode) {
        return Optional.of(jackson.requestCast(Role.class, jsonNode));
    }

    @Override
    BindingResult applyValidator(String rule, Role item, BindingResult errors) {
        return null;
    }


//    @Override
//    RoleValidator applyValidator(String rule) {
//        return null;
//    }

}
