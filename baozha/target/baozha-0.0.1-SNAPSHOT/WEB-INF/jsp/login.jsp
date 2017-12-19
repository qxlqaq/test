<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% 
	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<!--//引用css-->
<base href="<%=basepath%>"/>
<link href="css/jquery.idcode.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<!--//引用idcode插件-->
<script type="text/javascript" src="js/jquery.idcode.js"></script>
<script>
$.idcode.setCode();   //加载生成验证码方法
function sendJson(){
	//请求json响应json
	var data={username:$("#username").val(),password:$("#password").val()};
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/user/login.action",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		success:function(result){
			var result=eval('('+result+')');
			if(result.msg)
			{
				alert("登录成功");
				/* window.location("${pageContext.request.contextPath}/user/main.action"); */
			}else
			{
				alert("登录失败");
			}
		}
	});
}
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user/login.action" method="post">
		账号：<input type="text" name="username" id="username"/><br />
		密码：<input type="text" name="password" id="password"/><br/>
		<!-- <input type="text" id ="Txtidcode" class ="txtVerification"><span id="idcode"></span><br/> -->
		<input type="submit" value="普通登录"/>
		<input type="button" onclick="sendJson()" value="ajax登录"/>
		<input type="reset" value="重置"/>
	</form>
</body>
</html>