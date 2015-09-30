<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="table-responsive">
	<table id="busiLogTable" class="table table-bordered table-hover table-condensed mytable">
		<thead>
			<tr>
				<th width="6%" class="info">项目</th>
				<th width="13%" class="info">日期</th>
				<th width="4%" class="info">级别</th>
				<th width="6%" class="info">线程</th>
				<th class="info">名称</th>
				<th class="info">描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="log" items="${page.resultList}">
				<tr>
					<td class="busilog_qtip" title="${log.systemName }">${log.systemName }</td>
					<td><fmt:formatDate value="${log.createTime }" pattern="yy/MM/dd HH:mm:ss.SSS" /></td>
					<td>${log.level }</td>
					<td class="busilog_qtip" title="${log.threadName }">${log.threadName }</td>
					<td class="busilog_qtip" title="${log.logName }">${log.logName }</td>
					<td class="busilog_qtip" title="${log.message }">${log.message }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
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

    $('.busilog_qtip').qtip({
      position: {
        my: 'bottom left',
        at: 'top left'
      },
      style: {
        classes: 'qtip-blue qtip-rounded qtip-light'
      }
    });
  });
</script>





