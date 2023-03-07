package com.wholesale.exception;

import com.wholesale.api.dto.PlatformResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class PlatformExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<PlatformResponse> handleNullPointer(AccessDeniedException exception) {
        log.warn("Access denied exception occurred in platform: ", exception);
        return new ResponseEntity<>(
                getPlatformResponse(
                        ErrorMessageEnum.UNAUTHORIZED_USER,
                        exception.getMessage()),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<PlatformResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("Unhandled exception occurred in platform {}", exception.getMessage());
        return new ResponseEntity<>(
                getPlatformResponse(
                        ErrorMessageEnum.RUNTIME_EXCEPTION,
                        exception.getMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<PlatformResponse> handleException(Exception exception) {
        log.error("Unhandled exception occurred in platform {}", exception.getMessage());
        return new ResponseEntity<>(
                getPlatformResponse(
                        ErrorMessageEnum.RUNTIME_EXCEPTION,
                        exception.getMessage()),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED);
    }

    private PlatformResponse getPlatformResponse(ErrorMessageEnum runtimeException, String exception) {
        PlatformError errorToUser = new PlatformError();
        errorToUser.setError(runtimeException);
        errorToUser.setErrorMessage(exception);

        return PlatformResponse.builder().error(errorToUser).build();
    }
}
