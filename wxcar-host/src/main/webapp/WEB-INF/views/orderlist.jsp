<%--
  Created by IntelliJ IDEA.
  User: MaxYu
  Date: 2016/12/11
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>司机抢单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <spring:url value="/assets/img/favicon.ico" var="icon"/>
    <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/assets/css/pullToRefresh.min.css" var="pullToRefreshCss"/>
    <spring:url value="/assets/css/grumble.min.css" var="grumbleCss"/>

    <spring:url value="/assets/js/jquery-1.11.2.min.js" var="jqueryJs"/>
    <spring:url value="/assets/js/jquery.validate.min.js" var="jqueryValJs"/>
    <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/assets/js/iscroll.min.js" var="iscrollJs"/>
    <spring:url value="/assets/js/pullToRefresh.min.js" var="pullToRefreshJs"/>
    <spring:url value="/assets/js/utils.js" var="utilJs"/>
    <spring:url value="/assets/js/handlebars-v4.0.5.min.js" var="handlebarsJs"/>
    <spring:url value="/assets/js/jquery.grumble.min.js" var="grumbleJs"/>
    <spring:url value="/assets/js/json2.min.js" var="jsonJs"/>


    <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${pullToRefreshCss}" rel="stylesheet"/>
    <link href="${grumbleCss}" rel="stylesheet"/>

    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${jsonJs}"></script>
    <script type="text/javascript" src="${jqueryValJs}"></script>
    <script type="text/javascript" src="${bootstrapJs}"></script>
    <script type="text/javascript" src="${iscrollJs}"></script>
    <script type="text/javascript" src="${pullToRefreshJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${handlebarsJs}"></script>
    <script type="text/javascript" src="${grumbleJs}"></script>
</head>
<body>
<form role="form">
    <c:import url="top.jsp"></c:import>



    <div id="wrapper" class="panel-body" style="margin-top: 20px">
        <ul id="orderList">


        </ul>
    </div>

    <c:import url="navigator.jsp"></c:import>

</form>

<script id="table-template" type="text/x-handlebars-template">
    {{#each responseData}}

    <li class="row" style="border-bottom-style: ridge">
        <div class="col-xs-8">
            <div>订单编号: <span name="spOrderID" class="label label-success">{{orderId}}</span>&nbsp;<span
                    class="label label-info">{{getProductType productType}}</span>
            </div>
            <div>乘客手机：<a href=”tel://110 ”>{{customerMobile}}</a></div>
            <div>用车时间：<span style="color:#398ab9">{{getUseDate useDateTime}}</span>&nbsp;<span
                    class="label label-danger">{{getUseTime useDateTime}}</span></div>
            <div><span class="label label-success">出发</span>{{departLocation}}</div>
            <div><span class="label label-danger">到达</span>{{arriveLocation}}</div>
            <div>到达详情:{{addressDetail}}</div>
            <div>价格：<span class="label label-primary">{{price}}</span></div>
            <div>{{remark}}</div>
        </div>
        <div class="col-xs-2" style="vertical-align: middle;margin-top: 30px">
            <input type="button" name="btnCatch" data-loading-text="抢单中..."
                   style="width:80px;height:80px" ontouchstart="operation.catchOrder(this)" class="btn btn-danger" value="抢单"/>
        </div>
        <div class="col-xs-2"></div>
    </li>
    {{/each}}
</script>

<script type="text/javascript">
    var myTemplate = {};
    $(document).ready(
        function () {
            var today = new Date();
            myTemplate = Handlebars.compile($("#table-template").html())
            Handlebars.registerHelper('getUseDate', function (useDateTime) {
                return useDateTime.split(" ")[0];
            });
            Handlebars.registerHelper("getUseTime", function (useDateTime) {
                return useDateTime.split(" ")[1];
            });
            Handlebars.registerHelper('getProductType', function (productType) {
                switch (productType) {
                    case "JieJi":
                        return "国内接机";
                    case "SongJi":
                        return "国内送机";
                }
            });
            Handlebars.registerHelper('compare', function (lvalue, rvalue, options) {
                if (arguments.length < 3)
                    throw new Error("Handlerbars Helper 'compare' needs 2 parameters");

                var operator = options.hash.operator || "==";

                var operators = {
                    '==': function (l, r) {
                        return l == r;
                    },
                    '===': function (l, r) {
                        return l === r;
                    },
                    '!=': function (l, r) {
                        return l != r;
                    },
                    '<': function (l, r) {
                        return l < r;
                    },
                    '>': function (l, r) {
                        return l > r;
                    },
                    '<=': function (l, r) {
                        return l <= r;
                    },
                    '>=': function (l, r) {
                        return l >= r;
                    },
                    'typeof': function (l, r) {
                        return typeof l == r;
                    }
                }

                if (!operators[operator])
                    throw new Error("Handlerbars Helper 'compare' doesn't know the operator " + operator);

                var result = operators[operator](lvalue, rvalue);

                if (result) {
                    return options.fn(this);
                } else {
                    return options.inverse(this);
                }

            });

            operation.inquiry(myTemplate, null, false);

        });

    var operation = {
        pageNo: 1,
        isAdded: false,
        currentRow: null,
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
        catchOrder: function (btn) {
            var $btn = btn == null ? null : $(btn).button("loading");
            this.currentRow = $(btn).closest("li");
            var orderId = $(btn).closest("li").find("span[name='spOrderID']").text();

            if (window.confirm("确定接这一单吗?")) {
                $.ajax({
                    url: "order/catch?orderId=" + orderId,
                    type: "get",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'JSON',
                    beforeSend: function (xhr) {

                    },
                    success: function (data) {
                        if ($btn != null) $btn.button("reset");
                        if(operation.currentRow != null) {
                            operation.currentRow.hide(500);
                            operation.currentRow.remove();
                        }
                        alertEx(data.resultMsg, 2000);

                    },
                    error: function (data, textStatus, errorThrown) {
                        if ($btn != null) $btn.button("reset");
                        alertEx("服务端异常", 2000);
                    },
                    timeout: 10000

                });
            }//if
            else if ($btn != null) $btn.button("reset");

        },

        inquiry: function (myTemplate, btn, isNextPage) {
            var $btn = btn == null ? null : $(btn).button("loading");
            var today = new Date();
            var beginDate = today.format("yyyy-MM-dd");
            var pageNo = isNextPage ? ++this.pageNo : this.pageNo;
            operation.setIsAdded(isNextPage);

            $.ajax({
                url: "order/newlist?date=" + beginDate + "&pageNo=" + pageNo.toString(),
                type: "get",
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON',
                beforeSend: function (xhr) {
//                    $("body").css("overflow", "hidden");
//                    $("#cover").show(100);

                },
                success: function (data) {
                    //输出列表信息
                    if (data.responseData.length == 0 && operation.getIsAdded()) operation.prevPage();
                    else {
                        var html = myTemplate(data);
                        var isAdded = operation.getIsAdded();
                        isAdded ? $('#orderList').append(html) : $('#orderList').html(html);

                    }

                    if ($btn != null) $btn.button("reset");
//                    $("input[name='btnCatch']").bind(clickEventName, function (evt) {
//                        operation.catchOrder(this);
//                    });

                },
                error: function (data, textStatus, errorThrown) {
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
