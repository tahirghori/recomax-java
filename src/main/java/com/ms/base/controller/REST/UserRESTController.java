package com.ms.base.controller.REST;

import com.ms.base.beam.UserModel;
import com.ms.base.controller.DB.BaseDBController;
import com.ms.base.workspace.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userdd")
@Validated
public class UserRESTController extends BaseRESTController<UserService, UserModel>  {
}
