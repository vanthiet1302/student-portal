<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25-Dec-25
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Danh sách giảng viên</title>
    <link rel="stylesheet" href="${pageScope.request.contextPath}/css/style.css">
</head>
<body>

    <table>
        <h1>Danh sách các giảng viên</h1>
        <tr>
            <th>Họ tên</th>
            <th>Đơn vị công tác</th>
        </tr>
    <c:forEach var="lecturer" items="${lecturers}">
        <tr>
            <td><a href="#">${lecturer.name}</a></td>
            <td>${lecturer.department} </td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
