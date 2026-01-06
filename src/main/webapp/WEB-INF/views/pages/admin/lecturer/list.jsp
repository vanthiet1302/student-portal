<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1><fmt:message key="page.lecture.list.title"/></h1>
    <a href="${pageContext.request.contextPath}/admin/lecturers/add" class="btn btn-primary">
        <fmt:message key="page.admin.lecturers.add"/>
    </a>
</div>

<!-- Success Message -->
<c:if test="${param.msg == 'success'}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <fmt:message key="page.lecturer.add.success"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<c:if test="${empty lecturers}">
    <div class="alert alert-info">Không có dữ liệu</div>
</c:if>

<c:if test="${not empty lecturers}">
<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>#</th>
            <th><fmt:message key="page.lecture.table.col1"/></th>
            <th><fmt:message key="page.lecture.table.col2"/></th>
            <th><fmt:message key="page.lecture.table.col3"/></th>
            <th><fmt:message key="page.lecture.table.col4"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="lecturer" items="${lecturers}" varStatus="status">
            <tr>
                <td>${(currentPage - 1) * pageSize + status.index + 1}</td>
                <td>${lecturer.department}</td>
                <td>${lecturer.fullName}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty lecturer.user.avatarUrl}">
                            <img src="${lecturer.user.avatarUrl}"
                                 alt="Avatar"
                                 class="img-fluid rounded"
                                 width="40"/>
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/assets/images/logo.png"
                                 alt="Avatar"
                                 class="img-fluid rounded"
                                 width="40"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/lecturers/${lecturer.userId}" class="btn btn-sm btn-outline-primary">
                        <fmt:message key="page.lecture.table.col4"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Pagination Info -->
<div class="d-flex justify-content-between align-items-center mt-3">
    <div class="text-muted">
        <fmt:message key="pagination.showing"/>
        ${(currentPage - 1) * pageSize + 1}
        <fmt:message key="pagination.to"/>
        ${(currentPage - 1) * pageSize + fn:length(lecturers)}
        <fmt:message key="pagination.of"/>
        ${totalRecords}
        <fmt:message key="pagination.records"/>
    </div>

    <!-- Pagination Controls -->
    <nav aria-label="Page navigation">
        <ul class="pagination mb-0">
            <!-- First Page -->
            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=1&pageSize=${pageSize}">
                    <fmt:message key="pagination.first"/>
                </a>
            </li>

            <!-- Previous Page -->
            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${currentPage - 1}&pageSize=${pageSize}">
                    <fmt:message key="pagination.previous"/>
                </a>
            </li>

            <!-- Page Numbers -->
            <c:set var="startPage" value="${currentPage - 2 > 0 ? currentPage - 2 : 1}"/>
            <c:set var="endPage" value="${startPage + 4 > totalPages ? totalPages : startPage + 4}"/>
            <c:if test="${endPage - startPage < 4 && startPage > 1}">
                <c:set var="startPage" value="${endPage - 4 > 0 ? endPage - 4 : 1}"/>
            </c:if>

            <c:forEach begin="${startPage}" end="${endPage}" var="i">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${i}&pageSize=${pageSize}">${i}</a>
                </li>
            </c:forEach>

            <!-- Next Page -->
            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${currentPage + 1}&pageSize=${pageSize}">
                    <fmt:message key="pagination.next"/>
                </a>
            </li>

            <!-- Last Page -->
            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                <a class="page-link" href="${pageContext.request.contextPath}/admin/lecturers?page=${totalPages}&pageSize=${pageSize}">
                    <fmt:message key="pagination.last"/>
                </a>
            </li>
        </ul>
    </nav>
</div>

<!-- Page Size Selector -->
<div class="d-flex justify-content-end mt-2">
    <select class="form-select form-select-sm" style="width: auto;" onchange="changePageSize(this.value)">
        <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
        <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
        <option value="25" ${pageSize == 25 ? 'selected' : ''}>25</option>
        <option value="50" ${pageSize == 50 ? 'selected' : ''}>50</option>
    </select>
</div>

<script>
function changePageSize(size) {
    window.location.href = '${pageContext.request.contextPath}/admin/lecturers?page=1&pageSize=' + size;
}
</script>
</c:if>



