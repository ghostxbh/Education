<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script>
        $(function () {
            //刷新下拉框
            $('#select_course_category').selectpicker('refresh');

            var course_id = $("#details_course_id").val();
            if (course_id!=null&&course_id!=''){
                //设置产品id
                $("#span_course_id").text('${requestScope.course.id}');
                //设置产品创建日期
                $("#span_course_create_date").text('${requestScope.course.insertTime}');
            }
            $("#btn_course_save").click(function () {
                //名称
                var course_name = $.trim($("#input_course_name").val());
                //内容
                var course_info = $.trim($("#input_course_info").val());
                //类别id
                var category_id = $("#select_course_category").selectpicker("val");
                //价格
                var course_price = $.trim($("#input_course_price").val());
                //讲师
                var lecturer = $.trim($("#input_course_lecturer").val());
                //产品状态
                var course_status = $("input[name='radio_course_status']:checked").val();
                //是否免费
                var course_free = $("input[name='radio_course_free']:checked").val();
                //课程周期
                var period = $.trim($("#input_course_period").val());
                //销售量
                var sales_volume = $.trim($("#input_course_sales_volume").val());
                //校验数据合法性
                var yn = true;
                if (course_status === undefined) {
                    styleUtil.errorShow($("#text_courseStatus_msg"), "请选择产品状态！");
                    yn = false;
                }
                if (course_free === undefined) {
                    styleUtil.errorShow($("#text_courseFree_msg"), "请选择是否免费！");
                    yn = false;
                }
                if (course_name === "") {
                    styleUtil.basicErrorShow($("#lbl_course_name"));
                    yn = false;
                }
                if (course_info === "") {
                    styleUtil.basicErrorShow($("#lbl_course_info"));
                    yn = false;
                }
                if (lecturer === "") {
                    styleUtil.basicErrorShow($("#lbl_course_lecturer"));
                    yn = false;
                }
                if (period === "") {
                    styleUtil.basicErrorShow($("#lbl_course_period"));
                    yn = false;
                }
                if (sales_volume === "" || isNaN(sales_volume)) {
                    styleUtil.basicErrorShow($("#lbl_course_sales_volume"));
                    yn = false;
                }
                if(course_free=="0"){
                    if (course_price === "" || isNaN(course_price)) {
                        styleUtil.basicErrorShow($("#lbl_course_price"));
                        yn = false;
                    }
                }else{
                    course_price = 0.00;
                }

                if (!yn) {
                    return;
                }
                //产品图片
                var thum_photo_url = "";
                var photo_url = "";
                var lecturer_photo_url = "";
                if($(".image_thum_photo").find('img').length!=0){
                    thum_photo_url = $(".image_thum_photo").find('img').attr('src');
                }
                if($(".image_detail_photo").find('img').length!=0){
                    photo_url = $(".image_detail_photo").find('img').attr('src');
                }
                if($(".image_lecturer_photo").find('img').length!=0){
                    lecturer_photo_url = $(".image_lecturer_photo").find('img').attr('src');
                }

                //数据集
                var dataList = {
                    "category_id": category_id,
                    "course_name": encodeURI(course_name),
                    "course_info": encodeURI(course_info),
                    "course_price": course_price,
                    "lecturer": encodeURI(lecturer),
                    "period": encodeURI(period),
                    "sales_volume": sales_volume,
                    "course_status": course_status,
                    "course_free": course_free,
                    "thum_photo_url": thum_photo_url,
                    "photo_url": photo_url,
                    "lecturer_photo_url": lecturer_photo_url
                };
                if (course_id === "") {
                    doAction(dataList, "../admin/course", "POST");
                } else {
                    doAction(dataList, "../admin/course/" + course_id, "POST");
                }
            });
            //改变产品状态时
            $('input:radio').click(function () {
                if ($(this).val() === "1") {
                    styleUtil.errorShow($("#text_productState_details_msg"), "提示：产品停售时无法进行交易");
                } else {
                    styleUtil.errorHide($("#text_productState_details_msg"));
                }
            });
            //单击取消按钮时
            $("#btn_course_cancel").click(function () {
                $(".menu_li[data-toggle=course]").click();
            });
            //获取到输入框焦点时
            $("input:text").focus(function () {
                styleUtil.basicErrorHide($(this).prev("label"));
            });
        });
        $("input[name='radio_course_free']").on('click',function () {
            if($("input[name='radio_course_free']:checked").val()=="1"){
                $("#input_course_price").attr("disabled",'disabled');
            }else if($("input[name='radio_course_free']:checked").val()=="0"){
                $("#input_course_price").remove('disabled');
            }
        });
        //图片上传
        function uploadImage(fileDom,photo) {
            //获取文件
            var file = fileDom.files[0];
            console.log(file);
            //判断类型
            var imageType = /^image\//;
            if (file === undefined || !imageType.test(file.type)) {
                $("#btn-ok").unbind("click").click(function () {
                    $("#modalDiv").modal("hide");
                });
                $(".modal-body").text("请选择图片！");
                $('#modalDiv').modal();
                return;
            }
            //判断大小
            if (file.size > 10240000) {
                $("#btn-ok").unbind("click").click(function () {
                    $("#modalDiv").modal("hide");
                });
                $(".modal-body").text("图片大小不能超过1M！");
                $('#modalDiv').modal();
                return;
            }
            /*var ul = $(fileDom).parents(".details_picList");
            var type;
            if (ul.attr("id") === "course_single_list") {
                type = "single";
            } else {
                type = "details";
            }*/
            //清空值
            $(fileDom).val('');
            var formData = new FormData();
            formData.append("file", file);
            //formData.append("imageType", type);
            //上传图片
            $.ajax({
                url: "/Onlineducation/admin/uploadCourseImage",
                type: "post",
                data: formData,
                contentType: false,
                processData: false,
                dataType: "json",
                mimeType: "multipart/form-data",
                success: function (data) {
                    console.log(data);
                    console.log($(fileDom).val());
                    $(fileDom).attr("disabled", false).prev("span").text("上传/更新");
                    if (data.state == 1) {
                        var fileUrl = data.data.fileUrl;
                        switch (photo){
                            case 'thum':
                                if($(".image_thum_photo .img_photo_url").find('img').length==0){
                                    $(".image_thum_photo .img_photo_url").append("<img src='"+fileUrl+"' id='img_thum_photo_url'>");
                                }else{
                                    $("#img_thum_photo_url").attr('src',fileUrl);
                                }
                                break;
                            case 'detail':
                                if($(".image_detail_photo .img_photo_url").find('img').length==0){
                                    $(".image_detail_photo .img_photo_url").append("<img src='"+fileUrl+"' id='img_detail_photo_url'>");
                                }else{
                                    $("#img_photo_url").attr('src',fileUrl);
                                }
                                break;
                            case 'lecturer':
                                if($(".image_lecturer_photo .img_photo_url").find('img').length==0){
                                    $(".image_lecturer_photo .img_photo_url").append("<img src='"+fileUrl+"' id='img_lecturer_photo_url'>");
                                }else{
                                    $("#img_lecturer_photo_url").attr('src',fileUrl);
                                }
                                break;
                        }
                    } else {
                        alert("图片上传异常！");
                    }
                },
                beforeSend: function () {
                    $(fileDom).attr("disabled", true).prev("span").text("图片上传中...");
                },
                error: function () {
                    alert("图片上传异常");
                }
            });
        }

        /*  //判断是否允许上传文件
          function checkFileUpload(obj, size) {
              if(obj.children("li:not(.details_picList_fileUpload,:hidden)").length>=size){
                  obj.children(".details_picList_fileUpload").css("display","none");
              } else {
                  obj.children(".details_picList_fileUpload").css("display","inline-block");
              }
          }
        */
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

        function deletePhoto(photoType){
            var course_id = $("#details_course_id").val();
            if(course_id!=null&&course_id!=''){
                $.ajax({
                    url: "/Onlineducation/admin/deletePhotoById",
                    type: "delete",
                    data: {
                        "course_id": course_id,
                        "image_type": photoType
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.state == 1) {
                            $("#seccuss").stop(true, true).animate({
                                opacity: 1
                            }, 550, function () {
                                $("#seccuss").animate({
                                    opacity: 0
                                }, 1500);
                            });
                        } else {
                            alert("图片删除异常！");
                        }
                    },
                    beforeSend: function () {
                        $(".img_delete_url").attr("disabled", true).text("删除中...")
                    },
                    error: function () {

                    }
                });
            }else{            	
            	var photo_url;            	
                switch (photoType){
                    case 'thum':
                    	photo_url = $("#img_thum_photo_url").attr('src');                    	
                        $(".img_photo_url img").remove();
                        break;
                    case 'detail':
                    	photo_url = $("#img_photo_url").attr('src');                    	
                    	$(".img_photo_url img").remove();
                        break;
                    case 'lecturer':
                    	photo_url = $("#img_lecturer_photo_url").attr('src');                    	 
                    	$(".img_photo_url img").remove();
                        break;
                }                               
                $.ajax({
                    url: "/Onlineducation/admin/deletePhoto",
                    type: "GET",
                    data:{"photo_url":photo_url},
                    dataType: "json",
                    success: function (data) {
                        if (data.state == 1) {
                            $("#seccuss").stop(true, true).animate({
                                opacity: 1
                            }, 550, function () {
                                $("#seccuss").animate({
                                    opacity: 0
                                }, 1500);
                            });
                        } else {
                            alert("图片删除异常！");
                        }
                    },
                    beforeSend: function () {
                        $(".img_delete_url").attr("disabled", true).text("删除中...")
                    },
                    error: function () {

                    }
                });
            }
        }
        /* function delPhotoUrl(photoUrl){
        	 $.ajax({
                 url: "/Onlineducation/admin/deletePhoto",
                 type: "delete",
                 data: {
                     "photo_url": photoUrl                        
                 },
                 success: function (data) {
                     if (data.state == 1) {
                         $("#seccuss").stop(true, true).animate({
                             opacity: 1
                         }, 550, function () {
                             $("#seccuss").animate({
                                 opacity: 0
                             }, 1500);
                         });
                     } else {
                         alert("图片删除异常！");
                     }
                 },
                 beforeSend: function () {
                     $(".img_delete_url").attr("disabled", true).text("删除中...")
                 },
                 error: function () {

                 }
             });
        } */
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
    </style>
</head>
<body>
<div class="details_div_first">
    <input type="hidden" value="${requestScope.course.id}" id="details_course_id"/>
    <div class="frm_div_last warn_height">
        <label class="frm_label text_info" id="lbl_course_category_id" for="select_course_category">产品类型</label>
        <select class="selectpicker" id="select_course_category" data-size="8">
            <c:forEach items="${requestScope.categoryList}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <label class="frm_label text_info" id="lbl_course_status" for="radio_course_status_true">产品状态</label>
        <input id="radio_course_status_true" name="radio_course_status" type="radio" value="1" checked>
        <label class="frm_label text_info" id="lbl_course_status_true" for="radio_course_status_true">上架</label>
        <input id="radio_course_status_false" name="radio_course_status" type="radio" value="0">
        <label class="frm_label text_info" id="lbl_course_status_false" for="radio_course_status_false">已下架</label>
        <span class="frm_error_msg" id="text_courseStatus_msg"></span>

        <label class="frm_label text_info" id="lbl_course_free" for="radio_course_free_true">是否免费</label>
        <input id="radio_course_free_true" name="radio_course_free" type="radio" value="0" checked>
        <label class="frm_label text_info" id="lbl_course_free_true" for="radio_course_free_true">否</label>
        <input id="radio_course_free_false" name="radio_course_free" type="radio" value="1">
        <label class="frm_label text_info" id="lbl_course_free_false" for="radio_course_free_false">是</label>
        <span class="frm_error_msg" id="text_courseFree_msg"></span>
    </div>
</div>
<div class="details_div">
    <span class="details_title text_info">基本信息</span>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_course_id">产品编号</label>
        <span class="details_value" id="span_course_id">系统指定</span>
        <label class="frm_label text_info" id="lbl_course_create_date">上架日期</label>
        <span class="details_value" id="span_course_create_date">系统指定</span>
    </div>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_course_name" for="input_course_name">产品名称</label>
        <input class="frm_input" id="input_course_name" type="text" maxlength="50"
               value="${requestScope.course.courseName}"/>
        <label class="frm_label text_info" id="lbl_course_info" for="input_course_info">产品标题</label>
        <input class="frm_input" id="input_course_info" type="text" maxlength="1000"
               value="${requestScope.course.courseInfo}"/>
        <label class="frm_label text_info" id="lbl_course_period" for="input_course_period">课程周期</label>
        <input class="frm_input details_unit" id="input_course_period" type="text" maxlength="20"
               value="${requestScope.course.period}"/>
    </div>
    <div class="frm_div_last">
        <label class="frm_label text_info" id="lbl_course_price" for="input_course_price">产品价格</label>
        <input class="frm_input details_unit" id="input_course_price" type="text" maxlength="10"
               value="${requestScope.course.price}"/>
        <span class="details_unit text_info">元</span>
        <label class="frm_label text_info" id="lbl_course_lecturer" for="input_course_lecturer">讲师</label>
        <input class="frm_input details_unit" id="input_course_lecturer" type="text" maxlength="20"
               value="${requestScope.course.lecturer}"/>
        <span class="details_unit text_info">&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label class="frm_label text_info" id="lbl_course_sales_volume" for="input_course_sales_volume">销售量</label>
        <input class="frm_input details_unit" id="input_course_sales_volume" type="text" maxlength="20"
               value="${requestScope.course.salesVolume}"/>
    </div>
</div>
<div class="images_list_div">
    <div class="image_thum_photo">
        <span class="image_title text_info">封面缩略图</span>
        <div class="img_photo_url">
            <img src="${requestScope.course.thumPhotoUrl}" id="img_thum_photo_url" name="coverPhoto_${requestScope.course.id}"/>
        </div>
        <div class="input_button">
            <span class="frm_btn frm_add fileinput-button">
                <span>上传/更新</span>
                <input type="file" class="input_image_file" onchange="uploadImage(this,'thum')" accept="image/*">
            </span>
            <input type="button" class="frm_btn frm-danger img_delete_url" onclick="deletePhoto('thum')" value="删除">
        </div>
    </div>
    <div class="image_detail_photo">
        <span class="image_title text_info">详情信息图</span>
        <div class="img_photo_url">
            <img src="${requestScope.course.photoUrl}" id="img_photo_url" name="coverPhoto_${requestScope.course.id}"/>
        </div>
        <div class="input_button">
            <span class="frm_btn frm_add fileinput-button">
                <span>上传/更新</span>
                <input type="file" class="input_image_file" onchange="uploadImage(this,'detail')" accept="image/*">
            </span>
            <input type="button" class="frm_btn frm-danger img_delete_url" onclick="deletePhoto('detail')" value="删除">
        </div>
    </div>
    <div class="image_lecturer_photo">
        <span class="image_title text_info">讲师图片</span>
        <div class="img_photo_url">
            <img src="${requestScope.course.lecturerPortraitUrl}" id="img_lecturer_photo_url" name="coverPhoto_${requestScope.course.id}"/>
        </div>
        <div class="input_button">
            <span class="frm_btn frm_add fileinput-button">
                <span>上传/更新</span>
                <input type="file" class="input_image_file" onchange="uploadImage(this,'lecturer')" accept="image/*">
            </span>
            <input type="button" class="frm_btn frm-danger img_delete_url" onclick="deletePhoto('lecturer')" value="删除">
        </div>
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