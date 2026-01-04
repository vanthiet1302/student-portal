<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<div class="container-fluid">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Thêm giảng viên</h1>
        <a href="${pageContext.request.contextPath}/admin/lecturers" class="btn btn-secondary">
            Quay lại
        </a>
    </div>

    <!-- ALERT -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show">
            ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <!-- FORM -->
    <form id="addLecturerForm"
          action="${pageContext.request.contextPath}/admin/lecturers/add"
          method="post"
          novalidate>

        <!-- ================= ACCOUNT ================= -->
        <div class="card mb-4">
            <div class="card-header bg-primary text-white">
                <strong>Tài khoản</strong>
            </div>
            <div class="card-body row">

                <div class="col-md-4 mb-3">
                    <label class="form-label">Username *</label>
                    <input type="text" class="form-control" name="username"
                           value="${param.username}" required>
                </div>

                <div class="col-md-4 mb-3">
                    <label class="form-label">Email *</label>
                    <input type="email" class="form-control" name="emailWork"
                           value="${param.emailWork}" required>
                </div>

            </div>
        </div>

        <!-- ================= PERSONAL ================= -->
        <div class="card mb-4">
            <div class="card-header bg-info text-white">
                <strong>Thông tin cá nhân</strong>
            </div>
            <div class="card-body row">

                <div class="col-md-3 mb-3">
                    <label class="form-label">Họ *</label>
                    <input type="text" class="form-control" name="lastName"
                           value="${param.lastName}" required>
                </div>

                <div class="col-md-3 mb-3">
                    <label class="form-label">Tên *</label>
                    <input type="text" class="form-control" name="firstName"
                           value="${param.firstName}" required>
                </div>

                <div class="col-md-3 mb-3">
                    <label class="form-label">Năm sinh</label>
                    <input type="number" class="form-control" name="birthYear"
                           min="1950" max="2010"
                           value="${param.birthYear}">
                </div>

                <div class="col-md-3 mb-3">
                    <label class="form-label">Giới tính</label>
                    <select class="form-select" name="gender">
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                        <option value="Khác">Khác</option>
                    </select>
                </div>

                <div class="col-md-6 mb-3">
                    <label class="form-label">CCCD / CMND</label>
                    <input type="text" class="form-control" name="identityCard"
                           value="${param.identityCard}">
                </div>

            </div>
        </div>

        <!-- ================= ACADEMIC ================= -->
        <div class="card mb-4">
            <div class="card-header bg-success text-white">
                <strong>Học thuật</strong>
            </div>
            <div class="card-body row">

                <div class="col-md-3 mb-3">
                    <label class="form-label">Học hàm</label>
                    <select class="form-select" name="academicRank">
                        <option value="">--</option>
                        <option value="Giáo sư">Giáo sư</option>
                        <option value="Phó Giáo sư">Phó Giáo sư</option>
                    </select>
                </div>

                <div class="col-md-3 mb-3">
                    <label class="form-label">Học vị</label>
                    <select class="form-select" name="degree">
                        <option value="">--</option>
                        <option value="Cử nhân">Cử nhân</option>
                        <option value="Thạc sĩ">Thạc sĩ</option>
                        <option value="Tiến sĩ">Tiến sĩ</option>
                    </select>
                </div>

                <div class="col-md-3 mb-3">
                    <label class="form-label">Chuyên ngành</label>
                    <input type="text" class="form-control" name="specialization"
                           value="${param.specialization}">
                </div>

                <div class="col-md-3 mb-3">
                    <label class="form-label">Chức vụ</label>
                    <input type="text" class="form-control" name="position"
                           value="${param.position}">
                </div>

            </div>
        </div>

        <!-- ================= WORK ================= -->
        <div class="card mb-4">
            <div class="card-header bg-warning">
                <strong>Công tác</strong>
            </div>
            <div class="card-body row">

                <div class="col-md-4 mb-3">
                    <label class="form-label">Khoa / Bộ môn *</label>
                    <input type="text" class="form-control" name="department"
                           value="${param.department}" required>
                </div>

                <div class="col-md-4 mb-3">
                    <label class="form-label">Cơ quan</label>
                    <input type="text" class="form-control" name="workAgency"
                           value="${param.workAgency}">
                </div>

                <div class="col-md-4 mb-3">
                    <label class="form-label">Địa chỉ cơ quan</label>
                    <input type="text" class="form-control" name="agencyAddress"
                           value="${param.agencyAddress}">
                </div>

            </div>
        </div>

        <!-- ================= CONTACT ================= -->
        <div class="card mb-4">
            <div class="card-header bg-secondary text-white">
                <strong>Liên hệ</strong>
            </div>
            <div class="card-body row">

                <div class="col-md-4 mb-3">
                    <label class="form-label">Di động</label>
                    <input type="text" class="form-control" name="phoneMobile"
                           value="${param.phoneMobile}">
                </div>

                <div class="col-md-4 mb-3">
                    <label class="form-label">Điện thoại bàn</label>
                    <input type="text" class="form-control" name="phoneFixed"
                           value="${param.phoneFixed}">
                </div>

                <div class="col-md-4 mb-3">
                    <label class="form-label">Fax</label>
                    <input type="text" class="form-control" name="fax"
                           value="${param.fax}">
                </div>

            </div>
        </div>

        <!-- ================= BANK ================= -->
        <div class="card mb-4">
            <div class="card-header bg-dark text-white">
                <strong>Ngân hàng</strong>
            </div>
            <div class="card-body row">

                <div class="col-md-4 mb-3">
                    <label class="form-label">Số tài khoản</label>
                    <input type="text" class="form-control" name="bankAccountNumber"
                           value="${param.bankAccountNumber}">
                </div>

                <div class="col-md-4 mb-3">
                    <label class="form-label">Ngân hàng</label>
                    <input type="text" class="form-control" name="bankName"
                           value="${param.bankName}">
                </div>

                <div class="col-md-4 mb-3">
                    <label class="form-label">Chi nhánh</label>
                    <input type="text" class="form-control" name="bankBranch"
                           value="${param.bankBranch}">
                </div>

            </div>
        </div>

        <!-- ACTION -->
        <div class="d-flex justify-content-end gap-2 mb-5">
            <a href="${pageContext.request.contextPath}/admin/lecturers"
               class="btn btn-secondary">Hủy</a>
            <button type="submit" class="btn btn-primary">
                Lưu
            </button>
        </div>

    </form>
</div>
