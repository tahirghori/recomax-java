package com.ms.base.throwable.exception;

import com.ms.base.throwable.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MethodArgumentNotValidException  extends BaseException {

    public MethodArgumentNotValidException(ErrorMessage bMessage) {
            super(bMessage);
            this.baseMessage = bMessage;
        }
}
