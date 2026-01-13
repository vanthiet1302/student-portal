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
               href="#">
                <span class="text" data-i18n="menu.home">
                    <fmt:message key="menu.home"/>
                </span>
            </a>
        </li>

    </ul>
    <div class="nav-item">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="Bootstrap" width="100" height="100" />
    </div>
</aside>
