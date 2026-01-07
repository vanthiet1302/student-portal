<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07-Jan-26
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<h1><fmt:message key="admin.title.student.edit"/></h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <fmt:message key="${error}"/>
    </div>
</c:if>
<c:if test="${not empty success}">
    <div class="alert alert-success">
        <fmt:message key="${success}"/>
    </div>
</c:if>

<c:if test="${not empty requestScope.student}">
    <c:set var="student" value="${requestScope.student}"/>
    <form action="${pageContext.request.contextPath}/admin/students/edit" method="post">
        <input type="hidden" name="userId" value="${student.userId}">

        <h3>Thông tin đăng nhập</h3>
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text"
                   name="username"
                   class="form-control"
                   id="username"
                   value="${student.user.username}"
                   disabled>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email"
                   name="email"
                   class="form-control"
                   id="email"
                   value="${student.user.email}"
                   required>
        </div>

        <h3>Thông tin cá nhân</h3>
        <div class="mb-3">
            <label for="lastName" class="form-label">Họ</label>
            <input type="text"
                   name="lastName"
                   class="form-control"
                   id="lastName"
                   value="${student.user.lastName}"
                   required>
        </div>
        <div class="mb-3">
            <label for="firstName" class="form-label">Tên</label>
            <input type="text"
                   name="firstName"
                   class="form-control"
                   id="firstName"
                   value="${student.user.firstName}"
                   required>
        </div>
        <div class="mb-3">
            <label for="dob" class="form-label">Ngày sinh</label>
            <input type="date"
                   name="dob"
                   class="form-control"
                   id="dob"
                   value="${student.dob}">
        </div>
        <div class="mb-3">
            <label for="pob" class="form-label">Nơi sinh</label>
            <input type="text"
                   name="pob"
                   class="form-control"
                   id="pob"
                   value="${student.pob}">
        </div>
        <div class="mb-3">
            <label for="gender" class="form-label">Giới tính</label>
            <select name="gender" id="gender" class="form-control">
                <option value="">-- Chọn giới tính --</option>
                <option value="Nam" <c:if test="${student.gender == 'Nam'}">selected</c:if>>Nam</option>
                <option value="Nữ" <c:if test="${student.gender == 'Nữ'}">selected</c:if>>Nữ</option>
                <option value="Khác" <c:if test="${student.gender == 'Khác'}">selected</c:if>>Khác</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Số điện thoại</label>
            <input type="text"
                   name="phoneNumber"
                   class="form-control"
                   id="phoneNumber"
                   value="${student.phoneNumber}">
        </div>
        <div class="mb-3">
            <label for="citizenId" class="form-label">Số CMND/CCCD</label>
            <input type="text"
                   name="citizenId"
                   class="form-control"
                   id="citizenId"
                   value="${student.citizenId}">
        </div>
        <div class="mb-3">
            <label for="nation" class="form-label">Dân tộc</label>
            <input type="text"
                   name="nation"
                   class="form-control"
                   id="nation"
                   value="${student.nation}">
        </div>
        <div class="mb-3">
            <label for="religion" class="form-label">Tôn giáo</label>
            <input type="text"
                   name="religion"
                   class="form-control"
                   id="religion"
                   value="${student.religion}">
        </div>
        <div class="mb-3">
            <label for="nationality" class="form-label">Quốc tịch</label>
            <input type="text"
                   name="nationality"
                   class="form-control"
                   id="nationality"
                   value="${student.nationality}">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Địa chỉ</label>
            <textarea name="address" class="form-control" id="address" rows="3">${student.address}</textarea>
        </div>
        <div class="mb-3">
            <label for="classId" class="form-label">Lớp</label>
            <input type="text"
                   name="classId"
                   class="form-control"
                   id="classId"
                   value="${student.classId}">
        </div>

        <div>
            <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
            <a href="${pageContext.request.contextPath}/admin/students" class="btn btn-secondary">Quay lại</a>
            <form action="${pageContext.request.contextPath}/admin/students/delete" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="${student.userId}">
                <button type="submit" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên này không?')">Xóa sinh viên</button>
            </form>
        </div>
    </form>
</c:if>
<c:if test="${empty requestScope.student}">
    <div class="alert alert-warning">
        Không tìm thấy thông tin sinh viên. <a href="${pageContext.request.contextPath}/admin/students">Quay lại danh sách</a>
    </div>
</c:if>

