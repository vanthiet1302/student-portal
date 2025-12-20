package dev.nlu.portal.controller.student;

import dev.nlu.portal.dao.ClassDAOImpl;
import dev.nlu.portal.dao.CourseDAOImpl;
import dev.nlu.portal.model.Class;
import dev.nlu.portal.model.Course;
import dev.nlu.portal.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoursesServlet extends HttpServlet {
    private ClassDAOImpl classDAO = new ClassDAOImpl();
    private CourseDAOImpl courseDAO = new CourseDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Class> classes = classDAO.findAll();
        Map<Long, Course> courseMap = new HashMap<>();
        
        // Cache courses by ID
        for (Class c : classes) {
            if (!courseMap.containsKey(c.getCourseId())) {
                courseMap.put(c.getCourseId(), courseDAO.findById(c.getCourseId()));
            }
        }
        
        req.setAttribute("classes", classes);
        req.setAttribute("courseMap", courseMap);
        
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/student/courses.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
