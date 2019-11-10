package com.ms.base.throwable;

import com.ms.base.throwable.exception.*;
import com.ms.base.throwable.message.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Component
public class ResponseBuilder<T> {

    private String timestamp = new Date().toString();
    private String message;
    private int status;
    private List<Map<String, String>> errors = new ArrayList<>();
    private String details = "";
    private T data;

    public ResponseBuilder setDetails(String details) {
        this.details = details;
        return this;
    }

    public ResponseBuilder setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResponseBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseBuilder setErrors(List<Map<String, String>> errors) {
        this.errors.addAll(errors);
        return this;
    }


    public ResponseBuilder setError(String key, String value) {
        Map<String, String> error = new HashMap<>();
        error.put(key, value);
        this.errors.add(error);
        return this;
    }


    public BaseException buildThrow(HttpStatus status) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
        if (HttpStatus.INTERNAL_SERVER_ERROR.value() == this.status) {
            return new InternalServerErrorException(new ErrorMessage(this.timestamp, this.message, this.status, this.errors, this.details));
        } else if (HttpStatus.CONFLICT.value() == this.status) {
            return new ResourceConflictException(new ErrorMessage(this.timestamp, this.message, this.status, this.errors, this.details));
        } else if (HttpStatus.UNPROCESSABLE_ENTITY.value() == this.status) {
            return new UnProcessableEntitiyException(new ErrorMessage(this.timestamp, this.message, this.status, this.errors, this.details));
        } else if (HttpStatus.NOT_FOUND.value() == this.status) {
            return new ResourceNotFoundException(new ErrorMessage(this.timestamp, this.message, this.status, this.errors, this.details));
        } else if (HttpStatus.BAD_REQUEST.value() == this.status) {
            return new UnProcessableEntitiyException(new ErrorMessage(this.timestamp, this.message, this.status, this.errors, this.details));
        } else {
            return new ResourceNotFoundException(new ErrorMessage(this.timestamp, this.message, this.status, this.errors, this.details));

        }
    }

    public void buildLog(HttpStatus status) {

    }


    public ResponseEntity buildResponse(HttpStatus status, Page<T> item) {
        return ResponseEntity.status(status).body(item);
    }
    public ResponseEntity buildResponse(HttpStatus status, List<T> item) {
        return ResponseEntity.status(status).body(item);
    }

    public ResponseEntity buildResponse(HttpStatus status, T item) {
        return ResponseEntity.status(status).body(item);
    }

    public ResponseEntity buildResponse(HttpStatus status, T item, String message) {
        this.message = message;
        this.data = item;
        return ResponseEntity.status(status).body(data);
    }



    public ResponseEntity buildResponse(HttpStatus status) {
        return ResponseEntity.status(status).body(data);
    }



    public ResponseBuilder buildvalidate(BindingResult item) {
        errors = new ArrayList<>();



        if ( !item.hasErrors()) {
            return this;
        }

            this.message = "Request params not valid!";

        this.status = HttpStatus.BAD_REQUEST.value();
        List<FieldError> fields = item.getFieldErrors();

        fields.forEach(x -> {
            Map<String, String> error = new HashMap<>();
            error.put(x.getField(), x.getDefaultMessage());
            this.errors.add(error);
        });

        throw new UnProcessableEntitiyException(new ErrorMessage(this.timestamp, this.message, this.status, this.errors, this.details));

    }

}
