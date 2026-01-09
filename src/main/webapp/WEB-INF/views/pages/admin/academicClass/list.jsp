<%--
  Academic Class List Page
  Date: 08-Jan-26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.academicClass.list"><fmt:message key="admin.title.academicClass.list"/></h1>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/academic-classes/add" class="btn btn-success" data-i18n="admin.academicClass.list.addNew">
        <i class="fa fa-plus"></i> <fmt:message key="admin.academicClass.list.addNew"/>
    </a>
</div>

<c:if test="${not empty requestScope.error}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <fmt:message key="${requestScope.error}"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<c:if test="${not empty requestScope.classes}">
    <div style="overflow-x: auto;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th data-i18n="admin.academicClass.list.number"><fmt:message key="admin.academicClass.list.number"/></th>
                <th data-i18n="admin.academicClass.list.code"><fmt:message key="admin.academicClass.list.code"/></th>
                <th data-i18n="admin.academicClass.list.name"><fmt:message key="admin.academicClass.list.name"/></th>
                <th data-i18n="admin.academicClass.list.facultyId"><fmt:message key="admin.academicClass.list.facultyId"/></th>
                <th data-i18n="admin.academicClass.list.advisorId"><fmt:message key="admin.academicClass.list.advisorId"/></th>
                <th data-i18n="admin.academicClass.list.nienKhoa"><fmt:message key="admin.academicClass.list.nienKhoa"/></th>
                <th data-i18n="admin.academicClass.list.action"><fmt:message key="admin.academicClass.list.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="clazz" items="${requestScope.classes}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${clazz.code}</td>
                    <td>${clazz.name}</td>
                    <td>${clazz.facultyId}</td>
                    <td>${clazz.advisorId}</td>
                    <td>${clazz.nienKhoa}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/academic-classes/students?classId=${clazz.id}"
                           class="btn btn-sm btn-info" title="<fmt:message key='admin.academicClass.list.viewStudents'/>" data-i18n="admin.academicClass.list.viewStudents">
                            <i class="fa fa-users"></i> <fmt:message key="admin.academicClass.list.viewStudents"/>
                        </a>
                        <a href="${pageContext.request.contextPath}/admin/academic-classes/edit?classId=${clazz.id}"
                           class="btn btn-sm btn-warning" title="<fmt:message key='admin.academicClass.list.edit'/>" data-i18n="admin.academicClass.list.edit">
                            <i class="fa fa-edit"></i> <fmt:message key="admin.academicClass.list.edit"/>
                        </a>
                        <form action="${pageContext.request.contextPath}/admin/academic-classes/delete" method="post" style="display:inline;">
                            <input type="hidden" name="classId" value="${clazz.id}">
                            <button type="submit" class="btn btn-sm btn-danger" title="<fmt:message key='admin.academicClass.list.delete'/>" data-i18n="admin.academicClass.list.delete"
                                    onclick="return confirm('<fmt:message key='admin.academicClass.list.confirmDelete'/>')">
                                <i class="fa fa-trash"></i> <fmt:message key="admin.academicClass.list.delete"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test="${empty requestScope.classes}">
    <div class="alert alert-info" data-i18n="admin.academicClass.list.empty">
        <fmt:message key="admin.academicClass.list.empty"/>
    </div>
</c:if>

