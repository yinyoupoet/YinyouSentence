$(document).ready(function(){
	// 初始化作者喜欢样式
	initLoveAuthor();

	// 初始化句子喜欢按钮点击事件
  	initRecommendLove();

  	// 初始化收藏到句子集事件
	initColectSentence();
});


// 初始化收藏到句子集事件
var initColectSentence = function(){
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









// 初始化作者喜欢样式
var initLoveAuthor = function(){
    $('.author-love').on('click',function(){
        var giantId = $(this).attr('GID');
        dwrGiant.loveGiant(giantId,function(data){
            console.log(data);
            if(!data.success){
                alert(data.reason);
                return;
            }
            var img = $('#love-img');
            if(data.follow){
                img.attr('src','/imgs/sys/love-2.png');
            }else{
                img.attr('src','/imgs/sys/love.png');
            }
            $('#love-giant-num').html(data.loveNum);
        });
    });
};