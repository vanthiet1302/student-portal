package dev.nlu.portal.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nUtils {
    public static String get(HttpServletRequest request, String key){
        Locale locale = (Locale) request.getAttribute("locale");
        return ResourceBundle.getBundle("i18n.messages", locale).getString(key);
    }
}
