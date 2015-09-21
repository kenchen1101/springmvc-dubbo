<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${ctx}/static/images/favicon.ico">
<title>日志系统-<sitemesh:write property='title' /></title>
<link href="${ctx}/static/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${ctx }/static/css/blog.css" rel="stylesheet">

<sitemesh:write property='head' />
</head>
<body>
	<div class="blog-masthead">
		<div class="container">
			<nav class="nav blog-nav">
				<a class="nav-link active" href="${ctx }">首页</a>
			</nav>
		</div>
	</div>
	<sitemesh:write property='body' />
	<footer class="blog-footer">
		<p>Company copyright</p>
	</footer>
	<script src="${ctx}/static/jquery/jquery-1.9.1.js"></script>
	<script src="${ctx}/static/bootstrap/js/bootstrap.js"></script>
	<script src="${ctx }/static/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>