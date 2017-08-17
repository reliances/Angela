<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="page" uri="../WEB-INF/tld/pager.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Angela后台管理平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="header.jsp" flush="true"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>


<body>
	<div id="header">
	    <h1><a href="#">Angela后台管理平台</a></h1>
	</div>
	<div id="user-nav" class="navbar navbar-inverse">
	    <jsp:include page="top.jsp" flush="true"/>
	</div>
	
	<div id="sidebar">
		<jsp:include page="left.jsp" flush="true"/>
	</div>
	<div id="content" style="background:#fff;">
		<div id="breadcrumb">
			<a href="#" title="首页" class="tip-bottom"><i class="icon-home"></i>首页</a>
			<a href="#" class="current">系统介绍</a>
		</div>
      <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-content">
                    <div class="span5">
                        <h3 class="indexTit">概况</h3>
                        <p class="generalc"> 。</p>
                        <p class="generalShow">。</p>
                        <h3 class="indexTit">使用帮助</h3>
                        <ul class="homeHelps">
                            <li><a href="#">使用帮助</a></li>
                            <li><a href="#">添加字典表</a></li>
                           <!--  <li><a href="../version.jsp">Version</a></li> -->
                          </ul>
                      </div>
                      <!-- <div class="span7"><div class=""><img src="../img/prossess_chart.jpg"/></div></div> -->
                  </div>
              </div>
          </div>
         <div class="row-fluid">
          	<div id="footer" class="span12"> Copyright 2013-2015 TCloud All Rights Reserved </div>
         </div>
      </div>
   </div>

</body>
</html>

