<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-05
  Time: 16:40
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
    <title>编辑个人资料——吟游佳句</title>

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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/edit.css">
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
    <%--<img src="/file/4181f32517ab310698072f9155a401f1.jpg" alt="">--%>
    <div class="container edit-content">
        <form action="/edit" method="post" enctype="multipart/form-data">
            <input type="text" id="head-change" value="false" style="display: none;" name="headChange">
            <div class="head-img">
                <img src="${sessionScope.userInfo.headPath}" id="my-img" title="点击更换头像">
                <div class="change-img-hover" id="change-img-hover">
                    <div class="change-img-title"><p style="text-align: center;">点击修改头像</p></div>
                </div>
                <input type="file" id="img-upload" name="imgUpload" accept="image/gif,image/jpeg,image/jpg,image/png"/>
            </div>

            <!-- 一个输入框 -->
            <div class="text-field">
                <span class="text-field-title">用户名：</span><input type="text" name="userName" placeholder="请输入用户名"  id="userName" value="${sessionScope.userInfo.userName}" class="input-field" maxlength="16">
                <p class="tip tip-warn" id="userName-tip"></p>
                <%--<p class="tip" id="userName-tip">修改用户</p>--%>
            </div>
            <!-- 一个输入框结束 -->
            <!-- 一个输入框 -->
            <div class="text-field" style="margin-top:15px;">
                <span class="text-field-title">修改密码：</span><input type="password" name="userPwd" placeholder="在此输入新密码(6-16个字符)"  id="userPwd" value="" class="input-field" maxlength="16" minlength="6">
                <p class="tip" id="userPwd-tip">如需修改密码，请填写此项</p>
            </div>
            <!-- 一个输入框结束 -->
            <!-- 一个输入框 -->
            <div class="text-field">
                <span class="text-field-title">性别：</span>
                <div class="input-sex">
                    <c:if test="${sessionScope.userInfo.gender == '男'}">
                        <label id="sex-male" >
                            <input type="radio" name="optionsRadios" value="男" class="select-sex" checked>男
                        </label>
                        <label id="sex-female">
                            <input type="radio" name="optionsRadios" class="select-sex" value="女">女
                        </label>
                    </c:if>
                    <c:if test="${sessionScope.userInfo.gender == '女'}">
                        <label id="sex-male" >
                            <input type="radio" name="optionsRadios" value="男" class="select-sex">男
                        </label>
                        <label id="sex-female">
                            <input type="radio" name="optionsRadios" class="select-sex" value="女" checked>女
                        </label>
                    </c:if>

                </div>
            </div>
            <!-- 一个输入框结束 -->

            <!-- 一个输入框 -->
            <div class="text-field">
                <span class="text-field-title">出生时间：</span><input type="date" name="userBirth" class="data-field" value="${sessionScope.birthDate}" max="${sessionScope.nowDate}" min="1900-01-01" required="true">
            </div>
            <!-- 一个输入框结束 -->

            <!-- 一个输入框 -->
            <div class="text-field">
                <span class="text-field-title">个性签名：</span>
                <textarea name="userMotto" placeholder="请输入个性签名" class="textarea-motto" maxlength="64">${sessionScope.userInfo.motto}</textarea>
                <p class="tip" id="userMotto-tip">最多只能输入64个字符</p>
            </div>
            <!-- 一个输入框结束 -->

            <div class="operate-btn">
                <input type="submit" name="提交" class="btn btn-success my-btn" value="提交" id="submit-btn">
                <input type="button" name="取消" class="btn btn-info my-btn" value="返回首页" id="cancel-btn">
            </div>



        </form>
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



<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrRegister.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrEditInfo.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layx.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/toCollect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/edit.js"></script>

</body>
</html>
