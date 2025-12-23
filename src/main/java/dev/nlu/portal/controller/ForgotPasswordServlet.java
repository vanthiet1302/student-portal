package dev.nlu.portal.controller;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.StudentServiceImpl;
import dev.nlu.portal.utils.EmailSender;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;

public class ForgotPasswordServlet extends HttpServlet {
	private final StudentServiceImpl studentService = new StudentServiceImpl();
	private final SecureRandom random = new SecureRandom();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/forgot-password.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		Student student = studentService.findByEmail(email);

		if (student == null) {
			req.setAttribute("error", "Email không tồn tại trong hệ thống.");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/forgot-password.jsp");
			rd.forward(req, resp);
			return;
		}

		String code = String.format("%06d", random.nextInt(1_000_000));
		long expiresAt = Instant.now().plusSeconds(10 * 60).toEpochMilli(); // 10 minutes

		req.getSession().setAttribute("fp_email", email);
		req.getSession().setAttribute("fp_code", code);
		req.getSession().setAttribute("fp_expires", expiresAt);

		String subject = "Mã xác thực đặt lại mật khẩu";
		String body = "Xin chào,\n\n" +
				"Mã xác thực của bạn là: " + code + "\n" +
				"Mã có hiệu lực trong 10 phút.\n\n" +
				"Nếu bạn không yêu cầu, vui lòng bỏ qua email này.";

		boolean sent = EmailSender.send(email, subject, body);
		if (!sent) {
			req.setAttribute("error", "Không thể gửi email. Vui lòng thử lại sau.");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/forgot-password.jsp");
			rd.forward(req, resp);
			return;
		}

		resp.sendRedirect(req.getContextPath() + "/reset-password");
	}
}
