package com.epam.lowcost.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error(e.getClass().getName());
        for (StackTraceElement element : e.getStackTrace()) {
            logger.error(element.toString());
        }
        model.addAttribute("errorCause", e.getClass().getName());
        model.addAttribute("trace", e.getStackTrace()[0]);
        return "/404";
    }

}
