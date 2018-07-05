var oldName;
var okChangeName = false;

$(document).ready(function(){
	// 初始化更换图片按钮
	initChangeHeadImg();

	// 用户名修改校验
	oldName = $('#userName').val();
	checkUserName();

	// 提交按钮点击事件
	initSubmitBtn();
});

// 提交按钮点击事件
var initSubmitBtn = function () {
	$('#submit-btn').on('click',function(){
		// 检验用户名是否重复
        var newName = $('#userName').val();
        if(newName.trim() != oldName.trim()){
            dwr.engine.setAsync(false);
            dwrRegister.isNameDuplicate(newName, callback_changeName);
            // 重置dwr为异步
            dwr.engine.setAsync(true);

            console.log(okChangeName);
            if(okChangeName == false){
            	return false;
			}
		}

		//检验是否需要修改密码
		var pwd = $('#userPwd').val();
        if(pwd != ""){
        	if(pwd.length < 6 || pwd.length > 16){
        		$('#userPwd-tip').html("密码长度应在6-16位");
        		$('#userPwd-tip').addClass('tip-warn');
        		return false;
			}else{
                $('#userPwd-tip').html("确认修改密码");
                $('#userPwd-tip').removeClass('tip-warn');
			}
		}else{
            $('#userPwd-tip').html("如需修改密码，请填写此项");
            $('#userPwd-tip').removeClass('tip-warn');
		}

		return false;
	});
};

// 用户名就该校验
var checkUserName = function(){
    $('#userName').blur(function () {
		checkNameDuplicated();
    });
};

var checkNameDuplicated = function(){
    var newName = $('#userName').val();
    // 如果两个不相等才要修改
    if(newName.trim() != oldName.trim()){
        dwrRegister.isNameDuplicate(newName, callback_changeName);
    }
};

var callback_changeName = function (data) {
	okChangeName = data;
	console.log("okChangeName:" + okChangeName + "  data: " + data);
	if(data == false){
		$('#userName-tip').html("当前用户名已存在，请更换");
	}else{
        $('#userName-tip').html("");
	}
};

var initChangeHeadImg = function(){
	$('#my-img').click(function(){
		$('#img-upload').click();
	});
	$('#change-img-hover').click(function(){
		$('#img-upload').click();
	});
}