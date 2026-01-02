<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 31-Dec-25
  Time: 1:24 PM
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

    <title><fmt:message key="home.title"/></title>
    <script>
        const contextPath = '${pageContext.request.contextPath}';
        const defaultLang = '${sessionScope.lang != null ? sessionScope.lang : "vi"}';
    </script>
    <script src="${pageContext.request.contextPath}/assets/js/i18n.js"></script>
    <link href="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/common.css" rel="stylesheet"/>
</head>

<body>

<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>
<jsp:include page="./WEB-INF/views/common/sidebar.jsp"/>

<div class="main-content">
    <button>
        <fmt:message key="button.login"/>
    </button>
    <br>
    <a href="${pageContext.request.contextPath}/change-language?lang=vi">
        🇻🇳 Tiếng Việt
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/change-language?lang=en">
        🇺🇸 English
    </a>

    <h1 data-i18n="home.title">
        <fmt:message key="home.title"/>
    </h1>

    <li data-i18n="menu.student">
        <fmt:message key="menu.student"/>
    </li>

    <button data-i18n="button.login">
        <fmt:message key="button.login"/>
    </button>

    <button onclick="changeLanguage('vi')">🇻🇳</button>
    <button onclick="changeLanguage('en')">🇺🇸</button>
</div>

<script src="${pageContext.request.contextPath}/assets/js/common.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>