package com.udea.kbt.exception;

public class InvalidRating extends RuntimeException {

    public InvalidRating() {
        super();
    }

    public InvalidRating(String message) {
        super(message);
    }
}