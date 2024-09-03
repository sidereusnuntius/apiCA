package com.ca.apiCA.service.exception;

import lombok.Data;

@Data
public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }

}
