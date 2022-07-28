package com.common.Util;

import java.util.UUID;

//UUID生成器
//32位16进制字符串
public class UUIDUtils {
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
