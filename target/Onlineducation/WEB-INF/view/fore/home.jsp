<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/Onlineducation/style/images/favicon.ico" rel="SHORTCUT ICON">
<title>优账云财税 --好用+方便+免费--</title>
<link href="/Onlineducation/style/css/newindex.css" rel="stylesheet">
<script src="/Onlineducation/style/js/jquery.min.js"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<!--banner-->
<div class="content3">
	<!--  <div class="main-icon">
        <img src="../images/educationbanner.png"/> 
   	</div> -->
	<div class="hrefnew">
		<a href="newtitle.html" class="tiaozhuan"></a> <a
			href="newtitle.html" class="yuan"><img
			src="/Onlineducation/style/images/images/69rmb.png" /></a>
	</div>
</div>

<!--main-content-->
<div class="body_bg03">
	<div class="main-icon" id="main-top">
		<c:forEach items="${requestScope.categoryList}" var="category">		
			<div class="course-list clearfix" id="float01">				
				<div class="tit">
					<h4>
						${category.name}
					</h4>					
				</div>
				<div class="con clearfix" style="display: block;">
					<ul class="clearfix">
						<c:forEach items="${requestScope.courseList}" var="course" varStatus="index"> 
							<c:if test="${index.index} <= 7">					
								<li>
									<div class="pic">
										<a href="/Onlineducation/goDetail_${course.id}" title="${course.courseName}" target="_blank"> 
											<img src="${course.thumPhotoUrl}" alt="${course.courseName}">
										</a>
									</div>
									<h4>${course.courseName}</h4>
									<div class="price_wrap clearfix">
										<c:choose>
											<c:when test="${course.free}==1">
												<div class="price">免费</div>
											</c:when>
											<c:otherwise>
												<div class="price">￥${course.price}</div>
											</c:otherwise>
										</c:choose>									
										<div class="price">${course.salesVolume}</div>
									</div>
								</li>
							</c:if>	
							<c:otherwise>
								<li class="none">
									<div class="pic">
										<a href="/Onlineducation/goDetail/${course.id}" title="${course.courseName}" target="_blank"> 
											<img src="${course.thumPhotoUrl}" alt="${course.courseName}">
										</a>
									</div>
									<h4>${course.courseName}</h4>
									<div class="price_wrap clearfix">
										<c:choose>
											<c:when test="${course.free}==1">
												<div class="price">免费</div>
											</c:when>
											<c:otherwise>
												<div class="price">￥${course.price}</div>
											</c:otherwise>
										</c:choose>									
										<div class="price">${course.salesVolume}</div>
									</div>
								</li>
							</c:otherwise>								
						</c:forEach>						
					</ul>
					<a href="javascript:void(0)" class="more-btn"><span class="more">展开更多</span><span class="no-more">已展开全部课程</span>(8)<i></i></a>
				</div>
			</div>
		</c:forEach>
	</div>		
	<script>
		$(function() {
			//右侧导航
			$(".leftFix-nav li").hover(function() {
				if ($(this).hasClass("cur")) {
				} else {
					$(this).find(".on-show").stop(true, true).show();
				}
			}, function() {
				if ($(this).hasClass("cur")) {
				} else {
					$(this).find(".on-show").stop(true, true).hide();
				}
			});

			var sTop = $(window).scrollTop();
			var AllHet = $(window).height();
			var mainHet = $('.leftFix-nav').height();
			var fixedTop = (AllHet - mainHet) / 2;
			$('.leftFix-nav li a').click(function() {
				var ind = $(this).parents("li").index() + 1;
				var topVal = $('#float0' + ind).offset().top;
				$('body,html').animate({
					scrollTop : topVal
				}, 1000)
			})

			//吸顶模式
			if ($(".leftFix-nav").length > 0) {
				var showHeight = $("#main-top").offset().top;
				$(window).bind("scroll", function() {
					var sTop = $(document).scrollTop();
					if (sTop >= showHeight) {
						$('.leftFix-nav').fadeIn();
					} else {
						$('.leftFix-nav').fadeOut();
					}
				});
			}
			//课程列表展开收起
			$(".course-list").on("click", ".more-btn", function() {
				var _this = $(this).prev().find("li.none");
				if (_this.is(":hidden")) {
					_this.fadeIn();
					$(this).removeClass("on");
				} else {
					_this.hide();
					$(this).addClass("on");
				}
			});

			//左侧悬浮显示隐藏
			if ($(".leftFix-nav li").length <= 100) {
				$(".leftFix-nav").css("display", "none");
			} else {
				$(".leftFix-nav").css("display", "block");
			}

		});
	</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>