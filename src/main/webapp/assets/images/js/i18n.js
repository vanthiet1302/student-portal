function changeLanguage(lang) {
    fetch(contextPath + '/api/i18n?lang=' + lang)
        .then(res => res.json())
        .then(data => {
            // Save language preference to session
            fetch(contextPath + '/lang', {
                method: 'POST',
                headers: {
                    'X-Requested-With': 'XMLHttpRequest',
                    'Accept': 'application/json'
                },
                body: new URLSearchParams({ 'lang': lang })
            }).catch(err => console.error("Lang save error", err));

            // Update all i18n elements
            document.querySelectorAll('[data-i18n]').forEach(el => {
                const key = el.dataset.i18n;
                if (data[key]) {
                    // Update text content for elements without input
                    if (!['INPUT', 'TEXTAREA', 'SELECT'].includes(el.tagName)) {
                        el.textContent = data[key];
                    }
                }
            });

            // Update placeholders
            document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
                const key = el.dataset.i18nPlaceholder;
                if (data[key]) {
                    el.placeholder = data[key];
                }
            });

            // Update titles
            document.querySelectorAll('[data-i18n-title]').forEach(el => {
                const key = el.dataset.i18nTitle;
                if (data[key]) {
                    el.title = data[key];
                }
            });

            // Update value for options and similar
            document.querySelectorAll('[data-i18n-value]').forEach(el => {
                const key = el.dataset.i18nValue;
                if (data[key]) {
                    el.value = data[key];
                }
            });

            // Update HTML content for elements
            document.querySelectorAll('[data-i18n-html]').forEach(el => {
                const key = el.dataset.i18nHtml;
                if (data[key]) {
                    el.innerHTML = data[key];
                }
            });
        })
        .catch(err => console.error("i18n error", err));
}

// Initialize i18n on page load
document.addEventListener('DOMContentLoaded', function() {
    if (typeof defaultLang !== 'undefined') {
        // Optional: Load and apply current language on page load
        // changeLanguage(defaultLang);
    }
});
