package dev.nlu.portal.controller.command;

import dev.nlu.portal.service.ICrudService;
import dev.nlu.portal.service.LecturerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AdminLecturersController implements Command{
    ICrudService service = new LecturerServiceImpl();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession(false);
        var lecturers = service.findAll();
        session.setAttribute("lecturers", lecturers);

		return "/WEB-INF/views/admin/lecturers.jsp";
	}

}
