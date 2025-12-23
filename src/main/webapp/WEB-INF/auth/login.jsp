<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập - Cổng Thông Tin Sinh Viên</title>
    <style>
        :root {
            --bg: #0f172a;
            --panel: #0b1220;
            --card: #ffffff;
            --text: #0f172a;
            --muted: #64748b;
            --accent: #2563eb;
            --accent2: #22c55e;
            --border: #e2e8f0;
        }
        * { margin: 0; padding: 0; box-sizing: border-box; }
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
        .shell {
            width: 100%;
            max-width: 960px;
            background: linear-gradient(145deg, #0b1326, #0c1020);
            border-radius: 18px;
            padding: 18px;
            box-shadow: 0 25px 60px rgba(0,0,0,0.35);
            border: 1px solid rgba(255,255,255,0.04);
        }
        .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 16px; }
        .hero {
            padding: 28px;
            color: #e2e8f0;
            display: flex;
            flex-direction: column;
            gap: 14px;
        }
        .hero h1 { font-size: 28px; font-weight: 800; margin: 0; color: #fff; }
        .hero p { color: #cbd5e1; margin: 0; line-height: 1.5; }
        .pill {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            background: rgba(37,99,235,0.15);
            color: #cbd5e1;
            padding: 8px 12px;
            border-radius: 999px;
            font-weight: 600;
            font-size: 13px;
            width: fit-content;
        }
        .card {
            background: var(--card);
            border-radius: 14px;
            padding: 26px 24px 22px;
            border: 1px solid var(--border);
            box-shadow: 0 18px 40px rgba(0,0,0,0.12);
        }
        .card h2 { margin: 0 0 6px; font-size: 22px; color: var(--text); }
        .card .muted { color: var(--muted); margin-bottom: 18px; }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 8px; font-weight: 700; color: var(--text); font-size: 14px; }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 13px 14px;
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
        .alert {
            border-radius: 10px;
            padding: 12px 14px;
            margin-bottom: 14px;
            font-size: 14px;
            border-left: 4px solid;
        }
        .alert.error { background: #fef2f2; color: #b91c1c; border-color: #ef4444; }
        .alert.success { background: #ecfdf3; color: #166534; border-color: #22c55e; }
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
        }
        button:hover { transform: translateY(-1px); box-shadow: 0 14px 26px rgba(37, 99, 235, 0.22); }
        button:active { transform: translateY(0); }
        .links { text-align: center; margin-top: 16px; font-size: 14px; color: var(--muted); }
        .links a { color: var(--accent); text-decoration: none; font-weight: 600; }
        .links span { color: var(--muted); padding: 0 8px; }
    </style>
</head>
<body>
<div class="shell">
    <div class="grid">
        <section class="hero">
            <span class="pill">Cổng thông tin NLU</span>
            <h1>Đăng nhập vào hệ thống</h1>
            <p>Truy cập lịch học, điểm số, tài liệu và thông báo mới nhất của trường. Vui lòng dùng mã sinh viên hoặc tên đăng nhập đã được cấp.</p>
        </section>

        <section class="card">
            <h2>Chào mừng trở lại</h2>
            <p class="muted">Nhập thông tin để tiếp tục.</p>

            <c:if test="${not empty error}">
                <div class="alert error">
                    <strong>Lỗi:</strong> <c:out value="${error}"/>
                </div>
            </c:if>

            <c:if test="${not empty success}">
                <div class="alert success">
                    <c:out value="${success}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username">Mã số sinh viên hoặc Tên đăng nhập</label>
                    <input type="text"
                           id="username"
                           name="username"
                           value="${fn:escapeXml(param.username)}"
                           placeholder="Ví dụ: 23130314 hoặc sv001"
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

                <button type="submit">Đăng nhập</button>
            </form>

            <div class="links">
                <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
                <span>•</span>
                <a href="${pageContext.request.contextPath}/register">Tạo tài khoản</a>
            </div>
        </section>
    </div>
</div>
</body>
</html>