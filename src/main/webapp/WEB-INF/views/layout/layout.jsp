<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jan-26
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="pageTitle" value="${requestScope.pageTitle}" scope="request"/>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<body>
<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>
<jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>

<div class="main-content">
    <main class="container-fluid p-4">
        <c:choose>
            <c:when test="${not empty requestScope.contentPage}">
                <jsp:include page="${requestScope.contentPage}"/>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning">Không tìm thấy nội dung trang.</div>
            </c:otherwise>
        </c:choose>
    </main>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
