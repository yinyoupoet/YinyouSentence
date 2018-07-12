<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-08
  Time: 10:09
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
    <title>吟游诗人——个人中心</title>

    <link rel="icon" href="/imgs/sys/icon_2.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select-color.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/collect.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/nav.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-to-top.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-left.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/people.css">


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
        <!-- 头部个人简介 -->
        <div class="people-info-div">
            <img src="${sessionScope.peopleEntity.headPath}" class="people-info-img">
            <div class="people-info-detail">
                <p><span class="people-name" id="people-name" UID="${sessionScope.peopleEntity.userId}">${sessionScope.peopleEntity.userName}</span><span class="people-private-info">${sessionScope.peopleEntity.birthYear} /&nbsp; ${sessionScope.peopleEntity.gender}</span></p>
                <p class="people-motto">${sessionScope.peopleEntity.motto}</p>

                <c:choose>
                    <c:when test="${sessionScope.peopleEntity.self}">
                        <!-- 自己看 -->
                        <c:url value="/toEditInfo.action" var="editUrl"/>
                        <a class="btn-edit" href="${editUrl}"><i class="far fa-edit"></i> 编辑资料</a>
                    </c:when>
                    <c:otherwise>
                        <!-- 别人看 -->
                        <c:choose>
                            <c:when test="${sessionScope.peopleEntity.follow}">
                                <button class="btn-follow" UID="${sessionScope.peopleEntity.userId}">已关注</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn-follow" UID="${sessionScope.peopleEntity.userId}"><i class="fas fa-plus"></i> 关注</button>
                            </c:otherwise>
                        </c:choose>

                    </c:otherwise>
                </c:choose>


            </div>
        </div>
        <!-- 头部个人简介结束 -->

        <!-- 中间一大部分用户资料 -->
        <div class="people-file">
            <div class="row">

                <div class="col-xs-8 left">
                    <div class="people-left-side">
                        <div class="people-left-menu">
                            <span class="people-left-menu-item is-active" id="menu-love-sentence">喜欢的句子 <span class="people-left-menu-item-num">${sessionScope.peopleEntity.loveSentenceNum}</span></span>
                            <span class="people-left-menu-item" id="menu-publish-sentence">发布的句子 <span class="people-left-menu-item-num">${sessionScope.peopleEntity.publishNum}</span></span>
                            <span class="people-left-menu-item" id="menu-diy">原创 <span class="people-left-menu-item-num">${sessionScope.peopleEntity.originalNum}</span></span>
                            <span class="people-left-menu-item" id="menu-collect">句子集 <span class="people-left-menu-item-num">${sessionScope.peopleEntity.collectionNum}</span></span>
                            <span class="people-left-menu-item" id="love-menu-collect">喜欢的句子集 <span class="people-left-menu-item-num">${sessionScope.peopleEntity.loveCollectionNum}</span></span>
                        </div>
                        <!-- 左边菜单下的实质显示 -->
                        <div class="people-left-content">
                            <!-- 句子列表 -->
                            <div class="sentence-list">

                            </div>
                            <!-- 句子列表结束 -->

                            <!-- 句子集列表 -->
                            <div class="collect-list">

                            </div>
                            <!-- 句子集列表结束 -->


                        </div>
                        <!-- 左边菜单下的实质显示结束 -->
                    </div>
                </div>



                <div class="col-xs-4 right">
                    <!-- 仿知乎关注与被关注 -->
                    <div class="people-follow-fan">
                        <a href="#" class="people-follow-fan-div ">
                            <div href="#" class="people-follow-fan-div-left">
                                <p class="people-follow-fan-title">关注了</p>
                                <p class="people-follow-fan-num" id="hasFollowedNum">${sessionScope.peopleEntity.followingNum}</p>
                            </div>
                        </a>
                        <div class="people-follow-fan-hr"></div>
                        <a href="#" class="people-follow-fan-div ">
                            <div href="#" class="people-follow-fan-div-right">
                                <p class="people-follow-fan-title">关注者</p>
                                <p class="people-follow-fan-num" id="hasFanNum">${sessionScope.peopleEntity.followerNum}</p>
                            </div>
                        </a>
                    </div>
                    <!-- 仿知乎关注与被关注结束 -->

                    <!-- 关注的名家 -->
                    <div class="people-follow-giants">
                        <p class="category-title"><a class="title-a">>> 关注的名家</a></p>
                        <div class="people-follow-giant-list row">

                            <c:forEach items="${sessionScope.peopleEntity.followingGiants}" var="giant" varStatus="vs">
                                <c:if test="${vs.count == 4}">
                                    <div class="my-hide" id="more-giant">
                                </c:if>
                                    <div class="people-follow-giant-item col-xs-4">
                                        <c:url value="/giant.action" var="giantUrl">
                                            <c:param name="giantId" value="${giant.id}"/>
                                        </c:url>
                                        <a href="${giantUrl}"><img src="${giant.imgPath}" class="giant-img"></a>
                                        <p class="giant-name"><a href="${giantUrl}" class="index-a">${giant.name}</a></p>
                                    </div>
                                    <c:if test="${vs.last}">
                                        <c:if test="${vs.count >= 4}">
                                            </div>
                                            <p class="people-follow-giant-more-p"><span class="people-follow-giant-more index-a" id="all-giant" state="false">展开全部&nbsp;&nbsp;<i class="fas fa-arrow-down"></i></span></p>
                                        </c:if>
                                    </c:if>
                            </c:forEach>

                        </div>

                    </div>
                    <!-- 关注的名家结束 -->

                    <!-- 喜欢的作品 -->
                    <div class="people-love-books">
                        <p class="category-title"><a class="title-a">>> 喜欢的作品</a></p>
                        <div class="people-love-book-list row">

                            <c:forEach items="${sessionScope.peopleEntity.loveOrigins}" var="origin" varStatus="vs">
                                <c:if test="${vs.count == 4}">
                                    <div class="my-hide" id="more-books">
                                </c:if>
                                <div class="people-love-book-item col-xs-4">
                                    <c:url value="/origin.action" var="originUrl">
                                        <c:param name="originId" value="${origin.id}"/>
                                    </c:url>
                                    <a href="${originUrl}"><img src="${origin.imgPath}" class="giant-img"></a>
                                    <p class="giant-name"><a href="#" class="index-a">${origin.name}</a></p>
                                </div>


                                <c:if test="${vs.last}">
                                    <c:if test="${vs.count >= 4}">
                                        </div>
                                        <p class="people-follow-giant-more-p"><span class="people-follow-giant-more index-a" id="all-books" state="false">展开全部&nbsp;&nbsp;<i class="fas fa-arrow-down"></i></span></p>
                                    </c:if>
                                </c:if>
                            </c:forEach>

                        </div>
                    </div>
                    <!-- 喜欢的作品结束 -->

                </div>
            </div>
        </div>
        <!-- 中间一大部分用户资料结束 -->
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
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrPeople.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrSentenceInfo.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/collect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/people.js"></script>

</body>
</html>
