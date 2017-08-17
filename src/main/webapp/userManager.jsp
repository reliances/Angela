<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="Author" content="" />
<meta name="Copyright" content="" />
<title>ComProject</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="mrtit"><h1>用户管理</h1></td>
		</tr>
		<tr>
			<td class="space6">
			<form action="getAllUser" method="post">
				<table width="100%" cellpadding="0" cellspacing="0" class="tablestyle tabbg">
					<tr>
						<td align="right"><b>ID:</b></td>
						<td><input class="mript01" type="text" id="id" name="id" maxlength="20"/></td>
						<td align="right"><b>用户姓名:</b></td>
						<td><input class="mript01" type="text" id="userName" name="userName" maxlength="20"/></td>
						<td align="right"><b>性别:</b></td>
						<td><input class="mript01" type="text" id="sex" name="sex" maxlength="20"/></td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input class="btn82" type="submit" value="搜索" />&nbsp;&nbsp;&nbsp;
							<input class="btn82" type="button" onclick="window.location.href='toUpload'" value="上传文件"/>
						</td>
					</tr>
				</table>
			</form>
			</td>
		</tr>
		<tr>
			<td class="space6">
				<table width="100%" cellpadding="0" cellspacing="0" class="tablestyle">
					<tr class="tdtit03">
						<%--<td width="4%"><input type="checkbox" value="" /></td>--%>
						<td width="10%">用户ID</td>
						<td width="10%">用户姓名</td>
						<td width="10%">密码</td>
						<td width="10%">性别</td>
						<td width="6%">操作</td>
					</tr>
					<c:forEach items="${userList}" var="uList">
						<tr>
							<%--<td><input type="checkbox" value="" /></td>--%>
							<td>${uList.id }</td>
							<td class="blue01">${uList.userName } </td>
							<td>${uList.password }</td>
							<%--<td>${uList.sex eq 0 ? "男" : uList.sex eq 1 ? "女" : "未知"}</td>--%>
							<td>${uList.sex }</td>
							<td align="center">
								<a class="icon02" href="#">修改</a>
								<a class="icon01" href="deleteById?userId=${uList.id }">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="/page/page.jsp">
					    <jsp:param name="url" value="getAllUser" />					
				</jsp:include>
				<!--
				<div class="tdpage">
					<p class="fl">共有111条记录，当前第<span class="red02">1</span>页，共有10页</p>
					<p class="fr">
						<input class="tdpage_btn01" type="button" value="首页" />
						<input class="tdpage_btn01" type="button" value="上页" />
						<input class="tdpage_btn01" type="button" value="下页" />
						<input class="tdpage_btn01" type="button" value="尾页" />
						转到 <input class="tdpage_ipt" type="text" value="" /> 页
						<input class="tdpage_btn01" type="button" value="GO" />
					</p>
				</div>
			   -->
			</td>
		</tr>
	</table>
</body>
</html>