<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16-Dec-25
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký Tài Khoản Sinh Viên</title>
</head>
<body>
    <h2>Đăng ký thông tin sinh viên</h2>
    <form action="register" method="post" enctype="multipart/form-data">
        <!-- Account -->
        <h3>Thông tin tài khoản</h3>
        <input type="text" name="username" placeholder="Username" required />

        <input type="email" name="primaryEmail" placeholder="Email" required />

        <input type="password" name="password" placeholder="Mật khẩu" required />
        <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu" required />

        <!-- Personal info -->
        <h3>Thông tin cá nhân</h3>
        <input type="text" name="firstName" placeholder="Họ" />
        <input type="text" name="lastName" placeholder="Tên" />

        <label>Ngày sinh</label>
        <input type="date" name="dob" />

        <label>Giới tính</label>
        <select name="isMale">
            <option value="true">Nam</option>
            <option value="false">Nữ</option>
        </select>

        <input type="text" name="phone" placeholder="Số điện thoại" />
        <input type="text" name="citizenId" placeholder="CCCD/CMND" />

        <!-- Other info -->
        <h3>Thông tin khác</h3>
        <input type="text" name="nation" placeholder="Dân tộc" />
        <input type="text" name="religion" placeholder="Tôn giáo" />
        <input type="text" name="pob" placeholder="Nơi sinh" />
        <input type="text" name="nationality" placeholder="Quốc tịch" />

        <textarea name="address" placeholder="Địa chỉ"></textarea>

        <input type="text" name="classId" placeholder="Mã lớp" />

        <!-- Avatar -->
        <label>Ảnh đại diện</label>
        <input type="file" name="avatar" accept="image/*" />

        <!-- Status (ẩn) -->
        <input type="hidden" name="status" value="ACTIVE" />

        <button type="submit">Đăng ký</button>
    </form>


</form>

</body>
</html>