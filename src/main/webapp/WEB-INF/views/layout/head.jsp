<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05-Jan-26
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="messages"/>

<meta charset="UTF-8">
<title data-i18n="app.title"><fmt:message key="app.title"/></title>

<!-- Bootstrap 5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<!-- Bootstrap Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
      rel="stylesheet">

<!-- App CSS -->
<link href="${pageContext.request.contextPath}/assets/css/app.css" rel="stylesheet">
