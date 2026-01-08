<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07-Jan-26
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.student.add"><fmt:message key="admin.title.student.add"/></h1>
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

<form action="${pageContext.request.contextPath}/admin/students/add" method="post">
    <h3 data-i18n="form.student.account.info">
        <fmt:message key="form.student.account.info"/>
    </h3>
    <div class="mb-3">
        <label for="username" class="form-label" data-i18n="form.student.username"><fmt:message key="form.student.username"/></label>
        <input type="text"
               name="username"
               class="form-control"
               id="username"
               required>
    </div>

    <h3 data-i18n="form.student.personal.info"><fmt:message key="form.student.personal.info"/></h3>
    <div class="mb-3">
        <label for="lastName" class="form-label" data-i18n="form.student.lastName"><fmt:message key="form.student.lastName"/></label>
        <input type="text"
               name="lastName"
               class="form-control"
               id="lastName"
               required>
    </div>
    <div class="mb-3">
        <label for="firstName" class="form-label" data-i18n="form.student.firstName"><fmt:message key="form.student.firstName"/></label>
        <input type="text"
               name="firstName"
               class="form-control"
               id="firstName"
               required>
    </div>
    <div class="mb-3">
        <label for="dob" class="form-label" data-i18n="form.student.dob"><fmt:message key="form.student.dob"/></label>
        <input type="date"
               name="dob"
               class="form-control"
               id="dob">
    </div>
    <div class="mb-3">
        <label for="pob" class="form-label" data-i18n="form.student.pob"><fmt:message key="form.student.pob"/></label>
        <input type="text"
               name="pob"
               class="form-control"
               id="pob">
    </div>
    <div class="mb-3">
        <label for="gender" class="form-label" data-i18n="form.student.gender"><fmt:message key="form.student.gender"/></label>
        <select name="gender" id="gender" class="form-control">
            <option value="" data-i18n="form.student.gender.select"><fmt:message key="form.student.gender.select"/></option>
            <option value="Nam" data-i18n="form.student.gender.male"><fmt:message key="form.student.gender.male"/></option>
            <option value="Nữ" data-i18n="form.student.gender.female"><fmt:message key="form.student.gender.female"/></option>
            <option value="Khác" data-i18n="form.student.gender.other"><fmt:message key="form.student.gender.other"/></option>
        </select>
    </div>
    <div class="mb-3">
        <label for="phoneNumber" class="form-label" data-i18n="form.student.phoneNumber"><fmt:message key="form.student.phoneNumber"/></label>
        <input type="text"
               name="phoneNumber"
               class="form-control"
               id="phoneNumber">
    </div>
    <div class="mb-3">
        <label for="citizenId" class="form-label" data-i18n="form.student.citizenId"><fmt:message key="form.student.citizenId"/></label>
        <input type="text"
               name="citizenId"
               class="form-control"
               id="citizenId">
    </div>
    <div class="mb-3">
        <label for="nation" class="form-label" data-i18n="form.student.nation"><fmt:message key="form.student.nation"/></label>
        <input type="text"
               name="nation"
               class="form-control"
               id="nation">
    </div>
    <div class="mb-3">
        <label for="religion" class="form-label" data-i18n="form.student.religion"><fmt:message key="form.student.religion"/></label>
        <input type="text"
               name="religion"
               class="form-control"
               id="religion">
    </div>
    <div class="mb-3">
        <label for="nationality" class="form-label" data-i18n="form.student.nationality"><fmt:message key="form.student.nationality"/></label>
        <input type="text"
               name="nationality"
               class="form-control"
               id="nationality">
    </div>
    <div class="mb-3">
        <label for="address" class="form-label" data-i18n="form.student.address"><fmt:message key="form.student.address"/></label>
        <textarea name="address" class="form-control" id="address" rows="3"></textarea>
    </div>

    <h3 data-i18n="form.student.academic.info"><fmt:message key="form.student.academic.info"/></h3>
    <div class="mb-3">
        <label for="classId" class="form-label" data-i18n="form.student.classId"><fmt:message key="form.student.classId"/></label>
        <input type="text"
               name="classId"
               class="form-control"
               id="classId">
    </div>

    <div>
        <button type="submit" class="btn btn-primary" data-i18n="admin.student.add"><fmt:message key="admin.student.add"/></button>
        <a href="${pageContext.request.contextPath}/admin/students" class="btn btn-secondary" data-i18n="admin.student.back"><fmt:message key="admin.student.back"/></a>
    </div>

</form>