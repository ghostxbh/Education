<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script>
        $(function () {          
            //单击取消按钮时
            $("#btn_course_cancel").click(function () {
                $(".menu_li[data-toggle=section]").click();
            });
            //获取到输入框焦点时
            $("input:text").focus(function () {
                styleUtil.basicErrorHide($(this).prev("label"));
            });
        });    
        
        //产品操作
        function doAction(dataList, url, type) {
            $.ajax({
                url: url,
                type: type,
                data: dataList,
                traditional: true,
                success: function (data) {
                    $("#btn_course_save").attr("disabled", false).val("保存");
                    if (data.state==1) {
                        $("#save").stop(true, true).animate({
                            opacity: 1
                        }, 550, function () {
                            $("#save").animate({
                                opacity: 0
                            }, 1500);
                        });
                        setTimeout(function () {
                            //ajax请求页面
                            ajaxUtil.getPage("course/" + data.course_id, null, true);
                        }, 170);
                    }
                },
                beforeSend: function () {
                    $("#btn_course_save").attr("disabled", true).val("保存中...");
                },
                error: function () {

                }
            });
        }       
     	var index = 0;
        function addSaltIpGrp(obj,course_id){
        	index+=1;
        	html = '<div class="input-group saltIp_'+index+'">'+
  						'<label class="input-group-addon">课程id:</label>'+
  						'<input type="text" class="form-control" id="saltIp" value='+course_id+' disabled="disabled">'+
  						'<label class="input-group-addon">章节名称:</label>'+
  						'<input type="text" class="form-control" id="apiPort">'+  																			
  						'<span class="input-group-btn">'+
      						'<button class="btn btn-info" type="button" title="删除" onclick="delSectionInput(null,'+index+')">删除</button>'+
      					'</span>'+
        			'</div>'
        	obj.insertAdjacentHTML('beforebegin',html)
        }
        //删除章节行
        function delSectionInput(section_id,count){
        	if(section_id!=null){
        		$.ajax({
					url:"../admin/section/delete/"+section_id,
					type:'POST',						
					dataType:'json',
					data:null,
					success:function(result){
						if (result.state==1){							
							$(".saltIp_"+count).remove(); 					
							 $("#seccuss").stop(true, true).animate({
	                                   opacity: 1
	                               }, 550, function () {
	                                   $("#seccuss").animate({
	                                       opacity: 0
	                                   }, 1500);
	                               });							
						}else {
							$("#fail").stop(true, true).animate({
                                   opacity: 1
                               }, 550, function () {
                                   $("#fail").animate({
                                       opacity: 0
                                   }, 1500);
                               });
						}
					}
				});
        	}else{        		
       			$(".saltIp_"+count).remove(); 										
        	}       
        }
       
        $("#btn_course_save").click(function(){
        	var list = $("#saltIpGroup .input-group");
        	console.log(list);
        	var sectionArray = [];
        	$.each(list,function(index,item){       		
        		var courseId = $(item).find("#saltIp").val();         
        		var sectionName = $(item).find("#apiPort").val();         		  		
        		var ESection ={"courseId":courseId,"sectionName":sectionName};        		
        		sectionArray.push(ESection);
        	})
        	console.log(sectionArray);
        	 $.ajax({
				url:"../admin/section/addList",
				type:'POST',				 
				data: JSON.stringify(sectionArray),
	    		traditional: true,
				dataType:'json',
				contentType: "application/json; charset=utf-8", 			
				success:function(result){
					if (result.state==1) {
						$("#save").stop(true, true).animate({
                            opacity: 1
                        }, 550, function () {
                            $("#save").animate({
                                opacity: 0
                            }, 1500);
                        });
					}else{
						$("#fail").stop(true, true).animate({
                            opacity: 1
                        }, 550, function () {
                            $("#fail").animate({
                                opacity: 0
                            }, 1500);
                        });
					}
				}
        	})
        })
       function goSection(course_id) {            
            var title = "添加视频";
            var url = "video/"+course_id;          
			/* var data = {
					"course_id":course_id
			} */
            //设置样式
            $("#div_home_title").children("span").text(title);
            document.title = "优账云财税学院 - "+title;
            //ajax请求页面
            ajaxUtil.getPage(url,null,true);
        }               
    </script>
    <style rel="stylesheet">
        .bootstrap-select:not([class*=col-]):not([class*=form-control]):not(.input-group-btn) {
            margin: 0 130px 0 0;
        }

        .frm_input {
            margin-right: 130px;
        }

        .frm_error_msg {
            white-space: nowrap;
        }

        .warn_height {
            max-height: 25px;
        }

        div.br {
            height: 20px;
        }

        .details_property_list label {
            margin-left: 10px;
        }
        .msg {
            opacity:0;
            position: absolute;
            top: 150px;
            left: 0;
            right: 0;
            bottom: 0;
            margin: auto;
            width: 230px;
            height: 70px;
            line-height: 70px;
            color: white;
            border-radius: 5px;
            text-align: center;
            background-color: rgba(0, 0, 0, 0.75);
            font-size: 16px;
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        .detail_course_div{
        	height:80px;
        	width: 100%;
        	border-bottom: 1px solid #e9ebef;
        }
        .details_section_div{        	
        	height: 660px;
        	width: 100%;
        }   
        .detail_section_title{
        	margin-top: 15px;
        	border-bottom: 1px solid #e9ebef;
        	height:40px;
        } 
        .saltIp{
        	width:590px;
        }
        .saltIp #saltIp{
        	width:60px;
        }  
        .saltIp #apiPort{
        	width:200px;
        }    
    </style>
</head>
<body>
<div class="detail_course_div">    
    <div class="frm_div_last warn_height">
        <label class="frm_label text_info" id="lbl_course_id" for="details_course_id">课程id</label>
        <span class="details_value">${requestScope.course.id}</span>             
        <label class="frm_label text_info" id="lbl_course_name" for="details_course_name">课程名称</label>
        <span class="details_value">${requestScope.course.courseName}</span>    
    </div>
</div>
<div class="data_count_div text_info">
    <i class="fa fa-search" aria-hidden="true"></i> 
    <span class="data_count_title">查看合计</span>
    <span>章节总数:</span>
    <span class="data_count_value" id="course_count_data">${requestScope.sectionCount}</span>
    <span class="data_count_unit">章</span>
</div>
<div class="detail_section_div">
	<div class="detail_section_title">
		<span>章节列表</span>
	</div>	
	<div class="input-group" id="saltIpGroup">	
		<c:forEach items="${requestScope.sectionList}" var="section">
			<div class="input-group saltIp_${section.id}" id="${section.id}">
				<input type="hidden" value="${section.id}">
				<label class="input-group-addon">课程id:</label>
				<input type="text" class="form-control" id="saltIp" value="${section.courseId}" disabled="disabled">
				<label class="input-group-addon">章节名称:</label>
				<input type="text" class="form-control" id="apiPort" value="${section.sectionName}">
				<span class="input-group-btn">
	      			<button class="btn btn-info" type="button" title="删除" onclick="delSectionInput(${section.id},${section.id})">删除</button>
	      		</span>
	      		<span class="td_special" title="添加视频"><a href="javascript:void(0)" onclick="goSection(${section.id});">添加视频</a></span>
      		</div>
		</c:forEach>
		<button class="btn btn-info" type="button" data-toggle="tooltip" title="新增" id="addSaltIpGrpBtn" onclick="addSaltIpGrp(this,${requestScope.course.id})">新增</button>	
	</div>
</div>
<div class="details_tools_div">
    <input class="frm_btn" id="btn_course_save" type="button" value="保存"/>
    <input class="frm_btn frm_clear" id="btn_course_cancel" type="button" value="取消"/>
</div>

<%-- 模态框 --%>
<div class="modal fade" id="modalDiv" tabindex="-1" role="dialog" aria-labelledby="modalDiv" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">您确定要删除该产品图片吗？</div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="btn-ok">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btn-close">关闭</button>
            </div>
        </div>
        <%-- /.modal-content --%>
    </div>
    <%-- /.modal --%>
</div>
<div class="loader"></div>
<div class="msg" id="seccuss">
    <span>删除成功</span>
</div>
<div class="msg" id="save">
    <span>保存成功</span>
</div>
<div class="msg" id="fail">
    <span>无效删除</span>
</div>
</body>
</html>