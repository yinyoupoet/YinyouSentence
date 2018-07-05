// 这个文件记录的是，当点击收藏时，会显示现有的收藏夹，和新建收藏夹等等

var doCollect = function(){
	layx.confirm
};


$(document).ready(function(){
	$('.collect-new').on('click',function(){
		$('.collect-new-div').html('<input type="text" name="newCollectItem" class="new-collect-input"><button class="btn btn-info new-collect-btn"> 新增</button>');
	});

	$('.new-collect-btn').on('click',function(){
		$('.collect-new-div').html('<span class="collect-new">+新建句子集</span>');
	});

	$('.collect-btn').on('click',function(){
		
	});


});