<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" content="width=device-width, initial-scale=1.0"/>
    <title>Cổng thông tin sinh viên - NLU</title>
</head>
<body>
    <h2>Đăng nhập</h2>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="login" method="post">
        <label for="username">Mã số sinh viên:</label>
        <input type="text" id="username" name="username" required/>
        <br/>
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required/>
        <br/>
        <button type="submit">Đăng nhập</button>
        <p>
            Quên mật khẩu?
            <a href="forgot-password">Khôi phục mật khẩu</a>
        </p>
        <p>
            Chưa có tài khoản?
            <a href="register">Đăng ký tài khoản sinh viên</a>
        </p>
    </form>
</body>
</html>
