<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25-Dec-25
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="vi">
<head>
    <title>Admin Dashboard</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <div style="display: flex; align-items: center; justify-content: space-between; gap: 12px">
        <div>
            <div class="title">Bảng điều khiển Admin</div>
            <p class="subtitle">
                Xin chào, <strong>${sessionScope.user.username}</strong>
            </p>
        </div>
        <div>
            <a class="btn"
               style="background: #ef4444; color: #fff; border-color: #ef4444"
               href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>
    </div>
    <base href="${pageContext.request.contextPath}/">
</header>

<main>
    <div class="grid">
        <div class="card">
            <h3>Quản lí giảng viên</h3>
            <p>Xem danh sách, duyệt hồ sơ và cập nhật thông tin giảng viên.</p>
            <div class="actions">
                <a class="btn primary" href="admin/lecturers">
                    Đi tới trang
                </a>
                <span class="badge">Tác vụ</span>
            </div>
        </div>

    </div>
</main>
</body>
</html>