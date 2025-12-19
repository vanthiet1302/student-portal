<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Thông tin sinh viên</title>
</head>
<body>

<h2>THÔNG TIN SINH VIÊN</h2>
<c:if test="${not empty student}">

    <p><b>Username (MSSV):</b> ${student.username}</p>
    <p><b>Họ:</b> ${student.firstname}</p>
    <p><b>Tên:</b> ${student.lastname}</p>
    <p><b>Email chính:</b> ${student.primaryEmail}</p>
    <p><b>Số điện thoại:</b> ${student.phone}</p>
    <p><b>Ngày sinh:</b> ${student.dob}</p>
    <p><b>Giới tính:</b>
        <c:choose>
            <c:when test="${student.male}">Nam</c:when>
            <c:otherwise>Nữ</c:otherwise>
        </c:choose>
    </p>
    <p><b>Tình trạng:</b> ${student.status}</p>
    <p><b>CCCD:</b> ${student.citizenId}</p>
    <p><b>Dân tộc:</b> ${student.nation}</p>
    <p><b>Tôn giáo:</b> ${student.religion}</p>
    <p><b>Nơi sinh:</b> ${student.pob}</p>
    <p><b>Quốc tịch:</b> ${student.nationality}</p>
    <p><b>Địa chỉ:</b> ${student.address}</p>
    <p><b>Lớp học:</b> ${student.classId}</p>

    <p><b>Ngày tạo:</b> ${student.createAt}</p>
    <p><b>Ngày cập nhật:</b> ${student.updateAt}</p>

    <c:if test="${not empty student.avatarUrl}">
        <p><b>Avatar:</b><br>

        </p>
    </c:if>

</c:if>

<br>
<br><br>
<form action="edit" method="get">
    <input type="hidden" name="username" value="${student.username}">
    <button type="submit">Sửa đổi toàn bộ thông tin</button>
</form>
</body>
</html>
