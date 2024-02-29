package org.api.springf1.exception;

public class ConstructorNotFoundException extends RuntimeException {
    public ConstructorNotFoundException(String message) {
        super(message);
    }
}
