<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2025
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Load all user</title>
</head>
<body>
    <h1>Load User thành công!</h1>
    <h2>Xin chào ${sessionScope.user.username}</h2>

    <a href="profile">Xem thông tin sinh viên</a><br>
    <a href="logout">Đăng xuất</a>
    <c:set var="students" value="${sessionScope.allStudent}"></c:set>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Age</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>
                    <c:out value="${student.id}"></c:out>
                </td>
                <td>
                    <c:out value="${student.name}"></c:out>
                </td>
                <td>
                    <c:out value="${student.age}"></c:out>
                </td>
            </tr>
        </c:forEach>

    </table>


</body>
</html>
