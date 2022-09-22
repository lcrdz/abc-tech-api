package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class MinAssistRequiredException extends RuntimeException {

    private String description;

    public MinAssistRequiredException(String message, String description) {
        super(message);
        this.description = description;
    }
}
