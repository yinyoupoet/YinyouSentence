$(document).ready(function(){
	// 初始化句子集喜欢事件
	initCollectionLove();

	// 初始化句子喜欢按钮点击事件
  	initRecommendLove();

  	// 初始化菜单点击事件
  	initMenuClickAction();

  	// 初始化显示全部名家
  	initShowAllGiant();

  	// 初始化显示全部出处
  	initShowAllBooks();

});

// 初始化显示全部出处
var initShowAllBooks = function(){
	$('#all-books').on('click',function(){
		var more = $('#more-books');
		if($(this).attr('state') == 'false'){
			$(this).attr('state','true');
			more.animate({height: 'toggle', opacity: 'toggle'}, 500);
			$(this).html('收起全部&nbsp;&nbsp;<i class="fas fa-arrow-up"></i>');
			// more.removeClass('hide');
		}else{
			$(this).attr('state','false');
			more.animate({height: 'toggle', opacity: 'toggle'}, 500);
			$(this).html('展开全部&nbsp;&nbsp;<i class="fas fa-arrow-down"></i>');
			// more.addClass('hide');
		}
	});
};

// 初始化显示全部名家
var initShowAllGiant = function(){
	$('#all-giant').on('click',function(){
		var more = $('#more-giant');
		if($(this).attr('state') == 'false'){
			$(this).attr('state','true');
			more.animate({height: 'toggle', opacity: 'toggle'}, 500);
			$(this).html('收起全部&nbsp;&nbsp;<i class="fas fa-arrow-up"></i>');
			// more.removeClass('hide');
		}else{
			$(this).attr('state','false');
			more.animate({height: 'toggle', opacity: 'toggle'}, 500);
			$(this).html('展开全部&nbsp;&nbsp;<i class="fas fa-arrow-down"></i>');
			// more.addClass('hide');
		}
	});
};


// 初始化菜单点击事件
var initMenuClickAction = function(){
	var sentenceLove = $('#menu-love-sentence');
	var sentencePublish = $('#menu-publish-sentence');
	var diy = $('#menu-diy');
	var collect = $('#menu-collect');

	var items = new Array(sentenceLove,sentencePublish,diy,collect);
	for(var i = 0; i < items.length; i++){
		var item = items[i];
		item.on('click',function(){
			clearActiveStyle(sentenceLove,sentencePublish,diy,collect);
			$(this).addClass("is-active");
		});
	}

	


};

// 清除菜单的isActive样式
var clearActiveStyle = function(){
	var length = arguments.length;
	for(var i = 0; i < length; i++){
		var item = arguments[i];
		item.removeClass("is-active");
	}
}


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