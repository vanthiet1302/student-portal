package dev.nlu.portal.controller.command;

import dev.nlu.portal.model.User;
import dev.nlu.portal.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command{
    UserServiceImpl service = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = service.login(username, password);

        if (user == null) {
            request.setAttribute("error", "Mã sinh viên hoặc mật khẩu không đúng!");
            return "/WEB-INF/auth/login.jsp";
        }



        return "";
    }
}
