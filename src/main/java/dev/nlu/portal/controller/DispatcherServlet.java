package dev.nlu.portal.controller;

import dev.nlu.portal.controller.command.Command;
import dev.nlu.portal.controller.command.LoginCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private Map<String, Command> getRoutes = new HashMap<>();
    private Map<String, Command> postRoutes = new HashMap<>();

    @Override
    public void init() throws ServletException {

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

        String path = req.getPathInfo();
        Command command = routes.get(path);

        if (command == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            String view = command.execute(req, resp);

            if (view.startsWith("redirect:")) {
                resp.sendRedirect(view.substring(9));
            } else {
                req.getRequestDispatcher(view).forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
