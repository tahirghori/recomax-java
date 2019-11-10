package com.ms.base.throwable.exception;

import com.ms.base.throwable.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

/**
 * Created by Tahir-Appsiskey on 12/6/2018.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(ErrorMessage bMessage) {
        super(bMessage);
        this.baseMessage = bMessage;
    }
}
