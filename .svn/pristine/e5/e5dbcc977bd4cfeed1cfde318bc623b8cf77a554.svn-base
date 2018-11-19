<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<%@ include file="./common/common.jsp" %>
<title>优账云-会计学院</title>
<!--[if lt IE 9]>  
<script src="js/html5shiv.js"></script>  
<script src="js/respond.min.js"></script>  
<![endif]-->
<style>
body {padding-top: 50px;padding-bottom: 40px;color: #5a5a5a;}*{margin:0;padding:0;}li{list-style:none;}a{text-decoration:none;}img{border:none;}a:hover {outline: none}
.new-top{background:red;min-width:1200px;line-height:70px;position:fixed;top:0;width:100%;z-index:99997;border-bottom:1px solid #cacaca;box-sizing:border-box;background:#fff;}.top-div{width:100%;}
.new-top-content{background:#fff;width:1200px;margin:0 auto;}
 #header{height:70px;width:1200px;margin: 0 auto;overflow:hidden;}
 .header-left{padding-left:20px;height:70px;float:left;margin-right:300px;}
.header-ul-content{position:relative;overflow:hidden;} .header-ul-content li{float:left;}.header-ul-content li>a{color:#000;text-decoration:none;width:auto;padding:0 20px;height:70px;line-height:70px;font-size:17px;display:inline-block;text-align:center;outline:0}
.move{width:70px;height: 4px;border-top: 4px solid #019afe;box-sizing:border-box;position: absolute;left:0px; top: 52px;transition: left .2s ease-in-out 0s;-webkit-transition: left .2s ease-in-out 0s; -moz-transition: left .2s ease-in-out 0s;-o-transition: left .2s ease-in-out 0s;}
 .header-ul-content li:nth-child(1):hover~ .move{left: 0px;}
.header-ul-content li:nth-child(2):hover~ .move{left:90px;width:75px;}
.header-ul-content li:nth-child(3):hover~ .move{left: 200px;width:75px;}
.header-ul-content li:nth-child(4):hover~ .move{left: 305px;width:50px;}
.header-ul-content li:nth-child(5):hover~ .move{left: 380px;width:90px;}
.header-right{text-align:center;height:30px;width:180px;float:left;margin-left:10px;}
.header-right a{width:60px;margin-left:10px;border:1px solid #019afe;box-sizing:border-box;height:30px;border-radius:16px;line-height:30px;display:inline-block;font-size:16px;color:#000;font-family:"微软雅黑";}
.header-right a:hover{border:none;line-height:30px;height:30px;border-radius:16px;background:url('/Onlineducation/style//images/images/jianbian.png') no-repeat 100% 100%;color:#fff;text-decoration: none;}
.header-right a.active {border:none;background:url('/Onlineducation/style//images/images/jianbian.png') no-repeat 100% 100%;color: #FFF;}
@keyframes proRotate { 0%   {-webkit-transform: rotateY(90deg) ;}50%  {-webkit-transform: rotateY(180deg);}100% {-webkit-transform:rotateY(360deg);} }
.mian-ul li img:hover{ -webkit-animation: proRotate 0.5s ease-in-out 100ms alternate none 1;animation: proRotate 0.5s ease-in-out 100ms alternate none 1;}
.top-div{background-color:#FFF;height:70px;transition-duration:.5s;-webkit-transition-duration:.5s}

</style>

</head>

<body>
	<!--下面是顶部导航栏的代码-->
	<%-- <nav class="navbar navbar-default navbar-inverse navbar-fixed-top"
		role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }">会计学院 </a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath }">首页</a></li>
				<li class="dropdown">
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${loginUser != null }">
					<li><a href="${ctx }/mylearn">我的课程 </a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">[${loginUser.username }]<strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/user/logOut" class="glyphicon glyphicon-inbox">
									退出帐号</a></li>
						</ul></li>
				</c:if>
				<c:if test="${loginUser == null }">
					<li><a href="loginer"><span
							class="glyphicon glyphicon-log-in"></span> 登录</a></li>
					<li><a href="register"><span
							class="glyphicon glyphicon-user"></span> 注册 </a></li>
					<li><a href="http://www.jkj521.cn/yunzf/index/index.html">云账房</a></li>
				</c:if>

			</ul>
		</div>
	</div>
	</nav> --%>
	<div class="new-top">
    <div class="new-top-content">
       <div id="header" > 
                <div class="header-left"><img src="/Onlineducation/style/images/images/logo-jkj.png"/></div>
                <ul class="header-ul-content">
                     <li><a href="http://www.jkj521.cn/gateway">首页</a></li>
                    <li><a href="javascript:;">所有课程</a></li>
                    <li><a href="javascript:;">常见问题</a></li>
                    <li><a href="">关于</a></li>
                    <li><a href="http://www.jkj521.cn/gateway">优账云官网</a></li>
                    <li class="move"></li>
                <div class="header-right">
                    <a href="http://www.jkj521.cn/AccountingOnline/page/reg">注册</a>
                    <a href="http://www.jkj521.cn/AccountingOnline/" class="active">登录</a>
                </div>
                 </ul>
                
         </div> 
       </div>
</div>
     <div class="top-div clearfix">
        <div class="top clearfix"></div>
    </div>
	<div class="mc-search-course-breadcrumb">
		<ul class="g-flow f-cb">
			<li><a href="${pageContext.request.contextPath }"
				target="_blank">首页</a></li>

			<li class="current"><span class="arrow"></span> <a
				href="javascript: void(0);">课程详情</a></li>
		</ul>
	</div>
	<div class="g-flow f-cb">
		<div class="f-bg courseheadbox" id="j-coursehead">
			<div class="u-courseHead" id="auto-id-1509947716498">
				<div class="ov f-pr j-ch" style="padding-top: 0px; top: 10px;">
					<div class="g-sd1 left j-chimg">
						<img width="450" height="250"
							src="http://www.jkj521.com/resource${course.photoUrl }">
					</div>
					<div class="g-mn1">
						<div class="g-mn1c right j-right f-pr">
							<div class="ctarea f-fl j-cht">
								<div class="u-coursetitle f-fl" id="auto-id-1509947716616">
									<h2 class="f-thide">
										<span class="u-coursetitle_title" title="">${course.courseName}</span>
									</h2>
									<div class="f-cb margin-top-15">
										<span class="cmt">已有${course.salesVolume }人学过 </span>
										<%-- <div class="f-fl" style="margin-right: 3px;">
											<a href="#" target="_blank" class="j-userNode"
												data-id="837467" data-type="10" id="auto-id-1509947716617">${course.courseInfo}</a>
										</div> --%>
									</div>
									<div class="f-cb margin-top-15">
										<span class="cmt"><b>讲师：</b>${course.lecturer } </span>
									</div>
								</div>
							</div>
							<div>
								<div class="btnarea f-pa j-chbtnarea">
									<!-- <a class="learnbtn f-db f-cb j-joinBtn"><span>立即参加</span></a>  -->									
									 <c:if test="${course.free==0}">
										<a class="learnbtn f-db f-cb j-joinBtn"><span>立即支付</span></a>
									</c:if>													
									<c:if test="${course.free==1}">
										<a class="learnbtn f-db f-cb j-joinBtn"><span>立即参加</span></a>
									</c:if> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="g-flow f-cb">
		<div class="f-bg courseheadbox" id="j-coursehead">
			<div class="u-courseHead" id="auto-id-1509947716498">
				${course.courseInfo }
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="modal-title">
					
				</h4>
			</div>
			<div class="modal-body" id="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="confirm">
					
				</button>
				<button type="button" class="btn btn-default" id="cancle">
					
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
	
	<script>	
		$(document)	.ready(	function() {
							$(".learnbtn").click(function() {
												if (!"${loginUser}") {
													window.location = "${pageContext.request.contextPath }/loginer";
												} else {
													/* if ("${isSelect}" == "true") {
														deleteCourse();
													} else {
														insertCourse();
													} */
													var free = "${course.free}";
													if (free==1) {
														window.location = "${pageContext.request.contextPath }/learn/getSections?cid=${course.id}";
													}else{
														orderData();
													}
													
												}
											});
						});
		function GetDateNow() {
			var vNow = new Date();
			var sNow = "";
			sNow += String(vNow.getFullYear());
			sNow += String(vNow.getMonth() + 1);
			sNow += String(vNow.getDate());
			sNow += String(vNow.getHours());
			sNow += String(vNow.getMinutes());
			sNow += String(vNow.getSeconds());
			sNow += String(vNow.getMilliseconds());		
			return sNow;
		}
		
		function orderData(){
			var url = "${pageContext.request.contextPath }/pay/getConfirmInfoPage";
			var params={
				"cid": "${course.id}"	
			};
			 $('#myModal #modal-body').load(url,params,function(){
				 $("#modal-title").text("确认订单信息")
				 $("#confirm").text("去支付")
				 $("#cancle").text("取消")
				 $("#myModal #confirm").click(function(){
					 $("#myModal #formId").submit();
					 $("#confirm").text("支付完成")
				 });
				 $("#myModal #cancle").click(function(){
					 $("#myModal").modal("hide"); 
				 });
				 $("#myModal").modal("show"); 
			 })
			 
		}
			
		function insertCourse() {
			$.ajax({
				type : "post",
				url : "insertCourse",
				data : {
					"courseid" : "${course.id}",
					"userid" : "${loginUser.id}"
				},
				async : true,
				success : function(data) {
					/* alert(password==data); */
					if (data == "true") {
						location.href = "learn/watchvedio?cid=${course.id}"
						//location.reload(true) ;
					} else {
						$(".learnbtn").focus();
						return;
					}
					/* alert(data); */
				}
			})
		}
		function deleteCourse() {

			$.ajax({

						type : "post",
						url : "insertCourse",
						data : {
							"courseid" : "${course.id}" ,
							"userid" :   "${loginUser.id}"
						},
						async : true,
						success : function(data) {
							/* alert(password==data); */
							if (data == "true") {
								location.href="learn/getSections?cid=${course.id}"
								//location.reload(true) ;
							} else {
								$(".learnbtn").focus();
									return;
							}
							/* alert(data); */
						}
			})
}
function deleteCourse() {
	
	$.ajax({


				type : "post",
				url : "deleteCourse",
				data : {
					"courseid" : "${course.id}",
					"userid" : "${loginUser.id}"
				},
				async : true,
				success : function(data) {
					/* alert(password==data); */
					if (data == "true") {

						location.reload(true);
					} else {
						$(".learnbtn").focus();
						return;
					}
					/* alert(data); */
				}
	})
		}
	</script>
</body>
</html>
