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

public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Demo data; replace with service/DAO later
        List<Map<String, String>> scheduleItems = new ArrayList<>();
        scheduleItems.add(makeItem("CTDL & GT", "Thứ 2, 22/12", "08:00 - 10:00", "A101", "TS. Nguyễn Văn A", "Học"));
        scheduleItems.add(makeItem("CSDL", "Thứ 4, 24/12", "13:30 - 15:30", "B204", "ThS. Trần Thị B", "Học"));
        scheduleItems.add(makeItem("Toán rời rạc", "Thứ 6, 26/12", "09:00 - 11:00", "C303", "TS. Lê Văn C", "Thi"));
        req.setAttribute("scheduleItems", scheduleItems);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/student/schedule.jsp");
        rd.forward(req, resp);
    }

    private Map<String, String> makeItem(String course, String date, String time, String room, String lecturer, String type) {
        Map<String, String> m = new HashMap<>();
        m.put("course", course);
        m.put("date", date);
        m.put("time", time);
        m.put("room", room);
        m.put("lecturer", lecturer);
        m.put("type", type);
        return m;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
