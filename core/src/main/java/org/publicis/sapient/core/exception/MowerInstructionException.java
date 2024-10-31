package org.publicis.sapient.core.exception;

public class MowerInstructionException extends RuntimeException{
    public String message;
    public MowerInstructionException(String message) {
        super(message);
        this.message = message;
    }
}
