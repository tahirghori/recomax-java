package com.ms.base.throwable.exception;

import com.ms.base.throwable.message.ErrorMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException  {

    public ErrorMessage baseMessage;

    public BaseException(ErrorMessage bMessage) {
        super(String.format("%s  with %s : '%s'", bMessage.getTimestamp(), bMessage.getMessage(), bMessage.getStatus()));
        this.baseMessage = bMessage;
    }

}
