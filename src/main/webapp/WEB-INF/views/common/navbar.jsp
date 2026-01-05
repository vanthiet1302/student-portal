
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jan-26
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-dark bg-primary fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand fw-semibold" href="/">NLU Portal</a>

        <div class="d-flex align-items-center gap-3">

            <!-- Language switch -->
            <div class="dropdown">
                <button class="btn btn-sm btn-outline-light dropdown-toggle"
                        data-bs-toggle="dropdown">
                    <i class="bi bi-translate me-1"></i>
                    ${sessionScope.lang != null ? sessionScope.lang : "VI"}
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li>
                        <a class="dropdown-item" href="?lang=vi">🇻🇳 Tiếng Việt</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="?lang=en">🇬🇧 English</a>
                    </li>
                </ul>
            </div>

            <!-- Theme switch -->
            <div class="dropdown">
                <button class="btn btn-sm btn-outline-light dropdown-toggle"
                        data-bs-toggle="dropdown">
                    <i class="bi bi-circle-half"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li>
                        <button class="dropdown-item" onclick="setTheme('light')">
                            ☀ Light
                        </button>
                    </li>
                    <li>
                        <button class="dropdown-item" onclick="setTheme('dark')">
                            🌙 Dark
                        </button>
                    </li>
                    <li>
                        <button class="dropdown-item" onclick="setTheme('auto')">
                            ⚙ Auto
                        </button>
                    </li>
                </ul>
            </div>

            <!-- User -->
            <div class="dropdown">
                <a class="text-white dropdown-toggle text-decoration-none"
                   data-bs-toggle="dropdown">
                    <i class="bi bi-person-circle me-1"></i>
                    ${sessionScope.user.fullName}
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="/profile">Profile</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item text-danger" href="/logout">Logout</a></li>
                </ul>
            </div>

        </div>
    </div>
</nav>
