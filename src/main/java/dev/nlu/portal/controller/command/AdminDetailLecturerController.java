package dev.nlu.portal.controller.command;

import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.service.LecturerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDetailLecturerController implements Command{
    LecturerServiceImpl lecturerService = new LecturerServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("id"));
        Lecturer lecturer = lecturerService.findById(id);
        request.getSession().setAttribute("lecturer", lecturer);

        return "/WEB-INF/views/admin/detailLecturer.jsp";
    }
}
