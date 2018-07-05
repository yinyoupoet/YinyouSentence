var LOGIN_ERROR = "用户名或密码错误，请重试";
var CONFIRM_PWD_ERROR = "两次密码不相同喔，请重新输入";
var USERNAME_DUPLICATE = "用户名已存在，请换一个吧";
var REQUEST_EMPTY = "请全部填写完成后再提交喔";
var NAME_TOO_LONG = "用户名过长，不得超过16个字符";
var PASSWORD_TOO_SHORT = "密码过短，至少6个字符";
var PASSWORD_TOO_LONG = "密码过长，最多16个字符";

// 登录是否成功的标志位
var okLogin = false;
// 注册是否成功的标志位
var okRegister = false;


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

        $("title").html("吟游小铺——注册");

    });
    $('.turn-login').on('click',function(){
        login.toggle(2000);
        register.toggle(2000);
        $("title").html("吟游小铺——登录");
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
    var userPwd = $('#login-pwd');
    loginBtn.on('click',function(){
        // 判断如果有空没填，就直接报错
        if(userName.val().trim() == "" || userPwd.val().trim() == ""){
            loginError.html(REQUEST_EMPTY);
            return false;
        }else{
            loginError.html("");
        }

        // 设置dwr 为同步方法
        dwr.engine.setAsync(false);
        dwrLogin.isInfoRight(userName.val(),userPwd.val(),callback_login);
        // 重置dwr为异步
        dwr.engine.setAsync(true);


        console.log("同步完:" + okLogin);
        // 能够登录
        if( okLogin == true ){
            return true;
        }else{
            loginError.html(LOGIN_ERROR);
            return false;
        }

    });
};

var callback_login = function(data){
    console.log("data: " + data);
    okLogin = data;
    console.log("okLogin:" + okLogin);
}

// 初始化注册验证
var checkRegister = function(){
    var registerBtn = $('.register-btn');
    var registerError = $('.register-error');
    var userName = $('#register-name');
    var userPwd = $('#register-pwd');
    var userConfirmPwd = $('#register-confirm-pwd');

    // 当用户名一失去焦点，就异步判断用户名是否重复
    // 并且判断用户名是否过长
    userName.blur(function () {
        // 防止因为异步出现的问题
        okRegister = false;
        if(userName.val().length > 16){
            registerError.html(NAME_TOO_LONG);
            return;
        }
        if(userName.val() != "") {
            dwrRegister.isNameDuplicate(userName.val(), callback_register);
        }
    });

    // 当用户名输入框被修改，随时异步进行检查
    userName.on('input',function (e) {
        okRegister = false;
        if(userName.val() != "") {
            dwrRegister.isNameDuplicate(userName.val(), callback_register);
        }
    });

    // 当用户密码框失去焦点时，判断密码长度是否合法
    userPwd.blur(function (){
        if(userPwd.val() == ""){
            return;
        }
        if(userPwd.val().length < 6){
            registerError.html(PASSWORD_TOO_SHORT);
            okRegister = false;
        }else if(userPwd.val().length > 16) {
            registerError.html(PASSWORD_TOO_LONG);
            okRegister = false;
        }else{
            if(userConfirmPwd.val() != userPwd.val()){
                registerError.html(CONFIRM_PWD_ERROR);
                okRegister = false;
            }else {
                registerError.html("");
            }
        }
    });

    // 当用户确认密码框失去焦点时，就判断是否和密码相同
    userConfirmPwd.blur(function () {
        if(userConfirmPwd.val() == ""){
            return;
        }
        if(userConfirmPwd.val() != userPwd.val()){
            registerError.html(CONFIRM_PWD_ERROR);
            okRegister = false;
        }else{
            registerError.html("");
            userPwd.blur();
        }
    });


    registerBtn.on('click',function(){
        // 判断如果有空没填，就直接报错
        if(userName.val().trim() == "" || userPwd.val().trim() == "" || userConfirmPwd.val().trim() == ""){
            registerError.html(REQUEST_EMPTY);
            return false;
        }else{
            // 判断两次密码是否相等，不相等直接报错
            if(userPwd.val().trim() != userConfirmPwd.val().trim()){
                registerError.html(CONFIRM_PWD_ERROR);
                return false;
            }else{
                // 如果用户名和密码长度不正确，返回false
                if(userName.val().length > 16){
                    registerError.html(NAME_TOO_LONG);
                    return false;
                }else if(userPwd.val().length < 6){
                    registerError.html(PASSWORD_TOO_SHORT);
                    return false;
                }else if(userPwd.val().length > 16) {
                    registerError.html(PASSWORD_TOO_LONG);
                    return false;
                }
                else {
                    registerError.html("");
                    // 如果已经判断用户名不重复，就可以提交了
                    if (okRegister == true) {
                        return true;
                    } else {
                        // 这里不确定用户名是否重复（因为判断是异步的，因此再判断一次，这次采用同步的方式）
                        // 设置dwr 为同步方法
                        dwr.engine.setAsync(false);
                        dwrRegister.isNameDuplicate(userName.val(), callback_register);
                        // 重置dwr为异步
                        dwr.engine.setAsync(true);

                        // 这时再判断一次
                        if (okRegister == true) {
                            return true;
                        }
                        registerError.html(USERNAME_DUPLICATE);
                        return false;
                    }
                }
            }
        }

        return false;
    });
}

var callback_register = function(data){
    console.log("data: " + data);
    okRegister = data;
    console.log("okRegister:" + okRegister);

    if(data == false){
        $('.register-error').html(USERNAME_DUPLICATE);
    }else{
        $('.register-error').html("");
    }
}
