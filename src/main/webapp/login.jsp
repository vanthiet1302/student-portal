<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/7/2025
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>NLU Portal</title>
        <link rel="icon" type="image/png" href="/assets/logo.png">
    </head>
    <body>
        <c:import url="/share/header.jsp"></c:import>
        <form action="login" method="post">
            <label for="username">Tài khoản</label>
            <br>
            <input type="text" name="username">
            <br>
            <label for="password">Mật khẩu</label>
            <br>
            <input type="password" name="password">
            <br>
            <a href="">Quên mật khẩu?</a>
            <br>
            <input type="submit" value="Đăng nhập">
        </form>

    </body>
</html>
