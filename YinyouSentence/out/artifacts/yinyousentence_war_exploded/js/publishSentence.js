$(document).ready(function(){
	// 初始化点击选中原创后触发的事件
	initOriginalSelectAction();

	// 初始化标签的个数判断（正则）
	initTagNumCheck();

	// 初始化热门标签选择事件
	initHotTagSelect();
});

// 初始化热门标签选择事件
var initHotTagSelect = function(){
	$('.hot-tag').click(function(){
		if($(this).attr('state') == 'false'){
			// 如果没被选中
			var text = $("#input-tag").val().trim();
			text += " "+$(this).html();
			var result = text.split(/\s+/);
			if(result.length <= 5){
				// 能插入
				$("#input-tag").val(text);
				$(this).attr('state','true');
				$(this).addClass('hot-tag-select');
			}else{
				// 不能插入
				alert('最多只能输入5个标签');
			}
		}else{
			// 已经被选中了，则取消选中
			$(this).attr('state','false');
			$(this).removeClass('hot-tag-select');
			var text = $("#input-tag").val().trim();
			var result = text.split(/\s+/);
			var dx = -1;
			for(var i = 0; i < result.length; i++){
				if(result[i] == $(this).html()){
					dx = i;
					break;
				}
			}
			if(dx != -1 && dx < result.length){
				result.splice(dx,1);
			}
       		var showTag = " ";
       		for (var i = 0; i < result.length; i++) {
       			showTag += result[i] + " ";
       		}
       		showTag = showTag.trim();
       		$("#input-tag").val(showTag);

		}

	});
}


// 初始化标签的个数判断（正则）
var initTagNumCheck = function(){
	$("#input-tag").bind("input propertychange change",function(event){
		var text = $(this).val().trim();
		var result = text.split(/\s+/);
       	if(result.length <=5){
       		// 正确的个数
       		trueTag = text;
       	}else{
       		alert("最多只能输入5个标签");
       		var tags = result.splice(0, 5);
       		var showTag = " ";
       		for (var i = 0; i < tags.length; i++) {
       			showTag += tags[i] + " ";
       		}
       		showTag = showTag.trim();
       		$(this).val(showTag);
       	}
	});
}





// 初始化点击选中原创后触发的事件
var initOriginalSelectAction = function(){
	$('#sentence-original').change(function(){
		if($(this).is(':checked')){
			$('.original-tip').css('display','inline');
			$('.sentence-not-original').toggle(500);
			$('.category').toggle(500);
			// $('.sentence-not-original').css('display','none');
			// $('.category').css('display','none');
		}else{
			$('.original-tip').css('display','none');
			$('.sentence-not-original').toggle(500);
			$('.category').toggle(500);
			// $('.sentence-not-original').css('display','block');
			// $('.category').css('display','block');
		}
	});
};