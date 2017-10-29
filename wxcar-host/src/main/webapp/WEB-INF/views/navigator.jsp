<%--
  Created by IntelliJ IDEA.
  User: jiaweiyu
  Date: 2017/1/24
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-footer">
    <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
        <div class="btn-group dropup">
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    后台管理
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="<%=request.getContextPath()%>/admin_createorder">下单</a></li>
                    <li><a href="<%=request.getContextPath()%>/admin_driverlist">司机名录</a></li>
                    <li><a href="<%=request.getContextPath()%>/admin_orderlist">订单查询</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-primary btn-block"
                        onclick="location.href='<%=request.getContextPath()%>/login';">登录
                </button>
            </div>

            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    司机功能
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="<%=request.getContextPath()%>/orderlist">抢单</a></li>
                    <li><a href="<%=request.getContextPath()%>/myorderlist">我的订单</a></li>
                </ul>
            </div>
            <%if (request.getServletPath().indexOf("/admin_orderlist") >= 0) {%>
            <div class="btn-group">
                <button class="btn btn-warning" onclick="$('#myModal').modal('show');">检索</button>
            </div>
            <%--<div class="btn-group">--%>
                <%--<input type="button" class="btn btn-info" value="alert" onclick="alertEx('测试',2000);"/>--%>
            <%--</div>--%>
            <%}%>
            <%if (request.getServletPath().indexOf("/admin_orderdetail") >= 0) {%>
            <div class="btn-group">
                <button class="btn btn-success" onclick="history.go(-1)">返回</button>
            </div>
            <%}%>
            <%if (request.getServletPath().indexOf("/admin_createorder") >= 0) {%>
            <div class="btn-group">
                <input type="button" id="btnSelectFile" class="btn btn-warning"  data-loading-text="上传中..."
                       data-toggle="tooltip" data-placement="top" title="选择CSV格式的订单数据文件批量导入数据" onclick="operation.selectFile()" value="导入订单"/>
            </div>
            <%}%>
        </div>
    </nav>


</div>
