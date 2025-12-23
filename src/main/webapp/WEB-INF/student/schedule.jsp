<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
  <title>Lịch học</title>
  <style>
    :root{--bg:#f8fafc;--card:#fff;--text:#111827;--muted:#6b7280;--accent:#16a34a;--border:#e5e7eb}
    *{box-sizing:border-box}
    body{margin:0;font-family:"Segoe UI", Tahoma, sans-serif;background:var(--bg);color:var(--text)}
    header{padding:24px;background:linear-gradient(135deg,#16a34a,#22c55e);color:#fff}
    header .title{font-size:22px;font-weight:700;margin:0 0 4px}
    header .subtitle{margin:0;color:#e8ffe8}
    main{padding:24px}
    .card{background:var(--card);border:1px solid var(--border);border-radius:10px;padding:16px;box-shadow:0 6px 16px rgba(0,0,0,.04)}
    table{width:100%;border-collapse:collapse}
    th,td{padding:10px 12px;border-bottom:1px solid var(--border);text-align:left}
    th{background:#f1f5f9;font-weight:700}
    .badge{display:inline-block;padding:4px 8px;border-radius:999px;font-size:12px;font-weight:600}
    .badge.exam{background:#fee2e2;color:#b91c1c}
    .badge.class{background:#dcfce7;color:#16a34a}
    .toolbar{display:flex;gap:10px;margin-bottom:12px}
    .btn{padding:8px 12px;border-radius:8px;border:1px solid var(--border);background:#fff;text-decoration:none;color:var(--text);font-weight:600}
    .btn.primary{background:var(--accent);border-color:var(--accent);color:#fff}
  </style>
</head>
<body>
<header>
  <div class="title">Lịch học & Lịch thi</div>
  <p class="subtitle">Xem thời khóa biểu của bạn</p>
</header>
<main>
  <div class="card">
    <div class="toolbar">
      <a class="btn" href="${pageContext.request.contextPath}/student/home">⬅ Trở về</a>
      <a class="btn primary" href="#">Tải về (.ics)</a>
    </div>
    <table>
      <thead>
      <tr>
        <th>Môn học</th>
        <th>Ngày</th>
        <th>Thời gian</th>
        <th>Phòng</th>
        <th>Giảng viên</th>
        <th>Loại</th>
      </tr>
      </thead>
      <tbody>
      <c:choose>
        <c:when test="${not empty scheduleItems}">
          <c:forEach var="it" items="${scheduleItems}">
            <tr>
              <td>${it.course}</td>
              <td>${it.date}</td>
              <td>${it.time}</td>
              <td>${it.room}</td>
              <td>${it.lecturer}</td>
              <td>
                <c:choose>
                  <c:when test="${it.type == 'Thi'}"><span class="badge exam">Thi</span></c:when>
                  <c:otherwise><span class="badge class">Học</span></c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="6" style="color:var(--muted)">Chưa có lịch. Vui lòng quay lại sau.</td>
          </tr>
        </c:otherwise>
      </c:choose>
      </tbody>
    </table>
  </div>
</main>
</body>
</html>
