package com.json.extract.service;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.json.extract.util.FileWriteUtil;
import com.json.extract.util.ListUtil;
import com.json.extract.util.MultipartFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 文本提取服务测试类
 * @author LinSir
 * @date: 2025/7/15 17:05
 */
@Slf4j
@SpringBootTest
public class TextExtractServiceTest {

    @Resource
    private TextExtractService textExtractService;

    @Test
    public void testTextExtract() {
        String readPath = "/Users/linjie/download/ecd8168c-897e-45cd-b6c6-4aa7939d5db9.xlsx";
        MultipartFile multipartFile = MultipartFileUtil.fileToMultipartFile(readPath);
        if (multipartFile == null) {
            return;
        }
        List<String> result = textExtractService.textExtract(multipartFile);
        if (ListUtil.isEmpty(result)) {
            return;
        }
        String insertPath = "/Users/linjie/download/11.xlsx";
        FileWriteUtil.writeListToFile(result, insertPath);
        log.info("文件写入成功. size: {}", result.size());
    }
}
