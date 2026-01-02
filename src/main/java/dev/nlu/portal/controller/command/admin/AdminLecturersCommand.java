package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.controller.command.Command;
import dev.nlu.portal.service.ICrudService;
import dev.nlu.portal.service.LecturerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminLecturersCommand implements Command{
    ICrudService service = new LecturerService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession(false);
        var lecturers = service.findAll();
        session.setAttribute("lecturers", lecturers);

        request.setAttribute("contentPage", "/WEB-INF/views/pages/admin/lecturer/list.jsp");
        request.setAttribute("pageTitle", "Lecturers");

        return "/WEB-INF/views/layout/layout.jsp";
	}
}
