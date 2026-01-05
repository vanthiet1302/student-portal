<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jan-26
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<nav id="sidebar" class="sidebar bg-dark">
    <ul class="nav flex-column pt-3">
        <li class="nav-item text-center mb-3">
            <button id="sidebarToggle" class="btn btn-sm btn-outline-light">
                <i class="bi bi-list"></i>
            </button>
        </li>

        <li class="nav-item">
            <a class="nav-link text-white ${active == 'dashboard' ? 'active' : ''}" href="/dashboard">
                <i class="bi bi-speedometer2"></i>
                <span class="label">Dashboard</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link text-white ${active == 'users' ? 'active' : ''}" href="/users">
                <i class="bi bi-people"></i>
                <span class="label">Users</span>
            </a>
        </li>
    </ul>
</nav>

