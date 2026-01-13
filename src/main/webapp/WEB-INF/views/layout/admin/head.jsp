<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05-Jan-26
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<meta charset="UTF-8">

<title data-i18n="${requestScope.title}">
    <c:choose>
        <c:when test="${not empty requestScope.title}">
            <fmt:message key="${requestScope.title}"/>
        </c:when>
        <c:otherwise>
            <fmt:message key="page.default.title"/>
        </c:otherwise>
    </c:choose>
</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
      rel="stylesheet">

<link href="${pageContext.request.contextPath}/assets/css/admin.css"
      rel="stylesheet">
 <link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.png">