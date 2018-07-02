 $("#login-button").click(function(event){
		 event.preventDefault();
	 
	 //$('form').fadeOut(500);
	 //$('.wrapper').addClass('form-success');
	 var userName = $("#userName").val();
	 var userPwd = $("#userPwd").val();
	 console.log(userName+userPwd);

	 if(userName == "18874717941"){
	 	if(userPwd == "12345670"){
	 		//跳转
	 		self.location='./index.html'; 
	 	}else{
	 		alert("用户名或密码错误");
	 	}
	 }else{
	 	alert("用户名或密码错误");
	 }

});