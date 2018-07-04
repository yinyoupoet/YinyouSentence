var LOGIN_ERROR = "用户名或密码错误，请重试";
var CONFIRM_PWD_ERROR = "两次密码不相同喔，请重新输入";
var USERNAME_DUPLICATE = "用户名已存在，请换一个吧";
var REQUEST_EMPTY = "请全部填写完成后再提交喔";

$(document).ready(function(){

	// 初始化登录和注册页面之间的跳转
	initTransformOfLoginAndRegister();

	// 初始化登录验证
	checkLogin();

	// 初始化注册验证
	checkRegister();


});

// 初始化登录和注册页面之间的跳转
var initTransformOfLoginAndRegister = function(){
	var login = $('.login');
	var register = $('.register');
	// 当点击了跳转到注册页面
	$('.turn-register').on('click',function(){
		login.toggle(2000);
		register.toggle(2000);
		/*register.animateCss('flipInX');*/

		$("title").html("吟游佳句——注册");

	});
	$('.turn-login').on('click',function(){
		login.toggle(2000);
		register.toggle(2000);
		$("title").html("吟游佳句——登录");
	});
}

//	这是animate.css里面的，可以用于添加该动画
$.fn.extend({
  animateCss: function(animationName, callback) {
    var animationEnd = (function(el) {
      var animations = {
        animation: 'animationend',
        OAnimation: 'oAnimationEnd',
        MozAnimation: 'mozAnimationEnd',
        WebkitAnimation: 'webkitAnimationEnd',
      };

      for (var t in animations) {
        if (el.style[t] !== undefined) {
          return animations[t];
        }
      }
    })(document.createElement('div'));

    this.addClass('animated ' + animationName).one(animationEnd, function() {
      $(this).removeClass('animated ' + animationName);

      if (typeof callback === 'function') callback();
    });

    return this;
  },
});

// 初始化登录验证
var checkLogin = function(){
	var loginBtn = $('.login-btn');
	var loginError = $('.login-error');
	var userName = $('#login-name');
	var userPwd = $('#login-pwd')
	loginBtn.on('click',function(){
		// 判断如果有空没填，就直接报错
		if(userName.val().trim() == "" || userPwd.val().trim() == ""){
			loginError.html(REQUEST_EMPTY);
			return false;
		}else{
			loginError.html("");
		}
		return false;
	});
};

// 初始化注册验证
var checkRegister = function(){
	var registerBtn = $('.register-btn');
	var registerError = $('.register-error');
	var userName = $('#register-name');
	var userPwd = $('#register-pwd');
	var userConfirmPwd = $('#register-confirm-pwd');

	registerBtn.on('click',function(){
		// 判断如果有空没填，就直接报错
		if(userName.val().trim() == "" || userPwd.val().trim() == "" || userConfirmPwd.val().trim() == ""){
			registerError.html(REQUEST_EMPTY);
			return false;
		}else{
			// 判断两次密码是否相等，不相等直接报错
			if(userPwd.val().trim() != userConfirmPwd.val().trim()){
				registerError.html(CONFIRM_PWD_ERROR);
			}else{
				registerError.html("");
			}
		}


		return false;
	});
}
