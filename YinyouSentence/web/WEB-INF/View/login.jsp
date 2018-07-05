<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-05
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- https://www.cnblogs.com/baiyii/p/6973437.html  ,使用@media -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
    <title>吟游佳句——登录</title>
    <link rel="icon" href="<%=request.getContextPath() %>/imgs/icon_2.png" type="image/x-icon"/>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css">

</head>
<body>

<div class="content">
    <center>

        <!-- 标题块 -->
        <div class="login-register gradient-border">
            <h4>~~ 吟游佳句 ~~</h4>

            <!-- 登录 -->
            <div class="login animated">
                <h2>登录</h2>
                <form  action="/login" method="post">
                    <input type="text" name="lUserName" class="login-input gradient-border" id="login-name" placeholder="(*/ω＼*)账号">

                    <input type="password" name="lUserPwd" class="login-input gradient-border" id="login-pwd" placeholder="(*/ω＼*)密码">
                    <br><span class="login-error"></span><br>

                    <button type="submit" class="btn btn-success login-btn">登录</button>
                    <br><br>
                    <a class="turn-register">没有账号？立即注册</a>
                </form>
            </div>

            <!-- 注册 -->
            <div class="register animated">
                <h2>注册</h2>
                <form  action="/register" method="post">
                    <input type="text" name="rUserName" class="login-input gradient-border" id="register-name" placeholder="请输入用户名">

                    <input type="password" name="rUserPwd" class="login-input gradient-border" id="register-pwd" placeholder="(*/ω＼*)密码">

                    <input type="password" name="rUserConfirmPwd" class="login-input gradient-border" id="register-confirm-pwd" placeholder="确认密码">

                    <br><span class="register-error"></span><br>

                    <button type="submit" class="btn btn-success register-btn">注册</button>
                    <br><br>
                    <a class="turn-login">已有账号？进行登录</a>
                </form>
            </div>

        </div>
    </center>
</div>



<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrLogin.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/dwrRegister.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/login.js"></script>
</body>
</html>
