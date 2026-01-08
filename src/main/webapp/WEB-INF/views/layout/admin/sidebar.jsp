<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05-Jan-26
  Time: 11:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<aside class="sidebar bg-body-tertiary border-end">
    <ul class="nav flex-column p-2 gap-1">

        <li class="nav-item">
            <a class="nav-link d-flex align-items-center gap-2"
               href="${pageContext.request.contextPath}/admin/dashboard">
                <i class="bi bi-house"></i>
                <span class="text" data-i18n="menu.home">
                        <fmt:message key="menu.home"/>
                </span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link d-flex align-items-center gap-2"
               href="${pageContext.request.contextPath}/admin/students">
                <i class="bi bi-person-video3"></i>
                <span class="text" data-i18n="menu.student">
                    <fmt:message key="menu.student"/>
                </span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link d-flex align-items-center gap-2"
               href="${pageContext.request.contextPath}/admin/lecturers">
                <i class="bi bi-person-rolodex"></i>
                <span class="text" data-i18n="menu.lecturer">
                    <fmt:message key="menu.lecturer"/>
                </span>
            </a>
        </li>
    </ul>
</aside>
