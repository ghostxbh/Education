<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link href="/Onlineducation/style/images/favicon.ico" rel="SHORTCUT ICON">
<link rel="stylesheet" href="/Onlineducation/style/css/common/header.css" >
<link rel="stylesheet" href="/Onlineducation/style/css/common/iconn.css">
<script src="/Onlineducation/style/js/jquery-1.11.3.min.js"></script>
<script src="/Onlineducation/style/bootstrap/js/bootstrap.min.js"></script>
<script src="/Onlineducation/style/layui/layui.js"></script>
	
<meta name="Keywords"
	content="优账云 优账云财税 优账云财务软件 免费财务软件 财务软件免费版 记账软件 财务软件" />
<meta name="Description"
	content="优账云是遥遥领先的专业财务软件，承诺所有功能永久免费！更有众多财税服务及会计精品课程、财税书籍、专家服务，帮助会计人员提升。" />
</head>
<body>
	<script type="text/javascript">
		function getRootPath() {
			var curPath = window.document.location.href;
			var pathName = window.document.location.pathname;
			var pos = curPath.indexOf(pathName);
			var localhostPath = curPath.substring(0, pos);
			//var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
			return localhostPath;
		}
		$(document).ready(function() {
			checkLogin();

		});

		function checkLogin() {
			$.ajax({
				url : getRootPath() + "/Onlineducation/checkLogin",
				type : "POST",
				data : {},
				traditional : true,
				success : function(result) {
					if (result.state == 1) {
						//alert("登录了");
						var username = result.data.user.name;
						$("#container_login").empty();
						var html = "<span> Hi," + username + "</span>";
						html += "<a href='" + getRootPath()
								+ "/AccountingOnline/logout'>退出</a>"
						$("#container_login").append(html);
					} else {
						$.ajax({
							type : 'get',
							url : getRootPath()
									+ '/AccountingOnline/user/getuseraway',
							dataType : 'jsonp',
							jsonp : 'jsonpCallback',
							async : false,
							data : {},
							error : function() {
								//alert("0");
							},
							success : function(data) {
								//alert(data.name);
								if (data != null) {
									location.href = getRootPath()
											+ "/AccountingOnline/user/checkLogin?url=Onlineducation/getsign";
								}
							}
						})
					}
				}
			});
		}
	</script>
	<div class="new-top" style="display: block">
		<div id="nav" style="height: 50px;">
			<div class="nav_main">
				<div id="container_login" style="height: 30px;">
					<em>你好，欢迎来到优账财税学院</em> <a
						href="http://www.jkj521.cn/AccountingOnline/user/checkLogin?url=Onlineducation/getsign"
						style="color: #FA0808">请登录</a> <a
						href="http://www.jkj521.cn/AccountingOnline/page/reg">免费注册</a>
					<!-- <a href="" class="userName" target="_blank"></a> -->

				</div>
				<div class="mymall"
					style="height: 50px; list-style: none; float: left;">
					<ul class="quick_li" style="line-height: 30px;">
						<li><a href="http://www.jkj521.cn">优账云首页</a></li>						
					</ul>
				</div>
			</div>
		</div>
		<div id="header">
			<a href="">
				<div class="header-left">
					<a href="/Onlineducation/"><img
						src="/Onlineducation/style/images/images/logo-jkj.png" "/></a>
				</div>
			</a>
			<div class="qt-search-index">
				<div class="qt-container">
					<div class="search-wrap">
						<div class="search-main clearfix">
							<div class="btn-classify">
								<i class="icon icon-p-fenlei"></i>分类
							</div>
							<form action="/Onlineducation/course/search" method="get">
								<div class="fl search-input">
									<div>
										<input type="text" class="fl" placeholder="搜索 课程/名称"
											name="course_name" data-isshow="1" data-where="1">
									</div>
								</div>
								<input type="submit"
									class="qt-btn btn-green-linear btn-search fr" value="搜索"
									style="font-size: 14px; color: #fff;">
							</form>
							<div class="search-result" style="display: none;"></div>
							<div class="search-classify" style="display: none;">
								<dl class="list-box">									
                                    <dd>
                                        <a href="#">会计实训</a>
                                    </dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>

	</div>
	</div>
	</div>
	<div class="top-div clearfix">
		<div class="top clearfix"></div>
	</div>



</body>
<script>
	window.onload = function() {
		$(".btn-classify").click(
				function(e) {
					e.stopPropagation(), $(".search-result").hide(), $(this)
							.siblings(".search-classify").show()
				});
		$(".search-classify dd").mouseover(function() {
			var _index = $(this).index();
			$(this).children('div').css("display", 'block');
		});
		$(".search-classify dd").mouseout(function() {
			var _index = $(this).index();
			$(this).children('div').css("display", 'none');
		});

	}
</script>
</html>






