package dev.nlu.portal.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Utf8ResourceBundle {
    private static final ResourceBundle.Control UTF8_CONTROL = new ResourceBundle.Control() {
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format,
                                        ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {

            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            try (InputStream stream = loader.getResourceAsStream(resourceName)) {
                if (stream == null) return null;
                try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                    return new PropertyResourceBundle(reader);
                }
            }
        }
    };

    public static ResourceBundle getBundle(String baseName, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale, Utf8ResourceBundle.class.getClassLoader(), UTF8_CONTROL);
    }

    public static String get(HttpServletRequest request, String key){
        Locale locale = (Locale) request.getAttribute("locale");
        return ResourceBundle.getBundle("messages", locale).getString(key);
    }
}