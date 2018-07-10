<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-07
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- https://www.cnblogs.com/baiyii/p/6973437.html  ,使用@media -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
    <title>佳句赏析——吟游佳句</title>

    <link rel="icon" href="/imgs/sys/icon_2.png" type="image/x-icon"/>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select-color.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layx.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/nav.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-to-top.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/sentenceInfo.css">

</head>
<body>
<header>
    <nav class="navbar navbar-fixed-top my-navbar top-nav" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#yinyou-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <div>
                    <c:url value="/index.jsp" var="indexUrl"/>
                    <a href="${indexUrl}" class="navbar-brand"><b class="navbar-title" title="吟游佳句"><img src="imgs/sys/icon_1.png" class="icon">&nbsp;&nbsp;吟游佳句</b></a>
                </div>
            </div>

            <div class="collapse navbar-collapse" id="yinyou-navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#"><i class="fas fa-home"></i>&nbsp;&nbsp;首页</a></li>
                    <li><a href="#">
							<span class="notifaction-icon">
								<i class="fas fa-bell"></i>
								<span class="notifaction-num">1</span>
							</span>&nbsp;&nbsp;通知</a></li>

                    <c:choose>
                        <c:when test="${!empty sessionScope.userInfo}">
                            <%--已登录--%>
                            <li id="nav-head-infoIMG">
                                <a href="#">
                                    <img src="${sessionScope.userInfo.headPath}" class="nav-head-img" id="nav-head-img">
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <%--未登录--%>
                            <li id="nav-head-infoIMG">
                                <a href="/toLoginOrRegister.action" class="index-a sign-in"><i class="fas fa-sign-in-alt"></i>&nbsp;&nbsp;登录/注册</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>

        </div>

    </nav>

    <!-- 鼠标点击头像，显示信息 -->
    <div class="nav-self-info">
        <img src="${sessionScope.userInfo.headPath}" class="head-info">
        <div style="display: inline;">
            <ul style="display: inline; float: left;" class="info-ul">
                <c:url value="toPeople.action" var="selfUrl">
                    <c:param name="id" value="${userInfo.id}"/>
                </c:url>
                <c:url value="/toEditInfo.action" var="editUrl"/>
                <c:url value="/logout.action" var="logoutUrl"/>
                <li><a href="${selfUrl}" class="info-link">${userInfo.userName}</a></li>
                <li><a href="${editUrl}" class="info-link">编辑资料</a></li>
                <li><a href="${logoutUrl}" class="info-link">登出</a></li>
            </ul>
        </div>
    </div>

</header>



<content>
    <div class="container">
        <div class="row">

            <!-- 左边版块 -->
            <div class="col-xs-8 sentence-left-side">
                <!-- 句子核心块 -->
                <div class="sentence-info-div">

                    <p class="sentence-title">~~ 佳句赏析 ~~</p>
                    <div class="sentence-content-div">
                        <h4 class="sentence-content">${sessionScope.sentenceEntity.sentence.content}</h4>
                        <c:choose>
                            <c:when test="${sessionScope.sentenceEntity.original}">
                                <p class="sentence-from">——原创</p>
                            </c:when>
                            <c:when test="${sessionScope.sentenceEntity.giantInfo == null && sessionScope.sentenceEntity.originInfo == null}">

                            </c:when>
                            <c:otherwise>
                                <p class="sentence-from">——
                                    <c:if test="${sessionScope.sentenceEntity.giantInfo != null}">
                                        <c:url value="/giant.action" var="giantUrl">
                                            <c:param name="giantId" value="${sessionScope.sentenceEntity.giantInfo.id}"/>
                                        </c:url>
                                        &nbsp;<a href="${giantUrl}" class="index-a">${sessionScope.sentenceEntity.giantInfo.name}</a>&nbsp;
                                    </c:if>
                                    <c:if test="${sessionScope.sentenceEntity.originInfo != null}">
                                        <c:url value="/origin.action" var="originUrl">
                                            <c:param name="originId" value="${sessionScope.sentenceEntity.originInfo.id}"/>
                                        </c:url>
                                        &nbsp;<a href="${originUrl}" class="index-a">${sessionScope.sentenceEntity.originInfo.name}</a>
                                    </c:if>
                                </p>

                            </c:otherwise>
                        </c:choose>

                    </div>

                    <div>
                        <c:url value="toPeople.action" var="publisherUrl">
                            <c:param name="id" value="${sessionScope.sentenceEntity.userInfo.id}"/>
                        </c:url>
                        <p class="sentence-publisher-p"><a href="${publisherUrl}" class="index-a">${sessionScope.sentenceEntity.userInfo.userName}</a>发布于<span id="random-publish-time"><fmt:formatDate value="${sessionScope.sentenceEntity.sentence.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
                    </div>


                    <!-- 句子标签 -->
                    <div class="sentence-tag-div">
                        <p class="tag-p"><i class="fas fa-tags tag-i"></i>标签：<span class="sentence-tag-list">
                            <c:if test="${sessionScope.sentenceEntity.tags != null}">
                                <c:forEach items="${sessionScope.sentenceEntity.tags}" var="tag">
                                    <c:url value="/userLove.action" var="tagLoveUrl">
                                        <c:param name="tagId" value="${tag.id}"/>
                                    </c:url>
                                    <a href="tagLoveUrl" class="index-a">#${tag.name}</a>
                                </c:forEach>
                            </c:if>
                        </span></p>
                    </div>

                    <!-- 设置喜欢和收藏 -->
                    <div class="sentence-operate">

                        <span class="sentence-love index-a" SID="${sessionScope.sentenceEntity.sentence.id}">
                            <c:choose>
                                <c:when test="${sessionScope.sentenceEntity.userLove}">
                                    <img src="/imgs/sys/love-2.png" title="喜欢" id="love-img" state="false" >
                                </c:when>
                                <c:otherwise>
                                    <img src="/imgs/sys/love.png" title="喜欢" id="love-img" state="false" >
                                </c:otherwise>
                            </c:choose>
                            &nbsp;喜欢 (<span id="sentence-love-num">${sessionScope.sentenceEntity.sentence.loveNum}</span>)</span>

                        <span class="sentence-collect recommend-collect" SID="${sessionScope.sentenceEntity.sentence.id}"><i class="far fa-bookmark"></i> 收藏到句子集</span>
                        <a class="sentence-to-comment index-a cm" href="#comment" SID="${sessionScope.sentenceEntity.sentence.id}"><i class="far fa-comment"></i> 评论</a>
                    </div>

                    <!-- 举报这条句子 -->
                    <a href="#" class="sentence-accuse" style="margin-top:10px;">冒充原创？信息有误？有辱斯文？举报！</a>
                </div>
                <!-- 句子核心块结束 -->

                <!-- 查看句子评论 -->
                <div class="sentence-comment-div">
                    <p class="sentence-title">~~ 心得交流 ~~</p>
                    <c:choose>
                        <c:when test="${empty sessionScope.commentEntity.commentAuxiliaries}">
                            <%--如果没有评论--%>
                            <div class="no-comment-show">
                                <center><a href="#comment" class="index-a cm">还没有评论哦，赶紧去发布一条吧</a></center>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <%--有评论--%>
                            <!-- 评论列表 -->
                            <div class="comment-list">
                                <c:forEach items="${sessionScope.commentEntity.commentAuxiliaries}" var="commentAuxiliary">
                                    <!-- 评论一条 -->
                                    <div class="comment-item" id="comment${commentAuxiliary.sentenceComment.id}">
                                        <c:url value="/toPeople.action" var="peopleUrl">
                                            <c:param name="id" value="${commentAuxiliary.userInfo.id}"/>
                                        </c:url>
                                        <a href="${peopleUrl}" class="comment-item-img-a"><img src="${commentAuxiliary.userInfo.headPath}" class="comment-item-img"></a>
                                        <div class="comment-item-right">
                                            <!-- 评论头部用户信息 -->
                                            <div class="comment-item-head">
                                                <a class="comment-item-name index-a" href="${peopleUrl}" id="comment-author-${commentAuxiliary.sentenceComment.id}">${commentAuxiliary.userInfo.userName}</a>
                                                <c:if test="${! empty commentAuxiliary.userInfo.motto}">
                                                    <span class="comment-user-motto">(${commentAuxiliary.userInfo.motto})</span>
                                                </c:if>
                                                <%--说明：rpType:回复类型，0为回复评论，1为回复回复--%>
                                                <a class="comment-reply-a cm cr" href="#comment" name="${commentAuxiliary.sentenceComment.id}" rpType="0">回复</a>
                                                <p class="comment-item-time"><fmt:formatDate value="${commentAuxiliary.sentenceComment.commentTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                                            </div>
                                            <!-- 评论内容 -->
                                            <div class="comment-item-content" id="comment-content-${commentAuxiliary.sentenceComment.id}">
                                                    ${commentAuxiliary.sentenceComment.content}
                                            </div>

                                            <c:if test="${! empty commentAuxiliary.replyAuxiliaries}">
                                                <%--如果这条评论有回复啊--%>
                                                <c:forEach items="${commentAuxiliary.replyAuxiliaries}" var="replyAuxiliary">
                                                    <!-- 一条评论的回复 -->
                                                    <div class="comment-reply-list">
                                                        <c:url value="/toPeople.action" var="peopleReplyUrl">
                                                            <c:param name="id" value="${replyAuxiliary.publisherInfo.id}"/>
                                                        </c:url>
                                                        <c:url value="/toPeople.action" var="peopleBeRepliedUrl">
                                                            <c:param name="id" value="${replyAuxiliary.commentReply.replyObjectUserId}"/>
                                                        </c:url>
                                                        <a class="comment-reply-name index-a" href="${peopleReplyUrl}" id="comment-reply-writer-${replyAuxiliary.commentReply.id}">${replyAuxiliary.publisherInfo.userName}</a>:
                                                        &nbsp;回复 <a href="${peopleBeRepliedUrl}" class="index-a comment-reply-object">${replyAuxiliary.userBeRepliedInfo.userName}</a>:
                                                        &nbsp;<span class="comment-reply-content" id="comment-reply-content-${replyAuxiliary.commentReply.id}">${replyAuxiliary.commentReply.content}</span>
                                                        <div class="comment-reply-operate">
                                                            <span class="comment-reply-time"><fmt:formatDate value="${replyAuxiliary.commentReply.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                                            <a href="#comment" class="index-a comment-reply-a comment-reply-response-a cm rr" name="${replyAuxiliary.commentReply.id} rpType="1">回复</a>
                                                        </div>
                                                    </div>
                                                    <!-- 一条评论的回复结束 -->
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>

                                </c:forEach>
                            </div>
                            <!-- 评论列表结束 -->
                        </c:otherwise>
                    </c:choose>

                </div>
                <!-- 查看句子评论结束 -->

                <!-- 发布评论 -->
                <div class="publish-comment" id="comment">
                    <p class="sentence-title">~~ 发布评论 ~~</p>
                    <form class="comment-input-form" action="/comment.action?sentenceId=${sessionScope.sentenceEntity.sentence.id}" method="post" SID="${sessionScope.sentenceEntity.sentence.id}">
                        <p class="comment-reply-head">
                            <span class="comment-cancel index-a" id="comment-cancel" onclick="cancelComment();"><i class="far fa-times-circle"></i>取消回复</span>
                        </p>
                        <div class="form-group">
                            <textarea class="form-control" rows="5" id="comment-content" name="content" maxlength="498" placeholder="最多输入498个字符呦"></textarea>
                        </div>
                        <button class="btn btn-info comment-publish-btn" id="comment-publish-btn">发布评论</button>
                        <div style="clear: both;"></div>
                    </form>
                </div>
                <!-- 发布评论结束 -->


            </div>
            <!-- 左边版块结束 -->

            <!-- 句子右边版块 -->
            <div class="col-xs-4 sentence-right-side">
                <c:choose>
                    <c:when test="${sessionScope.sentenceEntity.original}">
                        <%--原创--%>
                        <!-- 发布者信息 -->
                        <div class="author-info">
                            <c:url value="toPeople.action" var="rightPublisherUrl">
                                <c:param name="id" value="${sessionScope.sentenceEntity.userInfo.id}"/>
                            </c:url>
                            <center><a href="${rightPublisherUrl}"><img src="${sessionScope.sentenceEntity.userInfo.headPath}" class="author-info-img" title="${sessionScope.sentenceEntity.userInfo.userName}"></a></center>
                            <center><a class="author-name title-a" href="${rightPublisherUrl}">${sessionScope.sentenceEntity.userInfo.userName}</a></center>
                            <div class="author-introduce">
                                <center><a class="author-introduce-content">${sessionScope.sentenceEntity.userInfo.motto}</a></center>
                            </div>

                        </div>
                        <!-- 发布者简介结束 -->
                    </c:when>
                    <c:when test="${sessionScope.sentenceEntity.giantInfo == null && sessionScope.sentenceEntity.originInfo == null}">
                        <%--如果没有出处和作者，那么仍然显示发布者信息--%>
                        <!-- 发布者信息 -->
                        <div class="author-info">
                            <c:url value="toPeople.action" var="rightPublisherUrl">
                                <c:param name="id" value="${sessionScope.sentenceEntity.userInfo.id}"/>
                            </c:url>
                            <center><a href="${rightPublisherUrl}"><img src="${sessionScope.sentenceEntity.userInfo.headPath}" class="author-info-img" title="${sessionScope.sentenceEntity.userInfo.userName}"></a></center>
                            <center><a class="author-name title-a" href="${rightPublisherUrl}">${sessionScope.sentenceEntity.userInfo.userName}</a></center>
                            <div class="author-introduce">
                                <center><a class="author-introduce-content">${sessionScope.sentenceEntity.userInfo.motto}</a></center>
                            </div>

                        </div>
                        <!-- 发布者简介结束 -->
                    </c:when>
                    <c:when test="${sessionScope.sentenceEntity.giantInfo != null}">
                        <%--作者信息不为空--%>
                        <!-- 作者信息 -->
                        <div class="author-info">
                            <c:url value="/giant.action" var="giantUrl">
                                <c:param name="giantId" value="${sessionScope.sentenceEntity.giantInfo.id}"/>
                            </c:url>
                            <center><a href="${giantUrl}"><img src="${sessionScope.sentenceEntity.giantInfo.imgPath}" class="author-info-img" title="${sessionScope.sentenceEntity.giantInfo.name}"></a></center>
                            <center><a class="author-name title-a" href="${giantUrl}">${sessionScope.sentenceEntity.giantInfo.name}</a></center>
                            <div class="author-introduce">
                                <span class="author-introduce-title">作者简介：</span><a class="author-introduce-content" href="${giantUrl}">${sessionScope.sentenceEntity.giantInfo.introduction}</a>
                            </div>
                            <div class="author-other-sentence">
                                <c:url value="/giantSentence.action" var="giantSentenceUrl">
                                    <c:param name="giantId" value="${sessionScope.sentenceEntity.giantInfo.id}"/>
                                </c:url>

                                <c:if test="${! empty sessionScope.sentenceEntity.giantSentences}">
                                    <%--如果有句子--%>
                                    <a href="${giantSentenceUrl}" class="title-a more-sentence-title">>>更多${sessionScope.sentenceEntity.giantInfo.name}的句子</a>
                                    <c:forEach items="${sessionScope.sentenceEntity.giantSentences}" var="stc">
                                        <!-- 一条更多句子 -->
                                        <div class="more-item">
                                            <c:url value="sentence.action" var="sentenceUrl">
                                                <c:param name="sentenceId" value="${stc.id}"/>
                                            </c:url>
                                            <i class="fas fa-chevron-right"></i> <a href="${sentenceUrl}" class="index-a">${stc.content}</a>
                                        </div>
                                        <!-- 一条更多句子结束 -->
                                    </c:forEach>
                                </c:if>

                            </div>
                        </div>
                        <!-- 作者简介结束 -->
                    </c:when>
                    <c:when test="${sessionScope.sentenceEntity.originInfo != null}">
                        <%-- 出处信息不为空 --%>
                        <!-- 出处信息 -->
                        <div class="author-info">
                            <c:url value="origin.action" var="originUrl">
                                <c:param name="originId" value="${sessionScope.sentenceEntity.originInfo.id}"/>
                            </c:url>
                            <center><a href="${originUrl}"><img src="${sessionScope.sentenceEntity.originInfo.imgPath}" class="author-info-img" title="${sessionScope.sentenceEntity.originInfo.name}"></a></center>
                            <center><a class="author-name title-a" href="${originUrl}">${sessionScope.sentenceEntity.originInfo.name}</a></center>
                            <div class="author-introduce">
                                <span class="author-introduce-title">出处简介：</span><a class="author-introduce-content" href="${originUrl}">${sessionScope.sentenceEntity.originInfo.introduction}</a>
                            </div>
                            <div class="author-other-sentence">
                                <c:if test="${! empty sessionScope.sentenceEntity.originSentences}">
                                    <%--如果出处有句子--%>
                                    <a href="${originUrl}" class="title-a more-sentence-title">>>更多${sessionScope.sentenceEntity.originInfo.name}的句子</a>
                                <c:forEach items="${sessionScope.sentenceEntity.originSentences}" var="sentence">
                                    <!-- 一条更多句子 -->
                                    <div class="more-item">
                                        <c:url value="sentence.action" var="sentenceUrl">
                                            <c:param name="sentenceId" value="${sentence.id}"/>
                                        </c:url>
                                        <i class="fas fa-chevron-right"></i> <a href="${sentenceUrl}" class="index-a">${sentence.content}</a>
                                    </div>
                                    <!-- 一条更多句子结束 -->
                                </c:forEach>
                                </c:if>

                            </div>
                        </div>
                        <!-- 出处简介结束 -->
                    </c:when>
                </c:choose>


                <!-- 喜欢该句子的人 -->
                <div class="love-this-sentence-div">
                    <c:url value="userLove.action" var="sentenceLoveUrl">
                        <c:param name="sentenceId" value="${sessionScope.sentenceEntity.sentence.id}"/>
                    </c:url>
                    <p class="love-this-sentence-title"><a href="${sentenceLoveUrl}" class="title-a">>> 这些人也喜欢这个句子</a></p>
                    <c:choose>
                        <c:when test="${empty sessionScope.sentenceEntity.loveUsers}">
                            <%--没有喜欢的用户--%>
                            <!-- 没人喜欢这个句子 -->
                            <p class="love-client-nobody">还没有人喜欢这个句子哦...</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${sessionScope.sentenceEntity.loveUsers}" var="user">
                                <!-- 喜欢的一个用户 -->
                                <div class="love-client-item">
                                    <c:url value="/toPeople.action" var="peopleUrl">
                                        <c:param name="id" value="${user.id}"/>
                                    </c:url>
                                    <a href="${peopleUrl}"><img src="${user.headPath}"></a>
                                    <div class="love-client-right">
                                        <a href="${peopleUrl}" class="index-a love-client-name ">${user.userName}</a>
                                        <span class="love-span-intr"><fmt:formatDate value="${user.birth}" pattern="YYYY"/>年 / ${user.gender}</span><br>
                                        <button class="btn-follow" UID="${user.id}"><i class="fas fa-plus"></i> 关注</button>
                                        <!-- <p class="fan-client" UID="1">+ 关注</p> -->
                                    </div>
                                </div>
                                <!-- 喜欢的一个用户结束 -->
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>




                </div>
                <!-- 喜欢该句子的人结束 -->

            </div>
            <!-- 右边部分结束 -->


        </div>

            </div>
        </div>
    </div>
</content>




<footer class="footer">
    <div class="container">
        <center>版权所有 © 吟游佳句 | 现代人心灵旅社，吟游诗人出品 </center>
    </div>
</footer>


<!-- 点击返回首部 -->
<a class="arrow-up back-to-top">
    <span class="left-arm"></span>
    <span class="right-arm"></span>
    <span class="arrow-slide"></span>
</a>





<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrSentenceInfo.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrLoginCheck.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layx.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/toCollect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sentenceInfo.js"></script>

</body>
</html>
