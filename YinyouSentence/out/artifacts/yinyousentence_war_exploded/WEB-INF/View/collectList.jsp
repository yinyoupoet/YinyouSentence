<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-11
  Time: 21:06
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
    <title>${sessionScope.sentenceCollection.name}——句子集——吟游佳句</title>

    <link rel="icon" href="/imgs/sys/icon_2.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select-color.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/nav.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/collect.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-to-top.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-left.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/collectList.css">
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
                    <c:url value="/index.action" var="indexUrl"/>
                    <a href="${indexUrl}" class="navbar-brand"><b class="navbar-title" title="吟游佳句"><img src="/imgs/sys/icon_1.png" class="icon">&nbsp;&nbsp;吟游佳句</b></a>
                </div>
            </div>

            <div class="collapse navbar-collapse" id="yinyou-navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="index.action"><i class="fas fa-home"></i>&nbsp;&nbsp;首页</a></li>
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
                                <a href="toLoginOrRegister.action" class="index-a sign-in"><i class="fas fa-sign-in-alt"></i>&nbsp;&nbsp;登录/注册</a>
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
        <!-- 句子集介绍 -->
        <div class="collectList-intro-div">
            <img src="${sessionScope.sentenceCollection.imgPath}" class="collectList-intro-img">
            <div class="collectList-intro">
                <span class="collectList-intro-title">句子集： ${sessionScope.sentenceCollection.name}</span><span class="collectList-intro-num">(收录${sessionScope.sentenceCollection.sentenceNum}条句子)</span>

                <span class="collect-love index-a" category="collect" CID="${sessionScope.sentenceCollection.id}">
                    <c:choose>
                        <c:when test="${sessionScope.loveOrNot}">
                            <img src="/imgs/sys/love-2.png" title="喜欢" id="love-img" state="true">
                        </c:when>
                        <c:otherwise>
                            <img src="/imgs/sys/love.png" title="喜欢" id="love-img" state="false">
                        </c:otherwise>
                    </c:choose>
                     喜欢 (<span id="collection-love-num">${session.sentenceCollection.loveNum}</span>)</span>
                <div style="clear: both;"></div>

                <c:url value="/toPeople.action" var="peopleUrl">
                    <c:param name="id" value="${sessionScope.publisherInfo.id}"/>
                </c:url>

                <%--做到这里了--%>
                <p class="collectList-intro-publish-div"><a href="${peopleUrl}" class="index-a">${sessionScope.publisherInfo.userName}</a><span class="collectList-intro-publish-time">发布于：<fmt:formatDate value="${sessionScope.sentenceCollection.publishDate}" pattern="yyyy-MM-dd hh:mm:ss"/> </p>

                <hr class="colelctList-intro-hr">
                <p class="collectList-intro-explain">${sessionScope.sentenceCollection.introduction}</p>
            </div>
        </div>
        <!-- 句子集介绍结束 -->

        <!-- 句子列表 -->
        <div class="collectList-sentence-div">
            <c:choose>
                <c:when test="${empty sessionScope.sentenceEntities}">
                    <!-- 没有句子 -->
                    <div class="collectList-sentence-null">
                        <h2 style="display: inline-block;">Opps...句子集 <a href="#" class="index-a">《王小波大全》</a> 还没有句子哦</h2>
                        <img src="/imgs/sys/4181f32517ab310698072f9155a401f1.jpg" class="collectList-sentence-cry">
                        <div style="clear: both;"></div>
                    </div>
                    <!-- 没有句子结束 -->
                </c:when>
                <c:otherwise>
                <!-- 有句子的列表 -->
                <div class="collectList-sentence-list">
                    <h4><li>句子列表</li></h4>

                    <c:forEach items="${sessionScope.sentenceEntities}" var="recommendSentence">
                        <!-- 一条句子 -->
                        <div class="recommend-item">
                            <c:url value="sentence.action" var="sentenceUrl">
                                <c:param name="sentenceId" value="${recommendSentence.sentence.id}"/>
                            </c:url>
                            <p><a href="${sentenceUrl}" class="recommend-a">${recommendSentence.sentence.content}</a></p>

                            <p class="recommend-from">
                                <c:choose>
                                <c:when test="${recommendSentence.original}">
                            <p class="sentence-from" style="text-align:right;">——原创</p>
                            </c:when>
                            <c:when test="${recommendSentence.giantInfo == null && recommendSentence.originInfo == null}">

                            </c:when>
                            <c:otherwise>
                                <p class="sentence-from" style="text-align:right;">——
                                    <c:if test="${recommendSentence.giantInfo != null}">
                                        <c:url value="/giant.action" var="giantUrl">
                                            <c:param name="giantId" value="${recommendSentence.giantInfo.id}"/>
                                        </c:url>
                                        &nbsp;<a href="${giantUrl}" class="index-a">${recommendSentence.giantInfo.name}</a>&nbsp;
                                    </c:if>
                                    <c:if test="${recommendSentence.originInfo != null}">
                                        <c:url value="/origin.action" var="originUrl">
                                            <c:param name="originId" value="${recommendSentence.originInfo.id}"/>
                                        </c:url>
                                        &nbsp;<a href="${originUrl}" class="index-a">${recommendSentence.originInfo.name}</a>
                                    </c:if>
                                </p>

                            </c:otherwise>
                            </c:choose>
                            </p>



                            <div class="recommend-bar">
								<span class="recommend-love-span" SID="${recommendSentence.sentence.id}">
                                    <c:choose>
                                        <c:when test="${recommendSentence.userLove}">
                                            <%--喜欢--%>
                                            <span class="recommend-love"><i class="fas fa-heart"></i></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="recommend-love"><i class="far fa-heart"></i></span>
                                        </c:otherwise>
                                    </c:choose>
									<span class="recommend-love-num-span">(<span class="recommend-love-num">${recommendSentence.sentence.loveNum}</span>人喜欢)</span>
								</span>

                                <span class="recommend-collect" SID="${recommendSentence.sentence.id}"><i class="far fa-bookmark"></i> 收藏到句子集</span>

                                <c:url value="sentence.action" var="commentUrl">
                                    <c:param name="sentenceId" value="${recommendSentence.sentence.id}"/>
                                </c:url>
                                <span class="recommend-comment"><a href="${commentUrl}#comment" class="index-a"><i class="far fa-comment"></i> 评论(<span class="recommend-num">${recommendSentence.commentNum}</span>)</a></span>

                                <c:url value="toPeople.action" var="peopleUrl">
                                    <c:param name="id" value="${recommendSentence.userInfo.id}"/>
                                </c:url>
                                <span class="recommend-publisher-span"><a href="${peopleUrl}" class="index-a"><i class="far fa-user"></i> <span class="recomend-publisher">${recommendSentence.userInfo.userName}</span></a></span>

                            </div>
                        </div>
                        <!-- 一条句子结束 -->
                    </c:forEach>

                </div>
                <!-- 有句子的列表结束 -->
             </div>
            <!-- 句子列表 -->
                </c:otherwise>
            </c:choose>

            </div>
            <!-- 有句子的列表结束 -->
        </div>
        <!-- 句子列表 -->



    </div>
</content>







<!-- 	<footer class="footer">
		<div class="container">
			<center>版权所有 © 吟游佳句 | 现代人心灵旅社，吟游诗人出品 </center>
		</div>
	</footer> -->

<!-- 点击返回首部 -->
<a class="arrow-up back-to-top">
    <span class="left-arm"></span>
    <span class="right-arm"></span>
    <span class="arrow-slide"></span>
</a>

<%--收藏到句子集--%>
<div class="collect-collection-div">
    <div class="collect-collection-list-div">
        <img src="/imgs/sys/search close.png" class="collect-collection-div-close">
        <h2 style="text-align: center;" id="collect-collection-title" SID="">收藏句子到句子集</h2>
        <%--句子集列表--%>
        <div class="collect-collection-list">

        </div>
        <%--句子集列表结束--%>
        <div class="collect-new-collection-div">
            <input type="text" class="collect-new-collection-input" maxlength="25" placeholder="请输入新建句子集名"> <button class="btn btn-info collect-new-collection-btn" onclick="addCollection()">新增</button>
        </div>
    </div>
</div>



<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrSentenceInfo.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrLoginCheck.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrCollect.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrGiant.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/collect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/collectList.js"></script>
</body>
</html>
