<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.course.add"><fmt:message key="admin.title.course.add"/></h1>
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

<form action="${pageContext.request.contextPath}/admin/courses/add" method="post">
    <div class="mb-3">
        <label for="code" class="form-label" data-i18n="form.course.code"><fmt:message key="form.course.code"/></label>
        <input type="text"
               name="code"
               class="form-control"
               id="code"
               data-i18n-placeholder="form.course.code.placeholder"
               placeholder="<fmt:message key='form.course.code.placeholder'/>"
               value="${code}"
               required>
    </div>

    <div class="mb-3">
        <label for="name" class="form-label" data-i18n="form.course.name"><fmt:message key="form.course.name"/></label>
        <input type="text"
               name="name"
               class="form-control"
               id="name"
               data-i18n-placeholder="form.course.name.placeholder"
               placeholder="<fmt:message key='form.course.name.placeholder'/>"
               value="${name}"
               required>
    </div>

    <div class="row">
        <div class="col-md-4 mb-3">
            <label for="soTinChi" class="form-label" data-i18n="form.course.soTinChi"><fmt:message key="form.course.soTinChi"/></label>
            <input type="number"
                   name="soTinChi"
                   class="form-control"
                   id="soTinChi"
                   min="0"
                   value="${soTinChi}">
        </div>
        <div class="col-md-4 mb-3">
            <label for="lyThuyet" class="form-label" data-i18n="form.course.lyThuyet"><fmt:message key="form.course.lyThuyet"/></label>
            <input type="number"
                   name="lyThuyet"
                   class="form-control"
                   id="lyThuyet"
                   min="0"
                   value="${lyThuyet}">
        </div>
        <div class="col-md-4 mb-3">
            <label for="thucHanh" class="form-label" data-i18n="form.course.thucHanh"><fmt:message key="form.course.thucHanh"/></label>
            <input type="number"
                   name="thucHanh"
                   class="form-control"
                   id="thucHanh"
                   min="0"
                   value="${thucHanh}">
        </div>
    </div>

    <div class="mb-3">
        <label for="url" class="form-label" data-i18n="form.course.url"><fmt:message key="form.course.url"/></label>
        <input type="url"
               name="url"
               class="form-control"
               id="url"
               data-i18n-placeholder="form.course.url.placeholder"
               placeholder="<fmt:message key='form.course.url.placeholder'/>"
               value="${url}">
    </div>

    <div>
        <button type="submit" class="btn btn-primary" data-i18n="admin.course.add"><fmt:message key="admin.course.add"/></button>
        <a href="${pageContext.request.contextPath}/admin/courses" class="btn btn-secondary" data-i18n="admin.course.back"><fmt:message key="admin.course.back"/></a>
    </div>

</form>