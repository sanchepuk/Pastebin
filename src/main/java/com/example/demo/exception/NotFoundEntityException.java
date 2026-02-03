package com.example.demo.exception;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String s) {
        super(s);
    }
}
