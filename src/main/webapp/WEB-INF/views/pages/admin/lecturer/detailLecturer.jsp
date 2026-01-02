<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26-Dec-25
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hồ sơ giảng viên</title>
</head>
<body>

<h1>Hồ sơ giảng viên: ${lecturer.fullName}</h1>

<h3>I. Thông tin cá nhân cơ bản</h3>
<table border="1" cellpadding="5" cellspacing="0" width="100%">
    <tr>
        <th align="left" width="30%">Họ và tên</th>
        <td>${lecturer.fullName}</td>
    </tr>
    <tr>
        <th align="left">Năm sinh</th>
        <td>${lecturer.birthYear}</td>
    </tr>
    <tr>
        <th align="left">Giới tính</th>
        <td>${lecturer.gender}</td>
    </tr>
    <tr>
        <th align="left">CMND/CCCD</th>
        <td>${lecturer.identityCard}</td>
    </tr>
</table>

<h3>II. Thông tin học thuật</h3>
<table border="1" cellpadding="5" cellspacing="0" width="100%">
    <tr>
        <th align="left" width="30%">Học hàm</th>
        <td>${lecturer.academicRank}</td>
    </tr>
    <tr>
        <th align="left">Học vị</th>
        <td>${lecturer.degree}</td>
    </tr>
    <tr>
        <th align="left">Chuyên ngành</th>
        <td>${lecturer.specialization}</td>
    </tr>
    <tr>
        <th align="left">Chức vụ</th>
        <td>${lecturer.position}</td>
    </tr>
</table>

<h3>III. Thông tin công tác</h3>
<table border="1" cellpadding="5" cellspacing="0" width="100%">
    <tr>
        <th align="left" width="30%">Khoa / Bộ môn</th>
        <td>${lecturer.department}</td>
    </tr>
    <tr>
        <th align="left">Cơ quan công tác</th>
        <td>${lecturer.workAgency}</td>
    </tr>
    <tr>
        <th align="left">Địa chỉ cơ quan</th>
        <td>${lecturer.agencyAddress}</td>
    </tr>
    <tr>
        <th align="left">Điện thoại cố định</th>
        <td>${lecturer.phoneFixed}</td>
    </tr>
    <tr>
        <th align="left">Fax</th>
        <td>${lecturer.fax}</td>
    </tr>
</table>

<h3>IV. Thông tin liên lạc</h3>
<table border="1" cellpadding="5" cellspacing="0" width="100%">
    <tr>
        <th align="left" width="30%">Email công việc</th>
        <td>${lecturer.emailWork}</td>
    </tr>
    <tr>
        <th align="left">Email cá nhân</th>
        <td>${lecturer.emailPersonal}</td>
    </tr>
    <tr>
        <th align="left">Điện thoại di động</th>
        <td>${lecturer.phoneMobile}</td>
    </tr>
</table>

<h3>V. Thông tin ngân hàng</h3>
<table border="1" cellpadding="5" cellspacing="0" width="100%">
    <tr>
        <th align="left" width="30%">Số tài khoản</th>
        <td>${lecturer.bankAccountNumber}</td>
    </tr>
    <tr>
        <th align="left">Tên ngân hàng</th>
        <td>${lecturer.bankName}</td>
    </tr>
    <tr>
        <th align="left">Chi nhánh</th>
        <td>${lecturer.bankBranch}</td>
    </tr>
</table>

<p>
    <a href="${pageContext.request.contextPath}/admin/lecturer-list">Quay lại danh sách</a>
</p>

</body>
</html>