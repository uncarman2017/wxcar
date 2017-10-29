<%--
  Created by IntelliJ IDEA.
  User: jiaweiyu
  Date: 2017/1/25
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--<header data-headroom data-tolerance="5" data-offset="205"--%>
<%--data-classes='{"initial":"animated","pinned":"slideDown","unpinned":"slideUp"}'>--%>

<nav class='navbar navbar-default navbar-fixed-top'>
    <div class="panel-heading">
        <div class="row">
            <div class="col-xs-10"><label>${viewNameCN}</label></div>
            <div class="col-xs-2"><span class="glyphicon glyphicon-home"
                                        onclick="location.href='./index'"></span></div>
        </div>
    </div>
    <%if (request.getServletPath().indexOf("/myorderlist") >= 0) {%>

    <div class="btn-group" role="group" aria-label="..." style="margin-left: 10px;width: 100%">

        <div class="btn-group" role="group">
            <select id="selOrderStatus" class="form-control">
                <option value="All">全部状态</option>
                <option value="Pending">进行中</option>
                <option value="Completed">完成</option>
                <option value="Cancelled">取消</option>
                <option value="Expired">过期</option>
            </select>
        </div>
        <div class="btn-group" role="group"><input type="date" class="form-control" id="txtQBeginDate"/></div>
        <div class="btn-group" role="group"><input type="date" class="form-control" id="txtQEndDate"/></div>
    </div>
    <%}%>
</nav>
<%--</header>--%>