<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04-Jan-26
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <fmt:message key="page.login.title"/>
    </title>
     <link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
          rel="stylesheet">

    <link href="${pageContext.request.contextPath}/assets/css/common.css"
          rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100 overflow-hidden">
<div   class="page-wrapper flex-grow-1 d-flex flex-column">
      <div class="page-frame flex-grow-1 d-flex flex-column">
<div class="login-container flex-grow-1 position-relative d-flex align-items-center justify-content-center p-3">
 <img
      src="${pageContext.request.contextPath}/assets/images/AQ1.png"
      class="bg-image d-none d-md-block"
       alt="Ảnh Thiên Lý"
          />
    <div class="card shadow-lg border-0 rounded-4 overflow-hidden">
        <div class="card-body p-4">
            <h1 class="text-center mb-4">
                  <div class="text-center">
                      <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="Logo" width="100" height="100" />
                      </div>
                <fmt:message key="page.login.title"/>
            </h1>

            <div id="alertContainer"></div>

            <form id="loginForm" action="${pageContext.request.contextPath}/sign-in" method="post">
                <!-- UserName -->
                <div class="mb-3">
                <label for="username" class="form-label">
                        <fmt:message key="page.login.username"/>
                    </label>
                            <div class="position-relative">
                                <input type="text"
                                       name="username"
                                       class="form-control form-control-lg ps-5"
                                       id="username"
                                       placeholder="<fmt:message key='page.login.username.placeholder'/>"
                                       required>
                                <i class="bi bi-person-fill position-absolute top-50 start-0 translate-middle-y ms-3 text-muted fs-4"></i>
                                </div>
                    <div class="invalid-feedback" id="usernameError"></div>
                </div>
                <!-- Password -->
   <div class="mb-3">
       <label for="password" class="form-label">
           <fmt:message key="page.login.password"/>
       </label>

       <div class="position-relative">
           <input type="password"
                  name="password"
                  class="form-control form-control-lg ps-5"
                  id="password"
                  placeholder="<fmt:message key='page.login.password.placeholder'/>"
                  required>
           <i class="bi bi-lock-fill position-absolute top-50 start-0 translate-middle-y ms-3 text-muted fs-4"></i>
       </div>
                    <div class="invalid-feedback" id="passwordError"></div>
                </div>
                <div class="mb-3 text-end">
                    <a href="${pageContext.request.contextPath}/forgot-password" class="text-decoration-none">
                        <fmt:message key="page.login.forgot.password"/>
                    </a>
                </div>
                <button type="submit"
                        class="btn btn-primary w-100"
                        id="submitBtn">
                    <fmt:message key="button.login"/>
                </button>
            </form>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/change-language?lang=vi&redirect=/login" class="me-2">🇻🇳 VI</a>
                <a href="${pageContext.request.contextPath}/change-language?lang=en&redirect=/login">🇺🇸 EN</a>
            </div>
        </div>
    </div>
</div>
<footer class="footer d-none d-md-flex">
  <div class="marquee text-white fs-2 d-flex align-items-center justify-content-center text-nowrap position-relative">
            <span>
              <b><fmt:message key="footer.text" /></b>
            </span>
          </div>
        </footer>
      </div>
    </div>
    </body>
</html>

