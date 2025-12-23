<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20-Dec-25
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
  <title>Admin Dashboard</title>
  <style>
    :root {
      --bg: #f5f7fb;
      --card: #ffffff;
      --text: #1f2937;
      --muted: #6b7280;
      --accent: #2563eb;
      --accent-soft: #e0e7ff;
      --border: #e5e7eb;
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
      background: linear-gradient(135deg, #1d4ed8, #2563eb);
      color: #fff;
    }
    header .title { font-size: 24px; font-weight: 700; margin: 0 0 4px; }
    header .subtitle { margin: 0; color: #e0e7ff; }
    main { padding: 24px; display: grid; gap: 16px; }
    .card {
      background: var(--card);
      border: 1px solid var(--border);
      border-radius: 10px;
      padding: 16px 18px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.04);
    }
    .card h3 { margin: 0 0 8px; font-size: 18px; }
    .card p { margin: 0; color: var(--muted); }
    .grid { display: grid; gap: 12px; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); }
    .badge {
      display: inline-flex;
      align-items: center;
      gap: 6px;
      padding: 6px 10px;
      background: var(--accent-soft);
      color: var(--accent);
      border-radius: 999px;
      font-weight: 600;
      font-size: 13px;
    }
    .actions { display: flex; gap: 10px; flex-wrap: wrap; margin-top: 10px; }
    .btn {
      padding: 10px 14px;
      border-radius: 8px;
      border: 1px solid var(--border);
      background: #fff;
      color: var(--text);
      text-decoration: none;
      font-weight: 600;
      transition: all 0.15s ease;
    }
    .btn.primary { background: var(--accent); color: #fff; border-color: var(--accent); }
    .btn:hover { transform: translateY(-1px); box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
  </style>
</head>
<body>
<header>
  <div style="display:flex;align-items:center;justify-content:space-between;gap:12px">
    <div>
      <div class="title">Bảng điều khiển Admin</div>
      <p class="subtitle">Xin chào, <strong>${sessionScope.user.username}</strong> (Role: Admin)</p>
    </div>
    <div>
      <a class="btn" style="background:#ef4444;color:#fff;border-color:#ef4444" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </div>
  </div>
</header>

<main>
  <div class="grid">
    <div class="card">
      <h3>Quản lý sinh viên</h3>
      <p>Xem danh sách, duyệt hồ sơ và cập nhật thông tin sinh viên.</p>
      <div class="actions">
        <a class="btn primary" href="${pageContext.request.contextPath}/admin/students">Đi tới trang</a>
        <span class="badge">Tác vụ</span>
      </div>
    </div>

    <div class="card">
      <h3>Quản lý môn học</h3>
      <p>Thêm, sửa, xoá môn học và phân công giảng viên.</p>
      <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/admin/courses">Quản lý</a>
        <a class="btn" href="${pageContext.request.contextPath}/admin/schedule">Lịch dạy</a>
      </div>
    </div>

    <div class="card">
      <h3>Báo cáo & thống kê</h3>
      <p>Theo dõi số lượng sinh viên, tỉ lệ hoàn thành và kết quả học tập.</p>
      <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/admin/reports">Xem báo cáo</a>
      </div>
    </div>

    <div class="card">
      <h3>Cài đặt hệ thống</h3>
      <p>Cấu hình quyền hạn, tài khoản và thông báo.</p>
      <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/admin/settings">Cài đặt</a>
      </div>
    </div>
  </div>
</main>
</body>
</html>
