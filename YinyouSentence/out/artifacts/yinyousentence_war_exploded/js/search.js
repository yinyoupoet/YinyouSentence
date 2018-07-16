$(document).ready(function(){
	// 初始化句子集喜欢事件
	initCollectionLove();

	// 收藏到句子集点击事件
	initCollectToCollection();

	// 初始化句子喜欢按钮点击事件
  	initRecommendLove();

});

// 收藏到句子集点击事件
var initCollectToCollection = function(){
    $('.recommend-collect').on('click',function(){
        // 先判断登录
        var isLogin = false;
        dwr.engine.setAsync(false);
        dwrLoginCheck.isLoginYet(function(data){
            isLogin = data;
        });
        dwr.engine.setAsync(true);
        if(isLogin == false){
            alert("请先登录呦");
            return false;
        }
        $('#collect-collection-title').attr('SID',$(this).attr('SID'));
        initCollectCollectionList($(this).attr('SID'));
        $('.collect-collection-div').show();
    });
};





// 初始化句子喜欢按钮点击事件
var initRecommendLove = function(){
    $('.recommend-love-span').on('click',function(){
        var SID = $(this).attr('SID');
        var span = $(this);
        dwrSentenceInfo.loveSentence(SID,function(data){
            if(! data.success){
                // 如果失败，那么弹出错误信息
                alert(data.reason);
                return;
            }
            // 如果成功喜欢或取消
            if(data.follow){
                // 喜欢
                span.children('span.recommend-love').html('<i class="fas fa-heart"></i>');
            }else{
                span.children('span.recommend-love').html('<i class="far fa-heart"></i>');
            }
            span.children('span.recommend-love-num-span').children('span.recommend-love-num').html(data.loveNum);
        });

    });
};


// 初始化句子集喜欢事件
var initCollectionLove = function(){
	$('.sentence-collect-love').click(function(){
        var span = $(this);
        var collectionId = span.attr('CID');
        dwrSentenceInfo.loveCollection(collectionId,function(data){
            if(!data.success){
                alert(data.reason);
                return;
            }
            if(data.follow){
                span.html('<img src="/imgs/sys/love-2.png" title="喜欢" class="love-img" state="true" cId="' + collectionId + '">' + ' 喜欢(' + data.loveNum + ')</span>');
            }else{
                span.html('<img src="/imgs/sys/love.png" title="喜欢" class="love-img" state="true" cId="' + collectionId + '">' + ' 喜欢(' + data.loveNum + ')</span>');
            }
        });
	});








};