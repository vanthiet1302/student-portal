<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Khôi phục mật khẩu</title>
    <style>
        :root {
            --bg: #0f172a;
            --card: #ffffff;
            --text: #0f172a;
            --muted: #64748b;
            --accent: #2563eb;
            --border: #e2e8f0;
        }
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: "Segoe UI", Tahoma, sans-serif;
            background: radial-gradient(circle at 20% 20%, rgba(37,99,235,0.18), transparent 35%),
                        radial-gradient(circle at 80% 0%, rgba(34,197,94,0.2), transparent 32%),
                        var(--bg);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 24px;
        }
        .card {
            width: 100%;
            max-width: 460px;
            background: var(--card);
            border-radius: 14px;
            padding: 24px 24px 22px;
            border: 1px solid var(--border);
            box-shadow: 0 20px 40px rgba(0,0,0,0.12);
        }
        h1 { margin: 0 0 6px; font-size: 24px; color: var(--text); }
        p.muted { margin: 0 0 18px; color: var(--muted); line-height: 1.5; }
        .alert { border-radius: 10px; padding: 12px 14px; margin-bottom: 14px; font-size: 14px; border-left: 4px solid; }
        .alert.error { background: #fef2f2; color: #b91c1c; border-color: #ef4444; }
        .alert.success { background: #ecfdf3; color: #166534; border-color: #22c55e; }
        label { display: block; margin-bottom: 8px; font-weight: 700; color: var(--text); font-size: 14px; }
        input[type="email"] {
            width: 100%;
            padding: 12px 13px;
            border-radius: 10px;
            border: 1px solid var(--border);
            font-size: 15px;
            background: #fff;
            transition: border 0.2s ease, box-shadow 0.2s ease;
        }
        input:focus {
            outline: none;
            border-color: var(--accent);
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
        }
        button {
            width: 100%;
            padding: 13px;
            background: linear-gradient(135deg, var(--accent), #1d4ed8);
            color: #fff;
            border: none;
            border-radius: 10px;
            font-size: 15px;
            font-weight: 700;
            cursor: pointer;
            box-shadow: 0 10px 20px rgba(37, 99, 235, 0.18);
            transition: transform 0.12s ease, box-shadow 0.12s ease;
            margin-top: 12px;
        }
        button:hover { transform: translateY(-1px); box-shadow: 0 14px 26px rgba(37, 99, 235, 0.22); }
        .links { text-align: center; margin-top: 14px; font-size: 14px; color: var(--muted); }
        .links a { color: var(--accent); text-decoration: none; font-weight: 600; }
    </style>
</head>
<body>
<div class="card">
    <h1>Quên mật khẩu</h1>
    <p class="muted">Nhập email trường để nhận liên kết đặt lại mật khẩu.</p>

    <c:if test="${not empty error}">
        <div class="alert error">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="alert success">
            <c:out value="${success}"/>
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/forgot-password" method="post">
        <label for="email">Email sinh viên</label>
        <input type="email" id="email" name="email" placeholder="123@st.hcmuaf.edu.vn" required />
        <button type="submit">Gửi liên kết đặt lại</button>
    </form>

    <div class="links">
        <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
    </div>
</div>
</body>
</html>