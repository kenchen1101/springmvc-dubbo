<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />
<!-- Site Properities -->
<title>后台系统登录</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/reset.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/site.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/container.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/grid.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/header.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/image.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/menu.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/divider.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/segment.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/form.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/input.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/button.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/list.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/message.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/semantic/components/icon.css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js"></script>
<script src="${ctx}/static/semantic/components/form.js"></script>
<script src="${ctx}/static/semantic/components/transition.js"></script>

<style type="text/css">
body {
	background-color: #DADADA;
}

body>.grid {
	height: 100%;
}

.image {
	margin-top: -100px;
}

.column {
	max-width: 450px;
}
</style>
<script>
  $(document).ready(function() {
    $('.ui.form').form({
      fields: {
        email: {
          identifier: 'email',
          rules: [{
            type: 'empty',
            prompt: '请输入您的账号'
          }]
        },
        password: {
          identifier: 'password',
          rules: [{
            type: 'empty',
            prompt: '请输入您的密码'
          }, {
            type: 'length[6]',
            prompt: '密码长度至少6位'
          }]
        }
      }
    });
  });
</script>
</head>
<body>
	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<h2 class="ui teal image header">
				<img src="${ctx}/static/images/logo.png" class="image" />
				<div class="content">登录后台管理系统</div>
			</h2>
			<form:form action="${ctx }/login" cssClass="ui large form" modelAttribute="userCommand" method="POST">
				<div class="ui stacked segment">
					<div class="field">
						<div class="ui left icon input">
							<i class="user icon"></i> <input type="text" id="username" name="username" placeholder="账号" />
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="lock icon"></i> <input type="password" id="password" name="password" placeholder="密码" />
						</div>
					</div>
					<div class="ui fluid large teal submit button">登录</div>
				</div>
				<form:errors class="message" cssStyle="color:red"></form:errors>
				<div class="ui error message"></div>
			</form:form>
			<div class="ui message">
				新用户 ? <a href="#">注册</a>
			</div>
		</div>
	</div>
</body>
</html>
