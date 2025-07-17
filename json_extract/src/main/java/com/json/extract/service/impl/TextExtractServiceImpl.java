package com.json.extract.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.json.extract.constants.CommonConstants;
import com.json.extract.dto.OneTextExtractRow;
import com.json.extract.service.TextExtractService;
import com.json.extract.util.ExcelListener;
import com.json.extract.util.JsonStringChecker;
import com.json.extract.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 文本提取对外接口服务实现
 * @author LinSir
 * @date: 2025/7/15 14:58
 */
@Slf4j
@Service
public class TextExtractServiceImpl implements TextExtractService {

    /**
     * excel表头行数
     */
    private static final Integer HEAD_ROW_NUM = CommonConstants.ONE;

    /**
     * 文本提取
     * @param file 文件
     * @return 文本提取结果
     */
    @Override
    public List<String> textExtract(MultipartFile file) {
        // 1、excel解析
        List<OneTextExtractRow> texts = this.excelAnalysis(file, HEAD_ROW_NUM);
        // 2、提取文本内容
        return this.extractText(texts);
    }

    /**
     * 提取字段
     * @param file 文件
     * @param extractField 提取字段
     * @return 提取字段内容
     */
    @Override
    public List<String> fieldExtract(MultipartFile file, String extractField) {
        // 1、提取文本内容
        List<String> contents = this.textExtract(file);
        // 2、提取文本字段
        return this.extractTextField(contents, extractField);
    }

    /**
     * 提取文本字段
     * @param contents 文本内容
     * @param extractField 提取字段
     * @return 提取字段内容
     */
    private List<String> extractTextField(List<String> contents, String extractField) {
        if (ListUtil.isEmpty(contents)) {
            return Collections.emptyList();
        }
        List<String> result = Collections.synchronizedList(new ArrayList<>());
        contents.stream().parallel().forEach(content -> {
            JSONObject jsonObject = JSONUtil.parseObj(content);
            if (jsonObject.containsKey(extractField)) {
                result.add(jsonObject.getStr(extractField));
            }
        });
        return result;
    }

    /**
     * 提取文本内容
     * @param texts 文本列表
     * @return 文本内容
     */
    private List<String> extractText(List<OneTextExtractRow> texts) {
        if (ListUtil.isEmpty(texts)) {
            return Collections.emptyList();
        }
        List<String> extractContents = Collections.synchronizedList(new ArrayList<>());
        texts.stream().filter(text -> StringUtils.isNotBlank(text.getContent())).parallel().forEach(text -> {
            String firstJson = JsonStringChecker.extractFirstJson(text.getContent());
            if (StringUtils.isNotBlank(firstJson)) {
                extractContents.add(firstJson);
            } else {
                log.error("文本提取失败. {}", text.getContent());
            }
        });
        return extractContents;
    }

    /**
     * excel解析
     * @param file 文件
     * @param rows 头行数
     * @return 解析结果
     */
    private List<OneTextExtractRow> excelAnalysis(MultipartFile file, Integer rows) {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("excel开始解析");
            ExcelListener<OneTextExtractRow> listener = new ExcelListener<>();
            ExcelReader excelReader = EasyExcelFactory.read(file.getInputStream(), OneTextExtractRow.class, listener).headRowNumber(rows).build();
            excelReader.readAll();
            List<OneTextExtractRow> listMap = listener.getData();
            excelReader.finish();
            stopWatch.stop();
            log.info("excel解析成功. rows行数={} 耗时={}. {}", listMap.size(), stopWatch.getTotalTimeSeconds(), stopWatch.prettyPrint());
            return listMap;
        } catch (IOException e) {
            log.error("excel解析异常.", e);
            return Collections.emptyList();
        }
    }
}
