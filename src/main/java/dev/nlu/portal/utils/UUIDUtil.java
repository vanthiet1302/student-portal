package dev.nlu.portal.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String generateUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
