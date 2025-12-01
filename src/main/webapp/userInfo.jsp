<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/7/2025
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dev.nlu.portal.bean.Student" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thông tin Sinh viên</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="share/header.jsp" />

        <main class="content-container">

            <%-- Lấy đối tượng Student từ Session --%>
            <c:set var="student" value="${sessionScope.loggedInStudent}" />
            <c:set var="schedule" value="${sessionScope.studentSchedule}" />

            <div class="welcome-section">
                <span class="icon">
                    <i class="fas fa-smile"></i>
                </span>
                <h1>Xin chào <c:out value="${student.hoTen}" /></h1>
                <p><span class="icon">📅</span> Chủ nhật, 30 Tháng 11</p>
            </div>

            <div class="schedule-section">
                <%-- Vòng lặp JSTL để hiển thị lịch học --%>
                <c:forEach var="item" items="${schedule}">
                    <div class="schedule-item">
                        <div class="time-block">
                            <c:out value="${item.time}" />
                        </div>
                        <div class="course-info">
                            <strong><c:out value="${item.courseName}" /></strong>
                            <small><c:out value="${item.location}" /></small>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <hr/>

            <%-- Khối Thông tin Cá nhân --%>
            <div class="profile-card">
                <div class="avatar-placeholder"></div>
                <div class="student-details">
                    <p><strong>Mã sinh viên:</strong> <c:out value="${student.maSV}" /></p>
                    <p><strong>Họ tên:</strong> <c:out value="${student.hoTen}" /></p>
                    <p><strong>Ngày sinh:</strong> <c:out value="${student.ngaySinh}" /></p>
                    <p><strong>Giới tính:</strong> <c:out value="${student.gioiTinh}" /></p>
                    <p><strong>Trạng thái:</strong> <c:out value="${student.trangThai}" /></p>
                </div>
                <div class="qr-code-placeholder">
                    QR
                </div>
                <div class="edit-icon">
                    ✏️
                </div>
            </div>
        </main>
    </body>
</html>