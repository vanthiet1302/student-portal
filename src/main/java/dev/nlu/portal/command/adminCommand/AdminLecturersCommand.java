package dev.nlu.portal.command.adminCommand;

import dev.nlu.portal.command.Command;
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

		return "/WEB-INF/views/admin/lecturers.jsp";
	}
}
