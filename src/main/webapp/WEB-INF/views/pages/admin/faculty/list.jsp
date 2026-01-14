<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.faculty.list"><fmt:message key="admin.title.faculty.list"/></h1>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/faculties/add" class="btn btn-success" data-i18n="admin.faculty.list.addNew">
        <i class="fa fa-plus"></i> <fmt:message key="admin.faculty.list.addNew"/>
    </a>
</div>

<c:if test="${not empty requestScope.faculties}">
    <div style="overflow-x: auto;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th data-i18n="admin.faculty.list.number"><fmt:message key="admin.faculty.list.number"/></th>
                <th data-i18n="admin.faculty.list.code"><fmt:message key="admin.faculty.list.code"/></th>
                <th data-i18n="admin.faculty.list.name"><fmt:message key="admin.faculty.list.name"/></th>
                <th data-i18n="admin.faculty.list.establishedDate"><fmt:message key="admin.faculty.list.establishedDate"/></th>
                <th data-i18n="admin.faculty.list.action"><fmt:message key="admin.faculty.list.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="faculty" items="${requestScope.faculties}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td><span class="badge bg-info">${faculty.code}</span></td>
                    <td>${faculty.name}</td>
                    <td>
                        <c:if test="${not empty faculty.establishedDate}">
                            ${faculty.establishedDate}
                        </c:if>
                        <c:if test="${empty faculty.establishedDate}">
                            <span class="text-muted">-</span>
                        </c:if>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/faculties/edit?facultyId=${faculty.id}"
                           class="btn btn-sm btn-warning" title="<fmt:message key='admin.faculty.list.edit'/>" data-i18n="admin.faculty.list.edit">
                            <i class="fa fa-edit"></i> <fmt:message key="admin.faculty.list.edit"/>
                        </a>
                        <form action="${pageContext.request.contextPath}/admin/faculties/delete" method="post" style="display:inline;">
                            <input type="hidden" name="facultyId" value="${faculty.id}">
                            <button type="submit" class="btn btn-sm btn-danger"
                                    onclick="return confirm('<fmt:message key="admin.faculty.list.confirm"/>')"
                                    title="<fmt:message key='admin.faculty.list.delete'/>" data-i18n="admin.faculty.list.delete">
                                <i class="fa fa-trash"></i> <fmt:message key="admin.faculty.list.delete"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${empty requestScope.faculties}">
    <div class="alert alert-info" data-i18n="admin.faculty.list.noData">
        <fmt:message key="admin.faculty.list.noData"/> <a href="${pageContext.request.contextPath}/admin/faculties/add"><fmt:message key="admin.faculty.list.addNew"/></a>
    </div>
</c:if>