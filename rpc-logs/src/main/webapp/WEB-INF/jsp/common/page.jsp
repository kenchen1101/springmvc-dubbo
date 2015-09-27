<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ul class="pagingFrom pagination">
	<input type="hidden" value="1" id="pIndex" name="currentPage" />
	<input type="hidden" value="${page.totalPage }" id="totalPage" />
	<input type="hidden" value="${page.currentPage }" id="curPage" />
	<li><span>共${page.totalPage }页 , 当前第${page.currentPage }页&nbsp;&nbsp;</span></li>

	<c:if test="${page.currentPage == 1 }">
		<li><span>首页</span></li>
		<li><span> 上一页 </span></li>
	</c:if>

	<c:if test="${page.currentPage > 1 }">
		<li><span class="paging" data-currentPage="1">首页 </span></li>
		<li><span class="paging" data-currentPage="${page.currentPage - 1 }"> 上一页 </span></li>
	</c:if>

	<c:if test="${page.currentPage == page.totalPage }">
		<li><span> 下一页 </span></li>
		<li><span> 尾页 </span></li>
	</c:if>

	<c:if test="${page.currentPage < page.totalPage }">
		<li><span class="paging" data-currentPage="${page.currentPage + 1 }"> 下一页 </span></li>
		<li><span class="paging" data-currentPage="${page.totalPage }"> 尾页 </span></li>
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
