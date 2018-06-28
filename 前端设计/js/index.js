$(document).ready(function(){
	// 初始化随机句子 的点击事件
	initRandomNext();

  // 初始化热门名人的图片高度
  initGiantImg();

  // 初始化 推荐句子 的菜单栏点击事件
  initRecommendBarAction();
});


// 初始化 推荐句子 的菜单栏点击事件
var initRecommendBarAction = function(){
  // 初始化喜欢按钮点击事件
  initRecommendLove();

  // 初始化收藏到句子集的点击事件
  initRecommendCollect();

}

var initRecommendCollect = function(){
  $('.recommend-collect').on('click',function(){
      layx.iframe('localsite','收藏到句子集','./collect.html',{

      });
  });
};


// 初始化喜欢按钮点击事件
var initRecommendLove = function(){
  $('.recommend-love-span').on('click',function(){
      if($(this).children('span.recommend-love').html() == '<i class="fas fa-heart"></i>'){
        $(this).children('span.recommend-love').html('<i class="far fa-heart"></i>');
      }else{
        $(this).children('span.recommend-love').html('<i class="fas fa-heart"></i>');
      }
      
  });
}


// 初始化热门名人的图片高度
var initGiantImg = function(){
  var width = $('.giant-img').css("width"); 
  $('.giant-img').css("height",width);
}


// 初始化随机句子 的点击事件
var initRandomNext = function(){
	$('.random-next').on('click',function(){
		$('#random-content').animateCss('flipInX');
		$('#random-from').animateCss('flipInX');
    $('#random-publisher-p').animateCss('flipInX');
		$('#random-content').html("墙里秋千墙外道。墙外行人，墙里佳人笑。笑渐不闻声渐悄。多情却被无情恼。花褪残红青杏小。燕子飞时，绿水人家绕。枝上柳绵吹又少。天涯何处无芳草。");
		$('#random-author').html('<a href="#">苏轼</a>');
		$('#random-orient').html('<a href="#">《蝶恋花·春景》</a>');
    $('#random-publisher').html('<a href="#">吟游词人</a>');
    $('#random-publish-time').html('2012年5月2日');
	});
}



//这是animate.css里面的，可以用于添加该动画
$.fn.extend({
  animateCss: function(animationName, callback) {
    var animationEnd = (function(el) {
      var animations = {
        animation: 'animationend',
        OAnimation: 'oAnimationEnd',
        MozAnimation: 'mozAnimationEnd',
        WebkitAnimation: 'webkitAnimationEnd',
      };

      for (var t in animations) {
        if (el.style[t] !== undefined) {
          return animations[t];
        }
      }
    })(document.createElement('div'));

    this.addClass('animated ' + animationName).one(animationEnd, function() {
      $(this).removeClass('animated ' + animationName);

      if (typeof callback === 'function') callback();
    });

    return this;
  },
});


/* 浏览器窗体大小变化事件监听 */
$(window).resize(function(){
  initGiantImg();
});