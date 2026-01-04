package dev.nlu.portal.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class I18nFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) request;

        String lang = req.getParameter("lang");

        if (lang == null) {
            lang = (String) req.getSession().getAttribute("lang");
        }

        if (lang == null) {
            lang = "vi";
        }

        req.getSession().setAttribute("lang", lang);
        req.setAttribute("locale", new Locale(lang));

        chain.doFilter(request, response);
    }
}

