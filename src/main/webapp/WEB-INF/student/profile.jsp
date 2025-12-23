<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20-Dec-25
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
  <title>Hồ sơ cá nhân</title>
  <style>
    :root {
      --bg: #f8fafc;
      --card: #ffffff;
      --text: #0f172a;
      --muted: #64748b;
      --accent: #0ea5e9;
      --accent-soft: #e0f2fe;
      --border: #e2e8f0;
      --danger: #ef4444;
    }
    * { box-sizing: border-box; }
    body {
      margin: 0;
      font-family: "Segoe UI", Tahoma, sans-serif;
      background: var(--bg);
      color: var(--text);
    }
    header {
      padding: 24px;
      background: linear-gradient(135deg, #0ea5e9, #22c55e);
      color: #fff;
    }
    header .title { font-size: 24px; font-weight: 700; margin: 0 0 4px; }
    header .subtitle { margin: 0; color: #e0f2fe; }
    main { padding: 24px; display: grid; gap: 16px; }
    .card {
      background: var(--card);
      border: 1px solid var(--border);
      border-radius: 12px;
      padding: 18px 20px;
      box-shadow: 0 8px 18px rgba(0, 0, 0, 0.05);
    }
    .card h3 { margin: 0 0 12px; font-size: 18px; }
    .muted { color: var(--muted); margin: 0 0 14px; }
    .form-grid { display: grid; gap: 14px; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); }
    label { display: block; font-weight: 600; margin-bottom: 6px; color: var(--text); }
    input, select {
      width: 100%;
      padding: 11px 12px;
      border-radius: 8px;
      border: 1px solid var(--border);
      background: #fff;
      font-size: 14px;
    }
    input[readonly] { background: #f1f5f9; color: var(--muted); }
    .actions { display: flex; gap: 10px; flex-wrap: wrap; margin-top: 12px; }
    .btn {
      padding: 11px 16px;
      border-radius: 8px;
      border: 1px solid var(--border);
      background: #fff;
      color: var(--text);
      text-decoration: none;
      font-weight: 600;
      cursor: pointer;
      transition: all 0.15s ease;
    }
    .btn.primary { background: var(--accent); color: #fff; border-color: var(--accent); }
    .btn.danger { background: var(--danger); color: #fff; border-color: var(--danger); }
    .btn:hover { transform: translateY(-1px); box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
    .row { display: grid; gap: 16px; grid-template-columns: 2fr 1fr; }
    @media (max-width: 860px) { .row { grid-template-columns: 1fr; } }
  </style>
</head>
<body>
<header>
  <div class="title">Hồ sơ cá nhân</div>
  <p class="subtitle">Xin chào, <strong>${sessionScope.user.username}</strong></p>
</header>

<main>
  <div class="row">
    <section class="card">
      <h3>Thông tin sinh viên</h3>
      <p class="muted">Cập nhật thông tin liên hệ và học tập.</p>
      <form action="${pageContext.request.contextPath}/student/profile" method="post">
        <div class="form-grid">
          <div>
            <label>Mã đăng nhập</label>
            <input type="text" name="username" value="${sessionScope.user.username}" readonly>
          </div>
          <div>
            <label>Họ và tên</label>
            <input type="text" name="fullName" value="${profile.fullName}" placeholder="Nhập họ tên">
          </div>
          <div>
            <label>Email</label>
            <input type="email" name="email" value="${profile.email}" placeholder="email@domain.com">
          </div>
          <div>
            <label>Số điện thoại</label>
            <input type="text" name="phone" value="${profile.phone}" placeholder="0123 456 789">
          </div>
          <div>
            <label>Khoa / Ngành</label>
            <input type="text" name="major" value="${profile.major}" placeholder="Công nghệ thông tin">
          </div>
          <div>
            <label>Khóa học</label>
            <input type="text" name="course" value="${profile.course}" placeholder="K2022">
          </div>
        </div>
        <div class="actions">
          <button class="btn primary" type="submit">Lưu thay đổi</button>
          <a class="btn" href="${pageContext.request.contextPath}/student/home">Về trang chủ</a>
        </div>
      </form>
    </section>

    <section class="card">
      <h3>Bảo mật tài khoản</h3>
      <p class="muted">Đổi mật khẩu để bảo vệ tài khoản của bạn.</p>
      <form action="${pageContext.request.contextPath}/student/profile/password" method="post">
        <div class="form-grid" style="grid-template-columns: 1fr;">
          <div>
            <label>Mật khẩu hiện tại</label>
            <input type="password" name="currentPassword" placeholder="••••••••">
          </div>
          <div>
            <label>Mật khẩu mới</label>
            <input type="password" name="newPassword" placeholder="••••••••">
          </div>
          <div>
            <label>Nhập lại mật khẩu mới</label>
            <input type="password" name="confirmPassword" placeholder="••••••••">
          </div>
        </div>
        <div class="actions">
          <button class="btn primary" type="submit">Đổi mật khẩu</button>
          <button class="btn danger" type="reset">Nhập lại</button>
        </div>
      </form>
    </section>
  </div>
</main>
</body>
</html>
