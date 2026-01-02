<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="i18n.messages"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <fmt:message key="page.forgot.password.title"/>
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
        .forgot-password-container {
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
<div class="forgot-password-container">
    <div class="card shadow">
        <div class="card-body p-4">
            <h1 class="text-center mb-4">
                <fmt:message key="page.forgot.password.title"/>
            </h1>

            <div id="alertContainer"></div>

            <form id="forgotPasswordForm" novalidate>
                <div class="mb-3">
                    <label for="email" class="form-label">
                        <fmt:message key="page.forgot.password.email"/>
                    </label>
                    <input type="email"
                           name="email"
                           class="form-control"
                           id="email"
                           placeholder="<fmt:message key='page.forgot.password.email.placeholder'/>">
                    <div class="invalid-feedback" id="emailError"></div>
                </div>
                <button type="submit"
                        class="btn btn-primary w-100 mb-3"
                        id="submitBtn">
                    <fmt:message key="page.forgot.password.submit"/>
                </button>
                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">
                        <fmt:message key="page.forgot.password.back.login"/>
                    </a>
                </div>
            </form>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/change-language?lang=vi&redirect=/forgot-password" class="me-2">🇻🇳 VI</a>
                <a href="${pageContext.request.contextPath}/change-language?lang=en&redirect=/forgot-password">🇺🇸 EN</a>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/bootstrap-5.3.8-dist/js/bootstrap.bundle.js"></script>
<script>
    const contextPath = '${pageContext.request.contextPath}';

    // i18n messages from server
    const i18n = {
        emailRequired: '<fmt:message key="page.forgot.password.error.email.required"/>',
        emailInvalid: '<fmt:message key="page.forgot.password.error.email.invalid"/>',
        sending: '<fmt:message key="page.forgot.password.sending"/>',
        submitBtn: '<fmt:message key="page.forgot.password.submit"/>',
        errorGeneric: '<fmt:message key="page.forgot.password.error.generic"/>'
    };

    document.getElementById('forgotPasswordForm').addEventListener('submit', function(e) {
        e.preventDefault();

        // Clear previous errors
        clearErrors();

        // Get form values
        const email = document.getElementById('email').value.trim();

        // Validate
        let isValid = true;

        if (!email) {
            showFieldError('email', i18n.emailRequired);
            isValid = false;
        } else if (!isValidEmail(email)) {
            showFieldError('email', i18n.emailInvalid);
            isValid = false;
        }

        if (!isValid) return;

        // Disable submit button
        const submitBtn = document.getElementById('submitBtn');
        submitBtn.disabled = true;
        submitBtn.textContent = i18n.sending;

        // Send AJAX request
        const formData = new URLSearchParams();
        formData.append('email', email);

        fetch(contextPath + '/forgot-password', {
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
                // Show success message
                showAlert(data.message, 'success');
                // Redirect to login after 3 seconds
                setTimeout(function() {
                    window.location.href = data.redirectUrl;
                }, 3000);
            } else {
                // Show error message
                showAlert(data.message, 'danger');
                submitBtn.disabled = false;
                submitBtn.textContent = i18n.submitBtn;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showAlert(i18n.errorGeneric, 'danger');
            submitBtn.disabled = false;
            submitBtn.textContent = i18n.submitBtn;
        });
    });

    function isValidEmail(email) {
        const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;
        return emailRegex.test(email);
    }

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
    document.getElementById('email').addEventListener('input', function() {
        this.classList.remove('is-invalid');
    });
</script>
</body>
</html>

