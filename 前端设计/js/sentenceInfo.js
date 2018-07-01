$(document).ready(function(){
	// 初始化点击喜欢按钮
	initLove();

	// 设置锚点滚动速度
	initMaoDianScroll();

});



// 设置锚点滚动速度
var initMaoDianScroll = function(){
	$('.cm').click(function(){
		$('html,body').animate({scrollTop:$($(this).attr("href")).offset().top-20+"px"},1000);
		// $('.comment-reply-head').css('display','block');
		var name = $(this).attr('name');
		if(name == undefined){
			return false;
		}
		var replyName = $('#comment-author-'+name).html();
		var replyContent = $('#comment-content-'+name).html();
		$('.comment-reply-head').html('回复： @'+ replyName + ' "' + replyContent + '"');
		$('.comment-reply-head').css('display','block');
		$('.comment-input-form').attr('action','comment.action?replyId=' + name);
		return false;
	});
};


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