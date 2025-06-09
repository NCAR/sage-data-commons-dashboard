package edu.sage.datacommonsdashboard.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@ConditionalOnProperty(name = "server.error.whitelabel.enabled", havingValue = "false", matchIfMissing = true)
@ConditionalOnProperty(name = "server.error.whitelabel.enabled", havingValue = "false")
public class CustomErrorController implements ErrorController {

    /* Catch-all for unhandled errors and HTTP status errors */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String errorMessage = "An unexpected error has occurred";
        String errorTitle = "Error";
        String exceptionMessage = null;

        if (exception != null && exception instanceof Exception) {
            exceptionMessage = ((Exception) exception).getMessage();
        }

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            switch(statusCode) {
                case 404:  // NOT_FOUND
                    errorTitle = "Page Not Found";
                    errorMessage = "The page you are looking for does not exist.";
                    break;
                case 403:  // FORBIDDEN
                    errorTitle = "Access Denied";
                    errorMessage = "You don't have permission to access this page.";
                    break;
                case 500:  // INTERNAL_SERVER_ERROR
                    errorTitle = "Internal Server Error";
                    errorMessage = "Something went wrong on our end. Please try again later.";
                    break;
            }
        }

        model.addAttribute("errorTitle", errorTitle);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("statusCode", status);
        model.addAttribute("exceptionMessage", exceptionMessage);

        return "custom-error";  // Changed from "error" to "custom-error"
    }
}
