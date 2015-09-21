<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="ui secondary menu">
	<div class="right menu">
		<div class="ui dropdown item">
			<shiro:principal />
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">修改密码</div>
				<div class="divider"></div>
				<div class="item">退出</div>
			</div>
		</div>
	</div>
</div>
<div class="ui styled accordion">
	<div class="title">
		<i class="dropdown icon"></i> What is a dog?
	</div>
	<div class="content">
		<p class="transition hidden">狗算是一种家畜。以其忠诚与忠心广受人类欢迎, 可以在世界各地的许多家庭里发现这个受欢迎的客人。</p>
	</div>
	<div class="title active">
		<i class="dropdown icon"></i> What kinds of dogs are there?
	</div>
	<div class="content active">
		<p class="transition visible">狗有许多品种，每个品种都有不同的大小及性格，饲主通常选择适合自己生活方式的品种当作自已的伙伴，其中最受欢迎的品种绝对是吉娃娃。</p>
	</div>
	<div class="title">
		<i class="dropdown icon"></i> How do you acquire a dog?
	</div>
	<div class="content">
		<p>通常有三种方式可以取得一只狗狗，宠物店、私人饲主或是动物之家。 (请以领养代替购买)</p>
		<p>你可以在动物之家内领养一只健康的小狗，比起在宠物店买，收容所中的宠物比较不容易有近亲交配出现的缺陷问题，而且领养一只小狗的费用远比你在宠物店中购买便宜得多。</p>
	</div>
</div>