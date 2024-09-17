package com.example.Validation.Exception;

import lombok.Getter;

@Getter
public class MessageNotFoundException extends RuntimeException{
    public MessageNotFoundException(String message) {
        super(message);
    }
}
