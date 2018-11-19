<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Onlineducation/style/css/fore/courseDetail.css">
<link rel="stylesheet"	href="/Onlineducation/style/css/fore/videoDetail.css">
<link rel="stylesheet"	href="/Onlineducation/style/videojs/video-js.css">
<script src="/Onlineducation/style/videojs/video.js"></script>
<title>优账云财税学院 - ${requestScope.course.courseName}</title>
</head>
<body>		
<div class="g-flow f-cb"> 
	<div class="f-bg courseheadbox" id="j-coursehead" style="min-width:1200px;height:650px;background:#E7F6F8;">
		<div class="u-courseHead" id="auto-id-1509947716498" style="min-width:1200px;height:600px;">
			<div class="ov f-pr j-ch" style="padding-top: 0px; top: 10px;height:600px;"> 
				<div class="g-sd1 left j-chimg">
					<div id="box" style="margin-top:40px;">
     					<video id="example_video_1" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="none" width="640" height="480" data-setup="{}">
          				<source src="http://www.jkj521.com/resource${requestScope.firstUrl}" type="video/mp4"> 
       			 		</video>
       			 		<div>
       			 			<h4 style="padding-top:10px;">当前播放：</h4> <h3 style="line-height:40px;"><span id="curPlay">${requestScope.firstName}</span></h3>
       			 			<h4><img src="/Onlineducation/style/images/images/hand1.png" style='background:#E7F6F8;width:30px;height:30px;margin-right:15px;'/><a href="http://www.jkj521.cn/AccountingOnline/"  target="_blank" style="color:#EFB27D;">  
       			 			看视频,学会计,用优账云财税软件,体验真实做账实操</a></h4>
       			 		</div>
  				 	</div>
				</div>
				<div class="g-mn1" >
					<div class="g-mn1c right" style="height:40px;">
						<h3 style="padding-left:50px;">播放列表</h3>
					</div>
					<div class="g-mn1c right j-right f-pr" style="height:500px;overflow-y:auto;">
						<div class="layui-collapse" lay-accordion="" style="width:400px;float:right;">
							<c:forEach items="${requestScope.sectionList}" var="section" varStatus="status">
								<div class="layui-colla-item">
   								<h2 class="layui-colla-title">${section.sectionName }</h2>
  									<div class="layui-colla-content">
  										 <ul class="list-group">
									 		<c:forEach items="${requestScope.videoMap}" var="videoMap" >
									 			<c:if test="${videoMap.key eq section.id}">
									 				<c:forEach items="${videoMap.value}" var="video">
									 					<li class="list-group-item" style="width:100%;cursor:pointer;" onclick="changeSource('${video.videoUrl }',this)">${video.videoName }</li>
									 				</c:forEach>
									 			</c:if>  										 			
									 		</c:forEach>
  										 </ul>
   									</div>
								 </div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
	
	<div class="layout clearfix msf">
	 <div class="main_left fl">
		<div class="title"><h3>学习资料</h3></div>
			<div class="study_data clearfix">
		    <ul>
		        <li>
		                  <i><img src="/Onlineducation/style/images/images/zl01.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>全真模拟试题3套</p>
		                </li>
		        <li>
		                  <i><img src="/Onlineducation/style/images/images/zl02.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>应试指南</p>
		                </li>
		        <li>
		                    <i><img src="/Onlineducation/style/images/images/zl04.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>历年真题</p>
		                   </li>
		        <li>
		                   <i><img src="/Onlineducation/style/images/images/zl05.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>课后作业</p>
		                  </li>
		        <li>
		                            <i><img src="/Onlineducation/style/images/images/zl06.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>上期辅导课程</p>
		                  </li>
		<li>
		                  <i><img src="/Onlineducation/style/images/images/zl07.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>考前15天预测卷3套</p>
		                </li>
		<li>
		                  <i><img src="/Onlineducation/style/images/images/zl08.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>无纸化题库</p>
		                </li>
		<li>
		                  <i><img src="/Onlineducation/style/images/images/zl15.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>1套真账实操用品大礼包</p>
		                </li>
		<li>
		                  <i><img src="/Onlineducation/style/images/images/zl16.png" alt="初级会计职称辅导课程学习资料"></i>
		                <p>4本实训资料</p>
		                </li>
		    </ul>
		</div>
	</div>
</div>
<script>
	layui.use(['element', 'layer'], function(){
		  var element = layui.element;
		  var layer = layui.layer;
	});
	
	
	videojs.options.flash.swf = "videojs/video-js.swf"
        //传入视频ID,适用于页面上只有一个播放器的情况
        //teachOrder 采用哪种播放方式，默认采用html5的video标签播放，苹果不支持flash
        //mydata 是要传给播放器上显示的用户名
        var player = videojs('#example_video_1',{
            techOrder: ["html5","flash"],
            control: {
                 captionsButton: true,
                 chaptersButton : true,
                 liveDisplay:true,
                 playbackRateMenuButton: true,
                 subtitlesButton:true
             },
               controlBar: {
                fullscreenToggle:true,
                volumeMenuButton:true
            },
            height:"480px",
            width:"640px"

        },function(){
            //这里是播放器初始化之后放置要显示的内容
            //更高级的应用可以创建一个组件(component)
             var textNode = document.createElement('div');
             textNode.className = 'user-name';
            //textNode.innerHTML = this.options().mydata.username||'';
             this.el().appendChild(textNode);
             this.userName = textNode;
             player.play();
        });
    
        // var volumeMenuButton  = player.controlBar.getChild('volumeMenuButton').el();
        // volumeMenuButton.style.display='none'
     

        function changeSource(value,that){
        	var curPlay=$("#curPlay").text();
        	if(!curPlay){
        		curPlay="其他视频"
        	}
        	layer.confirm('当前正在播放  '+curPlay+'，是否切换？',  function(index){
    	            layer.close(index);
    	            player.src("http://www.jkj521.com/resource"+value);
    	            $("#curPlay").text($(that).text());
    	            player.play();
        		});
            
        }
	</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
