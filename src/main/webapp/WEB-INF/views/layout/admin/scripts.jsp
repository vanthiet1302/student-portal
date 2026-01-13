<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function toggleSidebar() {
        document.querySelector('.app').classList.toggle('collapsed');
       const icon = document.getElementById('theMenuIcon');
           if (icon) {
               const btn = document.querySelector('.menu-toggle-btn');
               if (btn.classList.toggle('active')) {
                   icon.style.transform = 'rotate(90deg)';
               } else {
                   icon.style.transform = '';
               }
           }
    }

    const contextPath = '${pageContext.request.contextPath}';
    const defaultLang = '${sessionScope.lang != null ? sessionScope.lang : "vi"}';
    const defaultTheme = '${sessionScope.theme != null ? sessionScope.theme : "light"}';

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
            await fetch(contextPath + '/lang', {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: 'lang=' + encodeURIComponent(lang)
            });
            const data = await loadI18n(lang);
            applyI18n(data);
        } catch (e) {
            console.error('i18n error', e);
        }
    }

    function applyTheme(theme) {
        if (theme === 'auto') {
            const dark = window.matchMedia('(prefers-color-scheme: dark)').matches;
            document.documentElement.setAttribute('data-bs-theme', dark ? 'dark' : 'light');
        } else {
            document.documentElement.setAttribute('data-bs-theme', theme);
        }
    }

    async function changeTheme(theme) {
        try {
            await fetch(contextPath + '/theme', {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: 'theme=' + encodeURIComponent(theme)
            });
            localStorage.setItem('theme', theme);
            applyTheme(theme);
            updateThemeIcon(theme);
        } catch (e) {
            console.error('theme error', e);
        }
    }

    function updateThemeIcon(theme) {
        const map = {
            light: 'bi-sun-fill',
            dark: 'bi-moon-fill',
            auto: 'bi-display'
        };
        document.getElementById('themeIcon').className =
            'bi ' + (map[theme] || map.light);
    }

    window.matchMedia('(prefers-color-scheme: dark)')
        .addEventListener('change', () => {
            if (localStorage.getItem('theme') === 'auto') {
                applyTheme('auto');
            }
        });

    document.addEventListener('DOMContentLoaded', () => {
        const theme = localStorage.getItem('theme') || defaultTheme;
        applyTheme(theme);
        updateThemeIcon(theme);
        loadI18n(defaultLang).then(applyI18n);
    });
</script>