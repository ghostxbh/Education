<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script>
        //检索数据集
        var dataList = {
        		 course_name : null,
                 category_id : null,
                 course_sale_price: null,
                 course_price : null,
                 course_isEnabled_array: null,
                 orderBy :null,
                 isDesc :null        
        };        
        $(function () {
            //刷新下拉框
            $('#select_course_category').selectpicker('refresh');
            /******
             * event
             ******/
            //点击查询按钮时
            $("#btn_course_submit").click(function () {
                var course_name = $.trim($("#input_course_name").val());
                var lecturer = $.trim($("#input_course_lecturer").val());
                var category_id = parseInt($("#select_course_category").val());
                var lowest_price = $.trim($("#input_course_sale_price").val());
                var highest_price = $.trim($("#input_course_price").val());
                //产品状态
                var course_status = $("input[name = checkbox_course_status]:checked").val();
                var course_free = $("input[name = checkbox_course_free]:checked").val();
                
                //校验数据合法性
                if( isNaN(lowest_price) || isNaN(highest_price) ){
                    styleUtil.errorShow($('#text_course_msg'),"金额输入格式有误！");
                    return;
                }
                //封装数据
                dataList.course_name = encodeURI(course_name);
                dataList.category_id = category_id;                
                dataList.lecturer = encodeURI(lecturer);                
                dataList.course_status = course_status;
                dataList.course_free = course_free;
                dataList.hight_price = highest_price;
                dataList.lowest_price = lowest_price;

                getData($(this), "../admin/course/0/15", dataList);
            });
            //点击刷新按钮时
            $("#btn_course_refresh").click(function () {
                //清除数据
                dataList.course_name = null;
                dataList.category_id = null;
                dataList.course_sale_price = null;
                dataList.course_price = null;
                dataList.course_isEnabled_array = null;
                dataList.orderBy = null;
                dataList.isDesc = true;
                //获取数据
                getData($(this), "../admin/course/0/15", null);
                //清除排序样式
                var table = $("#table_course_list");
                table.find("span.orderByDesc,span.orderByAsc").css("opacity","0");
                table.find("th.data_info").attr("data-sort","asc");
            });
            //点击th排序时
            $("th.data_info").click(function () {
                var table = $("#table_course_list");
                if(table.find(">tbody>tr").length <= 1){
                    return;
                }
                //获取排序字段
                dataList.orderBy = $(this).attr("data-name");
                //是否倒序排序
                dataList.isDesc = $(this).attr("data-sort")==="asc";

                getData($(this), "../admin/course/0/15", dataList);
                //设置排序
                table.find("span.orderByDesc,span.orderByAsc").css("opacity","0");
                if(dataList.isDesc){
                    $(this).attr("data-sort","desc").children(".orderByAsc.orderBySelect").removeClass("orderBySelect").css("opacity","1");
                    $(this).children(".orderByDesc").addClass("orderBySelect").css("opacity","1");
                } else {
                    $(this).attr("data-sort","asc").children(".orderByDesc.orderBySelect").removeClass("orderBySelect").css("opacity","1");
                    $(this).children(".orderByAsc").addClass("orderBySelect").css("opacity","1");
                }
            });
            //点击table中的数据时
            $("#table_course_list").find(">tbody>tr").click(function () {
                trDataStyle($(this));
            });
        });

        //获取产品数据
        function getData(object,url,dataObject) {
            var table = $("#table_course_list");
            var tbody = table.children("tbody").first();
            $.ajax({
                url: url,
                type: "GET",
                data: dataObject,
                traditional: true,
                success: function (data) {
                    //清空原有数据
                    tbody.empty();
                    //设置样式
                    $(".loader").css("display","none");
                    object.attr("disabled",false);
                    //显示产品统计数据
                    $("#course_count_data").text(data.courseCount);
                    var courseList = data.data.courseList;
                    if (courseList.length > 0) {
                        for (var i = 0; i < courseList.length; i++) {                          
                            var course_id = courseList[i].id;
                            var course_name = courseList[i].courseName;                          
                            //显示产品数据
                            tbody.append("<td><input type='hidden' class='cbx_select' id='cbx_course_select_course_id' value='course_id'></td> <td title='"+course_name+"'>" + course_name + "</td><td><span class='td_special' title='添加章节/视频'><a href='javascript:void(0);' onclick='goSection(course_id)'>添加章节/视频</a></span></td></tr>");
                        }
                        //绑定事件
                        tbody.children("tr").click(function () {
                            trDataStyle($(this));
                        });
                        //分页
                        var page = data.data.pageUtil;
                        var pageUtil = {
                            index: page.index,
                            count: page.count,
                            total: page.total,
                            totalPage: data.data.totalPage
                        };
                        createPageDiv($(".loader"), pageUtil);
                    }
                },
                beforeSend: function () {
                    $(".loader").css("display","block");
                    object.attr("disabled",true);
                },
                error: function () {

                }
            });
        }

        //获取产品子界面
        function goSection(course_id) {            
            var title = "添加章节/视频";
            var url = "section/"+course_id;          
			/* var data = {
					"course_id":course_id
			} */
            //设置样式
            $("#div_home_title").children("span").text(title);
            document.title = "优账云财税学院 - "+title;
            //ajax请求页面
            ajaxUtil.getPage(url,null,true);
        }

        //获取页码数据
        function getPage(index) {
            getData($(this), "admin/course/" + index + "/15", dataList);
        }
        
    </script>
    <style rel="stylesheet">
        #text_cut{
            position: relative;
            right: 10px;
            color: #ccc;
        }
        #lbl_course_isEnabled_special{
            margin-right: 20px;
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
    </style>
</head>
<body>
<div class="frm_div text_info">
    <div class="frm_group">
        <label class="frm_label" id="lbl_course_name" for="input_course_name">产品名称</label>
        <input class="frm_input" id="input_course_name" type="text" maxlength="50"/>
        <!-- <label class="frm_label" id="lbl_course_lecturer" for="input_course_lecturer">讲师</label>
        <input class="frm_input" id="input_course_lecturer" type="text" maxlength="30"/> -->
        <label class="frm_label" id="lbl_course_category_id" for="select_course_category">产品类型</label>
        <select class="selectpicker" id="select_course_category" data-size="10">
            <option value="0">全部</option>
            <c:forEach items="${requestScope.categoryList}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <input class="frm_btn" id="btn_course_submit" type="button" value="查询"/>
        <input class="frm_btn frm_clear" id="btn_clear" type="button" value="重置"/>
    </div>
    <div class="frm_group">
        <label class="frm_label" id="lbl_course_isEnabled" for="checkbox_course_isEnabled_true">产品状态</label>
        <input id="checkbox_course_isEnabled_true" name="checkbox_course_status" type="radio" value="1" checked>
        <label class="frm_label" id="lbl_course_isEnabled_true" for="checkbox_course_isEnabled_true">上架</label>
        <input id="checkbox_course_isEnabled_false" name="checkbox_course_status" type="radio" value="0">
        <label class="frm_label" id="lbl_course_isEnabled_false" for="checkbox_course_isEnabled_false">已下架</label> 
        
        <label class="frm_label" id="lbl_course_free" for="checkbox_course_free_true">是否免费</label>
        <input id="checkbox_course_free_flase" name="checkbox_course_free" type="radio" value="0" checked>
        <label class="frm_label" id="lbl_course_free_flase" for="checkbox_course_free_flase">否</label>       
		<input id="checkbox_course_free_true" name="checkbox_course_free" type="radio" value="1">
        <label class="frm_label" id="lbl_course_free_true" for="checkbox_course_free_true">是</label>        
    </div>
    <!-- <div class="frm_group_last">
        <input class="frm_btn frm_add" id="btn_course_add" type="button" value="添加一件产品" onclick="getChildPage(null)"/>
        <input class="frm_btn frm_refresh" id="btn_course_refresh" type="button" value="刷新产品列表"/>
        <input class="frm_btn frm_danger" id="btn_course_delete" type="button" value="删除选中产品" onclick="deleteproduct()"/>
        <span class="frm_error_msg" id="text_tools_msg"></span>
    </div> -->
</div>
<div class="data_count_div text_info">
    <i class="fa fa-search" aria-hidden="true"></i> 
    <span class="data_count_title">查看合计</span>
    <span>产品总数:</span>
    <span class="data_count_value" id="course_count_data">${requestScope.courseCount}</span>
    <span class="data_count_unit">件</span>
</div>
<div class="table_normal_div">
    <table class="table_normal" id="table_course_list">
        <thead class="text_info">
        <tr>
           	<th><input type="hidden" class="cbx_select" id="cbx_select_all"></th> 
            <th class="data_info" data-sort="asc" data-name="course_name">
                <span>课程名称</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <!-- <th class="data_info" data-sort="asc" data-name="course_title">
                <span>课程情</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="course_price">
                <span>课程单价</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="course_lecturer">
                <span>讲师</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="course_create_date">
                <span>创建时间</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="course_sales_volume">
                <span>销售量</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th> -->
            <th>操作</th>
            <th hidden>产品ID</th>
        </tr>
        </thead>
        <tbody id="tbodyId">
        <c:forEach items="${requestScope.courseList}" var="course">
            <tr>
                <td><input type="hidden" class="cbx_select" id="cbx_course_select_${course.id}" value="${course.id}"></td> 
                <%-- <input type="hide" class="cbx_select" value="${course.id}"> --%>
                <td title="${course.courseName}">${course.courseName}</td>
                <%--<td title="${course.courseInfo}">${course.courseInfo}</td>
                <td title="${course.price}">${course.price}</td>
                <td title="${course.lecturer}">${course.lecturer}</td>
                <td title="${course.insertTime}">${course.insertTime}</td>
                <td title="${course.salesVolume}">${course.salesVolume}</td>  --%>               
                <td><span class="td_special" title="添加章节"><a href="javascript:void(0)" onclick="goSection(${course.id});">添加章节</a></span></td>
                <td hidden><span class="course_id">${course.id}</span></td>	
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@ include file="include/page.jsp" %>
    <div class="loader"></div>
</div>
<div class="msg" id="seccuss">
    <span>删除成功</span>
</div>
<div class="msg" id="fail">
    <span>无效删除</span>
</div>
</body>
</html>
