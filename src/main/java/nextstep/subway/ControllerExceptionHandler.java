package nextstep.subway;

import nextstep.subway.exception.BadRequestException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            ConstraintViolationException.class
            , BadRequestException.class})
    protected ResponseEntity<Void> handleConflict(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest().build();
    }
}
