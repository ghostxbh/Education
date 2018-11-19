<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="include/header.jsp"%>
<head>
    <script src="/Onlineducation/style/js/admin/admin_home.js"></script>
    <link rel="stylesheet" href="/Onlineducation/style/css/admin/admin_home.css"/>
    <link rel="stylesheet" href="/Onlineducation/style/css/admin/admin_main.css"/>      
    <title>优账云财税学院 - 后台管理</title>
</head>
<body>
<div id="div_home_main">
    <%@ include file="include/nav.jsp"%>
    <ul id="ul_home_menu" class="text_info">
        <li class="menu_li menu_li_select" data-toggle="home">
            <i class="fa fa-home"></i><span>管理首页</span>
        </li>
        <li class="menu_li" data-toggle="category">
            <i class="fa fa-list-ul"></i>
            <span>产品分类</span>
        </li>
        <li class="menu_li" data-toggle="course">
            <i class="fa fa-book"></i>
            <span>课程管理</span>
        </li>        
        <li class="menu_li" data-toggle="section">
            <i class="fa fa-file-video-o"></i>
            <span>视频管理</span>
        </li>
        <li class="menu_li" data-toggle="order">
            <i class="fa fa-shopping-cart"></i>
            <span>订单管理</span>
        </li>             
    </ul>
    <div id="div_home_title" class="text_info">
        <span>首页</span>
    </div>
    <div id="div_home_context">
        <div id="div_home_context_main">
            <%@include file="homeManagePage.jsp" %>
        </div>
    </div>
</div>
</body>