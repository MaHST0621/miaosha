package com.example.miaosha.miaosha002.util;

import java.util.UUID;

/**
 * @author
 */
public class UUIDUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
