package com.wholesale.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlatformError implements Serializable{

    private String errorCode;

    private String errorMessage;

    public void setError(ErrorMessageEnum messageEnum) {
        this.errorCode = messageEnum.getErrorCode();
        this.errorMessage = messageEnum.getErrorMessage();
    }

    public PlatformError(ErrorMessageEnum messageEnum) {
        this.errorCode = messageEnum.getErrorCode();
        this.errorMessage = messageEnum.getErrorMessage();
    }

    public PlatformError() {
    }
}
