<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập - Cổng Thông Tin Sinh Viên NLU</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .login-container {
            background: white;
            width: 100%;
            max-width: 420px;
            border-radius: 12px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .login-header {
            background: #4e73df;
            color: white;
            text-align: center;
            padding: 30px 20px;
        }
        .login-header h2 {
            font-size: 28px;
            margin-bottom: 8px;
        }
        .login-header p {
            opacity: 0.9;
            font-size: 15px;
        }
        .login-body {
            padding: 40px 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #333;
            font-size: 14px;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 14px 16px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            transition: border 0.3s ease;
        }
        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #4e73df;
            box-shadow: 0 0 0 3px rgba(78, 115, 223, 0.1);
        }
        .error-message {
            background: #fdf2f2;
            color: #e74c3c;
            padding: 12px 16px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-size: 14px;
            border-left: 4px solid #e74c3c;
        }
        .success-message {
            background: #fdf2f2;
            color: #5be73c;
            padding: 12px 16px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-size: 14px;
            border-left: 4px solid #42e73c;
        }
        button {
            width: 100%;
            padding: 14px;
            background: #4e73df;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        button:hover {
            background: #3b5cc4;
        }
        .links {
            text-align: center;
            margin-top: 25px;
            font-size: 14px;
        }
        .links a {
            color: #4e73df;
            text-decoration: none;
            margin: 0 10px;
        }
        .links a:hover {
            text-decoration: underline;
        }
        .footer {
            text-align: center;
            padding: 20px;
            background: #f8f9fa;
            color: #666;
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h2>Cổng Thông Tin Sinh Viên</h2>
    </div>

    <div class="login-body">

        <c:if test="${not empty error}">
            <div class="error-message">
                <strong>Lỗi:</strong> <c:out value="${error}"/>
            </div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="success-message">
                <c:out value="${success}"/>
            </div>
        </c:if>

        <!-- Form đăng nhập -->
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">Mã số sinh viên hoặc Tên đăng nhập</label>
                <input type="text"
                       id="username"
                       name="username"
                       value="${fn:escapeXml(param.username)}"
                       placeholder="Ví dụ: B20DCCN001 hoặc sv001"
                       required
                       autofocus />
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password"
                       id="password"
                       name="password"
                       placeholder="Nhập mật khẩu của bạn"
                       required />
            </div>

            <button type="submit">Đăng Nhập</button>
        </form>

        <div class="links">
            <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
            <span>•</span>
            <a href="${pageContext.request.contextPath}/register">Đăng ký tài khoản mới</a>
        </div>
    </div>
</div>
</body>
</html>