package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDashboardCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("contentPage", "/WEB-INF/views/pages/admin/dashboard.jsp");
        request.setAttribute("pageTitle", "Admin Dashboard");

        return "/WEB-INF/views/layout/layout.jsp";
    }
}
