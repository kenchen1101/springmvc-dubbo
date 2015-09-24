<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>首页</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/jquery/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/static/jquery/ui/jquery-ui.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/ui/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/ui/jquery.ui.datepicker.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/ui/i18n/jquery.ui.datepicker-zh-CN.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/search.js" type="text/javascript"></script>

</head>
<body>

	<div class="col-sm-offset-1">
		<div class="row">
			<form:form class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">开始时间</label> <input type="text" class="form-control" id="beginTime" name="beginTime" readonly="readonly" />
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">结束时间</label> <input type="text" class="form-control" id="endTime" name="endTime" readonly="readonly" />
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form:form>
		</div>
	</div>
	<br />
	<div class="col-sm-offset-1">
		<div class="row ">
			<table class="table table-hover table-condensed">
				<thead>
					<tr>
						<!-- <th class="cos-sm-1">项目</th> -->
						<th class="cos-sm-1">日期</th>
						<th class="cos-sm-1">级别</th>
						<!-- <th class="cos-sm-1">线程</th> -->
						<th class="cos-sm-1">名称</th>
						<!-- <th class="cos-sm-1">描述</th> -->
					</tr>
				</thead>
				<tbody id="newsBody">
					<c:forEach var="log" items="${page.resultList}">
						<tr>
							<%-- <td>${log.systemName }</td> --%>
							<td><fmt:formatDate value="${log.createTime }" pattern="yy/MM/dd HH:mm:ss.SSS" /></td>
							<td>${log.level }</td>
							<%-- <td>${log.threadName }</td> --%>
							<td>${log.logName }</td>
							<%-- <td>${log.message }</td> --%>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="17"><form:form action="${ctx }/evaluate/search.htm" commandName="searchCommand" cssClass="form-horizontal" id="pagingFrom" method="POST">
								<ul class="pagingFrom pagination">
									<%-- 									<input type="hidden" value="${shopId }" name="shopId" /> --%>
									<%-- 									<input type="hidden" value="${searchCommand.evaluate }" name="evaluate" /> --%>
									<%-- 									<input type="hidden" value="${searchCommand.qaUserId }" name="qaUserId" /> --%>
									<%-- 									<input type="hidden" value="<fmt:formatDate value="${searchCommand.beginTime }" pattern="yyyy-MM-dd" />" name="beginTime" /> --%>
									<%-- 									<input type="hidden" value="<fmt:formatDate value="${searchCommand.endTime }" pattern="yyyy-MM-dd" />" name="endTime" /> --%>

									<!-- 									<input type="hidden" value="1" id="pIndex" name="pageIndex" /> -->
									<%-- 									<input type="hidden" value="${page.totalPage }" id="totalPage" /> --%>
									<input type="hidden" value="${page.currentPage }" id="currentPageIndex" />
									<li><span>共${page.totalPage }页 , 当前第${page.currentPage }页&nbsp;&nbsp;</span></li>

									<c:if test="${page.currentPage == 1 }">
										<li><span>首页</span></li>
										<li><span> 上一页 </span></li>
									</c:if>

									<c:if test="${page.currentPage > 1 }">
										<li><span class="paging" data-pageIndex="1">首页 </span></li>
										<li><span class="paging" data-pageIndex="${page.currentPage - 1 }"> 上一页 </span></li>
									</c:if>

									<c:if test="${page.currentPage == page.totalPage }">
										<li><span> 下一页 </span></li>
										<li><span> 尾页 </span></li>
									</c:if>

									<c:if test="${page.currentPage < page.totalPage }">
										<li><span class="paging" data-pageIndex="${page.currentPage + 1 }"> 下一页 </span></li>
										<li><span class="paging" data-pageIndex="${page.totalPage }"> 尾页 </span></li>
									</c:if>
								</ul>
								<ul class="pagingFrom pagination">
									<li><span class="pagingIndex paging">跳转至 </span></li>
								</ul>
								<ul class="pagingFrom pagination">
									<span class="paging-input"><input class="input-mini-s" id="pagingIndex" /></span>
								</ul>
								<ul class="pagingFrom pagination">
									<li><span>页</span></li>
								</ul>
							</form:form></td>
					</tr>
				</tfoot>
			</table>
		</div>

	</div>

</body>
</html>
