function changeLanguage(lang) {
    fetch(contextPath + '/api/i18n?lang=' + lang)
        .then(res => res.json())
        .then(data => {
            document.querySelectorAll('[data-i18n]').forEach(el => {
                const key = el.dataset.i18n;
                if (data[key]) {
                    el.textContent = data[key];
                }
            });
        })
        .catch(err => console.error("i18n error", err));
}