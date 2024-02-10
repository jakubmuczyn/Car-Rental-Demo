package pl.sda.carrental.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.slf4j.Logger;

@ControllerAdvice
public class ErrorController {
    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("Błąd poczas próby uruchomienia Spring Security.", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Nieznany błąd");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
