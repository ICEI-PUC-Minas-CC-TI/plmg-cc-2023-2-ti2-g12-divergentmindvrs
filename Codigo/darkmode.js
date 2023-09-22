const themeIcon = document.getElementById('themeIcon');
const body = document.body;
const navbar = document.querySelector('.navbar');
const cards = document.querySelectorAll('.cartao_nd');
let isDarkMode = localStorage.getItem('isDarkMode') === 'true';

function setThemeMode() {
    if (isDarkMode) {
        themeIcon.classList.remove('fa-sun');
        themeIcon.classList.add('fa-moon');
        body.classList.add('dark-mode');
        body.classList.remove('light-mode');
        navbar.classList.remove('navbar-light-mode');
        navbar.classList.remove('navbar-light');
        navbar.classList.add('navbar-dark-mode');
        navbar.classList.add('navbar-dark');

        cards.forEach(card => {
            card.classList.add('dark-card');
            card.classList.remove('light-card');
        });
    } else {
        themeIcon.classList.remove('fa-moon');
        themeIcon.classList.add('fa-sun');
        body.classList.remove('dark-mode');
        body.classList.add('light-mode');
        navbar.classList.remove('navbar-dark-mode');
        navbar.classList.remove('navbar-dark');
        navbar.classList.add('navbar-light-mode');
        navbar.classList.add('navbar-light');

        cards.forEach(card => {
            card.classList.add('light-card');
            card.classList.remove('dark-card');
        });
    }
}

setThemeMode();

themeIcon.addEventListener('click', () => {
    isDarkMode = !isDarkMode;
    localStorage.setItem('isDarkMode', isDarkMode);

    setThemeMode();
});
