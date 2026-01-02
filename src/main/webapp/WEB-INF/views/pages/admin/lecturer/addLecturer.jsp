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
    <title>Thêm Giảng Viên</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-container { width: 800px; margin: auto; border: 1px solid #ccc; padding: 20px; }
        .section-title { background: #f0f0f0; padding: 10px; font-weight: bold; margin-top: 20px; border-left: 5px solid #007bff; }
        table { width: 100%; border-collapse: collapse; }
        td { padding: 8px; vertical-align: top; }
        .label { width: 25%; font-weight: bold; }
        input[type="text"], input[type="number"], input[type="email"], select { width: 100%; padding: 5px; }
        .btn-group { margin-top: 20px; text-align: right; }
        .btn-save { padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        .btn-reset { padding: 10px 20px; background-color: #6c757d; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>

<div class="form-container">
    <h2 style="text-align: center;">BIỂU MẪU THÊM GIẢNG VIÊN</h2>

    <form action="${pageContext.request.contextPath}/admin/addLecturer" method="POST">

        <div class="section-title">1. THÔNG TIN CÁ NHÂN</div>
        <input type="text" name="username">
        <table>
            <tr>
                <td class="label">Họ và tên:</td>
                <td colspan="3"><input type="text" name="fullName" required></td>
            </tr>
            <tr>
                <td class="label">Năm sinh:</td>
                <td><input type="number" name="birthYear"></td>
                <td class="label">Giới tính:</td>
                <td>
                    <select name="gender">
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label">Số CMND/CCCD:</td>
                <td><input type="text" name="identityCard"></td>
                <td class="label">Chuyên môn:</td>
                <td><input type="text" name="specialization"></td>
            </tr>
        </table>

        <div class="section-title">2. HỌC HÀM, HỌC VỊ & CÔNG TÁC</div>
        <table>
            <tr>
                <td class="label">Học hàm:</td>
                <td><input type="text" name="academicRank"></td>
                <td class="label">Học vị:</td>
                <td><input type="text" name="degree"></td>
            </tr>
            <tr>
                <td class="label">Chức vụ:</td>
                <td><input type="text" name="position"></td>
                <td class="label">Khoa/Phòng:</td>
                <td><input type="text" name="department"></td>
            </tr>
            <tr>
                <td class="label">Cơ quan:</td>
                <td><input type="text" name="workAgency"></td>
                <td class="label">Địa chỉ cơ quan:</td>
                <td><input type="text" name="agencyAddress"></td>
            </tr>
        </table>

        <div class="section-title">3. LIÊN LẠC & TÀI KHOẢN</div>
        <table>
            <tr>
                <td class="label">Email công việc:</td>
                <td><input type="email" name="emailWork"></td>
                <td class="label">Email cá nhân:</td>
                <td><input type="email" name="emailPersonal"></td>
            </tr>
            <tr>
                <td class="label">SĐT di động:</td>
                <td><input type="text" name="phoneMobile"></td>
                <td class="label">SĐT bàn:</td>
                <td><input type="text" name="phoneFixed"></td>
            </tr>
            <tr>
                <td class="label">Số Fax:</td>
                <td><input type="text" name="fax"></td>
                <td class="label">Số tài khoản:</td>
                <td><input type="text" name="bankAccountNumber"></td>
            </tr>
            <tr>
                <td class="label">Ngân hàng:</td>
                <td><input type="text" name="bankName"></td>
                <td class="label">Chi nhánh:</td>
                <td><input type="text" name="bankBranch"></td>
            </tr>
        </table>

        <div class="btn-group">
            <button type="reset" class="btn-reset">Nhập lại</button>
            <button type="submit" class="btn-save">Lưu giảng viên</button>
        </div>
    </form>
</div>

</body>
</html>