package com.wholesale.exception;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {
    NO_ERROR("0","No Error"),
    UNAUTHORIZED_USER("1","Unauthorized"),
    RUNTIME_EXCEPTION("2", "Runtime Exception");

    private String errorCode;

    private String errorMessage;

    private PlatformError platformError = new PlatformError();

    private ErrorMessageEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.platformError.setErrorCode(errorCode);
        this.platformError.setErrorMessage(errorMessage);
    }

}
