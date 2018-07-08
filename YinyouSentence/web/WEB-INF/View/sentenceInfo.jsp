<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-07
  Time: 19:33
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
    <title>佳句赏析——吟游佳句</title>

    <link rel="icon" href="/imgs/sys/icon_2.png" type="image/x-icon"/>

    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="./css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="./css/select-color.css">
    <link rel="stylesheet" type="text/css" href="./css/animate.css">
    <link rel="stylesheet" type="text/css" href="./css/layx.min.css">
    <link rel="stylesheet" type="text/css" href="./css/nav.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/back-to-top.css">
    <link rel="stylesheet" type="text/css" href="./css/sentenceInfo.css">

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
        <div class="row">

            <!-- 左边版块 -->
            <div class="col-xs-8 sentence-left-side">
                <!-- 句子核心块 -->
                <div class="sentence-info-div">

                    <p class="sentence-title">~~ 佳句赏析 ~~</p>
                    <div class="sentence-content-div">
                        <h4 class="sentence-content">丢失的日子如融化在人群里的好姑娘，我看着她沿途美丽下去，嫁给别人</h4>
                        <p class="sentence-from">——&nbsp;<a href="#" class="index-a">叶三</a>&nbsp;&nbsp;<a href="#" class="index-a">《九万字》</a></p>
                    </div>

                    <div>
                        <p class="sentence-publisher-p"><a href="#" class="index-a">吟游诗人</a>发布于<span id="random-publish-time">2018年12月12日</span></p>
                    </div>


                    <!-- 句子标签 -->
                    <div class="sentence-tag-div">
                        <p class="tag-p"><i class="fas fa-tags tag-i"></i>标签：<span class="sentence-tag-list">
								<a href="#" class="index-a">#姑娘</a>
								<a href="#" class="index-a">#时光</a>
								<a href="#" class="index-a">#日子</a>
								<a href="#" class="index-a">#诗意</a>
								<a href="#" class="index-a">#人生</a>
								<a href="#" class="index-a">#梦想</a>
								</span></p>
                    </div>

                    <!-- 设置喜欢和收藏 -->
                    <div class="sentence-operate">
                        <span class="sentence-love index-a"><img src="./imgs/love.png" title="喜欢" id="love-img" state="false"> 喜欢 (1820)</span>

                        <span class="sentence-collect recommend-collect"><i class="far fa-bookmark"></i> 收藏到句子集</span>
                        <a class="sentence-to-comment index-a cm" href="#comment"><i class="far fa-comment"></i> 评论</a>
                    </div>

                    <!-- 举报这条句子 -->
                    <a href="#" class="sentence-accuse">有辱斯文？举报！</a>


                </div>
                <!-- 句子核心块结束 -->

                <!-- 查看句子评论 -->
                <div class="sentence-comment-div">
                    <p class="sentence-title">~~ 心得交流 ~~</p>

                    <!-- 	<div class="no-comment-show">
                            <center><a href="#comment" class="index-a cm">还没有评论哦，赶紧去发布一条吧</a></center>
                        </div> -->


                    <!-- 评论列表 -->
                    <div class="comment-list">

                        <!-- 评论一条 -->
                        <div class="comment-item">
                            <a href="#" class="comment-item-img-a"><img src="./imgs/海子.jpg" class="comment-item-img"></a>
                            <div class="comment-item-right">
                                <!-- 评论头部用户信息 -->
                                <div class="comment-item-head">
                                    <a class="comment-item-name index-a" href="#" id="comment-author-1">海子</a>
                                    <span class="comment-user-motto">(灯寂人初灭，月影杳萧墙)</span>
                                    <a class="comment-reply-a cm" href="#comment" name="1">回复</a>
                                    <p class="comment-item-time">2018-6-12 20:15:24</p>

                                </div>
                                <!-- 评论内容 -->
                                <div class="comment-item-content" id="comment-content-1">
                                    丢失的日子如融化在人群里的好姑娘，我看着她沿途美丽下去，嫁给别人。
                                    而那些风里雨里过不去的日子啊，总会消散在赤练彩虹桥之前，美好即将到来。
                                </div>

                            </div>
                        </div>
                        <!-- 评论一条结束 -->

                        <!-- 评论一条 -->
                        <div class="comment-item">
                            <a href="#" class="comment-item-img-a"><img src="./imgs/海子.jpg" class="comment-item-img"></a>
                            <div class="comment-item-right">
                                <!-- 评论头部用户信息 -->
                                <div class="comment-item-head">
                                    <a class="comment-item-name index-a" href="#" id="comment-author-2">海子</a>
                                    <span class="comment-user-motto">(灯寂人初灭，月影杳萧墙)</span>
                                    <a class="comment-reply-a cm" href="#comment" name="2">回复</a>
                                    <p class="comment-item-time">2018-6-12 20:15:24</p>

                                </div>
                                <!-- 评论内容 -->
                                <div class="comment-item-content" id="comment-content-2">
                                    丢失的日子如融化在人群里的好姑娘，我看着她沿途美丽下去，嫁给别人。
                                    而那些风里雨里过不去的日子啊，总会消散在赤练彩虹桥之前，美好即将到来。
                                </div>

                            </div>
                        </div>
                        <!-- 评论一条结束 -->

                        <!-- 评论一条 -->
                        <div class="comment-item">
                            <a href="#" class="comment-item-img-a"><img src="./imgs/海子.jpg" class="comment-item-img"></a>
                            <div class="comment-item-right">
                                <!-- 评论头部用户信息 -->
                                <div class="comment-item-head">
                                    <a class="comment-item-name index-a" href="#" id="comment-author-3">海子</a>
                                    <span class="comment-user-motto">(灯寂人初灭，月影杳萧墙)</span>
                                    <a class="comment-reply-a cm" href="#comment" name="3">回复</a>
                                    <p class="comment-item-time">2018-6-12 20:15:24</p>
                                </div>
                                <!-- 评论内容 -->
                                <div class="comment-item-content" id="comment-content-3">
                                    丢失的日子如融化在人群里的好姑娘，我看着她沿途美丽下去，嫁给别人。
                                    而那些风里雨里过不去的日子啊，总会消散在赤练彩虹桥之前，美好即将到来。
                                </div>

                                <!-- 一条评论的回复 -->
                                <div class="comment-reply-list">
                                    <a class="comment-reply-name index-a" href="#" id="comment-author-4">吟游诗人</a>:
                                    &nbsp;回复 <a href="#" class="index-a comment-reply-object">海子</a>:
                                    &nbsp;<span class="comment-reply-content" id="comment-content-4">大师兄说得对</span>
                                    <div class="comment-reply-operate">
                                        <span class="comment-reply-time">2018-6-5 12:05:26</span>
                                        <a href="#comment" class="index-a comment-reply-a comment-reply-response-a cm" name="4">回复</a>
                                    </div>
                                </div>
                                <!-- 一条评论的回复结束 -->
                                <!-- 一条评论的回复 -->
                                <div class="comment-reply-list">
                                    <a class="comment-reply-name index-a" href="#" id="comment-author-5">吟游诗人</a>:
                                    &nbsp;回复 <a href="#" class="index-a comment-reply-object">海子</a>:
                                    &nbsp;<span class="comment-reply-content" id="comment-content-5">大师兄说得对</span>
                                    <div class="comment-reply-operate">
                                        <span class="comment-reply-time">2018-6-5 12:05:26</span>
                                        <a href="#comment" class="index-a comment-reply-a comment-reply-response-a cm" name="5">回复</a>
                                    </div>
                                </div>
                                <!-- 一条评论的回复结束 -->
                                <!-- 一条评论的回复 -->
                                <div class="comment-reply-list">
                                    <a class="comment-reply-name index-a" href="#" id="comment-author-6">吟游诗人</a>:
                                    &nbsp;回复 <a href="#" class="index-a comment-reply-object">海子</a>:
                                    &nbsp;<span class="comment-reply-content" id="comment-content-6">大师兄说得对</span>
                                    <div class="comment-reply-operate">
                                        <span class="comment-reply-time">2018-6-5 12:05:26</span>
                                        <a href="#comment" class="index-a comment-reply-a comment-reply-response-a cm" name="6">回复</a>
                                    </div>
                                </div>
                                <!-- 一条评论的回复结束 -->


                            </div>
                        </div>
                        <!-- 评论一条结束 -->

                    </div>
                    <!-- 评论列表结束 -->


                </div>
                <!-- 查看句子评论结束 -->

                <!-- 发布评论 -->
                <div class="publish-comment" id="comment">
                    <p class="sentence-title">~~ 发布评论 ~~</p>
                    <form class="comment-input-form" action="comment.action" method="post">
                        <p class="comment-reply-head">回复： @海子 "谢谢啦"
                            <span class="comment-cancel index-a" id="comment-cancel" onclick="cancelComment();"><i class="far fa-times-circle"></i>取消回复</span>
                        </p>
                        <div class="form-group">
                            <textarea class="form-control" rows="5" id="comment-content"></textarea>
                        </div>
                        <button class="btn btn-info comment-publish-btn">发布评论</button>
                        <div style="clear: both;"></div>
                    </form>
                </div>
                <!-- 发布评论结束 -->




            </div>
            <!-- 左边版块结束 -->

            <!-- 句子右边版块 -->
            <div class="col-xs-4 sentence-right-side">

                <!-- 作者信息 -->
                <div class="author-info">
                    <center><a href="#"><img src="./imgs/王小波.jpg" class="author-info-img" title="王小波"></a></center>
                    <center><a class="author-name title-a" href="#">王小波</a></center>
                    <div class="author-introduce">
                        <span class="author-introduce-title">作者简介：</span><a class="author-introduce-content" href="#">王小波 （1952年5月13日－1997年4月11日），北京人，作家。年轻时在云南农场作过知青，插过队，作过工人、老师。1978年至1982年在中国人民大学学习。1984年赴美。1988年获匹兹堡大学硕士学位。后任教于北京大学和中国人民大学。1992年后开始成为自由撰稿人。1997年4月11日因心脏病突发逝世于北京。 </a>
                    </div>
                    <div class="author-other-sentence">
                        <a href="#" class="title-a more-sentence-title">>>更多王小波的句子</a>
                        <!-- 一条更多句子 -->
                        <div class="more-item">
                            <i class="fas fa-chevron-right"></i> <a href="#" class="index-a">人的一切痛苦，本质上都是对自己的无能的愤怒。</a>
                        </div>
                        <!-- 一条更多句子结束 -->
                        <!-- 一条更多句子 -->
                        <div class="more-item">
                            <i class="fas fa-chevron-right"></i> <a href="#" class="index-a">我把我整个灵魂都给你，连同它的怪癖，耍小脾气，忽明忽暗，一千八百种坏毛病。它真讨厌，只有一点好，爱你。</a>
                        </div>
                        <!-- 一条更多句子结束 -->
                        <!-- 一条更多句子 -->
                        <div class="more-item">
                            <i class="fas fa-chevron-right"></i> <a href="#" class="index-a">不愿清醒，宁愿一直沉迷放纵。 不知归路，宁愿一世无悔追逐。</a>
                        </div>
                        <!-- 一条更多句子结束 -->

                    </div>
                </div>
                <!-- 作者简介结束 -->

                <!-- 喜欢该句子的人 -->
                <div class="love-this-sentence-div">
                    <p class="love-this-sentence-title"><a href="#" class="title-a">>> 这些人也喜欢这个句子</a></p>
                    <!-- 喜欢的一个用户 -->
                    <div class="love-client-item">
                        <a href="#"><img src="./imgs/鲁迅.jpg"></a>
                        <div class="love-client-right">
                            <a href="#" class="index-a love-client-name ">李白</a>
                            <span class="love-span-intr">湖南/男</span><br>
                            <button class="btn-follow" UID="1"><i class="fas fa-plus"></i> 关注</button>
                            <!-- <p class="fan-client" UID="1">+ 关注</p> -->
                        </div>
                    </div>
                    <!-- 喜欢的一个用户结束 -->

                    <!-- 没人喜欢这个句子 -->
                    <p class="love-client-nobody">还没有人喜欢这个句子哦...</p>

                </div>
                <!-- 喜欢该句子的人结束 -->

            </div>
            <!-- 右边部分结束 -->


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



<script type="text/javascript" src="./js/layx.min.js"></script>
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/nav.js"></script>
<script type="text/javascript" src="./js/toCollect.js"></script>
<script type="text/javascript" src="./js/sentenceInfo.js"></script>



</body>
</html>
