<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="vi">
<head>
  <title>Điểm số</title>
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
    .toolbar{display:flex;gap:10px;margin-bottom:12px}
    .btn{padding:8px 12px;border-radius:8px;border:1px solid var(--border);background:#fff;text-decoration:none;color:var(--text);font-weight:600}
    .btn.primary{background:var(--accent);border-color:var(--accent);color:#fff}
    .summary{margin-top:12px;color:var(--muted)}
  </style>
</head>
<body>
<header>
  <div class="title">Điểm số</div>
  <p class="subtitle">Theo dõi kết quả học tập của bạn</p>
</header>
<main>
  <div class="card">
    <div class="toolbar">
      <a class="btn" href="${pageContext.request.contextPath}/student/home">⬅ Trở về</a>
      <a class="btn primary" href="#">Xuất bảng điểm (PDF)</a>
    </div>
    <table>
      <thead>
      <tr>
        <th>Môn học</th>
        <th>Tín chỉ</th>
        <th>Giữa kỳ</th>
        <th>Cuối kỳ</th>
        <th>Tổng kết</th>
        <th>Xếp loại</th>
        <th>Học kỳ</th>
      </tr>
      </thead>
      <tbody>
      <c:choose>
        <c:when test="${not empty gradeItems}">
          <c:forEach var="it" items="${gradeItems}">
            <tr>
              <td>${it.course}</td>
              <td>${it.credits}</td>
              <td>${it.mid}</td>
              <td>${it.final}</td>
              <td>${it.total}</td>
              <td>${it.rank}</td>
              <td>${it.term}</td>
            </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="7" style="color:var(--muted)">Chưa có dữ liệu điểm. Vui lòng quay lại sau.</td>
          </tr>
        </c:otherwise>
      </c:choose>
      </tbody>
    </table>
    <div class="summary">
      <c:if test="${not empty gradeItems}">
        Tổng số môn: <c:out value="${fn:length(gradeItems)}"/>
      </c:if>
    </div>
  </div>
</main>
</body>
</html>
