$(document).ready(function() {
	var mySwiper = new Swiper('.swiper-container', {
	    autoplay: 0,
	    loop: !0,
	    speed: 1e3,
	    grabCursor: !0,
	    pagination: ".swiper-pagination",
	    paginationClickable: !0,
	    prevButton: ".swiper-button-prev",
	    nextButton: ".swiper-button-next",
	    parallax: !0
	});

	$("#back-page").on('click',function(){
		console.log("back");
		window.open("./back.html");
	});

	$("#reget-page").on('click',function(){
		console.log("back");
		window.open("./recycle.html");
	});
	

});

