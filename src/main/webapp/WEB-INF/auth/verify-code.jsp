<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Xác thực mã và đặt lại mật khẩu</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f6; }
        .container { max-width: 420px; margin: 60px auto; background: #fff; padding: 24px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        h1 { font-size: 20px; margin-bottom: 12px; }
        label { display: block; margin: 10px 0 6px; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px; }
        .error { background: #ffe3e3; color: #c00; padding: 8px 12px; border-radius: 6px; margin-bottom: 12px; }
        .message { background: #e3ffe9; color: #0a7; padding: 8px 12px; border-radius: 6px; margin-bottom: 12px; }
        button { width: 100%; padding: 10px; background: #0a7; color: #fff; border: none; border-radius: 6px; cursor: pointer; margin-top: 14px; }
        .muted { color: #666; font-size: 12px; margin-top: 6px; }
        a { color: #0a7; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <h1>Nhập mã xác thực và đặt lại mật khẩu</h1>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/reset-password">
        <label for="code">Mã xác thực</label>
        <input type="text" id="code" name="code" placeholder="Ví dụ: 123456" required />

        <label for="password">Mật khẩu mới</label>
        <input type="password" id="password" name="password" placeholder="Mật khẩu mới" required />

        <label for="confirmPassword">Xác nhận mật khẩu</label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Nhập lại mật khẩu" required />

        <button type="submit">Đặt lại mật khẩu</button>
    </form>

    <p class="muted">Không nhận được mã? <a href="${pageContext.request.contextPath}/forgot-password">Gửi lại email</a></p>
</div>
</body>
</html>
