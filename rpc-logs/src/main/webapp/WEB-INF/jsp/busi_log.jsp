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
<title>后台</title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap-submenu.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery/plugins/css/jquery.scrollToTop.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/layout.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap-submenu.js" type="text/javascript"></script>
<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/highlight.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/plugins/js/jquery.scrollToTop.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/menu.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/page.js" type="text/javascript"></script>
<script src="${ctx}/static/js/busiLog.js" type="text/javascript"></script>
<script type="text/javascript">
  var _ctx = "${pageContext.request.contextPath}";
  jQuery(function() {
    jQuery(window).scrollToTop();
  });
</script>

</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand">后台管理系统</a>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="col-sm-offset-1">
			<div class="row">
				<form action="${ctx }/query" id="queryForm">
					<div class="form-inline">
						<div class="form-group">
							<label for="systemName">所属系统</label><select class="form-control" id="systemName" name="systemName">
								<option>rpc-server</option>
								<option>rpc-web</option>
							</select>
						</div>
						<div class="form-group padding-left">
							<label for="beginTime">开始</label> <input type="text" class="form-control Wdate" id="beginTime" name="beginTime" value="" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
						</div>
						<div class="form-group padding-left">
							<label for="endTime">结束</label> <input type="text" class="form-control Wdate" id="endTime" name="endTime" value="" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
						</div>
						<div class="form-group padding-left">
							<label for="exampleInputName2">级别</label> <select class="form-control" id="level" name="level">
								<option value=""></option>
								<option value="trace">trace</option>
								<option value="debug">debug</option>
								<option value="info">info</option>
								<option value="warn">warn</option>
								<option value="error">error</option>
							</select>
						</div>
					</div>
					<br />
					<div class="form-inline">
						<div class="form-group padding-left">
							<label for="threadName">线程名</label> <input type="password" class="form-control" id="threadName" name="threadName" />
						</div>
						<div class="form-group padding-left">
							<label for="logName">类名</label> <input type="password" class="form-control" id="logName" name="logName" />
						</div>
						<div class="form-group padding-left">
							<label for="message">内容</label> <input type="password" class="form-control" id="message" name="message" />
						</div>
						<button type="button" class="btn btn-default" id="queryButton">查询</button>
					</div>
				</form>
			</div>
		</div>
		<br />
		<div class="col-sm-offset-1" id="busiLogBlock">
			<%@ include file="/WEB-INF/jsp/busi_log_page.jsp"%>
		</div>
		<div id="footer">
			<div class="container">
				<p class="text-center text-muted credit">
					Copyright &copy; 2008-2013 <a href="https://github.com/infowangxin/springmvc-dubbo">github.com</a>
				</p>
			</div>
		</div>
</body>
</html>