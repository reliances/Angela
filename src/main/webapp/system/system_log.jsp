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
    <title>管理平台</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="../header.jsp" flush="true"/>
    <script type="text/javascript" src="<%=path%>/js/ymPrompt.js" ></script>
	<link type="text/css" title="www"  rel="stylesheet" href="<%=path%>/css/ymPrompt.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    var cancel = function() {
	    	$("#userName").val("");
	    	$("#ipAddress").val("");
	    	$("#operationType").val("");
	    	$("#userPassword1").val("");
	    	$("#logComment").val("");
	    	$("#createDate").val("");
	    	$("#createDate2").val("");
	    }
    </script>
    </head>
<body>


<div id="header">
    <h1><a href="#">管理平台</a></h1>
</div>
<div id="user-nav" class="navbar navbar-inverse">
    <jsp:include page="../top.jsp" flush="true"/>
</div>

<div id="sidebar">
	<jsp:include page="../left.jsp" flush="true"/>
</div>
<div id="content">
<div id="breadcrumb">
    <a href="#" title="系统管理" class="tip-bottom"><i class="icon-home"></i> 系统管理</a>
    <a href="#" class="current">用户操作日志管理</a>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="widget-box collapsible">
                <div class="widget-title">
                    <a data-toggle="collapse" href="#collapseOne">
                        <span class="icon">
                          <i class="icon-arrow-right"></i>
                        </span>
                        <h5>说明：</h5>
                    </a>
                </div>
                <div id="collapseOne" class="collapse in">
                    <div class="widget-content">可以查询用户进入系统后的操作日志信息,包括：员工姓名、登录的IP、操作内容等。</div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">

  <form action="getAllSysLog" method="post">
	  <div class="row-fluid">
	    <div class="span12">
	      <div class="widget-box widcolum-box">
	      	<div class="widtable-title"> 
	          <span class="margin-l5">姓名</span>
	        	<input class="table-input margin-t5" id="userName" name="userName" value="${sysLog.userName}" style="width: 100px;" type="text">
	          <span class="margin-l5">IP地址</span>
	        	<input class="table-input margin-t5" id="ipAddress" name="ipAddress" value="${sysLog.ipAddress}" style="width: 100px;" type="text">
	          <span class="margin-l5">类型</span>
	        	<input class="table-input margin-t5" id="operationType" name="operationType" value="${sysLog.operationType}" style="width: 100px;" type="text">
	          <span class="margin-l5">内容</span>
	        	<input class="table-input margin-t5" id="logComment" name="logComment" value="${sysLog.logComment}" style="width: 100px;" type="text">
	          <span class="margin-l5">时间</span>
	          <input readonly style="width: 100px;" id="createDate" name="createDate" class="table-input margin-t5 form_datetime" value="${sysLog.createDate}" type="text">-
	          <input readonly style="width: 100px;" id="createDate2" name="createDate2" class="table-input margin-t5 form_datetime" value="${sysLog.createDate2}" type="text">
	          <button class="btn btn-info" type="submit"><i class="icon-search"></i>查询</button>
	          <button class="btn btn-warning" onclick="cancel();" type="button"><i class="icon-trash"></i>清空</button>
	      </div>
	    </div> 
	  </div>
	  </div>
  </form>
  
  <div class="row-fluid">
    <div class="span12">
        <div class="widget-box widtable-box">
            <div class="outer-box">
                <table class="table table-bordered table-hover no-search no-select">
                    <thead>
                      <tr>
                      	  <th>编号</th>
                          <th>用户姓名</th>
                          <th>IP地址</th>
                          <th>操作类型</th>
                          <th>操作内容</th>
                          <th>操作时间</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${list}" var="log" varStatus="nums">
	                      <tr>
	                      	  <td><c:out value="${nums.count }"/></td>
	                          <td>${log.userName}</td>
	                          <td>${log.ipAddress}</td>
	                          <td>${log.operationType}</td>
	                          <td>${log.logComment}</td>
	                          <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${log.createDate}" /></td>
	                      </tr>
                      </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/syslog/getAllSysLog">
                		<input type="hidden" name="sub" value="1"/>
                	</form>
		            <page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${pageNum}" formId="requestForm"/>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div id="footer" class="span12">
            Copyright 2013-2015 TCloud All Rights Reserved
        </div>
    </div>
</div>
</div>

<script type="text/javascript">
    page_data = {
        'sidebar' : { 'title': 'Table1', 'link':'#', 'clazzs': 'visible-phone', 'icon': 'picture' },
        'pages': { "picture": { "status": "active open" } }
    };
 
	$(document).ready(function() {
		$(".form_datetime").datetimepicker({
			format: 'yyyy-mm-dd hh:ii',
			language: 'zh-CN'
		});
	});
</script>

</body>
</html>
