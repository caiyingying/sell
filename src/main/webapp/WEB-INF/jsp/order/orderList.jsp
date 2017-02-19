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
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/bootstrap/easyui.css">
    <script type="text/javascript" src="static/js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/local/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/plugin/jquery-form/jquery.form.js"></script>
</head>
<body>
<script type="text/javascript">
    //选中菜单
    $(function () {
        var node = $('#navTree').tree('find', 'orderList');
        $('#navTree').tree('select', node.target);

        $('#dg').datagrid({
            onSelect: function (index, row) {
                var checked = $('#dg').datagrid('getChecked');
                var l = checked.length;
                if (l == 0) {

                } else {
                    var totalPrice = 0;
                    var totalRebate = 0;
                    for (i = 0; i < checked.length; i++) {
                        totalPrice = totalPrice + checked[i].totalPrice;
                        totalRebate = totalRebate + checked[i].rebate;
                    }
                    $("#totalPriceSpan").html(totalPrice);
                    $("#totalRebateSpan").html(totalRebate);
                }
            },
            onUnselect: function (index, row) {
                var checked = $('#dg').datagrid('getChecked');
                var l = checked.length;
                if (l == 0) {
                    $("#totalPriceSpan").html("");
                    $("#totalRebateSpan").html("");
                } else {
                    var totalPrice = 0;
                    var totalRebate = 0;
                    for (i = 0; i < checked.length; i++) {
                        totalPrice = totalPrice + checked[i].totalPrice;
                        totalRebate = totalRebate + checked[i].rebate;
                    }
                    $("#totalPriceSpan").html(totalPrice.toFixed(2));
                    $("#totalRebateSpan").html(totalRebate.toFixed(2));
                }
            },
            onSelectAll: function (rows){
                var totalPrice = 0;
                var totalRebate = 0;
                for (r = 0; r < rows.length; r++) {
                    if(rows[r].totalPrice != "" && rows[r].totalPrice != null){
                        totalPrice = totalPrice + rows[r].totalPrice;
                    }
                    if(rows[r].rebate != "" && rows[r].rebate != null){
                        totalRebate = totalRebate + rows[r].rebate;
                    }
                }
                $("#totalPriceSpan").html(totalPrice.toFixed(2));
                $("#totalRebateSpan").html(totalRebate.toFixed(2));
            },
            onUnselectAll: function (rows){
                $("#totalPriceSpan").html("");
                $("#totalRebateSpan").html("");
            }
        });
    });

    function query() {
        $('#dg').datagrid({
            queryParams: {
                itemId: $('#itemId').combobox("getValue"),
                comfirm: $('#comfirm').val(),
                orderId: $('#orderId').val(),
                payTimeBegin: $('#payTimeBegin').datebox('getValue'),
                payTimeEnd: $('#payTimeEnd').datebox('getValue')
            }
        });
    }

    function fEnabled(value, row, index) {
        var val = "";
        if (value == 0) {
            val = "否";
        } else if (value == 1) {
            val = "是";
        }
        return val;
    }
    //控制是否有选中框
    function fnCheck(value, row, index) {
        var val = true;
        if (row.comfirm == 1) {
            val = false;
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
		url:'order/orderData',
		noheader:true,
		fit:true,
		rownumbers:true,
		border:false,
		toolbar:'#tb',
		pagination:true,
		pageSize:50,
		loadMsg:'请稍后...'
		">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'id',hidden:'true'"/>
                <th data-options="field:'dealerName',width:100">分销商</th>
                <th data-options="field:'itemName',width:100">产品名称</th>
                <th data-options="field:'skuTitle',width:80">产品型号</th>
                <th data-options="field:'price',width:50">单价</th>
                <th data-options="field:'quantity',width:50">数量</th>
                <th data-options="field:'totalPrice',width:60">总金额</th>
                <th data-options="field:'discountPrice',width:80">折扣单价</th>
                <th data-options="field:'discountTotalPrice',width:80">折扣金额</th>
                <th data-options="field:'rebate',width:80">返现金额</th>
                <th data-options="field:'comfirm',width:60,formatter: fEnabled">是否返现</th>
                <th data-options="field:'comfirmDateStr',width:100">返现时间</th>
                <th data-options="field:'orderId'">订单编号</th>
                <th data-options="field:'payTime'">付款时间</th>
            </tr>
            </thead>
        </table>

        <div id="tb">
            <div style="margin-bottom:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="query()">查询</a>
            </div>
            <form action="order/orderList" method="post" id="queryForm">
                <input type="hidden" name="id" value=""/>
                <table>
                    <tr>
                        <td>产品:</td>
                        <td>
                            <input class="easyui-combobox" id="itemId" data-options="
                            url:'product/selectData',
                            method:'post',
                            valueField:'productId',
                            textField:'productName',
                            panelHeight:'auto'
                            ">
                        <td>是否返现:</td>
                        <td>
                            <select id="comfirm">
                                <option value=""></option>
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </td>
                        <td>订单编号:</td>
                        <td><input type="text" id="orderId"></td>
                    </tr>
                    <tr>
                        <td>付款日期:</td>
                        <td><input id="payTimeBegin" type="text" class="easyui-datebox"></td>
                        <td>至</td>
                        <td><input id="payTimeEnd" type="text" class="easyui-datebox"></td>
                    </tr>
                    <tr>
                        <td>总金额汇总:</td>
                        <td><span id="totalPriceSpan"></span></td>
                        <td>返现金额汇总:</td>
                        <td><span id="totalRebateSpan"></span></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>