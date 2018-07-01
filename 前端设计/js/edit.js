$(document).ready(function(){
	// 初始化更换图片按钮
	initChangeHeadImg();


});

var initChangeHeadImg = function(){
	$('#my-img').click(function(){
		$('#img-upload').click();
	});
	$('#change-img-hover').click(function(){
		$('#img-upload').click();
	});
}