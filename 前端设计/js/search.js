$(document).ready(function(){
	// 初始化句子集喜欢事件
	initCollectionLove();

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


// 初始化句子集喜欢事件
var initCollectionLove = function(){
	$('.sentence-collect-love').click(function(){
		var img = $(this).children('img.love-img');
		var state = img.attr('state');
		if(state == "false"){
			img.attr('state',"true");
			img.attr('src','./imgs/love-2.png');
		}else if(state == 'true'){
			img.attr('state','false');
			img.attr('src','./imgs/love.png');
		}
	});
}