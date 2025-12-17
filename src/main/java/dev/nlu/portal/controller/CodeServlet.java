package dev.nlu.portal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String code = request.getParameter("code");
        String textCode = "12345";
        if (code == textCode) {

            response.sendRedirect("loginagain.jsp");
        } else {
            request.getRequestDispatcher("code.jsp").forward(request,response);
        }
    }
}
