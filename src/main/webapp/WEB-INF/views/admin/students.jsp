<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26-Dec-25
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Quản lý sinh viên</title>
</head>
<body>

<h1>DANH SÁCH SINH VIÊN</h1>
<table>
    <tr>
        <th>STT</th>
        <th>MSSV</th>
        <th>Họ và tên</th>
    </tr>
    <c:forEach var="student" items="${sessionScope.students}" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${student.studentCode}</td>
            <td>${student.fullName}</td>
        </tr>
    </c:forEach>
</table>

<p>
    <a href="${pageContext.request.contextPath}/admin/addStudent">
        <b>[+] Thêm sinh viên</b>
    </a>
</p>

</body>
</html>