package com.json.extract.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class FileWriteUtil {

    /**
     * 将字符串列表写入指定文件，每个字符串占一行
     * @param lines 字符串列表
     * @param filePath 文件路径
     */
    public static void writeListToFile(List<String> lines, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, lines);
        } catch (IOException e) {
            log.error("写入文件异常.", e);
        }
    }
}
