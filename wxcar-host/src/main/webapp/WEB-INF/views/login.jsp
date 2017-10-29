<%--
  Created by IntelliJ IDEA.
  User: MaxYu
  Date: 2016/12/11
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>司机登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <spring:url value="/assets/img/favicon.ico" var="icon"/>
    <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/assets/css/bootstrap-switch.min.css" var="bootstrapSwitchCss"/>
    <spring:url value="/assets/css/grumble.min.css" var="grumbleCss"/>

    <spring:url value="/assets/js/jquery-1.11.2.min.js" var="jqueryJs"/>
    <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/assets/js/bootstrap-switch.min.js" var="bootstrapSwitchJs"/>
    <spring:url value="/assets/js/utils.js" var="utilJs"/>
    <spring:url value="/assets/js/jquery.grumble.min.js" var="grumbleJs"/>
    <spring:url value="/assets/js/json2.min.js" var="jsonJs"/>


    <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${bootstrapSwitchCss}" rel="stylesheet"/>
    <link href="${grumbleCss}" rel="stylesheet"/>

    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${jsonJs}"></script>
    <script type="text/javascript" src="${bootstrapJs}"></script>
    <script type="text/javascript" src="${bootstrapSwitchJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${grumbleJs}"></script>

</head>
<body>

<form role="form">
    <%--<div class="panel panel-default" id="panel1">--%>
        <c:import url="top.jsp"></c:import>

        <div class="panel-body" style="margin-top: 35px">
            <div class="form-group">
                <label for="txtMobile">手机号</label>
                <input type="text" class="form-control" id="txtMobile" placeholder="输入11位手机号码">
            </div>
            <div class="form-group">
                <label for="txtPwd">口令</label>
                <input type="password" class="form-control" id="txtPwd" placeholder="输入登录口令">

            </div>
            <div class="form-group">
                <label for="chkIsAdmin">管理员登录</label>
                <input type="checkbox" id="chkIsAdmin" data-size="small" data-on-text="是" data-off-text="否"/>
            </div>
            <div class="form-group">
                <input type="button" id="btnLogin" style="width:100%" class="btn btn-primary btn-block"
                       data-loading-text="登录中..." value="登录"/>

            </div>
        </div>
    <%--</div>--%>

    <c:import url="navigator.jsp"></c:import>

</form>

<script type="text/javascript">
    $(document).ready(function () {
        $("#chkIsAdmin").bootstrapSwitch();
        $("#chkIsAdmin").get(0).checked = false;

    });


    $("#btnLogin").on("click",
        function () {
            var userModel = {};
            userModel.mobile = $("#txtMobile").val();
            userModel.password = $("#txtPwd").val();
            userModel.isAdmin = $("#chkIsAdmin").get(0).checked;

            var result = true;
            if ($("#txtMobile").val().length == 0 || !validateHelper.isNum($("#txtMobile").val())) {
                $("#txtMobile").closest(".form-group").addClass('has-error');
                $("#txtMobile").grumble({
                    text: '手机号码必须是11位数字！',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtMobile").closest(".form-group").removeClass('has-error');

            if ($("#txtPwd").val().length == 0 ) {
                $("#txtPwd").closest(".form-group").addClass('has-error');
                $("#txtPwd").grumble({
                    text: '登录口令不能为空！',
                    angle: 85,
                    distance: 10,
                    showAfter: 500,
                    hideAfter: 2000
                });
                result = false;
            }
            else
                $("#txtPwd").closest(".form-group").removeClass('has-error');

            if(! result) return;

            var $btn = $(this).button("loading");
            $.ajax({
                url: "./login",
                type: "post",
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON',
                data: JSON.stringify(userModel),
                beforeSend: function (xhr) {

                },
                success: function (data) {
                    if ($btn != null) $btn.button("reset");
                    if (data.result)  location.href = $("#chkIsAdmin").get(0).checked ? "./admin_orderlist" : "./orderlist";
                    else alertEx(data.resultMsg, 2000);
                },
                error: function (data, textStatus, errorThrown) {
                    if ($btn != null) $btn.button("reset");
                    alertEx("服务端异常", 2000);
                },
                timeout: 10000

            });


        });
</script>
</body>
</html>
