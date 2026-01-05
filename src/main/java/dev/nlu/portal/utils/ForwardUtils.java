package dev.nlu.portal.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ForwardUtils {
    public static void forwardError(HttpServletRequest req, HttpServletResponse resp, String path ,String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    public static void forwardSuccess(HttpServletRequest req, HttpServletResponse resp, String path ,String message)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
