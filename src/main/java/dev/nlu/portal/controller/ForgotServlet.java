package dev.nlu.portal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ForgotServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gmail = request.getParameter("gmail");
        if (gmail == null) {
            System.out.println("xin hay nhap gmail");
        } else {
            response.sendRedirect("/WEB-INF/auth/code.jsp");
        }
    }
}
