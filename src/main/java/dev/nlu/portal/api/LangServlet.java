package dev.nlu.portal.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/lang")
public class LangServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String lang = req.getParameter("lang"); // vi | en
        if (lang != null && !lang.isBlank()) {
            req.getSession().setAttribute("lang", lang);
        }

        // Check if AJAX request
        String requestedWith = req.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(requestedWith)
                || req.getHeader("Accept") != null && req.getHeader("Accept").contains("application/json")
                || "fetch".equals(req.getHeader("Sec-Fetch-Mode"));

        if (isAjax || req.getHeader("Referer") == null) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write("{\"success\":true,\"lang\":\"" + lang + "\"}");
        } else {
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
