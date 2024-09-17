package com.example.Validation.Exception;

public class NotUniqueConstraintException extends RuntimeException{
    public NotUniqueConstraintException(String message) {
        super(message);
    }
}
