$(document).ready(function(){

    // 初始化 喜欢按钮点击事件
    initRecommendLove();

    // 初始化收藏
    initCollect();

    // 初始化关注按钮点击事件
    initFollowUser();

    // 初始化图片高度与宽度一致
    initImgWidth();
});


// 初始化图片高度与宽度一致
var initImgWidth = function(){
    $('.search-user-item-img').height($('.search-user-item-img').width());
    console.log("height:" + $('.search-user-item-img').height());
    console.log("width:" + $('.search-user-item-img').width());
};


// 初始化关注按钮点击事件
var initFollowUser = function(){
    $('.btn-follow').on('click',function () {
        var btn = $(this);
        var UID = btn.attr('UID');
        dwrPeople.followUser(UID,function(data){
            if(!data.success){
                alert(data.reason);
                return false;
            }
            if(data.follow){
                btn.html("已关注");
            }else{
                btn.html('<i class="fas fa-plus"></i> 关注');
            }
            /* $('#hasFanNum').html(data.followerNum);*/

        });
    });
};





// 初始化收藏
var initCollect = function(){
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








// 初始化喜欢按钮点击事件
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