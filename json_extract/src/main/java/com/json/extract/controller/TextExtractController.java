package com.json.extract.controller;

import com.json.extract.constants.ExtractConstants;
import com.json.extract.dto.response.ApiResult;
import com.json.extract.exception.TextExtractErrorCode;
import com.json.extract.service.TextExtractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 文本提取入口
 * @author LinSir
 * @date: 2025/7/15 13:44
 */
@Slf4j
@RestController
@RequestMapping("/extract")
public class TextExtractController {

    @Resource
    private TextExtractService textExtractService;

    /**
     * 文本提取
     * @param file 文件
     * @return 文本提取结果
     */
    @PostMapping("/text")
    public ApiResult<List<String>> textExtract(@RequestPart("file") MultipartFile file) {
        if (file == null) {
            return ApiResult.error(TextExtractErrorCode.PARAM_NULL);
        }
        try {
            return ApiResult.success(textExtractService.textExtract(file));
        } catch (Exception e) {
            log.error("文本提取异常.", e);
            return ApiResult.error(TextExtractErrorCode.SYSTEM_001);
        }
    }

    /**
     * 字段提取
     * @param file 文件
     * @return 字段提取结果
     */
    @PostMapping("/field")
    public ApiResult<List<String>> fieldExtract(@RequestPart("file") MultipartFile file) {
        if (file == null) {
            return ApiResult.error(TextExtractErrorCode.PARAM_NULL);
        }
        try {
            return ApiResult.success(textExtractService.fieldExtract(file, ExtractConstants.ORDER_ID));
        } catch (Exception e) {
            log.error("字段提取异常.", e);
            return ApiResult.error(TextExtractErrorCode.SYSTEM_001);
        }
    }
}
