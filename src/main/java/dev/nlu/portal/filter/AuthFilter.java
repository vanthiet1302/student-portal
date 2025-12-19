package dev.nlu.portal.filter;

import dev.nlu.portal.model.Student;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();

        // Bỏ qua các tài nguyên public
        if (uri.equals(contextPath + "/login") ||
                uri.startsWith(contextPath + "/assets/") ||
                uri.startsWith(contextPath + "/css/") ||
                uri.startsWith(contextPath + "/js/") ||
                uri.matches(".*\\.(css|js|png|jpg|jpeg|gif|ico)$")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        Student student = (session != null) ? (Student) session.getAttribute("student") : null;

        if (student == null) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        if (uri.startsWith(contextPath + "/admin/")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Quyền truy cập bị từ chối");
            return;
        }
        if (uri.startsWith(contextPath + "/student/")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Quyền truy cập bị từ chối");
            return;
        }

        chain.doFilter(request, response);
    }
}