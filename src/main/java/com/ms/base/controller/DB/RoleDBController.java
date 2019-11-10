package com.ms.base.controller.DB;

import com.fasterxml.jackson.databind.JsonNode;
import com.ms.base.beam.RoleModel;
import com.ms.base.beam.UserModel;
import com.ms.base.throwable.validator.BaseValidator;
import com.ms.base.throwable.validator.RoleValidator;
import com.ms.base.workspace.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ms.base.controller.route.ROLE_CONTROLLER;

@RestController
@RequestMapping(value = ROLE_CONTROLLER)
public class RoleDBController extends BaseDBController<RoleService, RoleModel, RoleValidator> {


    @Autowired
    RoleValidator userValidator;

    @Override
    Optional<RoleModel> requestCast(JsonNode jsonNode) {
        return Optional.of(jackson.requestCast(RoleModel.class, jsonNode));
    }

    @Override
    BindingResult applyValidator(String rule, RoleModel item, BindingResult errors) {
        return null;
    }


//    @Override
//    RoleValidator applyValidator(String rule) {
//        return null;
//    }

}
