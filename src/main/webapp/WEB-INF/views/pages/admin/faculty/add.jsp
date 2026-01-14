
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.faculty.add"><fmt:message key="admin.title.faculty.add"/></h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger" data-i18n="${error}">
        <fmt:message key="${error}"/>
    </div>
</c:if>
<c:if test="${not empty success}">
    <div class="alert alert-success" data-i18n="${success}">
        <fmt:message key="${success}"/>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/admin/faculties/add" method="post">
    <div class="mb-3">
        <label for="code" class="form-label" data-i18n="form.faculty.code"><fmt:message key="form.faculty.code"/></label>
        <input type="text"
               name="code"
               class="form-control"
               id="code"
               data-i18n-placeholder="form.faculty.code.placeholder"
               placeholder="<fmt:message key='form.faculty.code.placeholder'/>"
               value="${code}"
               required
               maxlength="10">
    </div>

    <div class="mb-3">
        <label for="name" class="form-label" data-i18n="form.faculty.name"><fmt:message key="form.faculty.name"/></label>
        <input type="text"
               name="name"
               class="form-control"
               id="name"
               data-i18n-placeholder="form.faculty.name.placeholder"
               placeholder="<fmt:message key='form.faculty.name.placeholder'/>"
               value="${name}"
               required
               maxlength="100">
    </div>

    <div class="mb-3">
        <label for="establishedDate" class="form-label" data-i18n="form.faculty.establishedDate"><fmt:message key="form.faculty.establishedDate"/></label>
        <input type="date"
               name="establishedDate"
               class="form-control"
               id="establishedDate"
               value="${establishedDate}">
    </div>

    <div>
        <button type="submit" class="btn btn-primary" data-i18n="admin.faculty.add"><fmt:message key="admin.faculty.add"/></button>
        <a href="${pageContext.request.contextPath}/admin/faculties" class="btn btn-secondary" data-i18n="admin.faculty.back"><fmt:message key="admin.faculty.back"/></a>
    </div>

</form>
