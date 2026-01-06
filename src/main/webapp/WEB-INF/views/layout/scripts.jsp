<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05-Jan-26
  Time: 11:35 PM
  To change this template use File | Settings | File Templates.
--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function toggleSidebar() {
        document.querySelector('.app').classList.toggle('collapsed');
    }
    const contextPath = '${pageContext.request.contextPath}';
    const defaultLang = '${sessionScope.lang != null ? sessionScope.lang : "vi"}';
    const defaultTheme = '${sessionScope.theme != null ? sessionScope.theme : "light"}';

    // ========== I18N Functions ==========
    async function loadI18n(lang) {
        const res = await fetch(contextPath + '/api/i18n?lang=' + lang);
        return res.json();
    }

    function applyI18n(data) {
        document.querySelectorAll('[data-i18n]').forEach(el => {
            const key = el.dataset.i18n;
            if (data[key]) {
                el.textContent = data[key];
            }
        });
    }

    async function changeLanguage(lang) {
        try {
            // persist to session FIRST, then load translations
            await fetch(contextPath + '/lang', {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: 'lang=' + encodeURIComponent(lang)
            });
            const data = await loadI18n(lang);
            applyI18n(data);
        } catch (err) {
            console.error('i18n error', err);
        }
    }

    // ========== Theme Functions ==========
    function getPreferredTheme() {
        const storedTheme = localStorage.getItem('theme');
        if (storedTheme) {
            return storedTheme;
        }
        return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
    }

    function applyTheme(theme) {
        if (theme === 'auto') {
            const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
            document.documentElement.setAttribute('data-bs-theme', prefersDark ? 'dark' : 'light');
        } else {
            document.documentElement.setAttribute('data-bs-theme', theme);
        }
    }

    async function changeTheme(theme) {
        try {
            // persist to session
            await fetch(contextPath + '/theme', {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: 'theme=' + encodeURIComponent(theme)
            });
            // save to localStorage for quick access
            localStorage.setItem('theme', theme);
            // apply theme immediately
            applyTheme(theme);
            // update dropdown icon
            updateThemeIcon(theme);
        } catch (err) {
            console.error('theme error', err);
        }
    }

    function updateThemeIcon(theme) {
        const iconMap = {
            'light': 'bi-sun-fill',
            'dark': 'bi-moon-fill',
            'auto': 'bi-display'
        };
        const themeIcon = document.getElementById('themeIcon');
        if (themeIcon) {
            themeIcon.className = 'bi ' + (iconMap[theme] || 'bi-sun-fill');
        }
    }

    // Listen for system theme changes when "auto" is selected
    window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
        const currentTheme = localStorage.getItem('theme') || defaultTheme;
        if (currentTheme === 'auto') {
            applyTheme('auto');
        }
    });

    // ========== Initialize ==========
    document.addEventListener('DOMContentLoaded', function() {
        // Initialize theme
        const savedTheme = localStorage.getItem('theme') || defaultTheme;
        applyTheme(savedTheme);
        updateThemeIcon(savedTheme);

        // Initialize i18n
        loadI18n(defaultLang).then(applyI18n).catch(err => console.error('i18n init error', err));
    });
</script>
