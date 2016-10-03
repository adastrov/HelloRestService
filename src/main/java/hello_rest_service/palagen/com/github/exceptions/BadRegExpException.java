package hello_rest_service.palagen.com.github.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid regular expression")
public class BadRegExpException extends Exception {

    public BadRegExpException(String message) {
        super(message);
    }

}