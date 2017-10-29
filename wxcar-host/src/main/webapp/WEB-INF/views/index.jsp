<%--
  Created by IntelliJ IDEA.
  User: jiaweiyu
  Date: 2016/12/26
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <spring:url value="/assets/img/favicon.ico" var="icon"/>
    <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/assets/css/slidebars.min.css" var="slidebarsCss"/>
    <spring:url value="/assets/js/jquery-1.11.2.min.js" var="jqueryJs"/>
    <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/assets/js/slidebars.min.js" var="slidebarsJs"/>

    <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${slidebarsCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${bootstrapJs}"></script>
    <script type="text/javascript" src="${slidebarsJs}"></script>

</head>
<body>


<nav class='navbar navbar-default navbar-fixed-top'>
    <div class="panel-heading">
        <div class="row">
            <div class="col-xs-1"><span id="btnSldeBar" class="glyphicon glyphicon-book"></span></div>
            <div class="col-xs-9"><label>首页</label></div>
            <div class="col-xs-2"><span class="glyphicon glyphicon-home"
                                        onclick="location.href='./index'"></span></div>
        </div>
    </div>
</nav>

<div canvas="container" class="panel-body" style="margin-top: 35px">


    <div style="text-align: center;vertical-align:middle">
        <div style="margin-top: 50px"></div>
        <img src="./assets/img/lion.jpg" />
        <H2>MerryCar</H2>
    </div>


</div>

<c:import url="navigator.jsp"></c:import>
<c:import url="slidebar.jsp"></c:import>



<script type="text/javascript">
    (function ($) {
        // Create a new instance of Slidebars
        var controller = new slidebars();
        // Initialize Slidebars
        controller.init();

        $('#btnSldeBar').on('click', function (event) {
            event.stopPropagation();
            controller.open('slidebar-1');
        });


        $('body').on('click', '.panel-body',function (event) {
            event.stopPropagation();
            controller.close('slidebar-1');
        });


    })(jQuery);

</script>
</body>
</html>
