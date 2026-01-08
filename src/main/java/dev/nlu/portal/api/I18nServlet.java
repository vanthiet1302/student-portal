package dev.nlu.portal.api;

import dev.nlu.portal.utils.Utf8ResourceBundle;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/api/i18n")
public class I18nServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json;charset=UTF-8");

        // Get language from parameter or from request attribute set by filter
        String lang = req.getParameter("lang");
        Locale locale;

        if (lang != null && !lang.isBlank()) {
            locale = new Locale(lang);
        } else {
            locale = (Locale) req.getAttribute("locale");
            if (locale == null) {
                locale = Locale.forLanguageTag("vi");
            }
        }

        ResourceBundle bundle =
                Utf8ResourceBundle.getBundle("i18n.messages", locale);

        StringBuilder json = new StringBuilder("{");

        for (String key : bundle.keySet()) {
            json.append("\"")
                    .append(key)
                    .append("\":\"")
                    .append(bundle.getString(key).replace("\"", "\\\""))
                    .append("\",");
        }

        if (json.length() > 1) {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("}");

        resp.getWriter().write(json.toString());
    }
}