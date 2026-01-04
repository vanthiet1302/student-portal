<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jan-26
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="i18n.messages"/>

<div id="sidebar" class="sidebar">
    <ul class="nav flex-column pt-3">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">
                <span data-i18n="menu.dashboard">
                    <fmt:message key="menu.dashboard"/>
                </span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/students">
                <span data-i18n="sidebar.menu.label.student">
                    <fmt:message key="sidebar.menu.label.student"/>
                </span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/lecturers">
                <span data-i18n="sidebar.menu.label.lecturer">
                    <fmt:message key="sidebar.menu.label.lecturer"/>
                </span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/courses">
                <span data-i18n="sidebar.menu.label.course">
                    <fmt:message key="sidebar.menu.label.course"/>
                </span>
            </a>
        </li>
    </ul>
</div>
