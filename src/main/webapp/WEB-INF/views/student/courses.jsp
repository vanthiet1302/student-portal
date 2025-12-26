<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
  <title>Đăng ký môn học</title>
  <style>
    :root{--bg:#f8fafc;--card:#fff;--text:#111827;--muted:#6b7280;--accent:#16a34a;--border:#e5e7eb}
    *{box-sizing:border-box}
    body{margin:0;font-family:"Segoe UI", Tahoma, sans-serif;background:var(--bg);color:var(--text)}
    header{padding:24px;background:linear-gradient(135deg,#16a34a,#22c55e);color:#fff;display:flex;justify-content:space-between;align-items:center}
    header .title{font-size:22px;font-weight:700;margin:0}
    header .subtitle{margin:0;color:#e8ffe8;font-size:13px}
    header > div:first-child{flex:1}
    header .btn{padding:8px 12px;border-radius:8px;border:1px solid #fff;background:#ef4444;color:#fff;text-decoration:none;font-weight:600}
    main{padding:24px}
    .alert{padding:12px;border-radius:8px;margin-bottom:12px}
    .alert.success{background:#dcfce7;color:#166534;border:1px solid #86efac}
    .alert.error{background:#fee2e2;color:#b91c1c;border:1px solid #fca5a5}
    .card{background:var(--card);border:1px solid var(--border);border-radius:10px;padding:16px;box-shadow:0 6px 16px rgba(0,0,0,.04);margin-bottom:12px}
    .course-item{border:1px solid var(--border);border-radius:8px;padding:12px;margin-bottom:10px;background:#f9fafb}
    .course-info{display:grid;grid-template-columns:1fr auto;gap:12px;align-items:center}
    .course-name{font-weight:600;font-size:15px;margin-bottom:4px}
    .course-meta{font-size:13px;color:var(--muted);line-height:1.4}
    .course-actions{display:flex;gap:8px}
    .btn-small{padding:6px 10px;border-radius:6px;border:1px solid var(--border);background:#fff;text-decoration:none;color:var(--text);font-weight:600;font-size:13px;cursor:pointer}
    .btn-small.register{background:var(--accent);color:#fff;border-color:var(--accent)}
    .btn-small.register:hover{opacity:0.9}
    .availability{font-size:13px;padding:4px 8px;border-radius:4px}
    .availability.available{background:#dcfce7;color:#166534}
    .availability.full{background:#fee2e2;color:#b91c1c}
    .back-btn{padding:8px 12px;border-radius:8px;border:1px solid var(--border);background:#fff;text-decoration:none;color:var(--text);font-weight:600;margin-bottom:12px}
  </style>
</head>
<body>
<header>
  <div>
    <div class="title">Đăng ký môn học</div>
    <p class="subtitle">Chọn và đăng ký các môn học cho học kỳ này</p>
  </div>
  <a class="btn" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</header>

<main>
  <a class="back-btn" href="${pageContext.request.contextPath}/student/home">⬅ Trở về</a>

  <c:if test="${not empty sessionScope.success}">
    <div class="alert success">${sessionScope.success}</div>
    <c:set var="x" scope="session" value="${null}"/>
  </c:if>
  <c:if test="${not empty sessionScope.error}">
    <div class="alert error">${sessionScope.error}</div>
    <c:set var="x" scope="session" value="${null}"/>
  </c:if>

  <div class="card">
    <h3>Danh sách lớp học khả dụng</h3>
    <c:choose>
      <c:when test="${not empty classes}">
        <c:forEach var="classItem" items="${classes}">
          <div class="course-item">
            <div class="course-info">
              <div>
                <div class="course-name">${courseMap[classItem.courseId].name} (${classItem.classCode})</div>
                <div class="course-meta">
                  <div>Mã môn: ${courseMap[classItem.courseId].courseCode}</div>
                  <div>Tín chỉ: ${courseMap[classItem.courseId].credits}</div>
                  <div>Lịch: ${classItem.schedule}</div>
                  <div>Tuần: ${classItem.weekRange}</div>
                </div>
              </div>
              <div style="text-align:right;white-space:nowrap">
                <div class="availability ${classItem.currentStudents >= classItem.maxStudents ? 'full' : 'available'}">
                  ${classItem.currentStudents}/${classItem.maxStudents}
                </div>
                <div class="course-actions" style="margin-top:8px">
                  <form method="post" action="${pageContext.request.contextPath}/student/register-course" style="margin:0">
                    <input type="hidden" name="classId" value="${classItem.id}">
                    <button type="submit" class="btn-small register" ${classItem.currentStudents >= classItem.maxStudents ? 'disabled' : ''}>
                      ${classItem.currentStudents >= classItem.maxStudents ? 'Đầy' : 'Đăng ký'}
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <p style="color:var(--muted)">Không có lớp học nào khả dụng. Vui lòng quay lại sau.</p>
      </c:otherwise>
    </c:choose>
  </div>
</main>
</body>
</html>
