package dev.nlu.portal.controller;

import java.io.IOException;

import dev.nlu.portal.model.User;
import dev.nlu.portal.service.StudentService;
import dev.nlu.portal.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	UserService userService = new UserService();
	StudentService studentService = new StudentService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = userService.login(username, password);

		if (user != null) {
			req.getSession().setAttribute("user", user);
			// Redirect
			String role = user.getRole().name();
			switch (role) {
			case "ADMIN":
				resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
				return;
			case "LECTURER":
				resp.sendRedirect(req.getContextPath() + "/lecturer/dashboard");
				return;
			case "STUDENT":
				resp.sendRedirect(req.getContextPath() + "/student/dashboard");
				return;
			}
		} else {
			req.setAttribute("error", "<strong>Username</strong> hoặc <strong>Password</strong> không đúng!");
			req.getRequestDispatcher("/WEB-INF/views/share/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/share/login.jsp").forward(req, resp);
	}
}
