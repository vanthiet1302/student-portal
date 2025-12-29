package dev.nlu.portal.command.adminCommand;

import dev.nlu.portal.command.Command;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AdminStudentsCommand implements Command{
    StudentService studentService = new StudentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var sesstion = request.getSession(false);
        List<Student> students = studentService.findAll();
        sesstion.setAttribute("studenst", students);

        return "/WEB-INF/views/admin/students.jsp";
    }
}
