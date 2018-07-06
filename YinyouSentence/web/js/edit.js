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

	// 修改密码blur事件
	initChangePwdBlur();

	// 修改头像点击事件
	changeHeadImg();
});

// 修改头像点击事件
var changeHeadImg = function(){
	$('#img-upload').change(function(){
		// 1、验证图片格式
		var fileTypes = [".jpg",".png",".gif","jpeg"];
		var file = $('#img-upload').get(0).files[0];
		var filePath = $('#img-upload').get(0).value;
		// console.log(filePath);
		if(filePath){
            // 图片类型是否合法
            var isValid = false
			var fileEnd = filePath.substring(filePath.indexOf("."));
            for(var i =0; i < fileTypes.length; i++){
                if(fileTypes[i] == fileEnd){
                	isValid = true;
                	break;
				}
            }
            if(!isValid){
            	alert("请上传图片！");
                $('#img-upload').value = "";
                return false;
			}

			// 2、到这里，图片类型已合法，不进行大小验证，显示图片
			var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
				// console.log(e);
				$('#my-img').get(0).src = e.target.result;
            }

            // 3、图片已经显示成功，开始异步上传图片并返回图片相对路径
			var img = DWRUtil.getValue("img-upload");
            alert(img);
            //var img = $('#img-upload').get(0).files[0];
            var imgName = $('#img-upload').get(0).value;
            console.log("img: " + img + "  imgName: " + imgName);

			/*dwrEditInfo.uploadHeadImg(img,imgName,function(data){
            	  console.log("data: " + data);
			});*/

		}else{
			return false;
		}

	});
};

// 修改密码blur事件
var initChangePwdBlur = function () {
    $('#userPwd').blur(function(){
        var pwd = $('#userPwd').val();
        if(pwd != ""){
            if(pwd.length < 6 || pwd.length > 16){
                $('#userPwd-tip').html("密码长度应在6-16位");
                $('#userPwd-tip').addClass('tip-warn');
            }else{
                $('#userPwd-tip').html("确认修改密码");
                $('#userPwd-tip').removeClass('tip-warn');
            }
        }else{
            $('#userPwd-tip').html("如需修改密码，请填写此项");
            $('#userPwd-tip').removeClass('tip-warn');
        }
	});
};

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

            console.log("false: " + okChangeName);
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

		//console.log("return true");
		return true;
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
    }else{
        $('#userName-tip').html("");
        okChangeName = true;
	}
};

var callback_changeName = function (data) {
	okChangeName = data;
	//console.log("okChangeName:" + okChangeName + "  data: " + data);
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