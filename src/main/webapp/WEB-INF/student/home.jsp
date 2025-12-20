<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20-Dec-25
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
  <title>Student Home</title>
  <style>
    :root {
      --bg: #f8fafc;
      --card: #ffffff;
      --text: #111827;
      --muted: #6b7280;
      --accent: #16a34a;
      --accent-soft: #dcfce7;
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
      background: linear-gradient(135deg, #16a34a, #22c55e);
      color: #fff;
    }
    header .title { font-size: 24px; font-weight: 700; margin: 0 0 4px; }
    header .subtitle { margin: 0; color: #e8ffe8; }
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
      <div class="title">Trang sinh viên</div>
      <p class="subtitle">Chào, <strong>${sessionScope.user.username}</strong> (Role: Student)</p>
    </div>
    <div>
      <a class="btn" style="background:#ef4444;color:#fff;border-color:#ef4444" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </div>
  </div>
 </header>

<main>
  <div class="grid">
    <div class="card">
      <h3>Đăng ký môn học</h3>
      <p>Chọn và đăng ký các môn học cho học kỳ này.</p>
      <div class="actions">
        <a class="btn primary" href="${pageContext.request.contextPath}/student/courses">Đăng ký</a>
      </div>
    </div>

    <div class="card">
      <h3>Lịch học</h3>
      <p>Xem thời khóa biểu và lịch thi sắp tới.</p>
      <div class="actions">
        <a class="btn primary" href="${pageContext.request.contextPath}/student/schedule">Xem lịch</a>
      </div>
    </div>

    <div class="card">
      <h3>Điểm số</h3>
      <p>Theo dõi kết quả học tập của bạn theo từng học kỳ.</p>
      <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/student/grades">Xem điểm</a>
      </div>
    </div>

    <div class="card">
      <h3>Tài liệu</h3>
      <p>Tải bài giảng, bài tập và tham khảo tài nguyên.</p>
      <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/student/resources">Tài liệu</a>
      </div>
    </div>

    <div class="card">
      <h3>Hồ sơ cá nhân</h3>
      <p>Cập nhật thông tin liên lạc và mật khẩu.</p>
      <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/student/profile">Hồ sơ</a>
      </div>
    </div>
  </div>
</main>
</body>
</html>
