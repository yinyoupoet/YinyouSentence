<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-14
  Time: 8:56
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
    <title>搜索：${sessionScope.searchContent}——吟游佳句</title>

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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/search.css">

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



<!-- 详情 -->
<content>
    <div class="container">
        <!-- 搜索部分的头部 -->
        <div class="search-head">
            <!-- 搜索块 -->
            <div class="content-search-div">
                <li class="search-title">搜索：${sessionScope.searchContent}</li>
                <c:url value="search.action" var="searchUrl">
                    <c:param name="searchCategory" value="${param.searchCategory}"/>
                    <c:param name="searchOption" value="${param.searchOption}"/>
                </c:url>
                <form class="form-search" action="${searchUrl}" method="post" >
                    <input type="text" name="searchContent" class="content-search" placeholder="(*/ω＼*)搜索一下吧" value="${sessionScope.searchContent}">
                    <button class="btn btn-info search-submit-btn">查询</button>
                </form>
                    <div class="search-category">
                        搜索类别：
                        <c:url value="/search.action" var="sentenceUrl">
                            <c:param name="searchCategory" value="0"/>
                            <c:param name="searchOption" value="0"/>
                            <c:param name="searchContent" value="${param.searchContent}"/>
                        </c:url>
                        <c:url value="/search.action" var="collectionUrl">
                            <c:param name="searchCategory" value="1"/>
                            <c:param name="searchOption" value="0"/>
                            <c:param name="searchContent" value="${param.searchContent}"/>
                        </c:url>
                        <c:url value="/search.action" var="userUrl">
                            <c:param name="searchCategory" value="2"/>
                            <c:param name="searchOption" value="0"/>
                            <c:param name="searchContent" value="${param.searchContent}"/>
                        </c:url>
                        <c:choose>

                            <c:when test="${sessionScope.category == 0}">
                                <a class="index-a search-select-category" href="${sentenceUrl}">句子</a>
                                <a class="index-a" href="${collectionUrl}">句子集</a>
                                <a class="index-a" href="${userUrl}">用户</a>

                                <c:url value="search.action" var="allOptionUrl">
                                    <c:param name="searchCategory" value="0"/>
                                    <c:param name="searchOption" value="0"/>
                                    <c:param name="searchContent" value="${param.searchContent}"/>
                                </c:url>
                                <c:url value="search.action" var="sentenceOptionUrl">
                                    <c:param name="searchCategory" value="0"/>
                                    <c:param name="searchOption" value="1"/>
                                    <c:param name="searchContent" value="${param.searchContent}"/>
                                </c:url>
                                <c:url value="search.action" var="authorOptionUrl">
                                    <c:param name="searchCategory" value="0"/>
                                    <c:param name="searchOption" value="2"/>
                                    <c:param name="searchContent" value="${param.searchContent}"/>
                                </c:url>
                                <c:url value="search.action" var="orientOptionUrl">
                                    <c:param name="searchCategory" value="0"/>
                                    <c:param name="searchOption" value="3"/>
                                    <c:param name="searchContent" value="${param.searchContent}"/>
                                </c:url>
                                <c:url value="search.action" var="tagOptionUrl">
                                    <c:param name="searchCategory" value="0"/>
                                    <c:param name="searchOption" value="4"/>
                                    <c:param name="searchContent" value="${param.searchContent}"/>
                                </c:url>
                                <div class="search-options">搜索选项：
                                    <c:choose>
                                        <c:when test="${sessionScope.option == 0}">
                                            <a class="index-a search-select-options" href="${allOptionUrl}">全部</a>
                                            <a class="index-a" href="${sentenceOptionUrl}">仅搜索句子部分</a>
                                            <a class="index-a" href="${authorOptionUrl}">仅搜索作者部分</a>
                                            <a class="index-a" href="${orientOptionUrl}">仅搜索出处部分</a>
                                            <a class="index-a" href="${tagOptionUrl}">仅搜索标签部分</a>
                                        </c:when>
                                        <c:when test="${sessionScope.option == 1}">
                                            <a class="index-a" href="${allOptionUrl}">全部</a>
                                            <a class="index-a search-select-options" href="${sentenceOptionUrl}">仅搜索句子部分</a>
                                            <a class="index-a" href="${authorOptionUrl}">仅搜索作者部分</a>
                                            <a class="index-a" href="${orientOptionUrl}">仅搜索出处部分</a>
                                            <a class="index-a" href="${tagOptionUrl}">仅搜索标签部分</a>
                                        </c:when>
                                        <c:when test="${sessionScope.option == 2}">
                                            <a class="index-a" href="${allOptionUrl}">全部</a>
                                            <a class="index-a" href="${sentenceOptionUrl}">仅搜索句子部分</a>
                                            <a class="index-a search-select-options" href="${authorOptionUrl}">仅搜索作者部分</a>
                                            <a class="index-a" href="${orientOptionUrl}">仅搜索出处部分</a>
                                            <a class="index-a" href="${tagOptionUrl}">仅搜索标签部分</a>
                                        </c:when>
                                        <c:when test="${sessionScope.option == 3}">
                                            <a class="index-a" href="${allOptionUrl}">全部</a>
                                            <a class="index-a" href="${sentenceOptionUrl}">仅搜索句子部分</a>
                                            <a class="index-a" href="${authorOptionUrl}">仅搜索作者部分</a>
                                            <a class="index-a search-select-options" href="${orientOptionUrl}">仅搜索出处部分</a>
                                            <a class="index-a" href="${tagOptionUrl}">仅搜索标签部分</a>
                                        </c:when>
                                        <c:when test="${sessionScope.option == 4}">
                                            <a class="index-a" href="${allOptionUrl}">全部</a>
                                            <a class="index-a" href="${sentenceOptionUrl}">仅搜索句子部分</a>
                                            <a class="index-a" href="${authorOptionUrl}">仅搜索作者部分</a>
                                            <a class="index-a" href="${orientOptionUrl}">仅搜索出处部分</a>
                                            <a class="index-a search-select-options" href="${tagOptionUrl}">仅搜索标签部分</a>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:when>
                            <c:when test="${sessionScope.category == 1}">
                                <a class="index-a" href="${sentenceUrl}">句子</a>
                                <a class="index-a search-select-category" href="${collectionUrl}">句子集</a>
                                <a class="index-a" href="${userUrl}">用户</a>
                            </c:when>
                            <c:when test="${sessionScope.category == 2}">
                                <a class="index-a" href="${sentenceUrl}">句子</a>
                                <a class="index-a" href="${collectionUrl}">句子集</a>
                                <a class="index-a search-select-category" href="${userUrl}">用户</a>
                            </c:when>
                        </c:choose>
                    </div>

            </div>
            <!-- 搜索块结束 -->
        </div>
        <!-- 搜索部分头部结束 -->

        <!-- 搜索正文部分 -->
        <div class="search-list">

            <c:choose>

                <c:when test="${sessionScope.category == 0}">
                    <%--句子列表--%>
                    <c:choose>
                        <c:when test="${empty sessionScope.sentenceEntities}">
                            <!-- 没找到的显示 -->
                            <div class="search-list-notFound">
                                抱歉，没有找到任何和 “<span style="color:red;">${sessionScope.searchContent}</span>” 相关的句子<br><br>
                                <b>建议：</b><br>
                                请检查搜索词是否有打错字<br>
                                简化搜索词<br>
                                尝试其他相关词，如同义、近义词等<br><br>
                                <c:url value="publishSentence.action" var="publishSentenceUrl"/>
                                欢迎 <a href="${publishSentenceUrl}" class="index-a">发布</a> 你感兴趣的相关句子
                            </div>
                            <!-- 没找到的显示结束 -->
                        </c:when>

                        <c:otherwise>
                            <c:forEach items="${sessionScope.sentenceEntities}" var="recommendSentence">
                                <!-- 一条最新发布 -->
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
                                <!-- 一条最新发布结束 -->
                            </c:forEach>

                        </c:otherwise>

                    </c:choose>
                </c:when>


                <c:when test="${sessionScope.category == 1}">

                    <c:choose>
                        <c:when test="${empty sessionScope.collectionEntities}">
                            <!-- 没找到的显示 -->
                            <div class="search-list-notFound">
                                抱歉，没有找到任何和 “<span style="color:red;">${sessionScope.searchContent}</span>” 相关的句子集<br><br>
                                <b>建议：</b><br>
                                请检查搜索词是否有打错字<br>
                                简化搜索词<br>
                                尝试其他相关词，如同义、近义词等<br><br>
                            </div>
                            <!-- 没找到的显示结束 -->
                        </c:when>
                        <c:otherwise>
                            <div class="sentence-collection">
                                <c:forEach items="${sessionScope.collectionEntities}" var="collection">
                                    <!-- 一条句子集 -->
                                    <div class="sentence-collect-item">
                                        <c:url value="collectList.action" var="collectUrl">
                                            <c:param name="collectionId" value="${collection.sentenceCollection.id}"/>
                                        </c:url>
                                        <a href="${collectUrl}"><img src="${collection.sentenceCollection.imgPath}" class="sentence-collect-pic"></a>
                                        <div class="sentence-collect-right">
                                            <a href="${collectUrl}" class="index-a sentence-collect-title"><i class="far fa-bookmark"></i>${collection.sentenceCollection.name}</a><span class="sentence-collect-num">(包含${collection.sentenceCollection.sentenceNum}条句子)</span>

                                            <span class="sentence-collect-love index-a" id="sentence-collect-${collection.sentenceCollection.id}" CID="${collection.sentenceCollection.id}">
                                                <c:choose>
                                                    <c:when test="${collection.loveOrNot}">
                                                        <img src="/imgs/sys/love-2.png" title="喜欢" class="love-img" state="true" cId="${collection.sentenceCollection.id}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="/imgs/sys/love.png" title="喜欢" class="love-img" state="false" cId="${collection.sentenceCollection.id}">
                                                    </c:otherwise>
                                                </c:choose>

                                                喜欢 (<span id="collection-love-num">${collection.sentenceCollection.loveNum}</span>)</span>

                                            <c:url value="toPeople.action" var="peopleUrl">
                                                <c:param name="id" value="${collection.publisherInfo.id}"/>
                                            </c:url>
                                            <p class="sentence-collect-publish-info"><a class="index-a sentence-collect-publisher" href="${peopleUrl}">${collection.publisherInfo.userName}</a> 发布于 <span class="sentence-collect-publish-time"><fmt:formatDate value="${collection.sentenceCollection.publishDate}" pattern="yyyy-MM-dd hh:mm:ss"/></span></p>

                                            <p class="sentence-collect-putlish-intro">${collection.sentenceCollection.introduction}</p>

                                        </div>
                                    </div>
                                    <!-- 一条句子集结束 -->
                                </c:forEach>
                            </div>
                        </c:otherwise>

                    </c:choose>
                </c:when>

                <c:when test="${sessionScope.category == 2}">

                    <c:choose>
                        <c:when test="${empty sessionScope.peopleEntities}">
                            <!-- 没找到的显示 -->
                            <div class="search-list-notFound">
                                抱歉，没有找到任何和 “<span style="color:red;">${sessionScope.searchContent}</span>” 相关的句子集<br><br>
                                <b>建议：</b><br>
                                请检查搜索词是否有打错字<br>
                                简化搜索词<br>
                                尝试其他相关词，如同义、近义词等<br><br>
                            </div>
                            <!-- 没找到的显示结束 -->
                        </c:when>
                        <c:otherwise>
                    <!-- 搜索用户 -->
                            <div class="search-user-div">
                                <div class="search-user-list row">
                                    <c:forEach items="${sessionScope.peopleEntities}" var="people">
                                        <!-- 一条用户信息 -->
                                        <div class="search-user-item col-xs-6">
                                            <div class="search-user-item-content">
                                                <c:url value="toPeople.action" var="peopleUrl">
                                                    <c:param name="id" value="${people.userId}"/>
                                                </c:url>
                                                <a href="${peopleUrl}"><img src="${people.headPath}" class="search-user-item-img"></a>
                                                <p class="search-user-name "><a href="${peopleUrl}" class="index-a">${people.userName}</a></p>
                                                <p class="search-user-motto">${people.motto}</p>
                                                <p class="search-user-self-info">${people.birthYear} / ${people.gender}</p>
                                                <p class="search-user-info"><a href="${peopleUrl}" class="index-a serach-user-info-num">关注 ${people.followingNum}</a> / <a class="index-a serach-user-info-num" href="${peopleUrl}">粉丝 <span id="fan-num-${people.userId}">${people.followerNum}</span></a></p>
                                                <p class="search-user-sentence"><a class="index-a search-user-sentence-info" href="${peopleUrl}"><i class="far fa-heart"></i>&nbsp;句子 ${people.publishNum}</a> / <a href="${peopleUrl}" class="index-a search-user-sentence-info"><i class="fas fa-pencil-alt"></i>&nbsp;原创 ${people.originalNum}</a> / <a href="${peopleUrl}" class="index-a search-user-sentence-info"><i class="far fa-bookmark"></i>&nbsp;句子集 ${people.collectionNum}</a></p>
                                                <%--<button class="btn-follow" UID="${people.userId}">
                                                    <c:choose>
                                                        <c:when test="${people.follow}">
                                                            已关注
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="fas fa-plus"></i> 关注
                                                        </c:otherwise>
                                                    </c:choose>
                                                </button>--%>
                                            </div>
                                        </div>
                                        <!-- 一条用户信息结束 -->
                                    </c:forEach>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>

            </c:choose>


            <!-- 搜索结果列表结束 -->
        </div>
        <!-- 搜索正文部分结束 -->
    </div>


</content>
<!-- 详情 -->





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

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/collect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/search.js"></script>
</body>
</html>
