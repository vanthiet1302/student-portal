package dev.nlu.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import dev.nlu.portal.model.User;
import dev.nlu.portal.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    private UserService userService = new UserService();

    private ResourceBundle getBundle(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String lang = (String) session.getAttribute("lang");
        Locale locale = (lang != null) ? new Locale(lang) : new Locale("vi");
        return ResourceBundle.getBundle("i18n.messages", locale);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/pages/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        ResourceBundle bundle = getBundle(req);

        // Server-side validation
        if (email == null || email.trim().isEmpty()) {
            String errorMsg = bundle.getString("page.forgot.password.error.email.required");
            if (isAjax) {
                sendJsonResponse(resp, false, errorMsg, null);
            } else {
                req.setAttribute("error", errorMsg);
                req.getRequestDispatcher("/WEB-INF/views/pages/forgot-password.jsp").forward(req, resp);
            }
            return;
        }

        // Validate email format
        if (!isValidEmail(email.trim())) {
            String errorMsg = bundle.getString("page.forgot.password.error.email.invalid");
            if (isAjax) {
                sendJsonResponse(resp, false, errorMsg, null);
            } else {
                req.setAttribute("error", errorMsg);
                req.getRequestDispatcher("/WEB-INF/views/pages/forgot-password.jsp").forward(req, resp);
            }
            return;
        }

        // Check if email exists
        User user = userService.getByEmail(email.trim());

        if (user != null) {
            // TODO: Generate reset token and send email
            // For now, just show success message
            String successMsg = bundle.getString("page.forgot.password.success");
            if (isAjax) {
                sendJsonResponse(resp, true, successMsg, req.getContextPath() + "/login");
            } else {
                req.setAttribute("success", successMsg);
                req.getRequestDispatcher("/WEB-INF/views/pages/forgot-password.jsp").forward(req, resp);
            }
        } else {
            String errorMsg = bundle.getString("page.forgot.password.error.email.notfound");
            if (isAjax) {
                sendJsonResponse(resp, false, errorMsg, null);
            } else {
                req.setAttribute("error", errorMsg);
                req.getRequestDispatcher("/WEB-INF/views/pages/forgot-password.jsp").forward(req, resp);
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private void sendJsonResponse(HttpServletResponse resp, boolean success, String message, String redirectUrl) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        StringBuilder json = new StringBuilder("{");
        json.append("\"success\":").append(success);

        if (message != null) {
            json.append(",\"message\":\"").append(escapeJson(message)).append("\"");
        }

        if (redirectUrl != null) {
            json.append(",\"redirectUrl\":\"").append(escapeJson(redirectUrl)).append("\"");
        }

        json.append("}");
        out.print(json.toString());
        out.flush();
    }

    private String escapeJson(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}

