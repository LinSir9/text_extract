package com.json.extract.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 文本提取异常码枚举类
 * @author LinSir
 * @date: 2025/7/15 13:55
 */
@Getter
@AllArgsConstructor
public enum TextExtractErrorCode {
    // 订单
    SYSTEM_001("SYSTEM_001", 10001,"系统错误"),
    ERROR_ARGUMENT("ERROR_ARGUMENT", 10002,"参数错误"),
    PARAM_ERROR("PARAM_ERROR", 10003,"参数校验失败"),
    PARAM_NULL("PARAM_NULL", 10004,"请求参数不可为空"),
    ERROR_NOT_FOUND("ERROR_NOT_FOUND", 10005,"没有找到记录"),
    ERROR_DO_FAIL("ERROR_DO_FAIL", 10006,"处理失败，请稍后重试"),
    INVALID_REQUEST("INVALID_REQUEST", 10007,"请求处理拒绝"),
    ERROR_NO_VALID_CONTENT("ERROR_NO_VALID_CONTENT", 10008,"无有效内容"),
    ERROR_NEED_RETRY("ERROR_NEED_RETRY", 10009,"服务繁忙请重试"),
    ;
    private final String code;
    private final Integer num;
    private final String desc;
}
