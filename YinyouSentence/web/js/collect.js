$(document).ready(function(){
    $('.collect-collection-div-close').click(function(){
        $('.collect-collection-div').hide();
    });
});


// 收藏或者取消收藏
var collectOrNot = function (collectionId) {
    var sentenceId = $('#collect-collection-title').attr('SID');
    dwrCollect.doCollect(sentenceId,collectionId,function(data){
        if(! data.success){
            // 失败
            alert(data.reason);
            return;
        }
        // 成功
        if(data.collect){
            $('#btn-collect-add-'+collectionId).html("已收藏");
        }else{
            $('#btn-collect-add-'+collectionId).html("收藏");
        }
        console.log(data.sentenceNum);
        $('#collect-collection-num-'+collectionId).html(data.sentenceNum);

    });
};

// 添加句子集
var addCollection = function(){
    var content = $('.collect-new-collection-input').val();
    if(content.trim() == ""){
        alert("请输入句子集名称");
        return false;
    }
    dwrCollect.addNewCollection(content,function(data){
        if(!data.success){
            alert(data.reason);
            return false;
        }
        // 如果成功，那么开始重新加载
        $('.collect-new-collection-input').val("");
        initCollectCollectionList($('#collect-collection-title').attr('SID'));
    });
};


// 重新加载句子集列表
var initCollectCollectionList = function(sentenceId){
    $('#collect-collection-title').attr('SID',sentenceId);
    dwrCollect.initCollectionList(sentenceId,function(arrayList){
        var contentDiv = $('.collect-collection-list');

        if( (!arrayList) || (arrayList.length == 0) ){
            // 如果没有句子集
            var code = ' <center>还没有句子集哦，新建一个吧!</center>';
            contentDiv.html(code);
            return;
        }

        // 清空contentDiv
        contentDiv.html("");
        for(var i = 0; i < arrayList.length; i++){
            var code = '<div class="collect-collection-item">';
            code += '<p class="collect-collection-name-p">';
            code += '<span id="collect-collection-name-'+ arrayList[i].sentenceCollection.id+'">';
            code += arrayList[i].sentenceCollection.name;
            code += '</span><span class="collect-collection-num-span">(包含<span id="collect-collection-num-'+arrayList[i].sentenceCollection.id+'">';
            code += arrayList[i].sentenceCollection.sentenceNum + '</span>条句子)</span></p>';
            code += '<button class="btn-collect-add-collection" id="btn-collect-add-'+ arrayList[i].sentenceCollection.id +'" onclick="collectOrNot('+ arrayList[i].sentenceCollection.id +');">';
            if(arrayList[i].collect){
                code += '已收藏';
            }else{
                code += '收藏';
            }
            code += '</button></div>';
            $('.collect-collection-list').append(code);
        }
    });
};


/*

<%--一条句子集--%>
<div class="collect-collection-item">
    <p class="collect-collection-name-p"><span id="collect-collection-name-${cltAux.sentenceCollection.id}">${cltAux.sentenceCollection.name}</span><span class="collect-collection-num-span">(包含<span id="collect-collection-num-${cltAux.sentenceCollection.id}">${cltAux.sentenceCollection.sentenceNum}</span>条句子)</span></p>
<button class="btn-collect-add-collection" id="btn-collect-add-${cltAux.sentenceCollection.id}" onclick="collectOrNot(${cltAux.sentenceCollection.id});">
    <c:choose>
<c:when test="${cltAux.collect}">
    已收藏
    </c:when>
    <c:otherwise>
收藏
</c:otherwise>
</c:choose>
</button>
</div>
<%--一条句子集结束--%>


*/




/*
dwrCollect.addNewCollection(content,function(arrayList){

    for(var i = 0; i < arrayList.length; i++){
        console.log(arrayList[i].success + "  " + arrayList[i].reason);
    }

});*/
