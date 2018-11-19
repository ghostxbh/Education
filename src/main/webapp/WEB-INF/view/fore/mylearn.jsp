<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>个人中心</title>
<style>
.content_div {
	min-height: 700px;
	width: 1200px;
	margin: 0 auto;
	background-repeat: no-repeat;
	background-position: center top;
}

.my_title {
	margin-bottom: 30px;
	margin-top: 60px;
	margin-left: 20px;
	height: 37px;
	font-size: 26px;
	color: #01c257;
	line-height: 37px;
}

.order_header {
	background-color: #EFEFEF;
	color: #5D5D5D;
	height: 42px
}
</style>
</head>
<body>
	<div class="content_div">
		<div class="my_title">我的课程<span>共有${requestScope.courseCount}个课程</span></div>
		<div class="order_header">
			<ul>
				<li>课程信息</li>
				<li>订单详情</li>				
				<li>状态</li>				
			</ul>
		</div>
		<div>			
			<c:forEach items="${requestScope.courseList}" var="course">
				<c:forEach items="${requestScope.orderList}" var="order">
					<ul>
						<li>${course.courseName}</li>						
						<li>${order.code}</li>						
						<li>${order.status}</li>
					</ul>
				</c:forEach>
			</c:forEach>		
		</div>
	</div>
	<%@ include file="page.jsp"%>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
