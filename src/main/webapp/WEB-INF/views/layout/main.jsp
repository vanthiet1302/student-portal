<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05-Jan-26
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<!DOCTYPE html>
<html data-bs-theme="${sessionScope.theme != null ? sessionScope.theme : 'light'}">

<head>
    <jsp:include page="head.jsp"/>
</head>

<body>
<div class="app d-flex vh-100">
    <jsp:include page="sidebar.jsp"/>

    <div class="main flex-grow-1 d-flex flex-column">
        <jsp:include page="navbar.jsp"/>

        <main class="content flex-grow-1 p-3 overflow-auto">
            <jsp:include page="${requestScope.content}"/>
        </main>
    </div>

</div>

<jsp:include page="scripts.jsp"/>
</body>
</html>
