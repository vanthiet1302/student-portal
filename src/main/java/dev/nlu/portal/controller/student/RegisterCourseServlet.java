package dev.nlu.portal.controller.student;

import dev.nlu.portal.dao.ClassDAOImpl;
import dev.nlu.portal.dao.ClassRegistrationDAOImpl;
import dev.nlu.portal.dao.CourseDAOImpl;
import dev.nlu.portal.model.Class;
import dev.nlu.portal.model.ClassRegistration;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

public class RegisterCourseServlet extends HttpServlet {
    private ClassRegistrationDAOImpl classRegDAO = new ClassRegistrationDAOImpl();
    private ClassDAOImpl classDAO = new ClassDAOImpl();
    private StudentServiceImpl studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classIdStr = req.getParameter("classId");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || classIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/student/courses");
            return;
        }

        try {
            long classId = Long.parseLong(classIdStr);
            long studentId = user.getId();
            
            Class courseClass = classDAO.findById(classId);
            if (courseClass == null) {
                session.setAttribute("error", "Lớp học không tồn tại.");
                resp.sendRedirect(req.getContextPath() + "/student/courses");
                return;
            }

            // Check if student already registered for this class
            String checkSql = "SELECT id FROM class_registrations WHERE student_id = ? AND class_id = ? AND status = 'REGISTERED'";
            // For now, proceed with simple registration (ideally use a DAO method to check)
            
            // Increment current_students if not at max
            if (courseClass.getCurrentStudents() < courseClass.getMaxStudents()) {
                ClassRegistration reg = new ClassRegistration();
                reg.setStudentId(studentId);
                reg.setClassId(classId);
                reg.setRegisteredAt(LocalDateTime.now());
                classRegDAO.save(reg);
                
                // Increment current_students
                courseClass.setCurrentStudents(courseClass.getCurrentStudents() + 1);
                classDAO.update(courseClass);
                
                session.setAttribute("success", "Đăng ký môn học thành công!");
            } else {
                session.setAttribute("error", "Lớp học đã đầy. Không thể đăng ký.");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID lớp học không hợp lệ.");
        }

        resp.sendRedirect(req.getContextPath() + "/student/courses");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
