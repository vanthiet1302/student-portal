<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test Student DAO</title>
</head>
<body>
<h2>Danh sách sinh viên</h2>

<table border="1">
    <tr>
        <th>Username</th>
        <th>Họ</th>
        <th>Tên</th>
        <th>Action</th>
    </tr>
   <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.username}</td>
            <td>${student.firstname}</td>
            <td>${student.lastname}</td>
            <td>
                <form method="post" style="display:inline">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="username" value="${student.username}">
                    <button type="submit">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h3>Thêm student mới</h3>
<form method="post">
    <input type="hidden" name="action" value="add">
    Username: <input type="text" name="username"><br>
    Họ: <input type="text" name="first_name"><br>
    Tên: <input type="text" name="last_name"><br>
    Email: <input type="text" name="primary_email"><br>
    Mật khẩu: <input type="text" name="hash_password"><br>
    DOB: <input type="date" name="dob"><br>
    Giới tính:
        <input type="radio" name="gender" value="male" checked>Nam
        <input type="radio" name="gender" value="female">Nữ<br>
    Trạng thái: <input type="text" name="status"><br>
    Phone: <input type="text" name="phone"><br>
    CitizenId: <input type="text" name="citizen_id"><br>
    Dân tộc: <input type="text" name="nation"><br>
    Tôn giáo: <input type="text" name="religion"><br>
    Nơi sinh: <input type="text" name="pob"><br>
    Quốc tịch: <input type="text" name="nationality"><br>
    Địa chỉ: <input type="text" name="address"><br>
    Avatar URL: <input type="text" name="avatar_url"><br>
    <button type="submit">Thêm student</button>
</form>
<div style="display: flex; align-items: center; gap: 20px; margin-bottom: 20px;">

    <form method="get" style="display: flex; gap: 10px;">
        <input type="text" name="search" placeholder="Nhập username cần tìm"
               value="${param.search}">
        <button type="submit">Tìm</button>
    </form>

    <c:if test="${not empty foundStudent}">
        <div>
            <b>${foundStudent.firstname} ${foundStudent.lastname}</b>
            (<i>${foundStudent.username}</i>)
        </div>
    </c:if>

</div>
</div>
</body>
</html>
