var now_to_top = 0;

// 监听屏幕滚动事件，判断是否需要显示 “返回顶部” 按钮
$(window).scroll(function () {
	// 判断是否需要显示 “返回顶部” 按钮
	checkForBackToTop();
});


$(document).ready(function(){
	// 点击头像出来个人详情
	initHeadInfo();	

	// 判断刚进来时是否需要显示 “返回顶部” 按钮
	checkForBackToTop();

	// 点击返回顶部按钮滚会顶部
	$('.back-to-top').click(function(){
		$("html,body").animate({scrollTop:0}, 500);
	});

	// // 登录注册
	// $('.sign-in').on('click',function(){
	// 	layx.iframe('localsite','登录注册','./login.html');
	// });

	
});


// 点击头像出来个人详情
var initHeadInfo = function(){
	$("#nav-head-img").click(function(){
		$(".nav-self-info").toggle();
	});
	// 初始化的时候也要判断
	userInfoLocation();

};

/* 点击用户头像后用户信息出现的位置，
 * 根据浏览器宽度不同因为导航栏显示不同因此需要分别判断
 */
var userInfoLocation = function(){
	var window_width = $(window).width();
	//console.log(window_width);
	if(window_width < 768){
		//console.log("小");
		$(".nav-self-info").css("float","left");
	}else{
		//console.log("大");
		$(".nav-self-info").css("float","right");
	}
};

/* 浏览器窗体大小变化事件监听 */
$(window).resize(function(){
	userInfoLocation();
});

/*判断是否需要显示 “返回顶部” 按钮*/
var checkForBackToTop = function(){
	var top = $(window).scrollTop();
	if(top > 100){
		$('.back-to-top').fadeIn(500);
	}else{
		$('.back-to-top').fadeOut(500);
	}
}

