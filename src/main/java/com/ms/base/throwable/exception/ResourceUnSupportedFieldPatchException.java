package com.ms.base.throwable.exception;

import com.ms.base.throwable.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;


@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class ResourceUnSupportedFieldPatchException extends BaseException {

    public ResourceUnSupportedFieldPatchException(ErrorMessage bMessage) {
        super(bMessage);
        this.baseMessage = bMessage;
    }
}
