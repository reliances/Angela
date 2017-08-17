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
    <title>Angela管理平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>


<body>
 <h2 class="tcloud_t">Tcloud 2</h2>
  <h3 class="tcloud_t2">Enterprise Edition</h3>
  <p>Release 2.0.1 SUV001</p>
  <p>Revision Detail：</p>
  <div class="ibm_scroll">
    <table class="table table-bordered data-table table-hover no-search no-select">
        <thead>
            <tr>
                <th>开发者</th>
                <th>功能</th>
                <th>版本</th>
                <th>日期</th>
                <th>更新内容</th>
            </tr>
        </thead>                
        <tbody>
            <!-- BF修复bug RC功能新增 -->  
            <tr>
                <td>IBM</td>
                <td>Web</td>
                <td>2.0.1 20160531 BF001</td>
                <td>2016-05-31</td>
                <td>添加版本日志记录.</td>
            </tr>  
        </tbody>
    </table>  
  </div>
  <p class="Copyright"> Copyright 2013-2015 TCloud All Rights Reserved</p>

<script>
  (function($) {
    $.unicornPopup('{{popup.name}}');
  })($);
</script>

</body>
</html>

