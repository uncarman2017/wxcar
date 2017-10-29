<%--
  Created by IntelliJ IDEA.
  User: MaxYu
  Date: 2016/12/11
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台管理-订单列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <spring:url value="/assets/img/favicon.ico" var="icon"/>
    <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/assets/css/pullToRefresh.min.css" var="pullToRefreshCss"/>
    <spring:url value="/assets/css/grumble.min.css" var="grumbleCss"/>
    <spring:url value="/assets/css/animate.min.css" var="animateCss"/>

    <spring:url value="/assets/js/jquery-1.11.2.min.js" var="jqueryJs"/>
    <spring:url value="/assets/js/jquery.validate.min.js" var="jqueryValJs"/>
    <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/assets/js/iscroll.min.js" var="iscrollJs"/>
    <spring:url value="/assets/js/pullToRefresh.min.js" var="pullToRefreshJs"/>
    <spring:url value="/assets/js/utils.js" var="utilJs"/>
    <spring:url value="/assets/js/handlebars-v4.0.5.min.js" var="handlebarsJs"/>
    <spring:url value="/assets/js/jquery.grumble.min.js" var="grumbleJs"/>
    <spring:url value="/assets/js/headroom.min.js" var="headRoomJs"/>
    <spring:url value="/assets/js/json2.min.js" var="jsonJs"/>


    <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${pullToRefreshCss}" rel="stylesheet"/>
    <link href="${grumbleCss}" rel="stylesheet"/>
    <link href="${animateCss}" rel="stylesheet"/>

    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${jsonJs}"></script>
    <script type="text/javascript" src="${jqueryValJs}"></script>
    <script type="text/javascript" src="${bootstrapJs}"></script>
    <script type="text/javascript" src="${iscrollJs}"></script>
    <script type="text/javascript" src="${pullToRefreshJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${handlebarsJs}"></script>
    <script type="text/javascript" src="${grumbleJs}"></script>
    <script type="text/javascript" src="${headRoomJs}"></script>

</head>


<body>


<form role="form">

    <c:import url="top.jsp"></c:import>


    <div id="wrapper" class="panel-body" style="margin-top: 20px">

        <ul id="orderList">


        </ul>
    </div>


    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h5 class="modal-title" id="myModalLabel">查询条件</h5>

                </div>
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group">
                            <label for="txtQOrderId">订单编号</label>
                            <input type=text class="form-control" id="txtQOrderId" placeholder="订单编号为数字" onblur="operation.validateField(this,'isNum');"/>
                        </div>
                        <div class="form-group">
                            <label for="txtQCustomerMobile">客人手机</label>
                            <input type="text" class="form-control" id="txtQCustomerMobile"
                                   placeholder="11位数字的客人手机号码" onblur="operation.validateField(this,'mobile');">
                        </div>
                        <div class="form-group">
                            <label for="txtQDriverMobile">司机手机</label>
                            <input type="text" class="form-control" id="txtQDriverMobile" placeholder="11位数字的司机手机号码" onblur="operation.validateField(this,'mobile');">
                        </div>
                        <div class="form-group">
                            <label for="txtQBeginDate">开始日期</label>
                            <input type="date" class="form-control" id="txtQBeginDate" placeholder="用车开始日期">
                        </div>
                        <div class="form-group">
                            <label for="txtQEndDate">结束日期</label>
                            <input type="date" class="form-control" id="txtQEndDate" placeholder="用车结束日期">
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success" data-dismiss="modal" aria-hidden="true">关闭</button>
                    <input type="button" id="btnInquiry" class="btn btn-primary" data-loading-text="查询中..." value="查询"/>

                </div>
            </div>
        </div>
    </div>
    <div id="cover" class="cover" style="vertical-align: middle"><h3 style="color: red;font-weight: bold">
        Loading...</h3></div>
    <c:import url="navigator.jsp"></c:import>

</form>
<script id="table-template" type="text/x-handlebars-template">
    {{#each responseData}}

    <li class="row">
        <div>订单编号: <span name="spOrderID" class="label label-success">{{orderId}}</span>
        </div>
        <div>乘客手机：<a href=”tel://110 ”>{{customerMobile}}</a></div>
        <div>司机姓名：{{driverName}}</div>
        <div>司机手机：<a href=”tel://110 ”>{{driverMobile}}</a></div>
        <div>用车时间：<span style="color:#398ab9">{{getUseDate useDateTime}}</span>&nbsp;<span
                class="label label-danger">{{getUseTime useDateTime}}</span></div>
        <div><span class="label label-success">出发</span>{{departLocation}}</div>
        <div><span class="label label-danger">到达</span>{{arriveLocation}}</div>
        <div>到达详情:{{addressDetail}}</div>
        <div>价格：<span class="label label-primary">{{carLiscence}}</span></div>
        <div>订单状态：<span class="label label-success">{{orderStatusName}}</span></div>
        <input type="button" name="btnDetail" class="btn btn-info" style="width: 93%"
                value="订单详情"/>

    </li>
    {{/each}}
</script>

<script type="text/javascript">
    var myTemplate = {};

    $(document).ready(
        function () {
            var today = new Date();
            $("#txtQBeginDate").val(today.format("yyyy-MM-dd"));
            $("#txtQEndDate").val(today.dateAdd("d", 2).format("yyyy-MM-dd"));
            myTemplate = Handlebars.compile($("#table-template").html())
            Handlebars.registerHelper('getUseDate', function (useDateTime) {
                return useDateTime.split(" ")[0];
            });
            Handlebars.registerHelper("getUseTime", function (useDateTime) {
                return useDateTime.split(" ")[1];
            });

            $("#btnInquiry").on("click",
                function () {
                    if (!operation.validateInquiryForm()) return;
                    operation.inquiry(myTemplate, this, false);
                    $('#myModal').modal('hide');
                    wrapper.refresh();

                });

            operation.inquiry(myTemplate, null, false);

        });



    var operation = {
        pageNo: 1,
        isAdded: false,
        setPageNo: function (pageNo) {
            this.pageNo = pageNo;
        },
        setIsAdded: function (isAdded) {
            this.isAdded = isAdded;
        },
        getIsAdded: function () {
            return this.isAdded;
        },
        nextPage: function () {
            this.pageNo++;
        },
        prevPage: function () {
            if (this.pageNo > 1) this.pageNo--;
        },

        //validateType:"isNum","mobile"
        validateField: function (fieldControl, validateType) {
            var val = $(fieldControl).val().trim();
            switch (validateType) {
                case "isNum":
                    if (val.length > 0 && !validateHelper.isNum(val)) {
                        $(fieldControl).grumble({
                            text: '必须输入数字！',
                            angle: 85,
                            distance: 10,
                            showAfter: 500,
                            hideAfter: 2000
                        });
                    }
                    break;
                case "mobile":
                    if (val.length > 0 && (!validateHelper.isNum(val) || !validateHelper.isLen(val, 11))) {
                        $(fieldControl).grumble({
                            text: '手机号码必须是11位数字！',
                            angle: 85,
                            distance: 10,
                            showAfter: 500,
                            hideAfter: 2000
                        });
                    }
                    break;
            }//switch
        },

        validateInquiryForm: function () {
            var orderId = $("#txtQOrderId").val().trim();
            var customerMobile = $("#txtQCustomerMobile").val().trim();
            var driverMobile = $("#txtQDriverMobile").val().trim();

            var result = true;
            if (orderId.length > 0 && !validateHelper.isNum(orderId)) {
                $("#txtQOrderId").closest(".form-group").addClass('has-error');
                result = false;
            }
            else
                $("#txtQOrderId").closest(".form-group").removeClass('has-error');

            if (customerMobile.length > 0 && (!validateHelper.isNum(customerMobile) || !validateHelper.isLen(customerMobile, 11))) {
                $("#txtQCustomerMobile").closest(".form-group").addClass('has-error');
                result = false;
            }
            else
                $("#txtQCustomerMobile").closest(".form-group").removeClass('has-error');

            if (driverMobile.length > 0 && (!validateHelper.isNum(driverMobile) || !validateHelper.isLen(driverMobile, 11))) {
                $("#txtQDriverMobile").closest(".form-group").addClass('has-error');
                result = false;
            }
            else
                $("#txtQDriverMobile").closest(".form-group").removeClass('has-error');

            return result;
        },

        inquiry: function (myTemplate, btn, isNextPage) {
            var $btn = btn == null ? null : $(btn).button("loading");

            var request = {
                orderId: parseInt($("#txtQOrderId").val().trim()),
                driverMobile: $("#txtQDriverMobile").val().trim(),
                customerMobile: $("#txtQCustomerMobile").val().trim(),
                beginDate: $("#txtQBeginDate").val().trim(),  //yyyy-MM-dd
                endDate: $("#txtQEndDate").val().trim(),//yyyy-MM-dd
                pageNo: isNextPage ? ++this.pageNo : this.pageNo
            };

            operation.setIsAdded(isNextPage);

            $.ajax({
                url: "order/inquire",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(request),
                dataType: 'JSON',
                beforeSend: function (xhr) {
//                    $("body").css("overflow", "hidden");
//                    $("#cover").show(100);

                },
                success: function (data) {
                    //输出列表信息
                    //console.info(data);
                    if (data.responseData.length == 0 && operation.getIsAdded()) operation.prevPage();
                    else {
                        var html = myTemplate(data);
                        var isAdded = operation.getIsAdded();
                        isAdded ? $('#orderList').append(html) : $('#orderList').html(html);

                    }
//                    $("body").css("overflow", "auto");
//                    $("#cover").hide();
                    if ($btn != null) $btn.button("reset");
                    $("input[name='btnDetail']").on(clickEventName,
                        function(){
                            var orderId = $(this).closest("li").find("span[name='spOrderID']").text();
                            location.href = "./admin_orderdetail?orderId=" + orderId;
                        });


                },
                error: function (data, textStatus, errorThrown) {
//                    $("body").css("overflow", "auto");
//                    $("#cover").hide();
                    if ($btn != null) $btn.button("reset");
                    if (operation.getIsAdded()) operation.prevPage();
                },
                timeout: 10000

            });
        }//inquiry
    };

</script>


<script type="text/javascript">
    refresher.init({
        id: "wrapper",//<------------------------------------------------------------------------------------┐
        pullDownAction: refresh,
        pullUpAction: load
    });
    function refresh() {
        setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
            operation.setPageNo(1);
            operation.inquiry(myTemplate, null, false);
            wrapper.refresh();

        }, 1000);

    }

    function load() {
        setTimeout(function () {// <-- Simulate network congestion, remove setTimeout from production!
            //分页查询,查询下一页
            operation.inquiry(myTemplate, null, true);
            wrapper.refresh();

        }, 1000);
    }
</script>


</body>
</html>
