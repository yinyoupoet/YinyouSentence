<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-05
  Time: 15:14
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
    <title>吟游佳句</title>

    <link rel="icon" href="/imgs/sys/icon_2.png" type="image/x-icon"/>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select-color.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-left.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-right.css">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/nav.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-to-top.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/collect.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css">

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
        <div class="random-sentence">
            <p class="random-title">~~ 缘来佳句 ~~</p>
            <%--<p class="random-next"><i class="fas fa-sync"></i> 换一句</p>--%>
            <h3 id="random-content">${sessionScope.indexEntity.randomSentenceEntity.sentence.content}</h3>
            <div>
                <c:url value="/toPeople.action" var="rdPeopleUrl">
                    <c:param name="id" value="${sessionScope.indexEntity.randomSentenceEntity.sentence.publisherId}"/>
                </c:url>
                <p id="random-publisher-p"><span id="random-publisher"><a href="${rdPeopleUrl}">${sessionScope.indexEntity.randomSentenceEntity.userInfo.userName}</a></span>发布于<span id="random-publish-time">${sessionScope.indexEntity.randomSentenceEntity.sentence.publishTime}</span></p>
                <p id="random-from">
                    <c:choose>
                        <c:when test="${sessionScope.indexEntity.randomSentenceEntity.original}">
                    <p class="sentence-from" style="text-align:right;">——原创</p>
                    </c:when>
                    <c:when test="${sessionScope.indexEntity.randomSentenceEntity.giantInfo == null && sessionScope.indexEntity.randomSentenceEntity.originInfo == null}">

                    </c:when>
                    <c:otherwise>
                        <p class="sentence-from" style="text-align:right;">——
                            <c:if test="${sessionScope.indexEntity.randomSentenceEntity.giantInfo != null}">
                                <c:url value="/giant.action" var="giantUrl">
                                    <c:param name="giantId" value="${sessionScope.indexEntity.randomSentenceEntity.giantInfo.id}"/>
                                </c:url>
                                &nbsp;<a href="${giantUrl}" class="index-a">${sessionScope.indexEntity.randomSentenceEntity.giantInfo.name}</a>&nbsp;
                            </c:if>
                            <c:if test="${sessionScope.indexEntity.randomSentenceEntity.originInfo != null}">
                                <c:url value="/origin.action" var="originUrl">
                                    <c:param name="originId" value="${sessionScope.indexEntity.randomSentenceEntity.originInfo.id}"/>
                                </c:url>
                                &nbsp;<a href="${originUrl}" class="index-a">${sessionScope.indexEntity.randomSentenceEntity.originInfo.name}</a>
                            </c:if>
                        </p>

                    </c:otherwise>
                </c:choose>


                <%--——&nbsp;<span id="random-author"><a href="#">叶三</a></span>&nbsp;&nbsp;<span id="random-orient"><a href="#">《九万字》</a></span></p>--%>
            </div>
        </div>

        <div class="row content">
            <!-- 左边大块 -->
            <div class="col-xs-8 content-left-side">

                <!-- 热门名人 -->
                <div class="giant-div content-left-box">
                    <p class="giant-title">~~ 热门名人 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 更多</a></p>
                    <div class="row giant-info">
                        <c:forEach items="${sessionScope.indexEntity.hotGiants}" var="hotGiants">
                            <c:url value="giant.action" var="giantUrl">
                                <c:param name="giantId" value="${hotGiants.id}"/>
                            </c:url>
                            <a href="${giantUrl}" class="index-a">
                                <div class="col-xs-3">
                                    <img src="${hotGiants.imgPath}" class="giant-img">
                                    <p class="giant-name">${hotGiants.name}</p>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>

                <!-- 热门名人结束 -->

                <!-- 推荐句子 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 推荐句子 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 更多</a></p>

                    <c:forEach items="${sessionScope.indexEntity.recommendSentenceEntities}" var="recommendSentence">
                        <!-- 一条推荐句子 -->
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
                        <!-- 一条推荐句子结束 -->
                    </c:forEach>


                </div>
                <!-- 推荐句子结束 -->


                <!-- 热门句子 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 热门句子排行榜 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 全部</a></p>



                    <c:forEach items="${sessionScope.indexEntity.hotSentenceEntities}" var="recommendSentence">
                        <!-- 一条热门句子 -->
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
                        <!-- 一条热门句子结束 -->
                    </c:forEach>

                </div>
                <!-- 热门句子结束 -->



                <!-- 热门原创 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 热门原创 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 更多</a></p>




                    <c:forEach items="${sessionScope.indexEntity.hotOriginSentenceEntities}" var="recommendSentence">
                        <!-- 一条热门原创 -->
                        <div class="recommend-item">
                            <c:url value="/sentence.action" var="sentenceUrl">
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
                        <!-- 一条热门原创结束 -->
                    </c:forEach>


                </div>
                <!-- 热门原创结束 -->


                <!-- 最新发布 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 最新发布 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 更多</a></p>


                    <c:forEach items="${sessionScope.indexEntity.newPublishSentenceEntities}" var="recommendSentence">
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


                </div>
                <!-- 最新发布结束 -->




            </div>
            <!-- 左边大块结束 -->

            <!-- 右边大块 -->
            <div class="col-xs-4 content-right-side">

                <!-- 搜索块 -->
                <div class="content-right-search content-right-box">
                    <form class="form-search">
                        <input type="text" name="content-right-search" class="input-medium search-query content-search" placeholder="(*/ω＼*)搜索一下吧">
                    </form>
                </div>
                <!-- 搜索块结束 -->

                <!-- 发布句子 -->
                <div class="content-right-publish ">
                    <form action="/publishSentence.action" method="post">
                        <center><button class="btn publish-btn">~~ 发布句子 ~~</button></center>
                    </form>
                </div>
                <!-- 发布句子块结束 -->


                <!-- 句子热门标签+分类 -->
                <div class="content-right-box content-right-tag">
                    <p class="category-title"><a href="#" class="title-a">>> 句子出自</a></p>
                    <div class="category-list">
                        <c:forEach items="${sessionScope.indexEntity.categories}" var="category">
                            <c:url value="userLove.action" var="categoryUrl">
                                <c:param name="categoryId" value="${category.id}"/>
                            </c:url>
                            <a href="${categoryUrl}" class="index-a category-item">${category.categoryName}</a>
                        </c:forEach>
                    </div>
                    <br>
                    <p class="category-title"><a href="#" class="title-a">>> 热门标签</a></p>
                    <div class="category-list">
                        <c:forEach items="${sessionScope.indexEntity.hotTags}" var="tag">
                            <c:url value="userLove.action" var="tagUrl">
                                <c:param name="tagId" value="${tag.id}"/>
                            </c:url>
                            <a href="${tagUrl}" class="index-a category-item">${tag.name}</a>
                        </c:forEach>
                    </div>
                </div>
                <!-- 句子热门标签+分类块结束 -->


                <!-- 热门句子集 -->
                <div class="hot-list-div content-right-box">
                    <p class="category-title"><a href="#" class="title-a">>> 热门句子集</a></p>

                    <c:forEach items="${sessionScope.indexEntity.hotCollections}" var="hotCollection">
                        <!-- 一条热门句子集 -->
                        <div class="hot-list-item">
                            <c:url value="/collectList.action" var="collectListUrl">
                                <c:param name="collectionId" value="${hotCollection.id}"/>
                            </c:url>
                            <i class="far fa-bookmark"></i> <a href="${collectListUrl}" class="index-a hot-item-title">${hotCollection.name}</a>(收录了<span class="hot-item-num">${hotCollection.sentenceNum}</span>条句子)
                        </div>
                        <!-- 一条热门句子集结束 -->
                    </c:forEach>

                </div>
                <!-- 热门句子集结束 -->



            </div>
            <!-- 右边大块结束 -->
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/collect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
</body>
</html>
