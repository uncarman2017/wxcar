<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台管理-制单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <spring:url value="/assets/img/favicon.ico" var="icon"/>
    <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/assets/css/grumble.min.css" var="grumbleCss"/>

    <spring:url value="/assets/js/jquery-1.11.2.min.js" var="jqueryJs"/>
    <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/assets/js/utils.js" var="utilJs"/>
    <spring:url value="/assets/js/jquery.grumble.min.js" var="grumbleJs"/>
    <spring:url value="/assets/js/json2.min.js" var="jsonJs"/>


    <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${grumbleCss}" rel="stylesheet"/>

    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${jsonJs}"></script>
    <script type="text/javascript" src="${bootstrapJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${grumbleJs}"></script>
</head>
<body>

<form id="uploadForm">
    <input type="file" id="file1" style="visibility: hidden" accept="text/plain,text/comma-separated-values"/>
</form>
<form role="form">
    <c:import url="top.jsp"></c:import>
    <div class="panel-body">

        <div class="form-group">
            <label for="txtDriverName">司机姓名</label>
            <input type="text" class="form-control" id="txtDriverName" placeholder="司机姓名"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="txtCustomerMobile">客人手机</label>
            <input type="text" class="form-control" id="txtCustomerMobile" placeholder="客人手机号码"/>
        </div>
        <div class="form-group">
            <label for="txtDriverMobile">司机手机</label>
            <input type="text" class="form-control" id="txtDriverMobile" placeholder="司机手机号码"/>
        </div>
        <div class="form-group">
            <label for="txtUseTime">用车时间</label>
            <input type="datetime-local" class="form-control" id="txtUseTime" placeholder="格式:2016-12-25 12:00"/>
        </div>
        <div class="form-group">
            <label for="txtRemark">预订产品</label>
            <input type="text" class="form-control" id="txtRemark" placeholder="预订产品备注"/>
        </div>
        <div class="form-group">
            <label for="selProductType">产品类型</label>
            <select class="form-control" id="selProductType" placeholder="产品类型">
                <option value="JieJi">国内接机</option>
                <option value="SongJi">国内送机</option>
            </select>
        </div>
        <div class="form-group">
            <label for="selVehicleLevel">车型</label>
            <select class="form-control" id="selVehicleLevel" placeholder="车辆等级如经济型">
                <option value="Economy">经济型</option>
                <option value="Comfort">舒适型</option>
                <option value="Business">商务型</option>

            </select>
        </div>
        <div class="form-group">
            <label for="txtPrice">价格</label>
            <input type="text" class="form-control" id="txtPrice" placeholder="输入数字" value="0.0"/>

        </div>
        <div class="form-group">
            <label for="txtDepartLocation">出发地点</label>
            <input type="text" class="form-control" id="txtDepartLocation" placeholder="出发地点"/>
        </div>

        <div class="form-group">
            <label for="txtArriveLocation">送达地点</label>
            <input type="text" class="form-control" id="txtArriveLocation" placeholder="到达地点"/>
        </div>

        <div class="form-group">
            <label for="txtAddressDetail">详细地点</label>
            <input type="text" class="form-control" id="txtAddressDetail" placeholder="送达地点详情"/>
        </div>

        <div class="form-group">
            <label for="txtCarLiscence">车牌</label>
            <input type="text" class="form-control" id="txtCarLiscence" placeholder="汽车牌照"/>
        </div>


        <div class="form-group" id="dvOpernation">
            <div class="row">
                <div class="col-xs-6">
                    <input id="btnSave" type="button" class="btn btn-primary btn-block"
                           data-loading-text="保存中..."
                           value="保存"/>
                </div>
                <div class="col-xs-6">
                    <input id="btnClean" type="button" class="btn btn-success btn-block"
                           value="清空"/>
                </div>
            </div>
        </div>

    </div>
    <c:import url="navigator.jsp"></c:import>

</form>
<script type="text/javascript">
    $(document).ready(
        function () {

        });

    $("#btnSave").on(clickEventName, function () {
        if (operation.validate()) operation.save(this);
    });

    $("#btnClear").on(clickEventName, function () {
        operation.clearForm(this.form);
    });

    $("#file1").on("change", function () {
        operation.uploadFile();
    });

    var operation = {
        validate: function () {
            var result = true;
            if ($("#txtCustomerMobile").val().trim().length == 0 || !validateHelper.isNum($("#txtCustomerMobile").val().trim())) {
                $("#txtCustomerMobile").closest(".form-group").addClass('has-error');
                $("#txtCustomerMobile").grumble({
                    text: '手机号码必须是11位数字',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtCustomerMobile").closest(".form-group").removeClass('has-error');


            if ($("#txtDriverName").val().trim().length == 0) {
                $("#txtDriverName").closest(".form-group").addClass('has-error');
                $("#txtDriverName").grumble({
                    text: '司机姓名必填',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtDriverName").closest(".form-group").removeClass('has-error');


            if ($("#txtDriverMobile").val().trim().length == 0 || !validateHelper.isNum($("#txtDriverMobile").val().trim())) {
                $("#txtDriverMobile").closest(".form-group").addClass('has-error');
                $("#txtDriverMobile").grumble({
                    text: '手机号码必须是11位数字',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtDriverMobile").closest(".form-group").removeClass('has-error');

            if ($("#txtUseTime").val().trim().length == 0) {
                $("#txtUseTime").closest(".form-group").addClass('has-error');
                $("#txtUseTime").grumble({
                    text: '用车时间必填',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtUseTime").closest(".form-group").removeClass('has-error');

            if ($("#txtRemark").val().trim().length == 0) {
                $("#txtRemark").closest(".form-group").addClass('has-error');
                $("#txtRemark").grumble({
                    text: '备注必填',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtRemark").closest(".form-group").removeClass('has-error');

            if ($("#txtPrice").val().trim().length == 0 || !$("#txtPrice").val().trim().isNumeric(null)) {
                $("#txtPrice").closest(".form-group").addClass('has-error');
                $("#txtPrice").grumble({
                    text: '价格必须输入数字',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtPrice").closest(".form-group").removeClass('has-error');

            if ($("#txtDepartLocation").val().trim().length == 0) {
                $("#txtDepartLocation").closest(".form-group").addClass('has-error');
                $("#txtDepartLocation").grumble({
                    text: '上车地点必填',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtDepartLocation").closest(".form-group").removeClass('has-error');

            if ($("#txtArriveLocation").val().trim().length == 0) {
                $("#txtArriveLocation").closest(".form-group").addClass('has-error');
                $("#txtArriveLocation").grumble({
                    text: '下车地点必填',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtArriveLocation").closest(".form-group").removeClass('has-error');

            if ($("#txtCarLiscence").val().trim().length == 0) {
                $("#txtCarLiscence").closest(".form-group").addClass('has-error');
                $("#txtCarLiscence").grumble({
                    text: '汽车牌照必填',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtCarLiscence").closest(".form-group").removeClass('has-error');

            return result;
        },

        save: function (btn) {

            var $btn = btn == null ? null : $(btn).button("loading");
            var request = {};
            request.customerMobile = $("#txtCustomerMobile").val().trim();
            request.driverName = $("#txtDriverName").val();
            request.driverMobile = $("#txtDriverMobile").val().trim();
            request.useDateTime = $("#txtUseTime").val().replace("T", " ");
            request.remark = $("#txtRemark").val();
            request.productType = $("#selProductType").val();
            request.vehicleLevel = $("#selVehicleLevel").val();
            request.price = parseFloat($("#txtPrice").val().trim());
            request.departLocation = $("#txtDepartLocation").val();
            request.arriveLocation = $("#txtArriveLocation").val();
            request.addressDetail = $("#txtAddressDetail").val();
            request.carLiscence = $("#txtCarLiscence").val();
            request.orderStatus = "Submitted";

            $.ajax({
                url: "./order/create",
                type: "POST",
                contentType: 'application/json;charset=UTF8',
                data: JSON.stringify(request),
                dataType: 'JSON',
                beforeSend: function (xhr) {

                },
                success: function (data) {
                    if ($btn != null) $btn.button("reset");
                    alert(data.resultMsg);
                    //alertEx(data.resultMsg, 2000, document.body, "absolute");

                },
                error: function (data, textStatus, errorThrown) {
                    if ($btn != null) $btn.button("reset");
                    alert("服务端异常");
                    //alertEx("服务端异常", 2000, document.body, "absolute");

                },
                timeout: 5000
            });
        },

        clearForm: function (form) {
            $(':input', form).each(function () {
                var type = this.type;
                var tag = this.tagName.toLowerCase();
                if (type == 'text' || type == 'password' || tag == 'textarea')
                    this.value = "";
                else if (type == 'checkbox' || type == 'radio')
                    this.checked = false;
                else if (tag == 'select')
                    this.selectedIndex = -1;
            });
        },

        selectFile: function () {
            $('#file1').click();
        },

        uploadFile: function () {
            var fileName = $('#file1').val();
            if($('#file1')[0].files[0].size > 200*1024)  //大于200K不能上传
            {
                alertEx("上传文件大小不能超过200K!",2000);
                return;
            }
            var $btn = $("#btnSelectFile").button("loading");
            if (typeof(fileName) != "undefined" && fileName != "") {
                var formData = new FormData();
                formData.append('file', $('#file1')[0].files[0]);
//                var formData = new FormData($("#uploadForm").get(0));

                $.ajax({
                    url: './order/upload',
                    type: 'POST',
                    data: formData,
//                    async: true,
                    cache: false,
                    contentType: false,
                    dataType: 'JSON',
                    processData: false,
                    success: function (data) {
                        $btn.button("reset");
                        alertEx(data.resultMsg, 2000);
                    },
                    error: function (data, textStatus, errorThrown) {
                        $btn.button("reset");
                        alertEx("服务端异常!", 2000);
                    }
                });
            }

        }

    };
</script>
</body>
</html>
