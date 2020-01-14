package com.ms.base.throwable.message;

import com.ms.base.beam.Base;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.util.*;


@Getter
@Setter
public class WarningMessage<T> extends Base {

    private String timestamp ;
    private String message;
    private int status;
    private T data;

    public WarningMessage(UUID id, Date createdAt, Date updatedAt, boolean deleted, BindingResult bindingResult, String timestamp, String message, int status, T details) {
        super(id, createdAt, updatedAt, deleted, bindingResult);
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.data = details;
    }
}
