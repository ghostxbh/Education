<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script>
        //检索数据集
        var dataList = {
            "category_name": null
        };
        $(function () {
            /******
             * event
             * *****/
            //点击查询按钮时
            $("#btn_category_submit").click(function () {
                var category_name = $.trim($("#input_category_name").val());
                //封装数据
                dataList.category_name = encodeURI(category_name);

                getData($(this), "../admin/category/0/10", dataList);
            });
            //点击刷新按钮时
            $("#btn_category_refresh").click(function () {
                //清除数据
                dataList.category_name = null;
                //获取数据
                getData($(this), "../admin/category/0/10", null);
            });
            //点击table中的数据时
            $("#table_category_list").find(">tbody>tr").click(function () {
                trDataStyle($(this));
            });
        });
        //获取分类数据
        function getData(object, url, dataObject) {
            var table = $("#table_category_list");
            var tbody = table.children("tbody").first();
            $.ajax({
                url: url,
                type: "get",
                data: dataObject,
                success: function (data) {
                    //清空原有数据
                    tbody.empty();                    
                    //设置样式
                    $(".loader").css("display","none");
                    object.attr("disabled",false);
                    //显示分类统计数据                    
                    $("#category_count_data").text(data.categoryCount);                   
                    var categoryList = data.data.categoryList;
                    var pageUtil = data.data.pageUtil;
                    if(categoryList.length > 0) {
                        for (var i = 0; i < categoryList.length; i++) {
                            var category_id = categoryList[i].id;
                            var category_name = categoryList[i].name;
                            //显示分类数据
                            tbody.append("<tr><td><input type='checkbox' class='cbx_select' value='"+category_id+"' id='cbx_category_select_" + category_id + "'><label for='cbx_category_select_" + category_id + "'></label></td><td title='" + category_name + "'>" + category_name + "</td><td><span class='td_special' title='查看分类详情'><a href='javascript:void(0)' onclick='getChildPage(this)'>详情</a></span></td><td hidden class='category_id'>" + category_id + "</td></tr>");
                        }
                        //绑定事件
                        tbody.children("tr").click(function () {
                            trDataStyle($(this));
                        });
                        //分页
                        var pageUtil = {
                            index: pageUtil.index,
                            count: pageUtil.count,
                            total: pageUtil.total,
                            totalPage: data.data.totalPage
                        };
                        createPageDiv($(".loader"), pageUtil);
                    }
                },
                beforeSend: function () {
                    $(".loader").css("display", "block");
                    object.attr("disabled",true);
                },
                error: function () {

                }
            });
        }

        // 获取产品分类子界面
        function getChildPage(obj) {
            var url;
            var title;
            if (obj === null) {
                title = "添加分类";
                url = "category/new";
            } else {
                title = "分类详情";
                url = "category/" + $(obj).parents("tr").find(".category_id").text();
            }

            //设置样式
            $("#div_home_title").children("span").text(title);
            document.title = "优账云财税学院 - " + title;
            //ajax请求页面
            ajaxUtil.getPage(url, null, true);
        }

        //获取页码数据
        function getPage(index) {
            getData($(this), "admin/category/" + index + "/10", dataList);
        }

        //删除分类
        function deleteCategory() {
            var arr = new Array();
            $("#tbodyId input[type='checkbox']:checked").each(function (index, item) {
                arr.push($(this).val());
            });
            console.log(arr);
            if (arr === undefined || arr.length == 0) {
                $("#fail").stop(true, true).animate({
                    opacity: 1
                }, 550, function () {
                    $("#fail").animate({
                        opacity: 0
                    }, 1500);
                });
            } else {
                if (window.confirm("确认删除？")) {
                    $.ajax({
                        url: "../admin/category/delete/" + arr,
                        type: "get",
                        date: null,
                        traditional: true,
                        success: function (data) {
                        	console.log(data);
                            if (data.state==1) {
                                $("#seccuss").stop(true, true).animate({
                                    opacity: 1
                                }, 550, function () {
                                    $("#seccuss").animate({
                                        opacity: 0
                                    }, 1500);
                                });
                            }
                        }
                    })
                }
            }
        }
    </script>
    <style>
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
        <label class="frm_label" id="lbl_category_name" for="input_category_name">分类名称</label>
        <input class="frm_input" id="input_category_name" type="text" maxlength="50"/>
        <input class="frm_btn" id="btn_category_submit" type="button" value="查询"/>
        <input class="frm_btn frm_clear" id="btn_clear" type="button" value="重置"/>
    </div>
    <div class="frm_group_last">
        <input class="frm_btn frm_add" id="btn_category_add" type="button" value="添加一个分类" onclick="getChildPage(null)"/>
        <input class="frm_btn frm_refresh" id="btn_category_refresh" type="button" value="刷新分类列表"/>
        <input class="frm_btn frm_danger" id="btn_product_delete" type="button" value="删除选中分类" onclick="deleteCategory()"/>
    </div>
</div>
<div class="data_count_div text_info">
    <i class="fa fa-search" aria-hidden="true"></i> 
    <span class="data_count_title">查看合计</span>
    <span>分类总数:</span>
    <span class="data_count_value" id="category_count_data">${requestScope.categoryCount}</span>
    <span class="data_count_unit">个</span>
</div>
<div class="table_normal_div">
    <table class="table_normal" id="table_category_list">
        <thead class="text_info">
        <tr>
            <th><input type="checkbox" class="cbx_select" id="cbx_select_all"><label for="cbx_select_all"></label></th>
            <th>分类名称</th>
            <th>操作</th>
            <th hidden class="category_id">分类ID</th>
        </tr>
        </thead>
        <tbody id="tbodyId">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <tr>
                <td><input type="checkbox" class="cbx_select" value="${category.id}" id="cbx_category_select_${category.id}"><label for="cbx_category_select_${category.id}"></label></td>
                <td title="${category.name}">${category.name}</td>
                <td><span class="td_special" title="查看分类详情"><a href="javascript:void(0)" onclick="getChildPage(this)">详情</a></span></td>
                <td hidden><span class="category_id">${category.id}</span></td>
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
