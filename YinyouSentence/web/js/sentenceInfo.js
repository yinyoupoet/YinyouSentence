$(document).ready(function(){
	// 初始化点击喜欢按钮
	initLove();

	// 设置锚点滚动速度
	initMaoDianScroll();

	// 初始化发布评论点击按钮
	initPublishCommentOrReply();

	// 初始化收藏按钮
	initCollect();

});

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
        $('.collect-collection-div').show();
	});
};

// 初始化发布评论点击按钮
var initPublishCommentOrReply = function(){
	$('.comment-input-form').on('submit',function () {
        if($('#comment-content').val().trim() == ""){
            alert("请输入内容后再提交呦");
            return false;
        }
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
		return true;
    });
};


var cancelComment = function(){
	console.log("消失吧");
	$('.comment-reply-head').html();
	// $('.comment-reply-head').css('display','none');
	$('.comment-reply-head').toggle(500);
	// $('.comment-reply-head').fadeOut(500);
	var sentenceId = $('.comment-input-form').attr('SID');
	$('.comment-input-form').attr('action','/comment.action?sentenceId='+sentenceId);
}



// 设置锚点滚动速度
var initMaoDianScroll = function(){
	// 前面是对评论的回复
	$('.cr').click(function(){
		$('html,body').animate({scrollTop:$($(this).attr("href")).offset().top-20+"px"},1000);
		// $('.comment-reply-head').css('display','block');
		var name = $(this).attr('name');
		if(name == undefined){
			return false;
		}
		var replyName = $('#comment-author-'+name).html();
		var replyContent = $('#comment-content-'+name).html();
		$('.comment-reply-head').html('回复： @'+ replyName + ' "' + replyContent + '"' + '<span class="comment-cancel index-a" id="comment-cancel" onclick="cancelComment();"><i class="far fa-times-circle"></i>取消回复</span>');
		$('.comment-reply-head').css('display','block');
		$('.comment-input-form').attr('action','/reply.action?type=0&commentId=' + name);
		return true;
	});

	// 下面是对回复的回复
	$('.rr').click(function () {
        $('html,body').animate({scrollTop:$($(this).attr("href")).offset().top-20+"px"},1000);
        var name = $(this).attr('name');
        if(name == undefined){
            return false;
        }
        var replyName = $('#comment-reply-writer-'+name).html();
        var replyContent = $('#comment-reply-content-'+name).html();
        $('.comment-reply-head').html('回复： @'+ replyName + ' "' + replyContent + '"' + '<span class="comment-cancel index-a" id="comment-cancel" onclick="cancelComment();"><i class="far fa-times-circle"></i>取消回复</span>');
        $('.comment-reply-head').css('display','block');
        $('.comment-input-form').attr('action','/reply.action?type=1&rpObjId=' + name);
        return true;
    });
};


// 初始化点击喜欢按钮
var initLove = function(){
	$('.sentence-love').on('click',function(){
		var img = $('#love-img');
		var state = img.attr('state');
		var SID = $(this).attr('SID');
        dwrSentenceInfo.loveSentence(SID,onLoveCallBack);
	});
};

// 点击喜欢句子的回调
var onLoveCallBack = function (data) {
	if(! data.success){
		// 如果失败，那么弹出错误信息
		alert(data.reason);
		return;
	}
	// 如果成功喜欢或取消
	if(data.follow){
		// 喜欢
        var img = $('#love-img');
        img.attr('src','/imgs/sys/love-2.png');
	}else{
        var img = $('#love-img');
        img.attr('src','/imgs/sys/love.png');
	}
	$('#sentence-love-num').html(data.loveNum);
};