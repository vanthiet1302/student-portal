package dev.nlu.portal.controller;

import dev.nlu.portal.controller.command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/*", "/student/*", "/lecturer/*"})
public class DispatcherServlet extends HttpServlet {
    private Map<String, Command> getRoutes = new HashMap<>();
    private Map<String, Command> postRoutes = new HashMap<>();

    @Override
    public void init() throws ServletException {
    	getRoutes.put("/admin/dashboard", new AdminDashboardController());
    	getRoutes.put("/lecturer/dashboard", new AdminDashboardController());
    	getRoutes.put("/student/dashboard", new AdminDashboardController());
        // Admin
        getRoutes.put("/admin/lecturers", new AdminLecturersController());
        getRoutes.put("/admin/addLecturer", new AdminAddLecturerController());
        getRoutes.put("/admin/detailLecturer", new AdminDetailLecturerController());
        getRoutes.put("/admin/students", new AdminStudentsController());

        postRoutes.put("/admin/addLecturer", new AdminAddLecturerController());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, getRoutes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, postRoutes);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, Map<String, Command> routes)
            throws ServletException, IOException {

        String path = req.getServletPath() + req.getPathInfo();
        Command command = routes.get(path.trim());

        if (command == null) {
            System.out.println(path);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, path);
            return;
        }

        try {
            String view = command.execute(req, resp);

            if (view.startsWith("redirect:")) {
                resp.sendRedirect(req.getContextPath() + view.substring(9));
            } else {
                req.getRequestDispatcher(view).forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
