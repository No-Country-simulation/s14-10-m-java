package com.s1410.calme.Infrastructure.Exceptions;

import org.springframework.validation.BindingResult;

public class BindingResultException extends RuntimeException{
    private final BindingResult bindingResult;

    public BindingResultException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
