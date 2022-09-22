package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class MaxAssistException extends RuntimeException {

    private String description;

    public MaxAssistException(String message, String description) {
        super(message);
        this.description = description;
    }
}
