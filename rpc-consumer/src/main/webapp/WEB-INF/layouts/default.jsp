<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>示例系统-<sitemesh:write property='title' /></title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />

<c:set var="version_css" value="20131213" scope="application"></c:set>
<c:set var="version_js" value="20131213" scope="application"></c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/reset.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/site.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/container.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/grid.css?${version_css}" />

<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/header.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/image.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/input.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/button.css?${version_css}" />

<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/menu.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/divider.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/list.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/segment.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/dropdown.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/icon.css?${version_css}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/transition.css?${version_css}" />
<style type="text/css">
body {
	background-color: #FFFFFF;
	padding: 1em;
}

.ui.menu {
	margin: 3em 0em;
}

.ui.menu:last-child {
	margin-bottom: 110px;
}

.main.container {
	margin-top: 2em;
}

.main.menu {
	margin-top: 4em;
	border-radius: 0;
	border: none;
	box-shadow: none;
	transition: box-shadow 0.5s ease, padding 0.5s ease;
}

.main.menu .item img.logo {
	margin-right: 1.5em;
}

.overlay {
	float: left;
	margin: 0em 3em 1em 0em;
}

.overlay .menu {
	position: relative;
	left: 0;
	transition: left 0.5s ease;
}

.main.menu.fixed {
	background-color: #FFFFFF;
	border: 1px solid #DDD;
	box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.2);
}

.overlay.fixed .menu {
	left: 800px;
}

.text.container .left.floated.image {
	margin: 2em 2em 2em -4em;
}

.text.container .right.floated.image {
	margin: 2em -4em 2em 2em;
}

.ui.footer.segment {
	margin: 5em 0em 0em;
	padding: 5em 0em;
}
</style>
<script src="${ctx}/static/jquery/jquery-1.9.1.js?${version_js}"></script>
<script src="${ctx}/static/semantic/components/transition.js?${version_js}"></script>
<script src="${ctx}/static/semantic/components/dropdown.js?${version_js}"></script>
<script src="${ctx}/static/semantic/components/visibility.js?${version_js}"></script>

<script type="text/javascript">
  var _ctx = "${ctx}";
  $(document).ready(function() {
    $('.secondary.menu').visibility({
      type: 'fixed'
    });

    $('.ui.menu .ui.dropdown').dropdown({
      on: 'hover'
    });
    $('.ui.menu a.item').on('click', function() {
      $(this).addClass('active').siblings().removeClass('active');
    });
  });
</script>
<sitemesh:write property='head' />
</head>
<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<sitemesh:write property='body' />
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>