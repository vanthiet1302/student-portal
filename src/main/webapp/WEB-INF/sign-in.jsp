<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04-Jan-26
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="i18n.messages"/>
<html>
<head>
    <title>
        <fmt:message key="page.signin"/>
    </title>
    <script>
        const contextPath = '${pageContext.request.contextPath}';
        const defaultLang = '${sessionScope.lang != null ? sessionScope.lang : "vi"}';
    </script>
    <script src="${pageContext.request.contextPath}/assets/js/i18n.js"></script>
    <link href="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="page.signin"/> </h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
            ${error}
    </div>
</c:if>
<form method="POST" action="${pageContext.request.contextPath}/sign-in">
    <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input type="text" name="username"class="form-control" id="username" required>
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" name="password" class="form-control" id="password">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
