<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <button class="btn btn-outline-light btn-sm"
            onclick="toggleSidebar()"><i class="bi bi-list"></i></button>

    <div class="ms-auto d-flex gap-2">
        <div class="dropdown">
            <button class="btn btn-sm btn-outline-light dropdown-toggle"
                    data-bs-toggle="dropdown" id="themeDropdown">
                <i class="bi bi-sun-fill" id="themeIcon"></i>
                <span data-i18n="theme.current" id="themeLabel"><fmt:message key="theme.current"/></span>
            </button>
            <ul class="dropdown-menu dropdown-menu-end">
                <li>
                    <button class="dropdown-item" type="button" onclick="changeTheme('light')">
                        <i class="bi bi-sun-fill me-2"></i><span data-i18n="theme.light"><fmt:message key="theme.light"/></span>
                    </button>
                </li>
                <li>
                    <button class="dropdown-item" type="button" onclick="changeTheme('dark')">
                        <i class="bi bi-moon-fill me-2"></i><span data-i18n="theme.dark"><fmt:message key="theme.dark"/></span>
                    </button>
                </li>
                <li>
                    <button class="dropdown-item" type="button" onclick="changeTheme('auto')">
                        <i class="bi bi-display me-2"></i><span data-i18n="theme.auto"><fmt:message key="theme.auto"/></span>
                    </button>
                </li>
            </ul>
        </div>

        <div class="dropdown">
            <button class="btn btn-sm btn-outline-light dropdown-toggle"
                    data-bs-toggle="dropdown" id="langDropdown">
                <i class="bi bi-translate me-1"></i>
                <span data-i18n="lang.current"><fmt:message key="lang.current"/></span>
            </button>
            <ul class="dropdown-menu dropdown-menu-end">
                <li>
                    <button class="dropdown-item" type="button" onclick="changeLanguage('vi')" data-i18n="lang.vi"><fmt:message key="lang.vi"/></button>
                </li>
                <li>
                    <button class="dropdown-item" type="button" onclick="changeLanguage('en')" data-i18n="lang.en"><fmt:message key="lang.en"/></button>
                </li>
            </ul>
        </div>
    </div>
</nav>
