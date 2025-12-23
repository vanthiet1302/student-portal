package dev.nlu.portal.controller.student;

import dev.nlu.portal.dto.GradeItemDTO;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.GradeServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GradesServlet extends HttpServlet {
    private final GradeServiceImpl gradeService = new GradeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        List<GradeItemDTO> gradeItems = null;
        if (student != null && student.getId() != null) {
            gradeItems = gradeService.getStudentGradeItems(student.getId());
        }
        req.setAttribute("gradeItems", gradeItems);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/student/grades.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
