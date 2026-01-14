<%--
  Add Student to Academic Class Page
  Date: 09-Jan-26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/academic-classes/students?classId=${requestScope.academicClass.id}" class="btn btn-secondary">
        <i class="fa fa-arrow-left"></i> <fmt:message key="admin.button.back"/>
    </a>
</div>

<h1 data-i18n="admin.title.academicClass.addStudent">
    <fmt:message key="admin.title.academicClass.addStudent"/> - ${requestScope.academicClass.code}
</h1>

<c:if test="${not empty requestScope.error}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <fmt:message key="${requestScope.error}"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<c:if test="${not empty requestScope.students}">
    <div class="row">
        <div class="col-md-8">
            <div style="overflow-x: auto;">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th style="width: 10%;"><input type="checkbox" id="selectAll" onchange="selectAllCheckboxes(this)"></th>
                        <th data-i18n="admin.student.list.number"><fmt:message key="admin.student.list.number"/></th>
                        <th data-i18n="admin.student.list.id"><fmt:message key="admin.student.list.id"/></th>
                        <th data-i18n="admin.student.list.fullName"><fmt:message key="admin.student.list.fullName"/></th>
                        <th data-i18n="admin.student.list.email"><fmt:message key="admin.student.list.email"/></th>
                        <th data-i18n="admin.student.list.phoneNumber"><fmt:message key="admin.student.list.phoneNumber"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${requestScope.students}" varStatus="loop">
                        <tr>
                            <td>
                                <input type="checkbox" class="student-checkbox" value="${student.userId}">
                            </td>
                            <td>${loop.count}</td>
                            <td>${student.user.username}</td>
                            <td>${student.user.lastName} ${student.user.firstName}</td>
                            <td>${student.user.email}</td>
                            <td>${student.phoneNumber}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div style="margin-top: 20px;">
                <form id="addStudentForm" action="${pageContext.request.contextPath}/admin/academic-classes/add-student" method="post">
                    <input type="hidden" name="classId" value="${requestScope.academicClass.id}">
                    <input type="hidden" id="studentIds" name="studentIds" value="">

                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-check"></i> <fmt:message key="admin.academicClass.addStudent.confirm"/>
                    </button>
                </form>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-header">
                    <h5 data-i18n="admin.academicClass.addStudent.info"><fmt:message key="admin.academicClass.addStudent.info"/></h5>
                </div>
                <div class="card-body">
                    <p><strong data-i18n="admin.academicClass.list.code"><fmt:message key="admin.academicClass.list.code"/>:</strong> ${requestScope.academicClass.code}</p>
                    <p><strong data-i18n="admin.academicClass.list.name"><fmt:message key="admin.academicClass.list.name"/>:</strong> ${requestScope.academicClass.name}</p>
                    <p><strong data-i18n="admin.academicClass.list.nienKhoa"><fmt:message key="admin.academicClass.list.nienKhoa"/>:</strong> ${requestScope.academicClass.nienKhoa}</p>
                    <hr>
                    <p><strong><fmt:message key="admin.academicClass.addStudent.selected"/>:</strong> <span id="selectedCount">0</span></p>
                </div>
            </div>
        </div>
    </div>

    <script>
        function selectAllCheckboxes(selectAllCheckbox) {
            const checkboxes = document.querySelectorAll('.student-checkbox');
            checkboxes.forEach(checkbox => {
                checkbox.checked = selectAllCheckbox.checked;
            });
            updateSelectedCount();
        }

        function updateSelectedCount() {
            const checkboxes = document.querySelectorAll('.student-checkbox:checked');
            document.getElementById('selectedCount').textContent = checkboxes.length;
        }

        document.getElementById('addStudentForm').addEventListener('submit', function(e) {
            const checkboxes = document.querySelectorAll('.student-checkbox:checked');
            if (checkboxes.length === 0) {
                e.preventDefault();
                alert('<fmt:message key="admin.academicClass.addStudent.selectAtLeast"/>');
                return false;
            }

            const studentIds = Array.from(checkboxes).map(cb => cb.value).join(',');
            document.getElementById('studentIds').value = studentIds;
        });

        // Update count on checkbox change
        document.querySelectorAll('.student-checkbox').forEach(checkbox => {
            checkbox.addEventListener('change', updateSelectedCount);
        });
    </script>
</c:if>

<c:if test="${empty requestScope.students}">
    <div class="alert alert-info" data-i18n="admin.academicClass.addStudent.noStudents">
        <fmt:message key="admin.academicClass.addStudent.noStudents"/>
    </div>
</c:if>