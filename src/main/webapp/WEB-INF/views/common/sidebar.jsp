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
            <a class="nav-link" href="#">
                <span data-i18n="menu.dashboard">
                    <fmt:message key="menu.dashboard"/>
                </span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <span data-i18n="menu.student">
                    <fmt:message key="menu.student"/>
                </span>
            </a>
        </li>
    </ul>
</div>
