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
    <title>Đăng ký tài khoản sinh viên</title>
    <style>
        :root {
            --bg: #0f172a;
            --panel: #0b1326;
            --card: #ffffff;
            --text: #0f172a;
            --muted: #64748b;
            --accent: #2563eb;
            --accent2: #22c55e;
            --border: #e2e8f0;
        }
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: "Segoe UI", Tahoma, sans-serif;
            background: radial-gradient(circle at 15% 20%, rgba(37,99,235,0.18), transparent 35%),
                        radial-gradient(circle at 90% 10%, rgba(34,197,94,0.18), transparent 32%),
                        var(--bg);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 24px;
        }
        .shell {
            width: 100%;
            max-width: 1100px;
            background: linear-gradient(145deg, #0c162c, #0b1120);
            border-radius: 18px;
            padding: 20px;
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
        .hero h1 { font-size: 30px; font-weight: 800; margin: 0; color: #fff; }
        .hero p { color: #cbd5e1; line-height: 1.6; }
        .pill { display: inline-flex; align-items: center; gap: 8px; background: rgba(37,99,235,0.2); color: #cbd5e1; padding: 8px 12px; border-radius: 999px; font-weight: 600; font-size: 13px; width: fit-content; }
        .card {
            background: var(--card);
            border-radius: 14px;
            padding: 22px 22px 20px;
            border: 1px solid var(--border);
            box-shadow: 0 18px 40px rgba(0,0,0,0.12);
        }
        .card h2 { margin: 0 0 6px; font-size: 22px; color: var(--text); }
        .muted { color: var(--muted); margin-bottom: 14px; }
        .section-title { margin: 18px 0 10px; font-size: 16px; font-weight: 800; color: var(--text); }
        .form-grid { display: grid; gap: 14px; grid-template-columns: repeat(auto-fit, minmax(240px, 1fr)); }
        .form-group { display: flex; flex-direction: column; gap: 8px; }
        label { font-weight: 700; color: var(--text); font-size: 14px; }
        input[type="text"], input[type="email"], input[type="password"], input[type="tel"], input[type="date"] {
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
        .radio-group { display: flex; gap: 14px; align-items: center; flex-wrap: wrap; }
        .alert { border-radius: 10px; padding: 12px 14px; margin-bottom: 14px; font-size: 14px; border-left: 4px solid; }
        .alert.error { background: #fef2f2; color: #b91c1c; border-color: #ef4444; }
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
        .login-link { text-align: center; margin-top: 14px; color: var(--muted); font-size: 14px; }
        .login-link a { color: var(--accent); text-decoration: none; font-weight: 600; }
    </style>
</head>
<body>
<div class="shell">
    <div class="grid">
        <section class="hero">
            <span class="pill">Cổng thông tin NLU</span>
            <h1>Tạo tài khoản sinh viên</h1>
            <p>Đăng ký để truy cập lịch học, điểm số, tài liệu và thông báo mới nhất. Vui lòng điền đầy đủ thông tin để được xét duyệt.</p>
        </section>

        <section class="card">
            <h2>Thông tin đăng ký</h2>
            <p class="muted">Điền thông tin tài khoản và cá nhân.</p>

            <c:if test="${not empty error}">
                <div class="alert error">
                    <c:out value="${error}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/register" method="post" novalidate>
                <div class="section-title">Thông tin tài khoản</div>
                <div class="form-grid">
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
                </div>

                <div class="section-title">Thông tin cá nhân</div>
                <div class="form-grid">
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
                </div>

                <button type="submit">Đăng ký ngay</button>
            </form>

            <div class="login-link">
                Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập tại đây</a>
            </div>
        </section>
    </div>
</div>
</body>
</html>