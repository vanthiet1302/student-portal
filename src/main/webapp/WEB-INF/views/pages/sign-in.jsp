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
    <link href="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        html, body {
            height: 100%;
        }
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f5f5f5;
        }
        .login-container {
            width: 100%;
            max-width: 400px;
            padding: 15px;
        }
        .is-invalid {
            border-color: #dc3545 !important;
        }
        .invalid-feedback {
            display: none;
            color: #dc3545;
            font-size: 0.875em;
        }
        .is-invalid ~ .invalid-feedback {
            display: block;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="card shadow">
        <div class="card-body p-4">
            <h1 class="text-center mb-4">
                <fmt:message key="page.login.title"/>
            </h1>

            <div id="alertContainer"></div>

            <form id="loginForm" novalidate>
                <div class="mb-3">
                    <label for="username" class="form-label">
                        <fmt:message key="page.login.username"/>
                    </label>
                    <input type="text"
                           name="username"
                           class="form-control"
                           id="username"
                           placeholder="<fmt:message key='page.login.username.placeholder'/>">
                    <div class="invalid-feedback" id="usernameError"></div>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">
                        <fmt:message key="page.login.password"/>
                    </label>
                    <input type="password"
                           name="password"
                           class="form-control"
                           id="password"
                           placeholder="<fmt:message key='page.login.password.placeholder'/>">
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

<script src="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/js/bootstrap.bundle.js"></script>
<script>
    const contextPath = '${pageContext.request.contextPath}';

    // i18n messages from server
    const i18n = {
        usernameRequired: '<fmt:message key="page.login.error.username.required"/>',
        passwordRequired: '<fmt:message key="page.login.error.password.required"/>',
        loggingIn: '<fmt:message key="page.login.logging.in"/>',
        loginBtn: '<fmt:message key="button.login"/>',
        errorGeneric: '<fmt:message key="page.login.error.generic"/>'
    };

    document.getElementById('loginForm').addEventListener('submit', function(e) {
        e.preventDefault();

        // Clear previous errors
        clearErrors();

        // Get form values
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value.trim();

        // Validate
        let isValid = true;

        if (!username) {
            showFieldError('username', i18n.usernameRequired);
            isValid = false;
        }

        if (!password) {
            showFieldError('password', i18n.passwordRequired);
            isValid = false;
        }

        if (!isValid) return;

        // Disable submit button
        const submitBtn = document.getElementById('submitBtn');
        submitBtn.disabled = true;
        submitBtn.textContent = i18n.loggingIn;

        // Send AJAX request
        const formData = new URLSearchParams();
        formData.append('username', username);
        formData.append('password', password);

        fetch(contextPath + '/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-Requested-With': 'XMLHttpRequest'
            },
            body: formData.toString()
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Redirect to appropriate dashboard
                    window.location.href = data.redirectUrl;
                } else {
                    // Show error message
                    showAlert(data.error, 'danger');
                    submitBtn.disabled = false;
                    submitBtn.textContent = i18n.loginBtn;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                showAlert(i18n.errorGeneric, 'danger');
                submitBtn.disabled = false;
                submitBtn.textContent = i18n.loginBtn;
            });
    });


    function showFieldError(fieldId, message) {
        const field = document.getElementById(fieldId);
        const errorDiv = document.getElementById(fieldId + 'Error');
        field.classList.add('is-invalid');
        errorDiv.textContent = message;
    }

    function clearErrors() {
        document.querySelectorAll('.is-invalid').forEach(el => el.classList.remove('is-invalid'));
        document.getElementById('alertContainer').innerHTML = '';
    }

    function showAlert(message, type) {
        const alertContainer = document.getElementById('alertContainer');
        alertContainer.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible fade show" role="alert">' +
            message +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            '</div>';
    }

    // Clear field error on input
    document.getElementById('username').addEventListener('input', function() {
        this.classList.remove('is-invalid');
    });

    document.getElementById('password').addEventListener('input', function() {
        this.classList.remove('is-invalid');
    });
</script>
</body>
</html>

