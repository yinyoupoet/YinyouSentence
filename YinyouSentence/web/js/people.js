$(document).ready(function(){
	// 初始化关注用户点击事件
	initFollowUser();

	// 初始化句子集喜欢事件
	// initCollectionLove();

	// 初始化句子喜欢按钮点击事件
  	// initRecommendLove();

  	// 初始化菜单点击事件
  	initMenuClickAction();

  	// 初始化显示全部名家
  	initShowAllGiant();

  	// 初始化显示全部出处
  	initShowAllBooks();

  	// 初始化显示句子
	initPageSentence();
});

// 采用缓存机制

var loveSentenceEntity;
var publishSentenceEntity;
var originalSentenceEntity;
var collectionEntity;
var loveCollectionEntity;

// 初始化显示句子
var initPageSentence = function(){
	var userId = $('#people-name').attr('UID');
    dwrSentenceInfo.getLoveSentenceEntities(userId,function(data){
    	//console.log(data);
		if(data == null){
			alert("加载句子失败，请刷新页面重试哦!");
			return;
		}
    	loveSentenceEntity = data;
    	showSentence(loveSentenceEntity);
	});
};


// 显示句子
var showSentence = function(sentenceEntity){
	var sentenceList = $('.sentence-list');
	sentenceList.html("");
    $('.collect-list').html("");
	for(var i=0; i < sentenceEntity.length; i++){
		var entity = sentenceEntity[i];
		var code = '<div class=\"recommend-item\">';
		code += '<p><a href="sentence.action?sentenceId=' + entity.sentence.id + '" class="recommend-a">';
		code += entity.sentence.content;
		code += '</a></p>'
		code += '<p class="recommend-from">';
		if(entity.sentence.isOriginal){
			// 如果是原创
			code += '—— 原创';
		}else{
			// 如果不是原创
			if(entity.giantInfo == null && entity.originInfo == null){
			}else{
				code += "——";
				if(entity.giantInfo != null){
					code += '<a href="giant.action?giantId=' + entity.giantInfo.id + '" class="recommend-author index-a">&nbsp;'+ entity.giantInfo.name +'</a>&nbsp;';
				}
				if(entity.originInfo != null){
					code += '&nbsp;<a href="origin.action?originId='+ entity.originInfo.id +'" class="recommend-orient index-a">'+ entity.originInfo.name +'</a>';
				}
			}
		}
		code += '</p>';
		code += '<div class="recommend-bar"><span class="recommend-love-span" id="sentenceLove'+entity.sentence.id+'" onclick="sentenceLoveClick('+ entity.sentence.id +');"><span class="recommend-love">';
		if(entity.peopleLove){
			// 如果已经喜欢
			code += '<i class="fas fa-heart"></i>';
		}else{
			code += '<i class="far fa-heart"></i>';
		}
		code += '</span>';
		code += '<span class="recommend-love-num-span">(<span class="recommend-love-num">'+ entity.sentence.loveNum +'</span>人喜欢)</span>';
		code += '</span>';
		code += '<span class="recommend-collect" onclick="addToCollection('+ entity.sentence.id +');"><i class="far fa-bookmark"></i> 收藏到句子集</span>';
		code += '<span class="recommend-comment"><a href="sentence.action?sentenceId='+ entity.sentence.id +'#comment" class="index-a"><i class="far fa-comment"></i> 评论(<span class="recommend-num">'+ entity.commentNum +'</span>)</a></span>';
		code += '<span class="recommend-publisher-span"><a href="toPeople.action?id='+ entity.sentence.publisherId +'" class="index-a"><i class="far fa-user"></i> <span class="recomend-publisher">'+ entity.userInfo.userName +'</span></a></span>';
		code += '</div></div>';
        sentenceList.append(code);
	}
};


// 显示句子集
var showCollect = function(collectEntity){
    var collectList = $('.collect-list');
    collectList.html("");
    $('.sentence-list').html("");
    for(var i = 0; i < collectEntity.length; i++){
    	var collection = collectEntity[i];
    	var code = '<div class="sentence-collect-item">';
    	code += '<a href="collectList.action?collectionId='+ collection.sentenceCollection.id +'">';
    	code += '<img src="'+ collection.sentenceCollection.imgPath +'" class="sentence-collect-pic"></a>';
    	code += '<div class="sentence-collect-right">';
    	code += '<a href="collectList.action?collectionId='+ collection.sentenceCollection.id +'" class="index-a sentence-collect-title"><i class="far fa-bookmark"></i>'+ collection.sentenceCollection.name +'</a><span class="sentence-collect-num">(包含'+ collection.sentenceCollection.sentenceNum +'条句子)</span>';
		code += '<span class="sentence-collect-love index-a" id="sentence-collect-'+ collection.sentenceCollection.id +'" onclick="loveCollection('+ collection.sentenceCollection.id +')">';
		if(collection.loveOrNot){
			code += '<img src="/imgs/sys/love-2.png" title="喜欢" class="love-img" state="true" cId="'+ collection.sentenceCollection.id +'">';
		}else{
			code += '<img src="/imgs/sys/love.png" title="喜欢" class="love-img" state="true" cId="'+ collection.sentenceCollection.id +'">';
		}
		code += '喜欢 (' + collection.sentenceCollection.loveNum + ')</span>';


		code += '<p class="sentence-collect-publish-info"><a class="index-a sentence-collect-publisher" href="toPeople.action?id=' + collection.sentenceCollection.publisherId + '">' + collection.publisherInfo.userName + '</a> 发布于 <span class="sentence-collect-publish-time">' + formatDate(collection.sentenceCollection.publishDate) + '</span></p>';
		code += '<p class="sentence-collect-putlish-intro">' + collection.sentenceCollection.introduction + '</p>';
		code += '</div></div>';
		collectList.append(code);
    }
};


// 格式化时间
var formatDate = function(date){
    var year = date.getFullYear(),
        month = date.getMonth()+1,//月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var newTime = year + '-' +
        (month < 10? '0' + month : month) + '-' +
        (day < 10? '0' + day : day) + ' ' +
        (hour < 10? '0' + hour : hour) + ':' +
        (min < 10? '0' + min : min) + ':' +
        (sec < 10? '0' + sec : sec);

    return newTime;
};



// 初始化关注用户点击事件
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
			$('#hasFanNum').html(data.followerNum);

		});
    });

};


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
	var loveCollect = $('#love-menu-collect');


	// 喜欢的句子的点击事件
    sentenceLove.on('click',function () {
    	$('.collect-list').html("");
        clearActiveStyle(sentenceLove,sentencePublish,diy,collect,loveCollect);
        $(this).addClass("is-active");
        if(loveSentenceEntity != null){
            showSentence(loveSentenceEntity);
		}else{
            var sentenceList = $('.sentence-list');
            sentenceList.html("");
            var userId = $('#people-name').attr('UID');
            dwrSentenceInfo.getLoveSentenceEntities(userId,function(data){
                //console.log(data);
                if(data == null){
                    alert("加载句子失败，请刷新页面重试哦!");
                    return;
                }
                loveSentenceEntity = data;
                showSentence(loveSentenceEntity);
            });
		}
    });


    // 发布的句子的点击事件
    sentencePublish.on('click',function () {
        $('.collect-list').html("");
        clearActiveStyle(sentenceLove,sentencePublish,diy,collect,loveCollect);
        $(this).addClass("is-active");
        if(publishSentenceEntity != null){
            showSentence(publishSentenceEntity);
        }else{
            var sentenceList = $('.sentence-list');
            sentenceList.html("");
            var userId = $('#people-name').attr('UID');
            dwrSentenceInfo.getPubishSentenceEntities(userId,function(data){
                //console.log(data);
                if(data == null){
                    alert("加载句子失败，请刷新页面重试哦!");
                    return;
                }
                publishSentenceEntity = data;
                showSentence(publishSentenceEntity);
            });
        }
    });


    // 原创句子点击事件
    diy.on('click',function(){
        $('.collect-list').html("");
        clearActiveStyle(sentenceLove,sentencePublish,diy,collect,loveCollect);
        $(this).addClass("is-active");
        if(originalSentenceEntity != null){
            showSentence(originalSentenceEntity);
        }else{
            var sentenceList = $('.sentence-list');
            sentenceList.html("");
            var userId = $('#people-name').attr('UID');
            dwrSentenceInfo.getOriginalSentenceEntites(userId,function(data){
                //console.log(data);
                if(data == null){
                    alert("加载句子失败，请刷新页面重试哦!");
                    return;
                }
                originalSentenceEntity = data;
                showSentence(originalSentenceEntity);
            });
        }
    });



    // 句子集点击事件
	collect.on('click',function(){
        $('.sentence-list').html("");
        clearActiveStyle(sentenceLove,sentencePublish,diy,collect,loveCollect);
        $(this).addClass("is-active");
        if(collectionEntity != null){
            showCollect(collectionEntity);
        }else{
            var collectionList = $('.collect-list');
            collectionList.html("");
            var userId = $('#people-name').attr('UID');
            dwrSentenceInfo.getPublishCollectionEntities(userId,function(data){
                //console.log(data);
                if(data == null){
                    alert("加载句子集失败，请刷新页面重试哦!");
                    return;
                }
                collectionEntity = data;
                showCollect(collectionEntity);
            });
        }
	});


	// 喜欢的句子集点击事件
    loveCollect.on('click',function(){
        $('.sentence-list').html("");
        clearActiveStyle(sentenceLove,sentencePublish,diy,collect,loveCollect);
        $(this).addClass("is-active");
        if(loveCollectionEntity != null){
            showCollect(loveCollectionEntity);
        }else{
            var collectionList = $('.collect-list');
            collectionList.html("");
            var userId = $('#people-name').attr('UID');
            dwrSentenceInfo.getLoveCollectionEntities(userId,function(data){
                //console.log(data);
                if(data == null){
                    alert("加载句子集失败，请刷新页面重试哦!");
                    return;
                }
                loveCollectionEntity = data;
                showCollect(loveCollectionEntity);
            });
        }
	});



};

// 清除菜单的isActive样式
var clearActiveStyle = function(){
	var length = arguments.length;
	for(var i = 0; i < length; i++){
		var item = arguments[i];
		item.removeClass("is-active");
	}
};


// 句子喜欢按钮点击事件
var sentenceLoveClick = function(SID){
	console.log("SID: " + SID);
    var span = $('#sentenceLove'+SID);
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
};


// 初始化收藏到句子集
var addToCollection = function(SID){
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
    $('#collect-collection-title').attr('SID',SID);
    initCollectCollectionList(SID);
    $('.collect-collection-div').show();
};



// 句子集喜欢事件
var loveCollection = function(collectionId){
	var span = $('#sentence-collect-'+collectionId);
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
};




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