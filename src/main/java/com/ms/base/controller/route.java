package com.ms.base.controller;

import org.springframework.beans.factory.annotation.Value;

public class route {

    @Value("${app.url}")
    private String BASE_URL;

    /* Application endpoint Base */
    public static final String USER_CONTROLLER = "user";
    public static final String ROLE_CONTROLLER = "role";
        /* Jpa Operations show at same link  */

    /* WebFlux Operations  */
    public static final String LISTING = "/listing";
    public static final String GETBYID = "/getById/";
    public static final String CREATE = "/create/";
    public static final String Edit = "/edit/";
    public static final String REMOVE = "/remove";

}
