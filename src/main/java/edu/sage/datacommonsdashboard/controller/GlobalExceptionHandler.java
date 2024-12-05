package edu.sage.datacommonsdashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

//@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + ex.getMessage());
    }

    // Handle all exceptions and return the 500 error page
//    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        // Add exception details to the model if needed
        model.addAttribute("message", ex.getMessage());

        // Set the view name to the 500 error page
        modelAndView.setViewName("error/500");

        return modelAndView;
    }
}