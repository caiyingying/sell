<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String pathTitle = request.getContextPath();
%>
<div data-options="region:'north'" style="height: 80px">
	<div style="float: left;">
		<h1 style="margin-left: 40px">
			<div>缺陷管理系统</div>
		</h1>
	</div>
	<div style="float: right; margin: 30px 20px 10px 20px;"><span style="margin-right: 20px">当前用户：${sessionScope.userInfo.userName}(${sessionScope.userInfo.loginName})</span><a style="text-decoration:none" onclick="logout()" href="javascript:void();">登出</a></div>
	<input type="hidden" id="path" value="<%=basePath%>"/>
</div>
<script type="text/javascript">
	function logout(){
		//跳转到登录页面
		window.location.href = $("#path").val() + "login/logout";
	}
</script>

