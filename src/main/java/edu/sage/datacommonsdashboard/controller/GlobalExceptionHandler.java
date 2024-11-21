package edu.sage.datacommonsdashboard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle all exceptions and return the 500 error page
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        // Add exception details to the model if needed
        model.addAttribute("message", ex.getMessage());

        // Set the view name to the 500 error page
        modelAndView.setViewName("error/500");

        return modelAndView;
    }
}