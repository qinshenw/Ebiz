package edu.support.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 执笔
 * @date 15:26
 */
public class CacheMap {

    private static Map<String, String> cache = new HashMap<>();

    /**
     * 设置缓存
     * @param key
     * @param value
     */
    public static void put(String key, String value) {
        cache.put(key, value);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public static String get(String key) {
        return cache.getOrDefault(key, "");
    }
}
