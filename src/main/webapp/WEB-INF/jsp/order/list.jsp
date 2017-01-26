<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>优达生物科技分销商管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/icon.css">
    <script type="text/javascript" src="static/js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/local/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/plugin/jquery-form/jquery.form.js"></script>
</head>
<body>
<script type="text/javascript">
    //选中菜单
    $(function(){
        var node = $('#navTree').tree('find', 'order');
        $('#navTree').tree('select', node.target);
    });

    function syncOrder(){
        $.ajax({
            type: 'post',
            url: 'order/sync',
            success: function (data) {
                if (!data.success) {
                    $.messager.alert('警告', data.reason, 'ERROR');
                } else {
                    $.messager.alert('提示', "同步订单成功!");
                    $('#dg').datagrid('reload');
                }
            }
        });
    }

    function query() {
        $('#dg').datagrid({
            queryParams: {
                userPhone: $('#userPhone').val(),
                itemName: $('#itemName').val(),
                comfirm: $('#comfirm').val(),
                orderId: $('#orderId').val()
            }
        });
    }

    function fEnabled(value, row, index) {
        var val = "";
        if (value == 0) {
            val = "禁用";
        } else if (value == 1) {
            val = "启用";
        }
        return val;
    }

</script>
<div class="easyui-layout" data-options="fit:true">
    <%@ include file="/WEB-INF/jsp/common/titleNav.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/sidebarNav.jsp" %>
    <div data-options="region:'center'">
        <table class="easyui-datagrid" id="dg"
               data-options="
		method:'post',
		url:'order/data',
		noheader:true,
		fit:true,
		rownumbers:true,
		border:false,
		toolbar:'#tb',
		pagination:true,
		pageSize:10,
		loadMsg:'请稍后...'
		">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'id',hidden:'true'"/>
                <th data-options="field:'userPhone',width:100">沙龙账号</th>
                <th data-options="field:'itemName',width:150">产品</th>
                <th data-options="field:'price',width:150">单价</th>
                <th data-options="field:'quantity',width:100">数量</th>
                <th data-options="field:'totalPrice',width:100">总金额</th>
                <th data-options="field:'totalPrice',width:100">折扣单价</th>
                <th data-options="field:'totalPrice',width:100">折扣金额</th>
                <th data-options="field:'totalPrice',width:100">返现金额</th>
                <th data-options="field:'comfirm',width:80">是否返现</th>
                <th data-options="field:'comfirmDate',width:100">返现时间</th>
                <th data-options="field:'orderId',width:100">订单编号</th>
            </tr>
            </thead>
        </table>

        <div id="tb">
            <div style="margin-bottom:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true"
                   onclick="syncOrder()">同步订单</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="query()">查询</a>
            </div>
            <form action="order/list" method="post" id="queryForm">
                <input type="hidden" name="id" value=""/>
                <table>
                    <tr>
                        <td>沙龙账号:</td>
                        <td><input type="text" id="userPhone"></td>
                        <td>产品名称:</td>
                        <td><input type="text" id="itemName"></td>
                        <td>是否返现:</td>
                        <td>
                            <select id="comfirm">
                                <option value=""></option>
                                <option value="0">禁用</option>
                                <option value="1">启用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>订单编号:</td>
                        <td><input type="text" id="orderId"></td>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>