package com.json.extract.exception;


import lombok.Getter;

/**
 * @author guolu
 */
@Getter
public class TextExtractApiException extends RuntimeException {
    private static final long serialVersionUID = -110141508981010290L;
    private final String errorCode;
    
    private final String errorMsg;

    public TextExtractApiException(TextExtractErrorCode textExtractErrorCode) {
        super(textExtractErrorCode.getDesc());
        this.errorCode = textExtractErrorCode.name();
        this.errorMsg = textExtractErrorCode.getDesc();
    }

    public TextExtractApiException(TextExtractErrorCode textExtractErrorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = textExtractErrorCode.name();
        this.errorMsg =  errorMsg;
    }

    public TextExtractApiException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg =  errorMsg;
    }
}
