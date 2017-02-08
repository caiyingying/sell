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
        var node = $('#navTree').tree('find', 'order');
        $('#navTree').tree('select', node.target);

        $('#dg').datagrid({
            onLoadSuccess: function (data) {//加载完毕后获取所有的checkbox遍历
                if (data.rows.length > 0) {
                    //循环判断操作为新增的不能选择
                    for (var i = 0; i < data.rows.length; i++) {
                        //根据comfirm让某些行不可选,如果已经返现的，则不可以在进行返现操作
                        if (data.rows[i].comfirm == 1) {
                            $("input[type='checkbox']")[i + 1].disabled = true;
                        }
                    }
                }
            },
            onClickRow: function (rowIndex, rowData) {
                //加载完毕后获取所有的checkbox遍历
                $("input[type='checkbox']").each(function (index, el) {
                    //如果当前的复选框不可选，则不让其选中
                    if (el.disabled == true) {
                        $('#dg').datagrid('unselectRow', index - 1);
                    }
                })
            },
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
                    $("#totalPriceSpan").html(totalPrice);
                    $("#totalRebateSpan").html(totalRebate);
                }
            }
        });
    });

    //同步订单
    function syncOrder() {
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

    //订单返现
    function orderBack() {
        var checked = $('#dg').datagrid('getChecked');
        var l = checked.length;
        if (l == 0) {
            $.messager.alert('警告', '请选择至少一条数据!', 'warning');
        } else {
            $(function () {
                var totalPrice = 0;
                var totalRebate = 0;
                var ids = "";
                var param = {};
                for (i = 0; i < checked.length; i++) {
                    totalPrice = totalPrice + checked[i].totalPrice;
                    totalRebate = totalRebate + checked[i].rebate;
                    ids = ids + checked[i].id + ",";
                }
                param['ids'] = ids;
                $.messager.defaults = {ok: "是", cancel: "否"};
                $.messager.confirm("操作提示", "总金额汇总：" + totalPrice + " 返现金额汇总：" + totalRebate + " 您确定要执行返现操作吗？", function (data) {
                    if (data) {
                        //alter("是");
                        $.ajax({
                            type: "post",  //提交方式
                            dataType: "json", //数据类型
                            data: param,
                            async: false,
                            url: "order/doRebate", //请求url
                            success: function (data) { //提交成功的回调函数
                                if (data) {
                                    $.messager.alert('警告', '返现操作成功!', 'warning');
                                    $('#dg').datagrid('reload');
                                } else {
                                    $.messager.alert('警告', '返现操作失败!', 'warning');
                                    $('#dg').datagrid('reload');
                                }
                            }
                        });
                    } else {
                        //alert("否");
                    }
                });
            });
            //$('#queryForm').attr('action', 'user/edit');
            //$('#queryForm input[name=id]').val(checked[0].id);
            //$('#queryForm').submit();
            //location.href = '<%=basePath%>user/edit?id=' + checked[0].id;
        }
    }

    function query() {
        $('#dg').datagrid({
            queryParams: {
                userPhone: $('#userPhone').combobox("getValue"),
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

    /**
     function formatDatebox(value) {
        if (value == null || value == '') {
            return '';
        }
        var dt;
        if (value instanceof Date) {
            dt = value;
        } else {
            dt = new Date(value);
        }

        return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
    }
     */

    function formatDatebox(value, row, index) {
        if (value == 0 || value == "" || value == null) {
            return "";
        }
        var unixTimestamp = new Date(value);
        return unixTimestamp.toLocaleString();
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
                <th data-options="field:'comfirmDate',width:100,formatter: formatDatebox">返现时间</th>
                <th data-options="field:'orderId'">订单编号</th>
                <th data-options="field:'payTime'">付款时间</th>
            </tr>
            </thead>
        </table>

        <div id="tb">
            <div style="margin-bottom:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true"
                   onclick="syncOrder()">同步订单</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                   onclick="orderBack()">订单返现</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="query()">查询</a>
            </div>
            <form action="order/list" method="post" id="queryForm">
                <input type="hidden" name="id" value=""/>
                <table>
                    <tr>
                        <td>分销商:</td>
                        <td><input class="easyui-combobox" id="userPhone" data-options="
                            url:'dealers/selectData',
                            method:'post',
                            valueField:'phone',
                            textField:'name',
                            panelHeight:'auto'
                            ">
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