package com.json.extract.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description: json字符串check
 * @author LinSir
 * @date: 2025/7/15 16:02
 */
public class JsonStringChecker {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 提取文本中的首个合法 JSON 串（对象或数组），找不到返回 null
     */
    public static String extractFirstJson(String text) {
        if (text == null) return null;
        int len = text.length();
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (c == '{' || c == '[') {
                char open = c;
                char close = (c == '{') ? '}' : ']';
                int count = 0;
                for (int j = i; j < len; j++) {
                    if (text.charAt(j) == open) count++;
                    if (text.charAt(j) == close) count--;
                    if (count == 0) {
                        String possibleJson = text.substring(i, j + 1);
                        if (isValidJson(possibleJson)) {
                            return possibleJson;
                        } else {
                            break; // 不是合法 JSON，跳出内层循环
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
