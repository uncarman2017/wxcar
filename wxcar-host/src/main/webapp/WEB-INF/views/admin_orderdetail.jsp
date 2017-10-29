<%--
  Created by IntelliJ IDEA.
  User: uncar_000
  Date: 2016-12-18
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单详情</title>
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
<form role="form" style="margin-outside: 10px" id="form1">
    <c:import url="top.jsp"></c:import>

    <div  class="panel-body" style="margin-top: 35px">

        <div class="form-group">
            <label for="spOrderID">订单编号</label>
            <span id="spOrderID" class="label label-success"><%=request.getParameter("orderId")%></span>
        </div>

        <div class="form-group">
            <label for="txtCustomerMobile">客人手机</label>
            <input type="text" class="form-control" id="txtCustomerMobile" placeholder="客人手机号码"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="txtDriverName">司机姓名</label>
            <input type="text" class="form-control" id="txtDriverName" placeholder="司机姓名"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="txtDriverMobile">司机手机</label>
            <input type="text" class="form-control" id="txtDriverMobile" placeholder="司机手机号码"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="txtUseTime">用车时间</label>
            <input type="datetime-local" class="form-control" id="txtUseTime" placeholder="格式:2016-12-25 12:00"
                   value=""/>
        </div>
        <div class="form-group">
            <label for="txtRemark">预订产品</label>
            <input type="text" class="form-control" id="txtRemark" placeholder="预订产品备注" value=""/>

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
            <input type="text" class="form-control" id="txtDepartLocation" placeholder="出发地点"
                   value=""/>

        </div>

        <div class="form-group">
            <label for="txtArriveLocation">送达地点</label>
            <input type="text" class="form-control" id="txtArriveLocation" placeholder="到达地点"
                   value=""/>

        </div>

        <div class="form-group">
            <label for="txtAddressDetail">详细地点</label>
            <input type="text" class="form-control" id="txtAddressDetail" placeholder="送达地点详情"
                   value="" />
        </div>

        <div class="form-group">
            <label for="txtCarLiscence">车牌</label>
            <input type="text" class="form-control" id="txtCarLiscence" placeholder="汽车牌照"
                   value=""/>
        </div>

        <div class="form-group">
            <label for="selOrderStatus">订单状态</label>
            <select class="form-control" id="selOrderStatus" placeholder="订单状态">
                <option value="Submitted">新建</option>
                <option value="Pending">进行中</option>
                <option value="Completed">完成</option>
                <option value="Cancelled">取消</option>
                <option value="Expired">已过期</option>
            </select>
        </div>

    </div>
    <div class="form-group" id="dvOpernation">
        <div class="row">
            <div class="col-xs-12">
                <input id="btnSave" type="button" class="btn btn-primary btn-block"
                       data-loading-text="保存中..."
                       value="保存"/>
            </div>

        </div>
    </div>
    <c:import url="navigator.jsp"></c:import>


</form>

<script type="text/javascript">
    $(document).ready(
        function () {
            var orderId = <%=request.getParameter("orderId")%>;
            operation.inquiry(orderId, null);


        });

    $("#btnSave").on(clickEventName, function () {

        if (operation.validate()) operation.save(this);
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
            request.orderId = parseInt($("#spOrderID").text());
            request.customerMobile = $("#txtCustomerMobile").val().trim();
            request.driverName = $("#txtDriverName").val();
            request.driverMobile = $("#txtDriverMobile").val().trim();
            request.useDateTime = $("#txtUseTime").val().replace("T"," ");
            request.remark = $("#txtRemark").val();
            request.productType = $("#selProductType").val();
            request.vehicleLevel = $("#selVehicleLevel").val();
            request.price = parseFloat($("#txtPrice").val().trim());
            request.departLocation = $("#txtDepartLocation").val();
            request.arriveLocation = $("#txtArriveLocation").val();
            request.addressDetail = $("#txtAddressDetail").val();
            request.carLiscence = $("#txtCarLiscence").val();
            request.orderStatus = $("#selOrderStatus").val();

            $.ajax({
                url: "./order/update",
                type: "POST",
                contentType: 'application/json',
                data: JSON.stringify(request),
                dataType: 'JSON',
                beforeSend: function (xhr) {

                },
                success: function (data) {
                    if ($btn != null) $btn.button("reset");
                    alertEx(data.resultMsg,2000);

                },
                error: function (data, textStatus, errorThrown) {
                    if ($btn != null) $btn.button("reset");
                    alertEx("服务端异常",2000);

                },
                timeout: 5000
            });
        },

        inquiry: function (orderId, btn) {
            var $btn = btn == null ? null : $(btn).button("loading");
            $.ajax({
                url: "./order/detail/" + orderId.toString(),
                type: "GET",
                contentType: 'application/json',
                dataType: 'JSON',
                beforeSend: function (xhr) {

                },
                success: function (data) {
                    if ($btn != null) $btn.button("reset");
                    $("#txtCustomerMobile").val(data.responseData.customerMobile);
                    $("#txtDriverName").val(data.responseData.driverName);
                    $("#txtDriverMobile").val(data.responseData.driverMobile);
                    $("#txtUseTime").val(data.responseData.useDateTime);
                    $("#txtRemark").val(data.responseData.remark);
                    $("#selProductType").get(0).selectedValue = data.responseData.productType;
                    $("#selVehicleLevel").get(0).selectedValue = data.responseData.vehicleLevel;
                    $("#txtPrice").val(data.responseData.price.toString());
                    $("#txtDepartLocation").val(data.responseData.departLocation);
                    $("#txtArriveLocation").val(data.responseData.arriveLocation);
                    $("#txtAddressDetail").val(data.responseData.addressDetail);
                    $("#txtCarLiscence").val(data.responseData.carLiscence);
                    $("#selOrderStatus").get(0).selectedValue = data.responseData.orderStatus;

                },
                error: function (data, textStatus, errorThrown) {
                    if ($btn != null) $btn.button("reset");

                },
                timeout: 5000

            });
        }

    };
</script>
</body>
</html>
