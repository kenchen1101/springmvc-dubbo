<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>首页</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="<c:url value='/static/jquery/themes/base/jquery-ui.min.css?${version_css}'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<form:form class="form-horizontal">
		<div class="form-group">
			<div class="col-sm-2">
				<input type="text" class="form-control" name="keywords" id="keywords" />
			</div>
			<div class="col-sm-2">
				<button class="btn btn-default" type="button" id="searchBtn">搜索</button>
			</div>
		</div>
	</form:form>
	<div class="well	">
		<div class="row">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>项目</th>
						<th>日期</th>
						<th>级别</th>
						<th>线程</th>
						<th>名称</th>
						<th>描述</th>
					</tr>
				</thead>
				<tbody id="newsBody">
					<c:forEach var="log" items="${rpcLogs}">
						<tr>
							<td>${log.project }</td>
							<td>${log.timeStamp }</td>
							<td>${log.level }</td>
							<td>${log.threadName }</td>
							<td>${log.loggerName }</td>
							<td>${log.formattedMessage }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
