<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05-Jan-26
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<meta charset="UTF-8">
<title data-i18n="app.title"><fmt:message key="app.title"/></title>

<!-- Bootstrap 5 -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
      rel="stylesheet">

<style>
    .sidebar {
        width: 240px;
        transition: width .3s;
        overflow: hidden;
    }

    .app.collapsed .sidebar {
        width: 64px;
    }

    .app.collapsed .sidebar .text {
        display: none;
    }
.navbar {
    --bs-navbar-bg: transparent;
    background-image: linear-gradient(90deg, #98d7c2 0%, #b4d5e3 100%);
}
</style>
