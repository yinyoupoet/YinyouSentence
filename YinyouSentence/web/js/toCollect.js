$(document).ready(function(){
	// 初始化收藏到句子集的点击事件
  	initRecommendCollect();
});

// 初始化收藏到句子集的点击事件
var initRecommendCollect = function(){
  $('.recommend-collect').on('click',function(){
      layx.iframe('localsite','收藏到句子集','./collect.html',{

      });
  });
};