<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String pathTitle = request.getContextPath();
%>
<div data-options="region:'north'" style="height: 60px">
	<div style="float: left;">
		<h1 style="margin-left: 40px">
			<div>缺陷管理系统</div>
		</h1>
	</div>
	<div style="float: right; margin: 30px 20px 10px 20px;"><span style="margin-right: 20px">当前登录人员：${sessionScope.userInfo.userName}(${sessionScope.userInfo.loginId})</span><a style="text-decoration:none" onclick="logout()" href="javascript:void();">登出</a></div>
</div>
<script type="text/javascript">
	function logout(){
		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
		//跳转到登录页面
		window.location.href = basePath + "/login/logout";
	}
</script>

