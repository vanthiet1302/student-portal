package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.controller.command.Command;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AdminStudentsCommand implements Command{
    StudentService studentService = new StudentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> students = studentService.getAll();
        request.setAttribute("students", students);
        request.setAttribute("contentPage", "/WEB-INF/views/pages/admin/students/list.jsp");
        request.setAttribute("pageTitle", "Students");

        return "/WEB-INF/views/layout/layout.jsp";
    }
}
