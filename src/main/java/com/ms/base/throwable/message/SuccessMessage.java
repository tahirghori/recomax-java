package com.ms.base.throwable.message;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SuccessMessage<T> extends BaseMessage {


    private String timestamp;
    private String message;
    private int status;

    public SuccessMessage(String timestamp, String message, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }


}
