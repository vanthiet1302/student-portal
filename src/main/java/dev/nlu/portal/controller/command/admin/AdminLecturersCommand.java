package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.controller.command.Command;
import dev.nlu.portal.service.LecturerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminLecturersCommand implements Command {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private LecturerService service = new LecturerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Get page parameter
        int page = 1;
        int pageSize = DEFAULT_PAGE_SIZE;

        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize < 1) pageSize = DEFAULT_PAGE_SIZE;
                if (pageSize > 100) pageSize = 100; // Max limit
            } catch (NumberFormatException e) {
                pageSize = DEFAULT_PAGE_SIZE;
            }
        }

        // Get paginated data
        var lecturers = service.getAllPaginated(page, pageSize);
        int totalRecords = service.countAll();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        // Set attributes
        request.setAttribute("lecturers", lecturers);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalRecords", totalRecords);

        request.setAttribute("contentPage", "/WEB-INF/views/pages/admin/lecturer/list.jsp");
        request.setAttribute("pageTitle", "Lecturers");

        return "/WEB-INF/views/layout/layout.jsp";
    }
}
