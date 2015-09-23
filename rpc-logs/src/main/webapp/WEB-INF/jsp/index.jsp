<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>首页</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%-- <link href="<c:url value='/static/jquery/themes/base/jquery-ui.min.css?${version_css}'/>" rel="stylesheet" type="text/css" /> --%>
</head>
<body>

	<div class="col-sm-offset-1">
		<div class="row">
			<form:form class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">项目</label> <input type="text" class="form-control" id="exampleInputName2" placeholder="项目">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">名称</label> <input type="text" class="form-control" id="exampleInputEmail2" placeholder="名称">
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form:form>
		</div>
	</div>
	<br />
	<div class="col-sm-offset-1">
		<div class="row ">
			<table class="table table-hover table-condensed col-sm-4 broder1">
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
