<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<h1>Đăng nhập</h1>
	<c:if test="${not empty error}">
		<div class="alert error">
			<c:out value="${error}" />
		</div>
	</c:if>

	<c:if test="${not empty success}">
		<div class="alert success">
			<c:out value="${success}" />
		</div>
	</c:if>

	<form action="login" method="post">
		<p>Tên đăng nhập</p>
		<input type='text' name='username' placeholder='Nhập tên đăng nhập'
			value='${parma.username}'>
		<p>Mật khẩu</p>
		<input type='password' name='password' placeholder='Nhập mật khẩu'
			value='${param.password}'> <input type="submit"
			value="Đăng nhập">
	</form>
</body>
</html>