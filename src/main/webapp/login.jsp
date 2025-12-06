<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div class="formstudent">
<form action="login" method="post">
		<label for="username">Tài khoản: </label>

		<input type="text" name="username">

		<label for="password">Mật khẩu: </label>

		<input type="password" name="password">

		<a href="forgot.jsp">Quên mật khẩu?</a>
		<br>
		<input type="submit" value="Đăng nhập">
	</form>
</div>

</body>
</html>