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

        Locale locale = (Locale) req.getAttribute("locale");
        if (locale == null) {
            String lang = req.getParameter("lang");
            if (lang == null || lang.isBlank()) {
                lang = (String) req.getSession().getAttribute("lang");
            }
            if (lang == null || lang.isBlank()) {
                lang = "vi";
            }
            req.getSession().setAttribute("lang", lang);
            locale = new Locale(lang);
        }

        ResourceBundle bundle = Utf8ResourceBundle.getBundle("i18n.messages", locale);

        StringBuilder json = new StringBuilder("{");

        for (String key : bundle.keySet()) {
            json.append("\"")
                    .append(key)
                    .append("\":\"")
                    .append(bundle.getString(key))
                    .append("\",");
        }

        if (json.length() > 1) {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("}");

        resp.getWriter().write(json.toString());
    }
}
