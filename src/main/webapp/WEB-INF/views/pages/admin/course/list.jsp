<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1 data-i18n="admin.title.course.list"><fmt:message key="admin.title.course.list"/></h1>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/courses/add" class="btn btn-success" data-i18n="admin.course.list.addNew">
        <i class="fa fa-plus"></i> <fmt:message key="admin.course.list.addNew"/>
    </a>
</div>

<c:if test="${not empty requestScope.courses}">
    <div style="overflow-x: auto;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th data-i18n="admin.course.list.number"><fmt:message key="admin.course.list.number"/></th>
                <th data-i18n="admin.course.list.id"><fmt:message key="admin.course.list.id"/></th>
                <th data-i18n="admin.course.list.name"><fmt:message key="admin.course.list.name"/></th>
                <th data-i18n="admin.course.list.soTinChi"><fmt:message key="admin.course.list.soTinChi"/></th>
                <th data-i18n="admin.course.list.lyThuyet"><fmt:message key="admin.course.list.lyThuyet"/></th>
                <th data-i18n="admin.course.list.thucHanh"><fmt:message key="admin.course.list.thucHanh"/></th>
                <th data-i18n="admin.course.list.action"><fmt:message key="admin.course.list.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${requestScope.courses}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${course.code}</td>
                    <td>${course.name}</td>
                    <td><span class="badge bg-primary">${course.soTinChi}</span></td>
                    <td>${course.lyThuyet}</td>
                    <td>${course.thucHanh}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/courses/edit?courseId=${course.id}"
                           class="btn btn-sm btn-warning" title="<fmt:message key='admin.course.list.edit'/>" data-i18n="admin.course.list.edit">
                            <i class="fa fa-edit"></i> <fmt:message key="admin.course.list.edit"/>
                        </a>
                        <form action="${pageContext.request.contextPath}/admin/courses/delete" method="post" style="display:inline;">
                            <input type="hidden" name="courseId" value="${course.id}">
                            <button type="submit" class="btn btn-sm btn-danger"
                                    onclick="return confirm('<fmt:message key="admin.course.list.confirm"/>')"
                                    title="<fmt:message key='admin.course.list.delete'/>" data-i18n="admin.course.list.delete">
                                <i class="fa fa-trash"></i> <fmt:message key="admin.course.list.delete"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${empty requestScope.courses}">
    <div class="alert alert-info" data-i18n="admin.course.list.noData">
        <fmt:message key="admin.course.list.noData"/> <a href="${pageContext.request.contextPath}/admin/courses/add"><fmt:message key="admin.course.list.addNew"/></a>
    </div>
</c:if>