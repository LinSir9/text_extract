package com.json.extract.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @description: 文件流工具类
 * @author LinSir
 * @date: 2025/7/15 17:28
 */
@Slf4j
public class MultipartFileUtil {

    /**
     * 将本地文件转换为 MultipartFile
     * @param filePath 本地文件路径
     * @return MultipartFile 对象
     */
    public static MultipartFile fileToMultipartFile(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(
                    file.getName(), // 文件名
                    file.getName(), // 原始文件名
                    null,           // ContentType，可为 null
                    input           // 文件流
            );
            input.close();
            return multipartFile;
        } catch (IOException e) {
            log.error("文件转换异常.", e);
            return null;
        }
    }
}
