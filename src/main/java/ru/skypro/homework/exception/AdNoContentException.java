package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class AdNoContentException extends RuntimeException {
    public AdNoContentException() {
    }

    public AdNoContentException(String message) {
        super(message);
    }

    public AdNoContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdNoContentException(Throwable cause) {
        super(cause);
    }

    public AdNoContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
