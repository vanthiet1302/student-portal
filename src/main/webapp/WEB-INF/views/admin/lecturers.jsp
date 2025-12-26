<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26-Dec-25
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý giảng viên</title>
</head>
<body>

<h1>DANH SÁCH GIẢNG VIÊN</h1>

<table border="1" cellpadding="10" cellspacing="0" width="80%">
    <thead>
    <tr bgcolor="#eeeeee">
        <th align="center" width="5%">STT</th>
        <th align="left">Họ và tên</th>
        <th align="left">Đơn vị công tác</th>
        <th align="center" width="15%">Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lec" items="${lecturers}" varStatus="status">
        <tr>
            <td align="center">${status.count}</td>
            <td>
                <strong>${lec.fullName}</strong>
            </td>
            <td>${lec.department}</td>
            <td align="center">
                <a href="${pageContext.request.contextPath}/admin/detailLecturer?id=${lec.id}">Xem hồ sơ</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty lecturers}">
        <tr>
            <td colspan="4" align="center">Chưa có giảng viên nào trong danh sách.</td>
        </tr>
    </c:if>
    </tbody>
</table>

<p>
    <a href="${pageContext.request.contextPath}/admin/addLecturer">
        <b>[+] Thêm giảng viên mới</b>
    </a>
</p>

</body>
</html>