<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Tra cứu sinh viên</title>
</head>
<body>

<h2>Tra cứu thông tin sinh viên</h2>

<form action="studentTest" method="post">
    MSSV: <input type="text" name="username" required>
    <button type="submit">Tra cứu</button>
</form>

<hr>

<c:if test="${empty student}">
    <p style="color: gray;">Không có dữ liệu để hiển thị</p>
</c:if>

<c:if test="${not empty student}">
    <h3>Kết quả tìm kiếm</h3>
    <p><b>MSSV:</b> ${student.username}</p>
    <p><b>Họ tên:</b> ${student.firstname} ${student.lastname}</p>
    <p><b>Email:</b> ${student.primaryEmail}</p>
    <p><b>SĐT:</b> ${student.phone}</p>
    <p><b>Giới tính:</b>
        <c:choose>
            <c:when test="${student.male}">Nam</c:when>
            <c:otherwise>Nữ</c:otherwise>
        </c:choose>
    </p>
    <p><b>Ngày Sinh:</b> ${student.dob}</p>
    <p><b>Trạng thái:</b> ${student.status}</p>
    <p><b>CCCD/CMND:</b> ${student.citizenId}</p>
    <p><b>Dân Tộc:</b> ${student.nation}</p>
    <p><b>Tôn Giáo:</b> ${student.religion}</p>
    <p><b>Nơi Sinh:</b> ${student.pob}</p>
    <p><b>Quốc Tịch:</b> ${student.nationality}</p>
    <p><b>Địa chỉ:</b> ${student.address}</p>
    <p><b>Lớp:</b> ${student.classId}</p>
    <p><b>Ảnh</b> ${student.avatarUrl}</p>
    </c:if>

</body>
</html>
