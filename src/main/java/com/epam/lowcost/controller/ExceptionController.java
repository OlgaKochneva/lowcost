package com.epam.lowcost.controller;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(HttpSessionRequiredException.class)
    public String handleNotFoundError() {
        return "login";
    }

}
