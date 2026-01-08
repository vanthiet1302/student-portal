<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26-Dec-25
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.student.list"><fmt:message key="admin.title.student.list"/></h1>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/students/add" class="btn btn-success" data-i18n="admin.student.list.addNew">
        <i class="fa fa-plus"></i> <fmt:message key="admin.student.list.addNew"/>
    </a>
</div>

<c:if test="${not empty requestScope.students}">
    <div style="overflow-x: auto;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th data-i18n="admin.student.list.number"><fmt:message key="admin.student.list.number"/></th>
                <th data-i18n="admin.student.list.id"><fmt:message key="admin.student.list.id"/></th>
                <th data-i18n="admin.student.list.fullName"><fmt:message key="admin.student.list.fullName"/></th>
                <th data-i18n="admin.student.list.email"><fmt:message key="admin.student.list.email"/></th>
                <th data-i18n="admin.student.list.classId"><fmt:message key="admin.student.list.classId"/></th>
                <th data-i18n="admin.student.list.phoneNumber"><fmt:message key="admin.student.list.phoneNumber"/></th>
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
                    <td>${student.classId}</td>
                    <td>${student.phoneNumber}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/students/edit?userId=${student.userId}"
                           class="btn btn-sm btn-warning" title="<fmt:message key='admin.student.list.edit'/>" data-i18n="admin.student.list.edit">
                            <i class="fa fa-edit"></i> <fmt:message key="admin.student.list.edit"/>
                        </a>
                        <form action="${pageContext.request.contextPath}/admin/students/delete" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="${student.userId}">
                            <button type="submit" class="btn btn-sm btn-danger"
                                    onclick="return confirm('<fmt:message key="admin.student.list.confirm"/>')"
                                    title="<fmt:message key='admin.student.list.delete'/>" data-i18n="admin.student.list.delete">
                                <i class="fa fa-trash"></i> <fmt:message key="admin.student.list.delete"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${empty requestScope.students}">
    <div class="alert alert-info" data-i18n="admin.student.list.noData">
        <fmt:message key="admin.student.list.noData"/> <a href="${pageContext.request.contextPath}/admin/students/add"><fmt:message key="admin.student.list.addNew"/></a>
    </div>
</c:if>
