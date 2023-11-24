window.addEventListener("scroll", function () {
  const header = document.getElementById("header");
  if (window.scrollY > 50) {
    header.style.background = "#447C57";
  } else {
    header.style.background = "transparent";
  }
});

const swiper = new Swiper(".swiper", {
  loop: true,
  slidesPerView: 2, 
  centeredSlides: true,
  spaceBetween: 20,
  scrollbar: {
    el: '.swiper-scrollbar',
  },
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
});
