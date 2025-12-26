package dev.nlu.portal.controller.command;

import dev.nlu.portal.model.User;
import dev.nlu.portal.service.UserServiceImpl;
import dev.nlu.portal.utils.PasswordUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
	UserServiceImpl service = new UserServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = service.login(username, password);
		
		if (user != null) {
			// Redirect
			String role = user.getRole().name();
			switch (role) {
			case "ADMIN":
				return "redirect:/admin/dashboard";
			case "LECTURER":
				return "redirect:/lecturer/dashboard";
			case "STUDENT":
				return "redirect:/student/dashboard";
			}
		}
		return "/WEB-INF/views/share/login.jsp";
	}
}
