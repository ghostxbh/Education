<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/Onlineducation/style/images/favicon.ico" rel="SHORTCUT ICON">
<title>优账云财税学院  - ${requestScope.searchName} </title>
<link href="/Onlineducation/style/css/fore/newindex.css" rel="stylesheet">
<script src="/Onlineducation/style/js/jquery.min.js"></script>
</head>
<style>
	.content_div{	
		min-height: 700px;
	    width: 1200px;
	    margin: 0 auto;
	    background-repeat: no-repeat;
	    background-position: center top;
    }
</style>
<body>
<%@ include file="../common/header.jsp" %>
<!--content-->
<div class="content_div">
	<div class="con clearfix" style="display: block;">
		<ul class="clearfix">			
			<c:forEach items="${requestScope.couseList}" var="course" varStatus="index">			
				<li>
					<div class="pic">
						<a href="/Onlineducation/fore/${course.id}" title="${course.courseName}" target="_blank"> 
							<img src="${course.thumPhotoUrl}" alt="${course.courseName}">
						</a>
					</div>
					<h4>${course.courseName}</h4>
					<div class="price_wrap clearfix">
						<c:choose>
							<c:when test="${course.free}=='1'">
								<div class="price">免费</div>
							</c:when>
							<c:otherwise>
								<div class="price">￥${course.price}</div>
							</c:otherwise>
						</c:choose>									
						<div class="price" style="float:right;"><span>销量:</span>${course.salesVolume}</div>
					</div>
				</li>																				
			</c:forEach>									
		</ul>		
	</div>
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