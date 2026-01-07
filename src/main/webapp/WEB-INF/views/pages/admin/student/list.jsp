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

<h1>Danh sách sinh viên</h1>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/admin/students/add" class="btn btn-success">
        <i class="fa fa-plus"></i> Thêm sinh viên mới
    </a>
</div>

<c:if test="${not empty requestScope.students}">
    <div style="overflow-x: auto;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>STT</th>
                <th>Mã sinh viên</th>
                <th>Họ và tên</th>
                <th>Email</th>
                <th>Lớp</th>
                <th>Số điện thoại</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${requestScope.students}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${student.userId}</td>
                    <td>${student.user.lastName} ${student.user.firstName}</td>
                    <td>${student.user.email}</td>
                    <td>${student.classId}</td>
                    <td>${student.phoneNumber}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/students/edit?userId=${student.userId}"
                           class="btn btn-sm btn-warning" title="Chỉnh sửa">
                            <i class="fa fa-edit"></i> Sửa
                        </a>
                        <form action="${pageContext.request.contextPath}/admin/students/delete" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="${student.userId}">
                            <button type="submit" class="btn btn-sm btn-danger"
                                    onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên này không?')" title="Xóa">
                                <i class="fa fa-trash"></i> Xóa
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
    <div class="alert alert-info">
        Không có sinh viên nào. <a href="${pageContext.request.contextPath}/admin/students/add">Thêm sinh viên mới</a>
    </div>
</c:if>
