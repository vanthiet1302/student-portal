package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.controller.command.Command;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.service.LecturerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDetailLecturerCommand implements Command {
    LecturerService lecturerService = new LecturerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Lecturer lecturer = lecturerService.getById(id);
        request.getSession().setAttribute("lecturer", lecturer);

        return "/WEB-INF/views/admin/detailLecturer.jsp";
    }
}
