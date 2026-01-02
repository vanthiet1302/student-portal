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
    <link href="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<h1>DANH SÁCH GIẢNG VIÊN</h1>

<div class="border border-primary m-3">
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>STT</th>
            <th>Họ và tên</th>
            <th>Đơn vị công tác</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="lec" items="${lecturers}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>
                    <strong>${lec.fullName}</strong>
                </td>
                <td>${lec.department}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/lecturers?id=${lec.id}">Xem hồ sơ</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty lecturers}">
            <tr>
                <td>Chưa có giảng viên nào trong danh sách.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>


<button
        onclick="window.location.href='${pageContext.request.contextPath}/admin/addLecturer'"
        class="btn btn-primary">
        Thêm Giảng Viên</button>


<script src="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>