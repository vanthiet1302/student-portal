package dev.nlu.portal.command.adminCommand;

import dev.nlu.portal.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDashboardCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/WEB-INF/views/admin/dashboard.jsp";
    }
}
