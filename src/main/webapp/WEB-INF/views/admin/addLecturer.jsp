<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26-Dec-25
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm giảng viên</title>
    <base href="${pageContext.request.contextPath}/">
</head>
<body>
<h2>Thêm Giảng Viên Mới</h2>

<form action="admin/addLecturer" method="POST">

    <fieldset>
        <legend>Tài khoản hệ thống</legend>
        Tên đăng nhập: <input type="text" name="username" required><br>
        <%--        Mật khẩu mặc định là năm sinh * 2 + @: 20052005@--%>
<%--        Mật khẩu: <input type="password" name="password" required><br>--%>
    </fieldset>

    <br>

    <fieldset>
        <legend>Thông tin cá nhân</legend>
        Họ và tên: <input type="text" name="fullName" required><br>
        Năm sinh: <input type="number" name="birthYear" required><br>
        Giới tính:
        <input type="radio" name="gender" value="Nam"> Nam
        <input type="radio" name="gender" value="Nữ"> Nữ<br>
        CMND/CCCD: <input type="text" name="identityCard" required><br>
    </fieldset>

    <br>

    <fieldset>
        <legend>Học thuật & Công tác</legend>
        Học hàm: <input type="text" name="academicRank" placeholder="GS, PGS..."><br>
        Học vị: <input type="text" name="degree" placeholder="Thạc sĩ, Tiến sĩ..."><br>
        Chuyên ngành: <input type="text" name="specialization"><br>
        Chức vụ: <input type="text" name="position"><br>
        Khoa/Phòng: <input type="text" name="department" required><br>
        Cơ quan công tác: <input type="text" name="workAgency"><br>
        Địa chỉ cơ quan: <input type="text" name="agencyAddress"><br>
        SĐT cố định: <input type="text" name="phoneFixed"><br>
        Fax: <input type="text" name="fax"><br>
    </fieldset>

    <br>

    <fieldset>
        <legend>Liên lạc</legend>
        Email cơ quan: <input type="email" name="emailWork"><br>
        Email cá nhân: <input type="email" name="emailPersonal"><br>
        SĐT di động: <input type="text" name="phoneMobile"><br>
    </fieldset>

    <br>

    <fieldset>
        <legend>Tài khoản ngân hàng</legend>
        Số tài khoản: <input type="text" name="bankAccountNumber"><br>
        Tên ngân hàng: <input type="text" name="bankName"><br>
        Chi nhánh: <input type="text" name="bankBranch"><br>
    </fieldset>

    <br>
    <button type="submit">Lưu Giảng Viên</button>
    <button type="reset">Hủy bỏ</button>
</form>
</body>
</html>
