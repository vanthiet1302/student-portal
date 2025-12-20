package dev.nlu.portal.controller.student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Demo data; replace with service/DAO later
        List<Map<String, Object>> gradeItems = new ArrayList<>();
        gradeItems.add(makeItem("CTDL & GT", 3, 8.5, 8.0, "HK1 2025"));
        gradeItems.add(makeItem("CSDL", 4, 7.0, 8.2, "HK1 2025"));
        gradeItems.add(makeItem("Lập trình Java", 3, 9.0, 8.8, "HK1 2025"));
        req.setAttribute("gradeItems", gradeItems);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/student/grades.jsp");
        rd.forward(req, resp);
    }

    private Map<String, Object> makeItem(String course, int credits, double mid, double finalExam, String term) {
        Map<String, Object> m = new HashMap<>();
        m.put("course", course);
        m.put("credits", credits);
        m.put("mid", mid);
        m.put("final", finalExam);
        double total = Math.round((mid * 0.4 + finalExam * 0.6) * 100.0) / 100.0;
        m.put("total", total);
        m.put("rank", rank(total));
        m.put("term", term);
        return m;
    }

    private String rank(double total) {
        if (total >= 8.5) return "Giỏi";
        if (total >= 7.0) return "Khá";
        if (total >= 5.5) return "Trung bình";
        return "Yếu";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
