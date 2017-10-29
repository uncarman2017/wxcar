<%--
  Created by IntelliJ IDEA.
  User: jiaweiyu
  Date: 2017/1/25
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <spring:url value="/assets/img/favicon.ico" var="icon"/>
    <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/assets/js/jquery-1.11.2.min.js" var="jqueryJs"/>

    <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${bootstrapJs}"></script>


</head>
<body>
<form role="form">
    <nav class='navbar navbar-default navbar-fixed-top'>
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-10"><label>出错了</label></div>
                <div class="col-xs-2"><span class="glyphicon glyphicon-home"
                                            onclick="location.href='./index'"></span></div>
            </div>
        </div>
    </nav>
    <div class="panel-body" style="margin-top: 35px">
        <div style="text-align: center;vertical-align:middle">
            <div style="margin-top: 50px"></div>
            <img src="./assets/img/lion.jpg"/>
            <h4><span class="label label-primary">${roleName}</span>不能访问<span
                    class="label label-primary">${viewNameCN}</span>页面！</h4>

        </div>


    </div>
    <c:import url="navigator.jsp"></c:import>
</form>
</body>
</html>
