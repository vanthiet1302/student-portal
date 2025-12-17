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
    <form action="register" method="post">
        <h3>Thông tin tài khoản</h3>
        Mã số sinh viên: <input type="text" name="username" placeholder="Mã số sinh viên" required />
        <br>
        Địa chỉ email: <input type="email" name="primaryEmail" placeholder="Email" required />
        <br>
        Họ: <input type="text" name="firstname" placeholder="Họ" required />
        <br>
        Tên: <input type="text" name="lastname" placeholder="Tên" required />
        <br>
        Mật khẩu: <input type="password" name="password" placeholder="Mật khẩu" required />
        <br>
        Nhập lại mật khẩu: <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu" required />
        <br>
        <button type="submit">Đăng ký</button>
    </form>
</body>
</html>