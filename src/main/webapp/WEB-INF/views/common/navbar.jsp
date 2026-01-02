
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jan-26
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<nav class="navbar navbar-expand navbar-dark bg-dark fixed-top">

    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="toggleSidebar" type="button">
        <span style="font-size: 1.5rem; color: white;">&#9776;</span>
    </button>

    <a class="navbar-brand ps-3" href="${pageContext.request.contextPath}/">
        <fmt:message key="page.default.title"/>
    </a>

    <div class="ms-auto d-flex align-items-center pe-3">
        <div class="dropdown">
            <button class="btn btn-outline-light btn-sm dropdown-toggle" type="button" id="languageDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                ${sessionScope.lang == 'en' ? '🇺🇸 EN' : '🇻🇳 VI'}
            </button>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="languageDropdown">
                <li><a class="dropdown-item ${sessionScope.lang == 'vi' ? 'active' : ''}" href="${pageContext.request.contextPath}/change-language?lang=vi">🇻🇳 Tiếng Việt</a></li>
                <li><a class="dropdown-item ${sessionScope.lang == 'en' ? 'active' : ''}" href="${pageContext.request.contextPath}/change-language?lang=en">🇺🇸 English</a></li>
            </ul>
        </div>
    </div>
</nav>
