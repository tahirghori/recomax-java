package com.ms.base.controller.REST;

import com.ms.base.beam.Role;
import com.ms.base.workspace.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role-rest")
@Validated
public class RoleRESTController extends BaseRESTController<RoleService, Role> {
}
