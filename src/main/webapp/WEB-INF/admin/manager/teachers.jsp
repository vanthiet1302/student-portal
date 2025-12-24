<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24-Dec-25
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head>
    <title>Quản lí Giảng viên</title>
</head>
<body>
<%-- Hiển thị danh sách giảng viên --%>
<%-- Có thể thêm xóa sửa cập nhật thông tin giảng viên --%>
<h2>Danh sách Giảng viên</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Họ tên</th>
        <th>Chuyên ngành</th>
    </tr>
    <c:forEach var="t" items="${teachers}">
        <tr>
            <td>${t.id}</td>
            <td>${t.fullName}</td>
            <td>${t.department}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
