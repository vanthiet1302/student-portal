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
    <title>Users</title>
</head>
<body>
    <c:forEach var = "user" items="${sessionScope.userId}" >
        <p><c:out value="${user}"></c:out> </p>
    </c:forEach>

</body>
</html>
