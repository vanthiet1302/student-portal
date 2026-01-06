function setTheme(mode) {
    localStorage.setItem('theme', mode);
    applyTheme();
}

function applyTheme() {
    const mode = localStorage.getItem('theme') || 'auto';

    if (mode === 'auto') {
        const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
        document.documentElement.setAttribute(
            'data-bs-theme',
            prefersDark ? 'dark' : 'light'
        );
    } else {
        document.documentElement.setAttribute('data-bs-theme', mode);
    }
}

applyTheme();
