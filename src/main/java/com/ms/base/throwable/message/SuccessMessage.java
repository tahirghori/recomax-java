package com.ms.base.throwable.message;

import com.ms.base.beam.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
