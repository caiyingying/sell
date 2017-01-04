<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<style type="text/css">
.accordion .accordion-header-selected {
	background: #FFF;
}

.accordion .accordion-header .panel-title {
	color: #777;
}

ul.easyui-tree li {
	padding: 10px;
}
</style>
<%
	String pathTop = request.getContextPath();
%>
<div data-options="region:'west'" style="width: 260px;">
	<div id="accordion" class="easyui-accordion" data-options="fit:true,border:false,animate:false">
		<div title="我的工作台" style="padding: 10px">
			<ul class="easyui-tree">
				<li><a href="user/list">用户管理</a></li>
				<li><a href="">分销商管理</a></li>
				<li><a href="">客户管理</a></li>
				<li><a href="">产品管理</a></li>
				<li><a href="">销售统计分析</a></li>
			</ul>
		</div>
	</div>
	<form action="" method="post" id="navForm">
	
	</form>
	<script type="text/javascript">
	$('#systemConfig').tree({
		onClick: function(node){
			//alert(node.text);  // alert node text property when clicked
			//$("#navForm").attr("action","project/list?accordionTitle=系统管理").submit();
		}
	});
	</script>
</div>