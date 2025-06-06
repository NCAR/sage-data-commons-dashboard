package edu.sage.datacommonsdashboard.handler;

import edu.sage.datacommonsdashboard.exception.FileNotReadableException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileErrorHandler {

    @ExceptionHandler(FileNotReadableException.class)
    public String handleFileNotReadableException(FileNotReadableException ex, Model model, HttpServletResponse response) {

        // Set status code to 500 Internal Server Error (Yes?)
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        model.addAttribute("errorTitle", "File Error");
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());

        // TODO: Log full exception for debugging with Log4j

        return "file-error";
    }

}
