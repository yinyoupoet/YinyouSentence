<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-07-10
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/collect.css">
</head>
<body>
<h2 style="margin-left: 20px;">
    选择句子集
</h2>
<form class="collect-form">
    <div class="collcet-list">
        <label class="collect-package" ><input type="checkbox" name="sentence" checked="checked"/>默认收藏夹</label>
        <label class="collect-package" ><input type="checkbox" name="sentence"/>好句子</label>
        <label class="collect-package" ><input type="checkbox" name="sentence"/>古诗词</label>
        <label class="collect-package" ><input type="checkbox" name="sentence"/>名句</label>
    </div>

    <div class="collect-new-div">
        <span class="collect-new">+新建句子集</span>

    </div>
</form>



<button class="btn btn-info collect-btn">收藏</button>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/collect.js"></script>
</body>
</html>
