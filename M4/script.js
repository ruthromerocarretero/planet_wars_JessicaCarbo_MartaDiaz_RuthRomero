var swiper = new Swiper(".mySwiper",{
    effect: "coverflow",
    grabCursor: true,
    centeredSlides: true,
    slidesPerView:"auto",
    converflowEffect: {
        rotate:15,
        strech:0,
        depth:300,
        modifier:1,
        slideShadows: true,
    },
    loop:true,
})