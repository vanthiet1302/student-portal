(() => {
    const storedTheme = localStorage.getItem('theme');

    const getPreferredTheme = () => {
        if (storedTheme) return storedTheme;
        return window.matchMedia('(prefers-color-scheme: dark)').matches
            ? 'dark'
            : 'light';
    };

    const setTheme = theme => {
        if (theme === 'auto') {
            document.documentElement.setAttribute(
                'data-bs-theme',
                window.matchMedia('(prefers-color-scheme: dark)').matches
                    ? 'dark'
                    : 'light'
            );
        } else {
            document.documentElement.setAttribute('data-bs-theme', theme);
        }
    };

    setTheme(getPreferredTheme());

    document.querySelectorAll('[data-theme]')
        .forEach(btn => {
            btn.addEventListener('click', () => {
                const theme = btn.getAttribute('data-theme');
                localStorage.setItem('theme', theme);
                setTheme(theme);
            });
        });
})();
