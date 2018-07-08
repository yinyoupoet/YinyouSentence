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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layx.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/nav.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-to-top.css">
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
                <li><a href="${selfUrl}" class="info-link">吟游诗人</a></li>
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
            <p class="random-next"><i class="fas fa-sync"></i> 换一句</p>
            <h3 id="random-content">丢失的日子如融化在人群里的好姑娘，我看着她沿途美丽下去，嫁给别人</h3>
            <div>
                <p id="random-publisher-p"><span id="random-publisher"><a href="#">吟游诗人</a></span>发布于<span id="random-publish-time">2018年12月12日</span></p>
                <p id="random-from">——&nbsp;<span id="random-author"><a href="#">叶三</a></span>&nbsp;&nbsp;<span id="random-orient"><a href="#">《九万字》</a></span></p>
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
                        <a href="#" class="index-a">
                            <div class="col-xs-3">
                                <img src="./imgs/鲁迅.jpg" class="giant-img">
                                <p class="giant-name">鲁迅</p>
                            </div>
                        </a>
                        <a href="#" class="index-a">
                            <div class="col-xs-3">
                                <img src="./imgs/王小波.jpg" class="giant-img">
                                <p class="giant-name">王小波</p>
                            </div>
                        </a>
                        <a href="#" class="index-a">
                            <div class="col-xs-3">
                                <img src="./imgs/三毛.jpg" class="giant-img">
                                <p class="giant-name">三毛</p>
                            </div>
                        </a>
                        <a href="#" class="index-a">
                            <div class="col-xs-3">
                                <img src="./imgs/海子.jpg" class="giant-img">
                                <p class="giant-name">海子</p>
                            </div>
                        </a>
                    </div>
                </div>
                <!-- 热门名人结束 -->

                <!-- 推荐句子 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 推荐句子 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 更多</a></p>

                    <!-- 一条推荐句子 -->
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
                    <!-- 一条推荐句子结束 -->

                    <!-- 一条推荐句子 -->
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
                    <!-- 一条推荐句子结束 -->


                </div>
                <!-- 推荐句子结束 -->



                <!-- 热门句子 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 热门句子排行榜 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 全部</a></p>

                    <!-- 一条热门句子 -->
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
                    <!-- 一条热门句子结束 -->

                    <!-- 一条热门句子 -->
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
                    <!-- 一条热门句子结束 -->


                </div>
                <!-- 热门句子结束 -->



                <!-- 热门原创 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 热门原创 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 更多</a></p>

                    <!-- 一条热门原创 -->
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
                    <!-- 一条热门原创结束 -->

                    <!-- 一条热门原创 -->
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
                    <!-- 一条热门原创结束 -->


                </div>
                <!-- 热门原创结束 -->


                <!-- 最新发布 -->
                <div class="recommend-div content-left-box">
                    <p class="giant-title">~~ 最新发布 ~~</p>
                    <p class="giant-more"><a href="#" class="index-a"><i class="fab fa-gitkraken"></i> 更多</a></p>

                    <!-- 一条最新发布 -->
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
                    <!-- 一条最新发布结束 -->

                    <!-- 一条最新发布 -->
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
                    <!-- 一条最新发布结束 -->


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
                        <a href="#" class="index-a category-item">散文/随笔</a>
                        <a href="#" class="index-a category-item">书籍</a>
                        <a href="#" class="index-a category-item">歌词</a>
                        <a href="#" class="index-a category-item">诗歌</a>
                        <a href="#" class="index-a category-item">古诗</a>
                        <a href="#" class="index-a category-item">宋词</a>
                        <a href="#" class="index-a category-item">格言</a>
                        <a href="#" class="index-a category-item">心语</a>
                        <a href="#" class="index-a category-item">古风句子</a>
                        <a href="#" class="index-a category-item">经典语录</a>
                        <a href="#" class="index-a category-item">名人名言</a>
                        <a href="#" class="index-a category-item">英语名言</a>
                        <a href="#" class="index-a category-item">小说摘抄</a>
                        <a href="#" class="index-a category-item">动漫语录</a>
                        <a href="#" class="index-a category-item">网友原创</a>
                        <a href="#" class="index-a category-item">电影台词</a>
                        <a href="#" class="index-a category-item">电视剧台词</a>
                        <a href="#" class="index-a category-item">古文名句</a>
                    </div>
                    <br>
                    <p class="category-title"><a href="#" class="title-a">>> 热门标签</a></p>
                    <div class="category-list">
                        <a href="#" class="index-a category-item">唯美</a>
                        <a href="#" class="index-a category-item">清新</a>
                        <a href="#" class="index-a category-item">优美</a>
                        <a href="#" class="index-a category-item">伤感</a>
                        <a href="#" class="index-a category-item">心痛</a>
                        <a href="#" class="index-a category-item">治愈</a>
                        <a href="#" class="index-a category-item">爱情</a>
                        <a href="#" class="index-a category-item">励志</a>
                        <a href="#" class="index-a category-item">骄傲</a>
                        <a href="#" class="index-a category-item">文艺</a>
                        <a href="#" class="index-a category-item">正能量</a>
                        <a href="#" class="index-a category-item">忧伤</a>
                        <a href="#" class="index-a category-item">思念</a>
                        <a href="#" class="index-a category-item">友情</a>
                        <a href="#" class="index-a category-item">成功</a>
                    </div>
                </div>
                <!-- 句子热门标签+分类块结束 -->


                <!-- 热门句子集 -->
                <div class="hot-list-div content-right-box">
                    <p class="category-title"><a href="#" class="title-a">>> 热门句子集</a></p>

                    <!-- 一条热门句子集 -->
                    <div class="hot-list-item">
                        <i class="far fa-bookmark"></i> <a href="#" class="index-a hot-item-title">黑色幽默</a>(收录了<span class="hot-item-num">35</span>条句子)
                    </div>
                    <!-- 一条热门句子集结束 -->

                    <!-- 一条热门句子集 -->
                    <div class="hot-list-item">
                        <i class="far fa-bookmark"></i> <a href="#" class="index-a hot-item-title">经典书摘</a>(收录了<span class="hot-item-num">20</span>条句子)
                    </div>
                    <!-- 一条热门句子集结束 -->

                    <!-- 一条热门句子集 -->
                    <div class="hot-list-item">
                        <i class="far fa-bookmark"></i> <a href="#" class="index-a hot-item-title">人生若只如初见</a>(收录了<span class="hot-item-num">41</span>条句子)
                    </div>
                    <!-- 一条热门句子集结束 -->

                    <!-- 一条热门句子集 -->
                    <div class="hot-list-item">
                        <i class="far fa-bookmark"></i> <a href="#" class="index-a hot-item-title">作文素材</a>(收录了<span class="hot-item-num">58</span>条句子)
                    </div>
                    <!-- 一条热门句子集结束 -->

                    <!-- 一条热门句子集 -->
                    <div class="hot-list-item">
                        <i class="far fa-bookmark"></i> <a href="#" class="index-a hot-item-title">美好的天气</a>(收录了<span class="hot-item-num">66</span>条句子)
                    </div>
                    <!-- 一条热门句子集结束 -->

                    <!-- 一条热门句子集 -->
                    <div class="hot-list-item">
                        <i class="far fa-bookmark"></i> <a href="#" class="index-a hot-item-title">吟唱天地的不朽</a>(收录了<span class="hot-item-num">99</span>条句子)
                    </div>
                    <!-- 一条热门句子集结束 -->


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

<script type="text/javascript" src="<%=request.getContextPath()%>/js/layx.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/toCollect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
</body>
</html>
