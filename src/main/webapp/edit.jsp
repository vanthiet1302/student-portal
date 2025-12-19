<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         <%@ taglib prefix="c" uri="jakarta.tags.core" %>
         <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sua Thong Tin</title>
</head>
<body>
<form action="edit" method="post">

    <input type="hidden" name="username" value="${student.username}">

    Họ: <input type="text" name="firstname" value="${student.firstname}"><br>
    Tên: <input type="text" name="lastname" value="${student.lastname}"><br>
    Email: <input type="text" name="primary_email" value="${student.primaryEmail}"><br>
    Ngày sinh: <input type="date" name="dob" value="${student.dob}"><br>
    Giới tính:
    <select name="gender">
               <option value="true" ${student.male ? "selected" : ""}>Nam</option>
                   <option value="false" ${!student.male ? "selected" : ""}>Nữ</option>
                   </select>
    Trạng thái: <input type="text" name="status" value="${student.status}"><br>
    Phone: <input type="text" name="phone" value="${student.phone}"><br>
    CMND/CCCD: <input type="text" name="citizen_id" value="${student.citizenId}"><br>
    Dân tộc: <input type="text" name="nation" value="${student.nation}"><br>
    Tôn giáo: <input type="text" name="religion" value="${student.religion}"><br>
    Nơi sinh: <input type="text" name="pob" value="${student.pob}"><br>
    Quốc tịch: <input type="text" name="nationality" value="${student.nationality}"><br>
    Địa chỉ: <input type="text" name="address" value="${student.address}"><br>
    Avatar URL: <input type="text" name="avatar_url" value="${student.avatarUrl}"><br>

    <button type="submit">Lưu lại</button>
</form>
</body>
</html>