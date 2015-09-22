<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="icon" href="${ctx}/static/images/favicon.ico">
<title>后台-<sitemesh:write property='title' /></title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery/plugins/css/jquery.scrollToTop.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/layout.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/plugins/js/jquery.scrollToTop.js" type="text/javascript"></script>
<script type="text/javascript">
  var _ctx = "${pageContext.request.contextPath}";
  jQuery(function() {
    jQuery(window).scrollToTop();
  });
</script>

<sitemesh:write property='head' />
</head>
<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div class="container">
			<div id="content">
				<sitemesh:write property='body' />
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>