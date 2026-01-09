<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.faculty.edit"><fmt:message key="admin.title.faculty.edit"/></h1>
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

<c:if test="${not empty requestScope.faculty}">
    <c:set var="faculty" value="${requestScope.faculty}"/>
    <form action="${pageContext.request.contextPath}/admin/faculties/edit" method="post">
        <input type="hidden" name="facultyId" value="${faculty.id}">

        <div class="mb-3">
            <label for="code" class="form-label" data-i18n="form.faculty.code"><fmt:message key="form.faculty.code"/></label>
            <input type="text"
                   name="code"
                   class="form-control"
                   id="code"
                   value="${faculty.code}"
                   required
                   maxlength="10">
        </div>

        <div class="mb-3">
            <label for="name" class="form-label" data-i18n="form.faculty.name"><fmt:message key="form.faculty.name"/></label>
            <input type="text"
                   name="name"
                   class="form-control"
                   id="name"
                   value="${faculty.name}"
                   required
                   maxlength="100">
        </div>

        <div class="mb-3">
            <label for="establishedDate" class="form-label" data-i18n="form.faculty.establishedDate"><fmt:message key="form.faculty.establishedDate"/></label>
            <input type="date"
                   name="establishedDate"
                   class="form-control"
                   id="establishedDate"
                   value="${faculty.establishedDate}">
        </div>

        <div>
            <button type="submit" class="btn btn-primary" data-i18n="button.submit"><fmt:message key="button.submit"/></button>
            <a href="${pageContext.request.contextPath}/admin/faculties" class="btn btn-secondary" data-i18n="admin.faculty.back"><fmt:message key="admin.faculty.back"/></a>
            <form action="${pageContext.request.contextPath}/admin/faculties/delete" method="post" style="display:inline;">
                <input type="hidden" name="facultyId" value="${faculty.id}">
                <button type="submit" class="btn btn-danger" onclick="return confirm('<fmt:message key="admin.faculty.list.confirm"/>')" data-i18n="admin.faculty.list.delete"><fmt:message key="admin.faculty.list.delete"/></button>
            </form>
        </div>
    </form>
</c:if>
<c:if test="${empty requestScope.faculty}">
    <div class="alert alert-warning">
        <span data-i18n="admin.faculty.error"><fmt:message key="admin.faculty.error"/></span> <a href="${pageContext.request.contextPath}/admin/faculties"><fmt:message key="admin.faculty.back"/></a>
    </div>
</c:if>

