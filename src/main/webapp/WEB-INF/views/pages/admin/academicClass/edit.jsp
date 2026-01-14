<%--
  Academic Class Edit Page
  Date: 08-Jan-26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h2 data-i18n="admin.title.academicClass.edit"><fmt:message key="admin.title.academicClass.edit"/></h2>

            <c:if test="${not empty requestScope.error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <fmt:message key="${requestScope.error}"/>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/academic-classes/edit" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="classId" value="${requestScope.academicClass.id}">

                <div class="mb-3">
                    <label for="code" class="form-label">
                        <fmt:message key="admin.academicClass.form.code"/>
                        <span class="text-danger">*</span>
                    </label>
                    <input type="text" class="form-control" id="code" name="code"
                           value="${not empty requestScope.code ? requestScope.code : requestScope.academicClass.code}" required>
                    <div class="invalid-feedback" data-i18n="admin.academicClass.add.error.codeRequired">
                        <fmt:message key="admin.academicClass.add.error.codeRequired"/>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label">
                        <fmt:message key="admin.academicClass.form.name"/>
                        <span class="text-danger">*</span>
                    </label>
                    <input type="text" class="form-control" id="name" name="name"
                           value="${not empty requestScope.name ? requestScope.name : requestScope.academicClass.name}" required>
                    <div class="invalid-feedback" data-i18n="admin.academicClass.add.error.nameRequired">
                        <fmt:message key="admin.academicClass.add.error.nameRequired"/>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="facultyId" class="form-label">
                        <fmt:message key="admin.academicClass.form.facultyId"/>
                        <span class="text-danger">*</span>
                    </label>
                    <select class="form-control" id="facultyId" name="facultyId" required>
                        <option value="">-- <fmt:message key="common.select"/> --</option>
                        <c:forEach var="faculty" items="${requestScope.faculties}">
                            <option value="${faculty.id}"
                                    <c:if test="${faculty.id == (not empty requestScope.facultyId ? requestScope.facultyId : requestScope.academicClass.facultyId)}">selected</c:if>>
                                ${faculty.name}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback" data-i18n="admin.academicClass.add.error.facultyIdRequired">
                        <fmt:message key="admin.academicClass.add.error.facultyIdRequired"/>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="advisorId" class="form-label">
                        <fmt:message key="admin.academicClass.form.advisorId"/>
                    </label>
                    <select class="form-control" id="advisorId" name="advisorId">
                        <option value="">-- <fmt:message key="common.select"/> --</option>
                        <c:forEach var="lecturer" items="${requestScope.lecturers}">
                            <option value="${lecturer.userId}"
                                    <c:if test="${lecturer.userId == (not empty requestScope.advisorId ? requestScope.advisorId : requestScope.academicClass.advisorId)}">selected</c:if>>
                                ${lecturer.user.lastName} ${lecturer.user.firstName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="nienKhoa" class="form-label">
                        <fmt:message key="admin.academicClass.form.nienKhoa"/>
                        <span class="text-danger">*</span>
                    </label>
                    <input type="text" class="form-control" id="nienKhoa" name="nienKhoa"
                           placeholder="2025-2029" value="${not empty requestScope.nienKhoa ? requestScope.nienKhoa : requestScope.academicClass.nienKhoa}" required>
                    <div class="invalid-feedback" data-i18n="admin.academicClass.add.error.nienKhoaRequired">
                        <fmt:message key="admin.academicClass.add.error.nienKhoaRequired"/>
                    </div>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary" data-i18n="common.save">
                        <i class="fa fa-save"></i> <fmt:message key="common.save"/>
                    </button>
                    <a href="${pageContext.request.contextPath}/admin/academic-classes" class="btn btn-secondary" data-i18n="common.cancel">
                        <i class="fa fa-times"></i> <fmt:message key="common.cancel"/>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    (function() {
        'use strict';
        var forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms).forEach(function(form) {
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>