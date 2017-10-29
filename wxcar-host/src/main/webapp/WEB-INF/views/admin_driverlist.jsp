<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台管理-司机列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <spring:url value="/assets/img/favicon.ico" var="icon"/>
    <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/assets/css/pullToRefresh.min.css" var="pullToRefreshCss"/>

    <spring:url value="/assets/js/jquery-1.11.2.min.js" var="jqueryJs"/>
    <spring:url value="/assets/js/jquery.validate.min.js" var="jqueryValJs"/>
    <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/assets/js/iscroll.min.js" var="iscrollJs"/>
    <spring:url value="/assets/js/pullToRefresh.min.js" var="pullToRefreshJs"/>
    <spring:url value="/assets/js/utils.js" var="utilJs"/>
    <spring:url value="/assets/js/handlebars-v4.0.5.min.js" var="handlebarsJs"/>
    <spring:url value="/assets/js/json2.min.js" var="jsonJs"/>


    <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${pullToRefreshCss}" rel="stylesheet"/>
    <link href="${skinCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${jsonJs}"></script>
    <script type="text/javascript" src="${jqueryValJs}"></script>
    <script type="text/javascript" src="${bootstrapJs}"></script>
    <script type="text/javascript" src="${iscrollJs}"></script>
    <script type="text/javascript" src="${pullToRefreshJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${handlebarsJs}"></script>

</head>

<body>

<form id="form1" role="form" style="margin-outside: 10px">
    <c:import url="top.jsp"></c:import>


    <div id="wrapper" class="panel-body" style="margin-top: 20px">
        <ul id="driverList">

        </ul>
    </div>

    <!--明细对话框-->
    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h5 class="modal-title" id="myModalLabel">司机详情编辑</h5>

                </div>
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group">
                            <label>司机编号</label>
                            <span id="detail_driverId"></span>
                        </div>
                        <div class="form-group">
                            <label for="detail_driverName">司机姓名</label>
                            <input type="text" class="form-control" id="detail_driverName"
                                   placeholder="司机中文姓名">
                        </div>
                        <div class="form-group">
                            <label for="detail_Mobile">司机手机</label>
                            <input type="text" class="form-control" id="detail_Mobile" placeholder="11位数字的司机手机号码">
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-xs-6">
                                    <label for="detail_driverType">司机类型</label>
                                    <div id="detail_driverType">
                                        <input id="detail_driverType_internal" type="radio" value="0"
                                               name="detail_driverType" checked>
                                        <span class="label label-danger">自营</span>
                                        <input id=detail_driverType_external type="radio" value="1"
                                               name="detail_driverType">
                                        <span class="label label-warning">外围</span>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <label for="detail_driverStatus">司机状态</label>
                                    <div id="detail_driverStatus">
                                        <input id="detail_driverStatus_enable" type="radio" value="0"
                                               name="detail_driverStatus"
                                               checked>
                                        <span class="label label-primary">有效</span>
                                        <input id="detail_driverStatus_disable" type="radio" value="1"
                                               name="detail_driverStatus">
                                        <span class="label label-danger">失效</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="detail_CarLiscence">牌照</label>
                            <input type="text" class="form-control" id="detail_CarLiscence" placeholder="输入汽车牌照">
                        </div>
                        <div class="form-group">
                            <label for="detail_Remark">备注</label>
                            <input type="text" class="form-control" id="detail_Remark" placeholder="输入司机的备注信息">
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success" data-dismiss="modal" aria-hidden="true">关闭</button>
                    <%--<input type="button" id="btnTest" onclick="alertEx('test',3000,document.body)" class="btn btn-primary" value="alert"/>--%>

                    <input type="button" id="btnSave" onclick="operation.save(this)" class="btn btn-primary"
                           data-loading-text="保存中..." value="保存"/>

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
    <li class="row" style="border-bottom-style: ridge">
        <div class="col-xs-8">
            <div>司机编号: <span name="spDriverID" class="label label-success">{{driverId}}</span></div>
            <div><span name="spName">{{name}}</span><a href=”tel://110 ” name="lnkMobile">{{mobile}}</a></div>
            <div><span name="spCarLiscence" class="label label-success">{{carLiscence}}</span>&nbsp;
                {{{getDriverStatus driverStatus}}}&nbsp;
                {{#compare driverType "Internal" operator="=="}}
                <span name="spDriverType" class="label label-danger">自营司机</span>
                {{/compare}}
                {{#compare driverType "External" operator="=="}}
                <span name="spDriverType" class="label label-warning">外围司机</span>
                {{/compare}}
            </div>
            <div><span name="spRemark">{{remark}}</span></div>

        </div>
        <div class="col-xs-2" style="vertical-align: middle;margin-top: 10px;">
            <input type="button" name="btnEdit" class="btn btn-primary" style="height:80px;width:80px;"
                   value="编辑"/>

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
            $("#txtQBeginDate").val(today.format("yyyy-MM-dd"));
            $("#txtQEndDate").val(today.dateAdd("d", 2).format("yyyy-MM-dd"));
            myTemplate = Handlebars.compile($("#table-template").html())
            operation.inquiry(myTemplate, null, false);
            Handlebars.registerHelper('getDriverStatus', function (driverStatus) {
                switch (driverStatus) {
                    case "Disable":
                        return "<span name='spDriverStatus' class='label label-danger'>失效</span>";
                    case "Enable" :
                        return "<span name='spDriverStatus' class='label label-primary'>有效</span>";
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

//            $("#detail_driverStatus_enable,#detail_driverStatus_disable").iCheck("uncheck");
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
        currentRow: null,

        edit: function (li) {
            this.currentRow = li;
            $('#myModal').modal('show');

            var driverId = li.find("span[name='spDriverID']").text();
            var name = li.find("span[name='spName']").text();
            var mobile = li.find("a[name='lnkMobile']").text();
            var carLiscense = li.find("span[name='spCarLiscence']").text();
            var driverStatus = li.find("span[name='spDriverStatus']").text();
            var driverType = li.find("span[name='spDriverType']").text();
            var remark = li.find("span[name='spRemark']").text();

            $("#myModal").find("#detail_driverId").text(driverId);
            $("#myModal").find("#detail_driverName").val(name);
            $("#myModal").find("#detail_Mobile").val(mobile);

            if (driverType == "自营司机") {
                $("#myModal").find("#detail_driverType_internal").get(0).checked = true;
            }
            else {
                $("#myModal").find("#detail_driverType_external").get(0).checked = true;
            }

            $("#myModal").find("#detail_CarLiscence").val(carLiscense);
            $("#myModal").find("#detail_Remark").val(remark);

            if (driverStatus == "有效") {
                $("#myModal").find("#detail_driverStatus_enable").get(0).checked = true;
            }
            else {
                $("#myModal").find("#detail_driverStatus_disable").get(0).checked = true;

            }

        },

        inquiry: function (myTemplate, btn, isNextPage) {
            var $btn = btn == null ? null : $(btn).button("loading");

            operation.setIsAdded(isNextPage);
            if (isNextPage) this.pageNo++;
            $.ajax({
                url: "./driver/getall?pageNo=" + this.pageNo.toString(),
                type: "GET",
                contentType: 'application/json',
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
                        isAdded ? $('#driverList').append(html) : $('#driverList').html(html);

                    }
//                    $("body").css("overflow", "auto");
//                    $("#cover").hide();
                    if ($btn != null) $btn.button("reset");
                    $("input[name='btnEdit']").on(clickEventName, function () {
                        operation.edit($(this).closest('li.row'));
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
        },//inquiry
        //保存
        save: function (btn) {
            var $btn = btn == null ? null : $(btn).button("loading");
            var request = {};
            request.driverId = $("#myModal").find("#detail_driverId").text();
            request.name = $("#myModal").find("#detail_driverName").val();
            request.mobile = $("#myModal").find("#detail_Mobile").val();
            request.driverType = $("#myModal").find("#detail_driverType_internal").get(0).checked ? "Internal" : "External";
            request.driverStatus = $("#myModal").find("#detail_driverStatus_enable").get(0).checked ? "Enable" : "Disable";
            request.carLiscence = $("#myModal").find("#detail_CarLiscence").val();
            request.remark = $("#myModal").find("#detail_Remark").val();

            $.ajax({
                url: "./driver/update",
                type: "POST",
                contentType: 'application/json',
                dataType: 'JSON',
                data: JSON.stringify(request),
                beforeSend: function (xhr) {

                },
                success: function (data) {
                    if ($btn != null) $btn.button("reset");
                    alertEx(data.resultMsg, 2000);
                    $('#myModal').modal('hide');
                    //更新列表行
                    var driverName = $("#myModal").find("#detail_driverName").val();
                    var mobile = $("#myModal").find("#detail_Mobile").val();
                    var driverType = $("#myModal").find("#detail_driverType_internal").get(0).checked ? "Internal" : "External";
                    var driverStatus = $("#myModal").find("#detail_driverStatus_enable").get(0).checked ? "Enable" : "Disable";
                    var carLiscence = $("#myModal").find("#detail_CarLiscence").val();
                    var remark = $("#myModal").find("#detail_Remark").val();
                    if (operation.currentRow != null) {
                        operation.currentRow.find("span[name='spName']").text(driverName);
                        operation.currentRow.find("span[name='lnkMobile']").text(mobile);
                        operation.currentRow.find("span[name='spCarLiscence']").text(carLiscence);

                        if (driverStatus == "Enable") {
                            operation.currentRow.find("span[name='spDriverStatus']").text("有效");
                            operation.currentRow.find("span[name='spDriverStatus']").attr("class", "label label-primary");
                        }
                        else {
                            operation.currentRow.find("span[name='spDriverStatus']").text("失效");
                            operation.currentRow.find("span[name='spDriverStatus']").attr("class", "label label-danger");

                        }
                        if (driverType == "Internal") {
                            operation.currentRow.find("span[name='spDriverType']").text("自营司机");
                            operation.currentRow.find("span[name='spDriverType']").attr("class", "label label-danger");
                        }
                        else {
                            operation.currentRow.find("span[name='spDriverType']").text("外围司机");
                            operation.currentRow.find("span[name='spDriverType']").attr("class", "label label-warning");
                        }
                        operation.currentRow.find("span[name='spRemark']").text(remark);

                    }
                },

                error: function (data, textStatus, errorThrown) {
                    if ($btn != null) $btn.button("reset");
                    alertEx("更新司机信息出错", 2000);

                },
                timeout: 10000
            });
        }
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
