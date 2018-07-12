<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-07
  Time: 10:45
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
    <title>发布句子——吟游佳句</title>

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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/publishSentence.css">
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
    <div class="container publish-div">
        <p class="giant-title">~~ 发布句子 ~~</p>

        <div class="sentence-area">
            <form class="sentence-input" method="post" action="/doPublishSentence.action">
                <label>收录到句子集：</label>
                <div class="collect-select-div">
                    <select class="collect-select" name="collectSelect">
                        <option value="0">--- 请选择句子集 ---</option>
                        <c:forEach  var="clt" items="${sessionScope.publishSentenceEntity.sentenceCollections}">
                            <option value="${clt.id}">${clt.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-sentence">
                    <textarea name="sentenceContent" placeholder="请输入句子，最多输入512个字符" class="textarea-sentence" maxlength="512" id="sentenceContent"></textarea>
                </div>

                <div class="sentence-from">
                    <div class="sentence-write-type">
                        <label>是否原创： <input type="checkbox" name="original" id="sentence-original"></label>
                        <span class="original-tip">
                            <c:if test="${sessionScope.userInfo.gender == '男'}">
                                (公子，请保证是您原创的才能勾选呦，否则我们可能会送您封号套餐哒)
                            </c:if>
                            <c:if test="${sessionScope.userInfo.gender == '女'}">
                                (小姐，请保证是您原创的才能勾选呦，否则我们可能会送您封号套餐哒)
                            </c:if>
                        </span>
                    </div>
                    <div class="sentence-not-original">
                        <div class="sentence-author">
                            <label>作者：<input type="text" name="authorName" class="input-style"></label>
                        </div>
                        <div class="sentence-original">
                            <label>出处：<input type="text" name="bookName" class="input-style"></label>
                        </div>
                    </div>

                    <div class="category">
                        <label>句子类别：</label>
                        <div class="collect-select-div">
                            <select class="collect-select" name="categorySelect">
                                <option value="0">--- 请选择类别 ---</option>
                                <c:forEach items="${sessionScope.publishSentenceEntity.categories}" var="category">
                                    <option value="${category.id}">${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="tags">
                        <p><span class="tag-title">句子标签：</span><span class="tag-info">(多个标签请用空格隔开，最多5个标签)</span></p>
                        <input type="text" name="tagInput" class="input-style input-tag" id="input-tag">
                        <p class="hot-tags"><span class="hot-tag-title">热门标签：</span>
                            <c:forEach items="${sessionScope.publishSentenceEntity.hotTags}" var="tag">
                                <span class="hot-tag" state="false">${tag.name}</span>
                            </c:forEach>
                        </p>
                    </div>
                </div>

                <div class="submit-btn-div">
                    <input type="submit" name="submit" class="btn btn-primary submit-btn" value="提交" id="submit-btn">
                </div>

            </form>
        </div>
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


<script type="text/javascript" src="<%=request.getContextPath()%>/js/layx.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/toCollect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/publishSentence.js"></script>



</body>
</html>
