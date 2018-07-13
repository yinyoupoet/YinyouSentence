<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-12
  Time: 23:48
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
    <title>${session.giantInfoEntity.giantInfo.name}——吟游佳句</title>

    <link rel="icon" href="/imgs/sys/icon_2.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select-color.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/hover.css" media="all">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/nav.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-to-top.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-left.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/authorInfo.css">
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

        <!-- 作者信息 -->
        <div class="row authorInfo-div">
            <!-- 左边用于放图片 -->
            <div class="col-xs-3 author-info-left">
                <img src="${sessionScope.giantInfoEntity.giantInfo.imgPath}" class="author-info-pic">
                <p class="author-love index-a" category="author" GID="${sessionScope.giantInfoEntity.giantInfo.id}">
                    <c:choose>
                        <c:when test="${sessionScope.giantInfoEntity.loveGiantState}">
                            <img src="/imgs/sys/love-2.png" title="喜欢" id="love-img" state="true">
                        </c:when>
                        <c:otherwise>
                            <img src="/imgs/sys/love.png" title="喜欢" id="love-img" state="false">
                        </c:otherwise>
                    </c:choose>
                    &nbsp;喜欢 (<span id="giant-love-num">${sessionScope.giantInfoEntity.giantInfo.loveNum}</span>)</p>
                <a href="#">信息不对、不全？点击编辑信息！</a>

                <c:url value="/giantSentence.action" var="giantSentenceUrl">
                    <c:param name="giantId" value="${sessionScope.giantInfoEntity.giantInfo.id}"/>
                </c:url>
                <a href="${giantSentenceUrl}" class="hvr-ripple-out author-info-sentences index-a">点击查看全部句子</a>
            </div>
            <!-- 左边放图片结束 -->

            <!-- 右边作者详细信息 -->
            <div class="col-xs-9 author-info-right">
                <h2 class="author-info-name">${sessionScope.giantInfoEntity.giantInfo.name}</h2>
                <h4 class="author-info-intro"><span class="author-info-intro-title">作者介绍：</span><span class="author-info-intro-content">${sessionScope.giantInfoEntity.giantInfo.introduction}</span></h4>
            </div>
            <!-- 右边作者详细信息结束 -->


        </div>
        <!-- 作者信息结束 -->

        <!-- 喜欢作者的人 -->
        <div class="author-fans">
            <h4 class="author-fans-title"><li><b>这些人也喜欢${sessionScope.giantInfoEntity.giantInfo.name}</b></li></h4>
            <a class="index-a author-more-fan-a" href="#">>> 更多</a>
            <!-- 喜欢作者的人的(最多12个) -->
            <div class="author-fans-list row">
                <c:choose>
                    <c:when test="${empty sessionScope.giantInfoEntity.fansList}">
                        <center>还没有用户喜欢哦，做第一个吧</center>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${sessionScope.giantInfoEntity.fansList}" var="fan">
                            <!-- 一个人 -->
                            <div class="author-fans-item col-xs-1">
                                <div class="author-fans-content">
                                    <c:url value="/toPeople.action" var="peopleUrl">
                                        <c:param name="id" value="${fan.id}"/>
                                    </c:url>
                                    <a href="${peopleUrl}"><img src="${fan.headPath}"></a>
                                    <a href="${peopleUrl}" class="index-a author-fan-name">${fan.userName}</a>
                                    <p class="author-fan-info"><fmt:formatDate value="${fan.birth}" pattern="yyyy年"/> / ${fan.gender}</p>
                                </div>
                            </div>
                            <!-- 一个人结束 -->
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


            </div>
            <!-- 喜欢作者的人结束 -->
        </div>
        <!-- 喜欢作者的人结束 -->


        <!-- 作者作品集 -->
        <div class="author-books-div">
            <h4><li><b>${sessionScope.giantInfoEntity.giantInfo.name}作品集</b></li></h4>
            <!-- 作品集列表 -->
            <div class="author-books-list row">
                <c:forEach items="${sessionScope.giantInfoEntity.originInfoList}" var="origin">
                    <!-- 一个作品 -->
                    <div class="author-books-item col-xs-3">
                        <div class="author-books-item-content">
                            <c:url value="/origin.action" var="originUrl">
                                <c:param name="originId" value="${origin.id}"/>
                            </c:url>
                            <a href="${originUrl}"><img src="${origin.imgPath}" class="author-books-item-pic"></a>
                            <p class="author-books-item-title"><a href="#" class="index-a">${origin.name}</a></p>
                            <p class="book-love index-a" category="book">喜欢<span> (${origin.loveNum})</span></p>
                        </div>
                    </div>
                    <!-- 一个作品结束 -->
                </c:forEach>

            </div>
            <!-- 作品集列表结束 -->
        </div>
        <!-- 作者作品集结束 -->

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
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrGiant.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/authorInfo.js"></script>
</body>
</html>
