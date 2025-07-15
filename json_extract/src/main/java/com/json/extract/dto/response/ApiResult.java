package com.json.extract.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.json.extract.exception.ApiErrorCode;
import com.json.extract.exception.TextExtractErrorCode;
import com.json.extract.util.MdcUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -1548290344118875340L;

    private boolean success;
    private T data;
    private String errorCode;
    private String errorMessage;
    private String requestId;


    private ApiResult(boolean success, T data, String errorCode, String errorMessage) {
        this(success,data,errorCode,errorMessage, MdcUtil.getRequestId());
    }

    public static <T> ApiResult<T> ofSuccess(T data) {
        return new ApiResult<>(true, data, null, null);
    }

    public static <T> ApiResult<T> ofFail(ApiErrorCode errorCode, String errorMsg) {
        return new ApiResult<>(false, null, errorCode.name(), errorMsg);
    }

    public static <T> ApiResult<T> ofFail(String errorCode, String errorMsg) {
        return new ApiResult<>(false, null, errorCode, errorMsg);
    }

    public static <T> ApiResult<T> ofFail(ApiErrorCode errorCode) {
        return new ApiResult<>(false, null, errorCode.name(), errorCode.getDesc());
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<>(true, null, null, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, data, null, null);
    }

    public static <T> ApiResult<T> success(T data, String errorMessage) {
        return new ApiResult<>(true, data, null, errorMessage);
    }

    public static <T> ApiResult<T> error(String errorCode, String errorMsg) {
        return new ApiResult<>(false, null, errorCode, errorMsg);
    }
    public static <T> ApiResult<T> error(String errorMsg) {
        return new ApiResult<>(false, null, null, errorMsg);
    }

    public static <T> ApiResult<T> error(ApiErrorCode status) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setErrorCode(status.name());
        apiResult.setErrorMessage(status.getDesc());
        return apiResult;
    }

    public static <T> ApiResult<T> error(TextExtractErrorCode status) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setErrorCode(status.name());
        apiResult.setErrorMessage(status.getDesc());
        return apiResult;
    }
}
