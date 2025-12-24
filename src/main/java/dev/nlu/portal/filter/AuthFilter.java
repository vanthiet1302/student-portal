package dev.nlu.portal.filter;

import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
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

        if (uri.equals(contextPath + "/login") ||
            uri.equals(contextPath + "/register") ||
            uri.equals(contextPath + "/logout") ||
            uri.equals(contextPath + "/forgot-password") ||
            uri.equals(contextPath + "/reset-password") ||
                uri.startsWith(contextPath + "/assets/") ||
                uri.startsWith(contextPath + "/css/") ||
                uri.startsWith(contextPath + "/js/") ||
                uri.startsWith(contextPath + "/api/") ||
                uri.matches(".*\\.(css|js|png|jpg|jpeg|gif|ico)$")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        String path = uri.substring(contextPath.length());

        if (path.startsWith("/admin/")) {
            if (user.getRole() != Role.ADMIN) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "Bạn không có quyền truy cập trang này!");
                return;
            }
            chain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/student/")) {
            if (user.getRole() != Role.STUDENT) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "Bạn không có quyền truy cập trang này!");
                return;
            }
            chain.doFilter(request, response);
            return;
        }
        if (path.startsWith("/teacher/")) {
            if (user.getRole() != Role.TEACHER) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang này!");
                return;
            }
            chain.doFilter(request, response);
            return;
        }

        String targetPath = getRoleBasedPath(path, user.getRole());
        RequestDispatcher rd = req.getRequestDispatcher(targetPath);
        rd.forward(req, resp);
    }

    private String getRoleBasedPath(String path, Role role) {
        String rolePrefix;
        switch (role) {
            case ADMIN:
                rolePrefix = "/admin";
                break;
            case STUDENT:
                rolePrefix = "/student";
                break;
            case TEACHER:
                rolePrefix = "/teacher";
                break;
            default:
                rolePrefix = "/student";
        }
        return rolePrefix + path;
    }
}