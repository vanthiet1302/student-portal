<%--
  Academic Class Students List Page
  Date: 09-Jan-26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/academic-classes" class="btn btn-secondary">
        <i class="fa fa-arrow-left"></i> <fmt:message key="admin.button.back"/>
    </a>
    <a href="${pageContext.request.contextPath}/admin/academic-classes/add-student?classId=${requestScope.academicClass.id}" class="btn btn-success">
        <i class="fa fa-plus"></i> <fmt:message key="admin.academicClass.students.addNew"/>
    </a>
</div>

<h1 data-i18n="admin.title.academicClass.students">
    <fmt:message key="admin.title.academicClass.students"/> - ${requestScope.academicClass.code}
</h1>

<c:if test="${not empty requestScope.students}">
    <div style="overflow-x: auto;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th data-i18n="admin.student.list.number"><fmt:message key="admin.student.list.number"/></th>
                <th data-i18n="admin.student.list.id"><fmt:message key="admin.student.list.id"/></th>
                <th data-i18n="admin.student.list.fullName"><fmt:message key="admin.student.list.fullName"/></th>
                <th data-i18n="admin.student.list.email"><fmt:message key="admin.student.list.email"/></th>
                <th data-i18n="admin.student.list.phoneNumber"><fmt:message key="admin.student.list.phoneNumber"/></th>
                <th data-i18n="admin.student.list.dob"><fmt:message key="admin.student.list.dob"/></th>
                <th data-i18n="admin.student.list.action"><fmt:message key="admin.student.list.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${requestScope.students}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${student.user.username}</td>
                    <td>${student.user.lastName} ${student.user.firstName}</td>
                    <td>${student.user.email}</td>
                    <td>${student.phoneNumber}</td>
                    <td>
                        <c:choose>
                            <c:when test="${student.dob != null}">
                                ${fn:substring(student.dob,8,10)}/${fn:substring(student.dob,5,7)}/${fn:substring(student.dob,0,4)}
                            </c:when>
                            <c:otherwise>
                                --
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/students/edit?userId=${student.userId}"
                           class="btn btn-sm btn-warning" title="<fmt:message key='admin.student.list.edit'/>" data-i18n="admin.student.list.edit">
                            <i class="fa fa-edit"></i> <fmt:message key="admin.student.list.edit"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test="${empty requestScope.students}">
    <div class="alert alert-info" data-i18n="admin.academicClass.students.noData">
        <fmt:message key="admin.academicClass.students.noData"/>
    </div>
</c:if>

