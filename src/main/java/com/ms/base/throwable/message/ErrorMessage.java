package com.ms.base.throwable.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
public class ErrorMessage<T> extends BaseMessage {

    private String timestamp;
    private String message;
    private int status;
    private List<Map<String, String>> errors;
    private String details;

    public ErrorMessage(String timestamp, String message, int status, List<Map<String, String>> errors, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.details = details;
    }
}
