<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <script type="text/javascript" src="static/plugin/jquery-validate/jquery.validate.min-1.14.js"></script>
</head>
<body>
<shiro:hasPermission name="user:nav">
<script type="text/javascript">
    //选中菜单
    $(function () {
        var node = $('#navTree').tree('find', 'user');
        $('#navTree').tree('select', node.target);

        //检查输入的两次密码是否一致
        $.validator.addMethod('checkPwd', function(value, element) {
            var newPassword = $("#newPassword").val();
            var newPasswordRepeat = $("#newPasswordRepeat").val();
            if (null != newPassword && "" != $.trim(newPassword)
                    && null != newPasswordRepeat && "" != $.trim(newPasswordRepeat)
                    && newPassword == newPasswordRepeat) {
                return true;
            } else {
                return false;
            }
        });

        $('#dg').datagrid({
            onDblClickRow: function (index,row) {//双击编辑
                $('#queryForm').attr('action', 'user/edit');
                $('#queryForm input[name=id]').val(row.id);
                $('#queryForm').submit();
                }
            });
    });

    function query() {
        $('#dg').datagrid({
            queryParams: {
                loginName: $('#loginName').val(),
                userName: $('#userName').val(),
                enabled: $('#enabled').val()
            }
        });
    }
    function edit() {
        var checked = $('#dg').datagrid('getChecked');
        var l = checked.length;
        if (l == 0) {
            $.messager.alert('警告', '请选择一条数据!', 'warning');
        } else if (l > 1) {
            $.messager.alert('警告', '只能同时编辑一条数据!', 'warning');
        } else {
            $('#queryForm').attr('action', 'user/edit');
            $('#queryForm input[name=id]').val(checked[0].id);
            $('#queryForm').submit();
            //location.href = '<%=basePath%>user/edit?id=' + checked[0].id;
        }
    }
    /**
     * 重置密码
     */
    function resetPassword() {
        var checked = $('#dg').datagrid('getChecked');
        var l = checked.length;
        if (l == 0) {
            $.messager.alert('警告', '请选择一个用户!', 'warning');
        } else if (l > 1) {
            $.messager.alert('警告', '一次只能重置一个用户数据!', 'warning');
        } else {
            $('#dlg_password').dialog('open');
        }
    }
    /**
     * 重置密码
     */
    function doResetPwd() {
        var checked = $('#dg').datagrid('getChecked');
        var l = checked.length;
        if (l == 0) {
            $.messager.alert('警告', '请选择一个用户!', 'warning');
        } else if (l > 1) {
            $.messager.alert('警告', '一次只能重置一个用户数据!', 'warning');
        } else {
            var validate = $('#updatePasswordForm').validate({
                rules: {
                    userPassword: {required:true, maxlength:10},
                    userPasswordRepeat: {required:true, maxlength:10, checkPwd:true},
                },
                messages: {
                    userPassword: {required:"必填字段!", maxlength:"最多10个字符"},
                    userPasswordRepeat: {required:"必填字段!", maxlength:"最多10个字符", checkPwd:"两次密码不一致"}
                },
                highlight: function (element) {
// 				$(element).parent('td').find('label').before('<br>');
                },
                success: function (element) {
                }
            });

            if (validate.form()) {
                var arr = $('#updatePasswordForm').serializeArray();
                var param = {};
                for (var i = 0; i < arr.length; i++) {
                    param[arr[i].name]=arr[i].value;
                }
                var userId = checked[0].id;
                param['id'] = userId;
                $.ajax({
                    type:"post",  //提交方式
                    dataType:"json", //数据类型
                    data:param,
                    async : false,
                    url:"user/updatePassword", //请求url
                    success:function(data){ //提交成功的回调函数
                        if (data) {
                            $('#dlg_password').dialog('close');
                            $("#newPassword").val("");
                            $("#newPasswordRepeat").val("");
                            $.messager.alert('警告', '重置成功!', 'warning');
                        } else {
                            $.messager.alert('警告', '重置失败!', 'warning');
                        }
                    }
                });
            }
        }
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
		url:'user/data',
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
                <th data-options="field:'loginName',width:100">登录账号</th>
                <th data-options="field:'userName',width:100">姓名</th>
                <th data-options="field:'mobile',width:150">手机号</th>
                <th data-options="field:'wechat',width:150">微信号</th>
                <th data-options="field:'enabled',width:80,formatter:fEnabled">是否启用</th>
            </tr>
            </thead>
        </table>

        <div id="tb">
            <div style="margin-bottom:5px">
                <a href="user/edit" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                   onclick="edit()">编辑</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true"
                   onclick="resetPassword()">重置密码</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="query()">查询</a>
            </div>
            <form action="user/list" method="post" id="queryForm">
                <input type="hidden" name="id" value="" id="checkUserId"/>
                <table>
                    <tr>
                        <td>登录账号:</td>
                        <td><input type="text" id="loginName"></td>
                        <td>姓名:</td>
                        <td><input type="text" id="userName"></td>
                        <td>是否启用:</td>
                        <td>
                            <select id="enabled">
                                <option value=""></option>
                                <option value="0">禁用</option>
                                <option value="1">启用</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<div id="dlg_password" class="easyui-dialog" title="重置密码" style="width:400px;height:150px;padding:10px;"
     data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons',
				closed:true
			">
    <form id="updatePasswordForm" action="user/updatePassword" method="post">
        <input type="hidden" name="id">
        <table>
            <tr>
                <td>新密码:</td>
                <td><input type="password" name="userPassword" id="newPassword"></td>
            </tr>
            <tr>
                <td>再输入一次:</td>
                <td><input type="password" name="userPasswordRepeat" id="newPasswordRepeat"></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="doResetPwd()">重置</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       onclick="javascript:$('#dlg_password').dialog('close')">取消</a>
</div>
</shiro:hasPermission>
<shiro:lacksPermission name="user:nav">
    您没有权限访问，请联系管理员。
</shiro:lacksPermission>
</body>
</html>