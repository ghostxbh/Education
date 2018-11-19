<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Onlineducation/style/css/fore/courseDetail.css"/>
<link href="/Onlineducation/style/css/fore/detail.css" rel="stylesheet">
<title>${requestScope.course.courseName}</title>
<%@ include file="../common/header.jsp"%>
</head>
<body>
	<div class="content3">		
		<div class="content-con">
			<div class="imgtext-course imgtext-course--free">
				<!--  视频-->
				<div class="img-left--wrap">
					<img class="img-left" src="${requestScope.course.lecturerPortraitUrl}" width="510" height="286"> 
					<div id="js-banner-cover" class="banner-cover"
						style="display: block;">
						<div class="banner-cover-video">
							<a class="banner-cover-play js-expr-btn" href="/Onlineducation/fore/goLearn/${requestScope.course.id}">  
								<i class="icon-play"></i>
							</a>
						</div>
					</div>
				</div>
				<!--视频右侧  -->
				<div class="information fr">
					<p class="title1" style="padding-left: 30px; font-size: 23px;">${requestScope.course.courseName}
					</p>
					<p class="title1" style="font-size: 14px;">
						本视频转载自中华会计网校,网易云课堂,腾讯课堂</p>
					<p class="title1" style="font-size: 15px;">零基础通过初级职称，夯实专业能力，基础会计实操，助你就业无忧！</p>
					<p class="title1" style="font-size: 15px;">
						金九银十季，充电正当时
					</p>
					<c:choose>
						<c:when test="${requestScope.course.free eq 1}">
							<div class="btn_wrap clearfix">
								<a href="javaScript:void(0);" onclick="goLearn(${requestScope.course.id})" class="buy fl">立即学习</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="btn_wrap clearfix">
								<a href="javaScript:void(0);" onclick="goLearn(${requestScope.course.id})" class="buy fl">立即购买</a>
							</div>
						</c:otherwise>	
					</c:choose>						
				</div>
				<div id="js-partner" class="course-partner"></div>
			</div>
			<!-- 课程 -->
			<div class="layout clearfix msf">
				<div class="main_left fl">
					<div class="title">
						<h3>课程内容</h3>
						<div class="more"></div>
					</div>
					<div class="course_con">
						<table cellspacing="0" cellpadding="0" class="biaoge">
							<tbody>
								<tr>
									<td class="t1" width="170">阶段课程</td>
									<td class="t1" width="250">授课名师</td>
									<td class="t1" width="180">课程名称</td>
								</tr>
								<tr>
									<td class="t2">视频授课</td>
									<td>优账云财税学院 - ${requestScope.course.lecturer}</td>
									<td>${requestScope.course.courseName}</td>
								</tr>
								<tr>
									<td colspan="4" style="text-align: left; padding-left: 145px;">课程章节:</td>
								</tr>
								<c:forEach items="${requestScope.sectionList}" var="section">
									<tr>
										<td colspan="4">
											<span class="subject"> 
												<img src="/Onlineducation/style/images/images/project.png" style="width: 25px; height: 25px; margin-right: 8px;"/>
												${section.sectionName}
											</span>
										</td>
									</tr>
								</c:forEach>								
							</tbody>
						</table>
					</div>
					<div class="title">
						<h3>简介</h3>
					</div>
					<div class="face">
						<div class="pic">
							<span>${requestScope.course.courseInfo}</span>
							<img alt="图片详情" src="${requestScope.course.photoUrl}">
						</div>
					</div>
				</div>
			</div>
			<!-- 课程推荐 -->
			<div class="section-postscript section--recommend">
				<div class="inner-center">
					<h3 class="section-tt">老师还为你推荐了以下几门课程</h3>
					<div class="section-bd course-card-list-single-wrap">

						<ul class="course-card-list" auto-test="">
							<li class="course-card-item" data-report-position="1"><a
								href="javascript:;" target="_blank" class="item-img-link"
								cors-name="course"> <img
									src="/Onlineducation/style/images/images/kecengone.jpg"
									alt="财税精英孵化训练营" title="财税精英孵化训练营" class="item-img" width="220"
									height="124">
							</a>
								<h4 class="item-tt">
									<a href="javascript:;" target="_blank" class="item-tt-link"
										title="财税精英孵化训练营" cors-name="course">财税精英孵化训练营</a>
								</h4>
								<div class="item-line item-line--bottom">
									<span class="line-cell item-price">¥899.00</span> <i
										class="icon-can-try"></i> <span
										class="line-cell item-user item-user--right"> 161人购买 </span>
								</div></li>
							<li class="course-card-item" data-report-position="2"><a
								href="javascript:;" target="_blank" class="item-img-link"
								cors-name="course"> <img
									src="/Onlineducation/style/images/images/kecengtwo.png"
									alt="一般纳税人全盘账精英孵化班" title="一般纳税人全盘账精英孵化班" class="item-img"
									width="220" height="124">
							</a>
								<h4 class="item-tt">
									<a href="javascript:;" target="_blank" class="item-tt-link"
										title="一般纳税人全盘账精英孵化班" cors-name="course">一般纳税人全盘账精英孵化班</a>
								</h4>
								<div class="item-line item-line--bottom">
									<span class="line-cell item-price">¥498.00</span> <i
										class="icon-can-try"></i> <span
										class="line-cell item-user item-user--right"> 765人购买 </span>
								</div></li>

							<li class="course-card-item" data-report-position="3"><a
								href="javascript:;" target="_blank" class="item-img-link"
								cors-name="course"> <img
									src="/Onlineducation/style/images/images/kecengthree.jpg"
									alt="中级会计实务习题精讲" title="中级会计实务习题精讲" class="item-img"
									width="220" height="124">
							</a>
								<h4 class="item-tt">
									<a href="javascript:;" target="_blank" class="item-tt-link"
										title="中级会计实务习题精讲" cors-name="course">中级会计实务习题精讲</a>
								</h4>
								<div class="item-line item-line--bottom">
									<span class="line-cell item-price">¥66.00</span> <i
										class="icon-can-try"></i> <span
										class="line-cell item-user item-user--right"> 347人购买 </span>
								</div></li>
							<li class="course-card-item" data-report-position="4"><a
								href="javascript:;" target="_blank" class="item-img-link"
								cors-name="course"> <img
									src="/Onlineducation/style/images/images/kecengfour.jpg"
									alt="中级财务管理习题精讲" title="中级财务管理习题精讲" class="item-img"
									width="220" height="124">
							</a>
								<h4 class="item-tt">
									<a href="javascript:;" target="_blank" class="item-tt-link"
										title="中级财务管理习题精讲"
										report-tdw="action=Coursecard_Coursesname-clk&amp;ver1=329671&amp;ver3=1.4"
										cors-name="course">中级财务管理习题精讲</a>
								</h4>
								<div class="item-line item-line--bottom">
									<span class="line-cell item-price">¥66.00</span> <i
										class="icon-can-try"></i> <span
										class="line-cell item-user item-user--right"> 225人购买 </span>
								</div></li>
							<li class="course-card-item" data-report-position="5"><a
								href="javascript:;" target="_blank" class="item-img-link"
								cors-name="course"> <img
									src="/Onlineducation/style/images/images/kecengfive.jpg"
									alt="中级经济法习题精讲" title="中级经济法习题精讲" class="item-img" width="220"
									height="124">
							</a>
								<h4 class="item-tt">
									<a href="javacsript:;" target="_blank" class="item-tt-link"
										title="中级经济法习题精讲" cors-name="course">中级经济法习题精讲</a>
								</h4>
								<div class="item-line item-line--bottom">
									<span class="line-cell item-price">¥66.00</span> 
									<i class="icon-can-try"></i> 
									<span class="line-cell item-user item-user--right"> 237人购买 </span>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>		
		<h3 style="line-height: 70px; color: #7295DD; padding-bottom: 10px;">
			<a href="http://www.jkj521.cn/AccountingOnline/" target="_blank">财税软件,专注财务软件管理,助力企业快速做账,提高工作效率,摆脱繁琐报表,轻松解决财务人难题,适用行业广泛</a>
		</h3>
	</div>
<script type="text/javascript">
	function goLearn(course_id){
		url="/Onlineducation/fore/goLearn"+course_id;		
		$.get(url,function(){});		
	}
</script>
<%@ include file="../common/footer.jsp"%>
</body>
</html>
