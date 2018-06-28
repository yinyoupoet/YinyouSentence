$(document).ready(function(){
	// 初始化点击喜欢按钮
	initLove();
});


// 初始化点击喜欢按钮
var initLove = function(){
	$('.sentence-love').on('click',function(){
		var img = $('#love-img');
		var state = img.attr('state');
		if(state == "false"){
			img.attr('state',"true");
			img.attr('src','./imgs/love-2.png');
		}else if(state == 'true'){
			img.attr('state','false');
			img.attr('src','./imgs/love.png');
		}
	});
};