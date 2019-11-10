package com.ms.base.throwable.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
public class BaseMessage {

    private String timestamp;
    private String message;
    private int status;
    private List<Map<String, String>> errors;
    private String details;



}
