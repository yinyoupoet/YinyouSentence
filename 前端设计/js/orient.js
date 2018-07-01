$(document).ready(function(){
	// 初始化作品喜欢样式
	initLoveOrient();

	// 初始化句子喜欢按钮点击事件
  	initRecommendLove();
});

// 初始化句子喜欢按钮点击事件
var initRecommendLove = function(){
  $('.recommend-love-span').on('click',function(){
      if($(this).children('span.recommend-love').html() == '<i class="fas fa-heart"></i>'){
        $(this).children('span.recommend-love').html('<i class="far fa-heart"></i>');
      }else{
        $(this).children('span.recommend-love').html('<i class="fas fa-heart"></i>');
      }
      
  });
}

// 初始化作品喜欢样式
var initLoveOrient = function(){
	$('.orient-love').on('click',function(){
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