package dev.nlu.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import dev.nlu.portal.model.User;
import dev.nlu.portal.service.StudentService;
import dev.nlu.portal.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	UserService userService = new UserService();
	StudentService studentService = new StudentService();

	private ResourceBundle getBundle(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String lang = (String) session.getAttribute("lang");
		Locale locale = (lang != null) ? new Locale(lang) : new Locale("vi");
		return ResourceBundle.getBundle("i18n.messages", locale);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
		ResourceBundle bundle = getBundle(req);

		// Server-side validation
		if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			String errorMsg = bundle.getString("page.login.error.required");
			if (isAjax) {
				sendJsonResponse(resp, false, errorMsg, null);
			} else {
				req.setAttribute("error", errorMsg);
				req.getRequestDispatcher("/WEB-INF/views/pages/login.jsp").forward(req, resp);
			}
			return;
		}

		User user = userService.login(username.trim(), password.trim());

		if (user != null) {
			req.getSession().setAttribute("user", user);

			String role = user.getRole().name();
			String redirectUrl = req.getContextPath();

			switch (role) {
				case "ADMIN":
					redirectUrl += "/admin/dashboard";
					break;
				case "LECTURER":
					redirectUrl += "/lecturer/dashboard";
					break;
				case "STUDENT":
					redirectUrl += "/student/dashboard";
					break;
				default:
					redirectUrl += "/";
			}

			if (isAjax) {
				sendJsonResponse(resp, true, null, redirectUrl);
			} else {
				resp.sendRedirect(redirectUrl);
			}
		} else {
			String errorMsg = bundle.getString("page.login.error.invalid");
			if (isAjax) {
				sendJsonResponse(resp, false, errorMsg, null);
			} else {
				req.setAttribute("error", errorMsg);
				req.getRequestDispatcher("/WEB-INF/views/pages/login.jsp").forward(req, resp);
			}
		}
	}

	private void sendJsonResponse(HttpServletResponse resp, boolean success, String error, String redirectUrl) throws IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();
		StringBuilder json = new StringBuilder("{");
		json.append("\"success\":").append(success);

		if (error != null) {
			json.append(",\"error\":\"").append(escapeJson(error)).append("\"");
		}

		if (redirectUrl != null) {
			json.append(",\"redirectUrl\":\"").append(escapeJson(redirectUrl)).append("\"");
		}

		json.append("}");
		out.print(json.toString());
		out.flush();
	}

	private String escapeJson(String value) {
		if (value == null) return "";
		return value.replace("\\", "\\\\")
				   .replace("\"", "\\\"")
				   .replace("\n", "\\n")
				   .replace("\r", "\\r")
				   .replace("\t", "\\t");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/pages/login.jsp").forward(req, resp);
	}
}
