package com.ms.base.throwable.exception;

import com.ms.base.throwable.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingHeaderInfoException extends BaseException
{
    public MissingHeaderInfoException(ErrorMessage bMessage) {
        super(bMessage);
        this.baseMessage = bMessage;
    }
}
