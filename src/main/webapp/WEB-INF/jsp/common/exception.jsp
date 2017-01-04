<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body>
	<h2 style="text-align: center;">
		<c:choose>
			<c:when test="${empty reason}">
				出错了，请重新登入系统后操作或联系管理员!
			</c:when>
			<c:otherwise>
				${reason}
			</c:otherwise>
		</c:choose>
	</h2>
</body>
</html>