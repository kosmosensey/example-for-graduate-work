package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongCurrentPasswordException extends RuntimeException {
    public WrongCurrentPasswordException() {
    }

    public WrongCurrentPasswordException(String message) {
        super(message);
    }

    public WrongCurrentPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCurrentPasswordException(Throwable cause) {
        super(cause);
    }

    public WrongCurrentPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
