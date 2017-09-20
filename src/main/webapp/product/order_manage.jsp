<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="page" uri="../WEB-INF/tld/pager.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Angela后台管理平台</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="../header.jsp" flush="true"/>
    <script type="text/javascript" src="<%=path%>/js/ymPrompt.js" ></script>
	<link type="text/css" title="www"  rel="stylesheet" href="<%=path%>/css/ymPrompt.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    function deleteOrder(){
			var Ids = [];
			$("input[name='checkbox2']").each(function() {
				if ($(this).attr("checked")) {
					Ids.push($(this).val());
				}
			});
			if (Ids == "") {
				ymPrompt.alert("请选择需要删除的订单!");
			} else {
				ymPrompt.confirmInfo("确定要删除选择的订单信息吗？",null,null,"订单提示",function(tp) {
					if (tp == "ok") {
						location.href = "deleteOrder?Ids="+Ids;
					}
				});
			}
	    }
	    //add
	    function toAddPage(){
	    	location.href = "toAddOrderPage?sub=2";
	    }
	  	//edit
	    function updateInfo(id){
	    	location.href = "toEditOrderPage?id="+id;
	    }
	    //修改数据
	    function updateCategory(id, catName, parentId, depth, priority){
	    	$("#id").val(id);
	    	$("#catName").val(catName);
	    	$("#parentId").val(parentId);
	    	$("#depth").val(depth);
	    	$("#priority").html(priority);
	    }
	    
	    var cancel = function() {
	    	$("#catName1").val("");
	    }
	    
	    function updateStatus(id,status){
	    	if(status == 0){
	    		status = 1;
	    	}else{
	    		status = 0;
	    	}
			ymPrompt.confirmInfo("确定要处理改订单吗？",null,null,"处理提示",function(tp) {
				if (tp == "ok") {
					
					location.href = "updateOrderStatus?id="+id+"&status="+status;
				}
			});
	    }
	</script>
</head>
<body>


<div id="header">
    <h1><a href="#">Angela后台管理平台</a></h1>
</div>
<div id="user-nav" class="navbar navbar-inverse">
    <jsp:include page="../top.jsp" flush="true"/>
</div>

<!--左侧菜单 start-->	
<div id="sidebar">
	<jsp:include page="../left.jsp" flush="true"/>
</div>
<!--左侧菜单 end-->
<div id="content">
<div id="breadcrumb">
    <a href="#" title="商品管理" class="tip-bottom"><i class="icon-home"></i>订单管理</a>
    <a href="#" class="current">订单管理</a>
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
                    <div class="widget-content">进行订单相关操作。</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
<div class="row-fluid">
	<div class="btn-box">
    <!-- <button class="btn btn-info" onclick="toAddPage();"><i class="icon-add"></i>新增订单</button> -->
    <button class="btn btn-danger margin-l5" onclick="deleteOrder();" data-toggle="modal"><i class="icon-delete"></i>删除</button>
  </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <div class="widget-box widtable-box">
            <div class="outer-box">
                <table class="table table-bordered with-check table-hover no-search no-select">
                    <thead>
                      <tr>
                          <th><span class="icon"><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></span></th>
                          <th>订单ID</th>
                          <th>公司名称</th>
                          <th>用户名称</th>
                          <th>E-Mail</th>
                          <th>PhoneNo</th>
                          <th>Address</th>
                          <th>FAX</th>
                          <th>订单时间</th>
                          <th>订单状态</th>
                          <th class="center">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${list}" var="ls">
	                      <tr>
	                          <td><span class="icon"><input type="checkbox" id="checkbox2" name="checkbox2" value="${ls.id}"/></span></td>
	                          <td><a href="getOrderDetail?orderId=${ls.id }&sub=2">${fn:substring(ls.id, 0, 13)}...</a></td>
	                          <td>${ls.companyName}</td>
	                          <td>${ls.customName}</td>
	                          <td>${ls.email}</td>
	                          <td>${ls.phoneNumber}</td>
	                          <td>${ls.asi}</td>
	                          <td>${ls.fax}</td>
	                          <td>${ls.createDate}</td>
	                          <td>
	                          	<c:if test="${ls.status eq 0}"><p style="color: red;">未回复</p></c:if>
	                          	<c:if test="${ls.status eq 1}"><p style="color: green;">已回复</p></c:if>
	                          </td>
	                          <td>
	                              <div class="btn-group">
	                                <button data-toggle="dropdown" class="btn min-btn dropdown-toggle"><i class="icon-wrench"></i>操作<span class="caret"></span></button>
	                                <ul class="dropdown-menu">
	                                	<li><a href="#myAlertEdit" data-toggle="modal" onclick="updateStatus('${ls.id}', '${ls.status }');">处理</a></li>
	                                </ul>
	                              </div>
	                          </td>
	                      </tr>
                      </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/order/getAllOrder">
                		<input type="hidden" value="2" name="sub"/>
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
</body>
</html>