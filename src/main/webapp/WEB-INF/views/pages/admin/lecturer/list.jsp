<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26-Dec-25
  Time: 4:33 PM
  Subject: Lecturer Management
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.lecturer.list"><fmt:message key="admin.title.lecturer.list"/></h1>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/lecturers/add" class="btn btn-success" data-i18n="admin.lecturer.list.addNew">
        <i class="fa fa-plus"></i> <fmt:message key="admin.lecturer.list.addNew"/>
    </a>
</div>

<c:if test="${not empty requestScope.lecturers}">
    <div style="overflow-x: auto;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th data-i18n="admin.lecturer.list.number"><fmt:message key="admin.lecturer.list.number"/></th>
                <th data-i18n="admin.lecturer.list.id"><fmt:message key="admin.lecturer.list.id"/></th>
                <th data-i18n="admin.lecturer.list.fullName"><fmt:message key="admin.lecturer.list.fullName"/></th>
                <th data-i18n="admin.lecturer.list.email"><fmt:message key="admin.lecturer.list.email"/></th>
                <th data-i18n="admin.lecturer.list.department"><fmt:message key="admin.lecturer.list.department"/></th>
                <th data-i18n="admin.lecturer.list.action"><fmt:message key="admin.lecturer.list.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lecturer" items="${requestScope.lecturers}" varStatus="loop">
                <tr>
                    <td>${loop.count + (currentPage - 1) * pageSize}</td>
                    <td>${lecturer.user.username}</td>
                    <td>${lecturer.user.lastName} ${lecturer.user.firstName}</td>
                    <td>${lecturer.user.email}</td>
                    <td>${lecturer.department}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/lecturers/edit?userId=${lecturer.userId}"
                           class="btn btn-sm btn-warning" title="<fmt:message key='admin.lecturer.list.edit'/>" data-i18n="admin.lecturer.list.edit">
                            <i class="fa fa-edit"></i> <fmt:message key="admin.lecturer.list.edit"/>
                        </a>
                        <form action="${pageContext.request.contextPath}/admin/lecturers/delete" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="${lecturer.userId}">
                            <button type="submit" class="btn btn-sm btn-danger"
                                    onclick="return confirm('<fmt:message key="admin.lecturer.list.confirm"/>')"
                                    title="<fmt:message key='admin.lecturer.list.delete'/>" data-i18n="admin.lecturer.list.delete">
                                <i class="fa fa-trash"></i> <fmt:message key="admin.lecturer.list.delete"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="d-flex justify-content-between align-items-center mt-3">
        <nav aria-label="Page navigation">
            <ul class="pagination mb-0">
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=1&pageSize=${pageSize}">
                        <fmt:message key="pagination.first"/>
                    </a>
                </li>
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${currentPage - 1}&pageSize=${pageSize}">
                        <fmt:message key="pagination.prev"/>
                    </a>
                </li>

                <c:if test="${endPage - startPage < 4 && startPage > 1}">
                    <c:set var="startPage" value="${endPage - 4 > 0 ? endPage - 4 : 1}"/>
                </c:if>

                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${i}&pageSize=${pageSize}">${i}</a>
                    </li>
                </c:forEach>

                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${currentPage + 1}&pageSize=${pageSize}">
                        <fmt:message key="pagination.next"/>
                    </a>
                </li>
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${totalPages}&pageSize=${pageSize}">
                        <fmt:message key="pagination.last"/>
                    </a>
                </li>
            </ul>
        </nav>

        <div>
            <select class="form-select form-select-sm" style="width: auto;" onchange="changePageSize(this.value)">
                <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
                <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
                <option value="25" ${pageSize == 25 ? 'selected' : ''}>25</option>
                <option value="50" ${pageSize == 50 ? 'selected' : ''}>50</option>
            </select>
        </div>
    </div>
</c:if>

<c:if test="${empty requestScope.lecturers}">
    <div class="alert alert-info" data-i18n="admin.lecturer.list.noData">
        <fmt:message key="admin.lecturer.list.noData"/>
        <a href="${pageContext.request.contextPath}/admin/lecturers/add"><fmt:message key="admin.lecturer.list.addNew"/></a>
    </div>
</c:if>