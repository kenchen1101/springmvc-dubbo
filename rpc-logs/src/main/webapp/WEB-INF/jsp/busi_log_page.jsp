<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="table-responsive">
		<table class="table table-bordered table-hover table-condensed mytable">
			<thead>
				<tr>
					<th width="20px;" align="left">项目</th>
					<th width="40px;" align="left">日期</th>
					<th width="10px;" align="left">级别</th>
					<th width="20px;" align="left">线程</th>
					<th width="50px;" align="left">名称</th>
					<th width="300px;" align="left">描述</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="log" items="${page.resultList}">
					<tr>
						<td>${log.systemName }</td>
						<td><fmt:formatDate value="${log.createTime }" pattern="yy/MM/dd HH:mm:ss.SSS" /></td>
						<td>${log.level }</td>
						<td>${log.threadName }</td>
						<td>${log.logName }</td>
						<td>${log.message }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="row">
	<div class="blockquote-reverse">
		<form:form action="${ctx }/query" id="busiLogDto">
			<!-- 查询条件，用隐藏表单域 -->
			<input type="hidden" value="${busiLog.systemName }" name="systemName" />
			<input type="hidden" value="<fmt:formatDate value="${busiLog.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" />" name="beginTime" />
			<input type="hidden" value="<fmt:formatDate value="${busiLog.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />" name="endTime" />
			<input type="hidden" value="${busiLog.level }" name="level" />
			<input type="hidden" value="${busiLog.threadName }" name="threadName" />
			<input type="hidden" value="${busiLog.logName }" name="logName" />
			<input type="hidden" value="${busiLog.message }" name="message" />
			<%@ include file="/WEB-INF/jsp/common/page.jsp"%>
		</form:form>
	</div>
</div>
<script type="text/javascript">
  $(function() {
    jpage.queryPage('busiLogDto', 'busiLogBlock');
  });
</script>





