<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30-Dec-25
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="i18n.messages"/>

<h1><fmt:message key="page.lecture.list.title"/></h1>

<table class="table table-striped-columns">
    <tr>
        <th><fmt:message key="page.lecture.table.col1"/></th>
        <th><fmt:message key="page.lecture.table.col2"/></th>
        <th><fmt:message key="page.lecture.table.col3" var="avatarAlt"/></th>
        <th><fmt:message key="page.lecture.table.col4"/></th>
    </tr>
    <c:forEach var="lecturer" items="${sessionScope.lecturers}">
        <tr>
            <td>${lecturer.department}</td>
            <td>${lecturer.fullName}</td>
            <td>
                <img src="${lecturer.user.avatarUrl}"
                     alt="${avatarAlt}"
                     class="img-fluid rounded"
                     width="40"/>
            </td>
            <td>
                <a href="#">
                    <fmt:message key="page.lecture.table.col4"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="d-grid gap-2 d-md-block">
    <button class="btn btn-primary" type="button">
        <fmt:message key="page.admin.lecturers.add="/>
    </button>
</div>



