<%--
  Trang đăng ký sinh viên
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký Tài Khoản Sinh Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fa;
            display: flex;
            justify-content: center;
            padding: 40px 20px;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 600px;
        }
        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #34495e;
        }
        input[type="text"], input[type="email"], input[type="password"],
        input[type="tel"], input[type="date"], select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        .radio-group {
            display: flex;
            gap: 20px;
            margin-top: 5px;
        }
        .error {
            color: #e74c3c;
            font-size: 14px;
            margin-top: 5px;
            display: block;
        }
        .general-error {
            background: #fadbd8;
            color: #e74c3c;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
        }
        button {
            width: 100%;
            padding: 12px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            background-color: #2980b9;
        }
        .login-link {
            text-align: center;
            margin-top: 20px;
        }
        .login-link a {
            color: #3498db;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Đăng Ký Tài Khoản Sinh Viên</h2>

    <c:if test="${not empty error}">
        <div class="general-error">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post" novalidate>
        <h3>Thông tin tài khoản</h3>

        <div class="form-group">
            <label for="username">Tên đăng nhập</label>
            <input type="text" name="username" id="username" value="${param.username}" required placeholder="Ví dụ: 23130314" />
        </div>

        <div class="form-group">
            <label for="password">Mật khẩu</label>
            <input type="password" name="password" id="password" required />
        </div>

        <div class="form-group">
            <label for="confirmPassword">Xác nhận mật khẩu</label>
            <input type="password" name="confirmPassword" id="confirmPassword" required />
        </div>

        <hr style="margin: 30px 0; border: none; border-top: 1px solid #eee;">

        <h3>Thông tin cá nhân sinh viên</h3>

        <div class="form-group">
            <label for="studentCode">Mã số sinh viên</label>
            <input type="text" name="studentCode" id="studentCode" value="${param.studentCode}" required placeholder="Ví dụ: 23130314" />
        </div>

        <div class="form-group">
            <label for="fullName">Họ và tên</label>
            <input type="text" name="fullName" id="fullName" value="${param.fullName}" required />
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" id="email" value="${param.email}" required placeholder="sv@st.hcmuaf.edu.vn" />
        </div>

        <div class="form-group">
            <label for="phone">Số điện thoại</label>
            <input type="tel" name="phone" id="phone" value="${param.phone}" required placeholder="0901234567" />
        </div>

        <div class="form-group">
            <label for="dateOfBirth">Ngày sinh</label>
            <input type="date" name="dateOfBirth" id="dateOfBirth" value="${param.dateOfBirth}" required />
        </div>

        <div class="form-group">
            <label>Giới tính</label>
            <div class="radio-group">
                <label><input type="radio" name="gender" value="MALE" ${param.gender == 'MALE' ? 'checked' : ''}> Nam</label>
                <label><input type="radio" name="gender" value="FEMALE" ${param.gender == 'FEMALE' ? 'checked' : ''}> Nữ</label>
                <label><input type="radio" name="gender" value="OTHER" ${param.gender == 'OTHER' ? 'checked' : ''}> Khác</label>
            </div>
        </div>

        <div class="form-group">
            <label for="address">Địa chỉ</label>
            <input type="text" name="address" id="address" value="${param.address}" required />
        </div>

        <div class="form-group">
            <label for="major">Ngành học</label>
            <input type="text" name="major" id="major" value="${param.major}" required placeholder="Ví dụ: Công nghệ thông tin" />
        </div>

        <div class="form-group">
            <label for="className">Lớp</label>
            <input type="text" name="className" id="className" value="${param.className}" required placeholder="Ví dụ: D20CQCN01-B" />
        </div>

        <button type="submit">Đăng Ký Ngay</button>
    </form>

    <div class="login-link">
        Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập tại đây</a>
    </div>
</div>
</body>
</html>