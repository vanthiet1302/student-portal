 <%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04-Jan-26
  Time: 2:23 PM
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
<h1><fmt:message key="page.signup"/></h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
            ${error}
    </div>
</c:if>
<c:if test="${not empty success}">
    <div class="alert alert-success">
            ${success}
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/sign-up" method="post">
    <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input type="text"
               name="username"
               class="form-control"
               id="username"
               required>
    </div>
    <div class="mb-3">
        <label for="password"
               class="form-label">Password</label>
        <input type="password"
               name="password"
               class="form-control"
               id="password"
               required>
    </div>
    <div class="mb-3">
        <label for="confirmPassword"
               class="form-label">Confirm Password</label>
        <input type="password"
               name="confirmPassword"
               class="form-control"
               id="confirmPassword"
               required>
    </div>
    <div class="mb-3">
        <label for="email"
               class="form-label">Email</label>
        <input type="email"
               name="email"
               class="form-control"
               id="email"
               required>
    </div>
    <div class="mb-3">
        <label for="lastName"
               class="form-label">Last name</label>
        <input type="text"
               name="lastName"
               class="form-control"
               id="lastName"
               required>
    </div>
    <div class="mb-3">
        <label for="firstName"
               class="form-label">First name</label>
        <input type="text"
               name="firstName"
               class="form-control"
               id="firstName"
               required>
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input"
               type="radio" name="role"
               id="inlineRadio1"
               value="STUDENT"
               required>
        <label class="form-check-label" for="inlineRadio1">Student</label>
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input"
               type="radio"
               name="role"
               id="inlineRadio2"
               value="LECTURER">
        <label class="form-check-label" for="inlineRadio2">Lecturer</label>
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input"
               type="radio"
               name="role"
               id="inlineRadio3"
               value="ADMIN">
        <label class="form-check-label" for="inlineRadio3">Admin</label>
    </div>
    <div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>

</form>
<script src="${pageContext.request.contextPath}/assets/js/common.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
