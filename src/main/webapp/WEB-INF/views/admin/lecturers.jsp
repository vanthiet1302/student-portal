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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
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
    <button onclick="window.location.href='${pageContext.request.contextPath}/admin/addLecturer'"
        class="btn btn-primary">
    Thêm Giảng Viên
</button>
</p>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>