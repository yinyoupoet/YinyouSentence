<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-11
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- https://www.cnblogs.com/baiyii/p/6973437.html  ,使用@media -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
    <title>王小波大全——句子集——吟游佳句</title>

    <link rel="icon" href="/imgs/sys/icon_2.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select-color.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/nav.css">
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
                    <c:url value="/index.jsp" var="indexUrl"/>
                    <a href="${indexUrl}" class="navbar-brand"><b class="navbar-title" title="吟游佳句"><img src="imgs/sys/icon_1.png" class="icon">&nbsp;&nbsp;吟游佳句</b></a>
                </div>
            </div>

            <div class="collapse navbar-collapse" id="yinyou-navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="/index.action"><i class="fas fa-home"></i>&nbsp;&nbsp;首页</a></li>
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
        <!-- 句子集介绍 -->
        <div class="collectList-intro-div">
            <img src="./imgs/三毛.jpg" class="collectList-intro-img">
            <div class="collectList-intro">
                <span class="collectList-intro-title">句子集： 王小波大全</span><span class="collectList-intro-num">(收录45条句子)</span>

                <span class="collect-love index-a" category="collect"><img src="./imgs/love.png" title="喜欢" id="love-img" state="false"> 喜欢<span> (1520)</span></span>
                <div style="clear: both;"></div>

                <p class="collectList-intro-publish-div"><a href="#" class="index-a">吟游诗人</a><span class="collectList-intro-publish-time">发布于：2018-05-06 12:15:02</p>

                <hr class="colelctList-intro-hr">
                <p class="collectList-intro-explain">《绝望的主妇》故事背景设定在美国一个虚构的小镇——美景镇，描绘了美景镇紫藤巷的五位家庭主妇的婚后生活，每季的剧情也都有新的主线和主妇加入。</p>
            </div>
        </div>
        <!-- 句子集介绍结束 -->

        <!-- 句子列表 -->
        <div class="collectList-sentence-div">
            <!-- 没有句子 -->
            <div class="collectList-sentence-null">
                <h2 style="display: inline-block;">Opps...句子集 <a href="#" class="index-a">《王小波大全》</a> 还没有句子哦</h2>
                <img src="./imgs/4181f32517ab310698072f9155a401f1.jpg" class="collectList-sentence-cry">
                <div style="clear: both;"></div>
            </div>
            <!-- 没有句子结束 -->

            <!-- 有句子的列表 -->
            <div class="collectList-sentence-list">
                <h4><li>句子列表</li></h4>

                <!-- 一条句子结果 -->
                <div class="recommend-item">

                    <p><a href="#" class="recommend-a">如果有来生， <br>
                        要做一棵树， <br>
                        站成永恒， <br>
                        没有悲欢的姿势。<br>
                        一半在土里安详， <br>
                        一半在风里飞扬， <br>
                        一半洒落阴凉， <br>
                        一半沐浴阳光， <br>
                        非常沉默非常骄傲， <br>
                        从不依靠，从不寻找。</a></p>

                    <p class="recommend-from">——<a href="#" class="recommend-author index-a">&nbsp;三毛</a>&nbsp;&nbsp;<a href="#" class="recommend-orient index-a">《说给自己听》</a></p>

                    <div class="recommend-bar">
							<span class="recommend-love-span">
								<span class="recommend-love"><i class="far fa-heart"></i></span>
								<span class="recommend-love-num-span">(<span class="recommend-love-num">1980</span>人喜欢)</span>
							</span>

                        <span class="recommend-collect"><i class="far fa-bookmark"></i> 收藏到句子集</span>

                        <span class="recommend-comment"><a href="#" class="index-a"><i class="far fa-comment"></i> 评论(<span class="recommend-num">128</span>)</a></span>

                        <span class="recommend-publisher-span"><a href="#" class="index-a"><i class="far fa-user"></i> <span class="recomend-publisher">吟游诗人</span></a></span>

                    </div>
                </div>
                <!-- 一条句子结果结束 -->

                <!-- 一条句子结果 -->
                <div class="recommend-item">

                    <p><a href="#" class="recommend-a">因为有了因为，所以有了所以。既然已成既然，何必再说何必。</a></p>

                    <p class="recommend-from">——<a href="#" class="recommend-author index-a">&nbsp;周立波</a>&nbsp;&nbsp;<a href="#" class="recommend-orient index-a">《因为》</a></p>

                    <div class="recommend-bar">
							<span class="recommend-love-span">
								<span class="recommend-love"><i class="far fa-heart"></i></span>
								<span class="recommend-love-num-span">(<span class="recommend-love-num">1980</span>人喜欢)</span>
							</span>

                        <span class="recommend-collect"><i class="far fa-bookmark"></i> 收藏到句子集</span>

                        <span class="recommend-comment"><a href="#" class="index-a"><i class="far fa-comment"></i> 评论(<span class="recommend-num">128</span>)</a></span>

                        <span class="recommend-publisher-span"><a href="#" class="index-a"><i class="far fa-user"></i> <span class="recomend-publisher">吟游诗人</span></a></span>

                    </div>
                </div>
                <!-- 一条句子结果结束 -->




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



<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/collectList.js"></script>
</body>
</html>
