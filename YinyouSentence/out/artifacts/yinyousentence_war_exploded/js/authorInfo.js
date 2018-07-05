$(document).ready(function(){
	// 初始化作者喜欢样式
	initLoveAuthor();

	// 初始化作品喜欢样式
	initLoveBooks();
});

// 初始化作品喜欢样式
var initLoveBooks = function(){
	$('.book-love').on('click',function(){
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
};


// 初始化作者喜欢样式
var initLoveAuthor = function(){
	$('.author-love').on('click',function(){
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