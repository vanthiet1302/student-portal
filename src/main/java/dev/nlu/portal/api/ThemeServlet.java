package dev.nlu.portal.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/theme")
public class ThemeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String theme = req.getParameter("theme"); // light | dark | auto
        if (theme != null && !theme.isBlank()) {
            req.getSession().setAttribute("theme", theme);
        }

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write("{\"success\":true,\"theme\":\"" + theme + "\"}");
    }
}