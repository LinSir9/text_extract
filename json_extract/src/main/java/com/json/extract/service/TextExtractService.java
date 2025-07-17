package com.json.extract.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 文本提取对外接口服务
 * @author LinSir
 * @date: 2025/7/15 13:46
 */
public interface TextExtractService {

    /**
     * 文本提取
     * @param file 文件
     * @return 提取结果
     */
    List<String> textExtract(MultipartFile file);

    /**
     * 字段提取
     * @param file 文件
     * @param extractField 提取字段
     * @return 提取结果
     */
    List<String> fieldExtract(MultipartFile file, String extractField);
}
