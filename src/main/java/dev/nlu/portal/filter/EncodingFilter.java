package dev.nlu.portal.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Force UTF-8 at servlet container layer to avoid mojibake
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpServletRequest req = (HttpServletRequest) request;

        // Priority: explicit param > session > default
        String lang = req.getParameter("lang");

        // Only use session if no explicit param provided
        if (lang == null || lang.isBlank()) {
            lang = (String) req.getSession().getAttribute("lang");
        }

        if (lang == null || lang.isBlank()) {
            lang = "vi";
        }

        req.getSession().setAttribute("lang", lang);
        req.setAttribute("locale", new Locale(lang));

        chain.doFilter(request, response);
    }
}