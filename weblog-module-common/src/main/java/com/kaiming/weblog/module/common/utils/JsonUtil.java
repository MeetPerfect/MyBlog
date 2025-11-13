package com.kaiming.weblog.module.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: JsonUtil
 * Package: com.kaiming.weblog.module.common.utils
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/13 17:02
 * @Version 1.0
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }
}
