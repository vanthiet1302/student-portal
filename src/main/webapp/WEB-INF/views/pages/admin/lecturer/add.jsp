<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.lecturer.add"><fmt:message key="admin.title.lecturer.add"/></h1>
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

<form action="${pageContext.request.contextPath}/admin/lecturers/add" method="post">
    <h3 data-i18n="form.lecturer.account.info">
        <fmt:message key="form.lecturer.account.info"/>
    </h3>
    <div class="mb-3">
        <label for="username" class="form-label" data-i18n="form.lecturer.username"><fmt:message key="form.lecturer.username"/></label>
        <input type="text"
               name="username"
               class="form-control"
               id="username"
               data-i18n-placeholder="form.lecturer.username.placeholder"
               placeholder="<fmt:message key='form.lecturer.username.placeholder'/>"
               value="${username}"
               required>
    </div>
    <div class="mb-3">
        <label for="emailWork" class="form-label" data-i18n="form.lecturer.emailWork"><fmt:message key="form.lecturer.emailWork"/></label>
        <input type="email"
               name="emailWork"
               class="form-control"
               id="emailWork"
               data-i18n-placeholder="form.lecturer.emailWork.placeholder"
               placeholder="<fmt:message key='form.lecturer.emailWork.placeholder'/>"
               value="${emailWork}"
               required>
    </div>
    <div class="mb-3">
        <label for="emailPersonal" class="form-label" data-i18n="form.lecturer.emailPersonal"><fmt:message key="form.lecturer.emailPersonal"/></label>
        <input type="email"
               name="emailPersonal"
               class="form-control"
               id="emailPersonal"
               data-i18n-placeholder="form.lecturer.emailPersonal.placeholder"
               placeholder="<fmt:message key='form.lecturer.emailPersonal.placeholder'/>"
               value="${emailPersonal}">
    </div>

    <h3 data-i18n="form.lecturer.personal.info"><fmt:message key="form.lecturer.personal.info"/></h3>
    <div class="mb-3">
        <label for="fullName" class="form-label" data-i18n="form.lecturer.fullName"><fmt:message key="form.lecturer.fullName"/></label>
        <input type="text"
               name="fullName"
               class="form-control"
               id="fullName"
               data-i18n-placeholder="form.lecturer.fullName.placeholder"
               placeholder="<fmt:message key='form.lecturer.fullName.placeholder'/>"
               value="${fullName}"
               required>
    </div>
    <div class="mb-3">
        <label for="birthYear" class="form-label" data-i18n="form.lecturer.birthYear"><fmt:message key="form.lecturer.birthYear"/></label>
        <input type="number"
               name="birthYear"
               class="form-control"
               id="birthYear"
               data-i18n-placeholder="form.lecturer.birthYear.placeholder"
               placeholder="<fmt:message key='form.lecturer.birthYear.placeholder'/>"
               value="${birthYear}">
    </div>
    <div class="mb-3">
        <label for="gender" class="form-label" data-i18n="form.lecturer.gender"><fmt:message key="form.lecturer.gender"/></label>
        <select name="gender" id="gender" class="form-control">
            <option value="">-- Chọn giới tính --</option>
            <option value="Nam" data-i18n="form.lecturer.gender.male"><fmt:message key="form.lecturer.gender.male"/></option>
            <option value="Nữ" data-i18n="form.lecturer.gender.female"><fmt:message key="form.lecturer.gender.female"/></option>
            <option value="Khác" data-i18n="form.lecturer.gender.other"><fmt:message key="form.lecturer.gender.other"/></option>
        </select>
    </div>
    <div class="mb-3">
        <label for="identityCard" class="form-label" data-i18n="form.lecturer.identityCard"><fmt:message key="form.lecturer.identityCard"/></label>
        <input type="text"
               name="identityCard"
               class="form-control"
               id="identityCard"
               data-i18n-placeholder="form.lecturer.identityCard.placeholder"
               placeholder="<fmt:message key='form.lecturer.identityCard.placeholder'/>"
               value="${identityCard}">
    </div>

    <h3 data-i18n="form.lecturer.academic.info"><fmt:message key="form.lecturer.academic.info"/></h3>
    <div class="mb-3">
        <label for="academicRank" class="form-label" data-i18n="form.lecturer.academicRank"><fmt:message key="form.lecturer.academicRank"/></label>
        <input type="text"
               name="academicRank"
               class="form-control"
               id="academicRank"
               data-i18n-placeholder="form.lecturer.academicRank.placeholder"
               placeholder="<fmt:message key='form.lecturer.academicRank.placeholder'/>"
               value="${academicRank}">
    </div>
    <div class="mb-3">
        <label for="degree" class="form-label" data-i18n="form.lecturer.degree"><fmt:message key="form.lecturer.degree"/></label>
        <input type="text"
               name="degree"
               class="form-control"
               id="degree"
               data-i18n-placeholder="form.lecturer.degree.placeholder"
               placeholder="<fmt:message key='form.lecturer.degree.placeholder'/>"
               value="${degree}">
    </div>
    <div class="mb-3">
        <label for="specialization" class="form-label" data-i18n="form.lecturer.specialization"><fmt:message key="form.lecturer.specialization"/></label>
        <input type="text"
               name="specialization"
               class="form-control"
               id="specialization"
               data-i18n-placeholder="form.lecturer.specialization.placeholder"
               placeholder="<fmt:message key='form.lecturer.specialization.placeholder'/>"
               value="${specialization}">
    </div>
    <div class="mb-3">
        <label for="position" class="form-label" data-i18n="form.lecturer.position"><fmt:message key="form.lecturer.position"/></label>
        <input type="text"
               name="position"
               class="form-control"
               id="position"
               data-i18n-placeholder="form.lecturer.position.placeholder"
               placeholder="<fmt:message key='form.lecturer.position.placeholder'/>"
               value="${position}">
    </div>

    <h3 data-i18n="form.lecturer.work.info"><fmt:message key="form.lecturer.work.info"/></h3>
    <div class="mb-3">
        <label for="department" class="form-label" data-i18n="form.lecturer.department"><fmt:message key="form.lecturer.department"/></label>
        <input type="text"
               name="department"
               class="form-control"
               id="department"
               data-i18n-placeholder="form.lecturer.department.placeholder"
               placeholder="<fmt:message key='form.lecturer.department.placeholder'/>"
               value="${department}">
    </div>
    <div class="mb-3">
        <label for="workAgency" class="form-label" data-i18n="form.lecturer.workAgency"><fmt:message key="form.lecturer.workAgency"/></label>
        <input type="text"
               name="workAgency"
               class="form-control"
               id="workAgency"
               data-i18n-placeholder="form.lecturer.workAgency.placeholder"
               placeholder="<fmt:message key='form.lecturer.workAgency.placeholder'/>"
               value="${workAgency}">
    </div>
    <div class="mb-3">
        <label for="agencyAddress" class="form-label" data-i18n="form.lecturer.agencyAddress"><fmt:message key="form.lecturer.agencyAddress"/></label>
        <textarea name="agencyAddress"
                  class="form-control"
                  id="agencyAddress"
                  rows="3"
                  data-i18n-placeholder="form.lecturer.agencyAddress.placeholder"
                  placeholder="<fmt:message key='form.lecturer.agencyAddress.placeholder'/>">${agencyAddress}</textarea>
    </div>

    <h3 data-i18n="form.lecturer.contact.info"><fmt:message key="form.lecturer.contact.info"/></h3>
    <div class="mb-3">
        <label for="phoneFixed" class="form-label" data-i18n="form.lecturer.phoneFixed"><fmt:message key="form.lecturer.phoneFixed"/></label>
        <input type="tel"
               name="phoneFixed"
               class="form-control"
               id="phoneFixed"
               data-i18n-placeholder="form.lecturer.phoneFixed.placeholder"
               placeholder="<fmt:message key='form.lecturer.phoneFixed.placeholder'/>"
               value="${phoneFixed}">
    </div>
    <div class="mb-3">
        <label for="phoneMobile" class="form-label" data-i18n="form.lecturer.phoneMobile"><fmt:message key="form.lecturer.phoneMobile"/></label>
        <input type="tel"
               name="phoneMobile"
               class="form-control"
               id="phoneMobile"
               data-i18n-placeholder="form.lecturer.phoneMobile.placeholder"
               placeholder="<fmt:message key='form.lecturer.phoneMobile.placeholder'/>"
               value="${phoneMobile}">
    </div>
    <div class="mb-3">
        <label for="fax" class="form-label" data-i18n="form.lecturer.fax"><fmt:message key="form.lecturer.fax"/></label>
        <input type="tel"
               name="fax"
               class="form-control"
               id="fax"
               data-i18n-placeholder="form.lecturer.fax.placeholder"
               placeholder="<fmt:message key='form.lecturer.fax.placeholder'/>"
               value="${fax}">
    </div>

    <h3 data-i18n="form.lecturer.bank.info"><fmt:message key="form.lecturer.bank.info"/></h3>
    <div class="mb-3">
        <label for="bankAccountNumber" class="form-label" data-i18n="form.lecturer.bankAccountNumber"><fmt:message key="form.lecturer.bankAccountNumber"/></label>
        <input type="text"
               name="bankAccountNumber"
               class="form-control"
               id="bankAccountNumber"
               data-i18n-placeholder="form.lecturer.bankAccountNumber.placeholder"
               placeholder="<fmt:message key='form.lecturer.bankAccountNumber.placeholder'/>"
               value="${bankAccountNumber}">
    </div>
    <div class="mb-3">
        <label for="bankName" class="form-label" data-i18n="form.lecturer.bankName"><fmt:message key="form.lecturer.bankName"/></label>
        <input type="text"
               name="bankName"
               class="form-control"
               id="bankName"
               data-i18n-placeholder="form.lecturer.bankName.placeholder"
               placeholder="<fmt:message key='form.lecturer.bankName.placeholder'/>"
               value="${bankName}">
    </div>
    <div class="mb-3">
        <label for="bankBranch" class="form-label" data-i18n="form.lecturer.bankBranch"><fmt:message key="form.lecturer.bankBranch"/></label>
        <input type="text"
               name="bankBranch"
               class="form-control"
               id="bankBranch"
               data-i18n-placeholder="form.lecturer.bankBranch.placeholder"
               placeholder="<fmt:message key='form.lecturer.bankBranch.placeholder'/>"
               value="${bankBranch}">
    </div>

    <div>
        <button type="submit" class="btn btn-primary" data-i18n="admin.lecturer.add"><fmt:message key="admin.lecturer.add"/></button>
        <a href="${pageContext.request.contextPath}/admin/lecturers" class="btn btn-secondary" data-i18n="admin.lecturer.back"><fmt:message key="admin.lecturer.back"/></a>
    </div>

</form>

