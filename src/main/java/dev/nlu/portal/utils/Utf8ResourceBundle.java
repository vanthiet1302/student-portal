package dev.nlu.portal.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Utf8ResourceBundle {
    public static ResourceBundle getBundle(String baseName, Locale locale) {
        return ResourceBundle.getBundle(
                baseName,
                locale,
                Utf8ResourceBundle.class.getClassLoader()
        );
    }
}
