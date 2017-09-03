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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Angela后台管理平台</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="../header.jsp" flush="true"/>
    <script type="text/javascript" src="<%=path%>/js/ymPrompt.js" ></script>
	<link type="text/css" title="www"  rel="stylesheet" href="<%=path%>/css/ymPrompt.css">
	<link type="text/css" rel="stylesheet" href="<%=path%>/js/view/css/viewer.min.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    function deleteById(){
			var Ids = [];
			$("input[name='checkbox2']").each(function() {
				if ($(this).attr("checked")) {
					Ids.push($(this).val());
				}
			});
			if (Ids == "") {
				ymPrompt.alert("请选择需要删除的评价!");
			} else {
				ymPrompt.confirmInfo("确定要删除选择的评价信息吗？",null,null,"删除提示",function(tp) {
					if (tp == "ok") {
						location.href = "deleteById?sub=2&Ids="+Ids;
					}
				});
			}
	    }
	    //add
	    function toAddCasePage(){
	    	location.href = "toAddCasePage?sub=4";
	    }
	  	//edit
	    function updateCaseInfo(id){
	    	location.href = "toEditCasePage?id="+id;
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
	</script>
	
	
	<style>
	* { margin: 0; padding: 0;}
	.pictures { width: 300px; margin: 0 auto; font-size: 0;}
	.pictures li { display: inline-block; margin-left: 1%; padding-top: 1%;}
	.pictures li img { width: 50px; hight:50px; cursor:pointer}
	</style>
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
    <a href="#" title="商品管理" class="tip-bottom"><i class="icon-home"></i>评价管理</a>
    <a href="#" class="current">评价管理</a>
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
                    <div class="widget-content">进行评价相关操作。</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
<div class="row-fluid">
	<div class="btn-box">
    <!-- <button class="btn btn-info" onclick="toAddCasePage();"><i class="icon-add"></i>新增评价</button> -->
    <button class="btn btn-danger margin-l5" onclick="deleteById();" data-toggle="modal"><i class="icon-delete"></i>删除</button>
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
                          <th>产品ID</th>
                          <th>评价内容</th>
                          <th>整体满意度</th>
                          <th>产品质量</th>
                          <th>响应能力</th>
                          <th>交付</th>
                          <th>解决问题</th>
                          <th>印记/个性化</th>
                          <th>创建时间</th>
                          <!-- <th class="center">操作</th> -->
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${list}" var="ls">
	                      <tr>
	                          <td><span class="icon"><input type="checkbox" id="checkbox2" name="checkbox2" value="${ls.id}"/></span></td>
	                          <td>${ls.productId}</td>
	                          <td>${ls.comment}</td>
	                          <td>${ls.satisfaction}</td>
	                          <td>${ls.productQuality}</td>
	                          <td>${ls.responsiveness}</td>
	                          <td>${ls.delivery}</td>
	                          <td>${ls.problemResolution}</td>
	                          <td>${ls.imprinting}</td>
	                          <td>${ls.createDate}</td>
	                          <%-- <td>
	                              <div class="btn-group">
	                                <button data-toggle="dropdown" class="btn min-btn dropdown-toggle"><i class="icon-wrench"></i>操作<span class="caret"></span></button>
	                                <ul class="dropdown-menu">
	                                	<li><a href="#myAlertEdit" data-toggle="modal" onclick="updateCaseInfo('${ls.id}');">编辑</a></li>
	                                </ul>
	                              </div>
	                          </td> --%>
	                      </tr>
                      </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/review/getAllReview">
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
            Copyright 2017 TCloud All Rights Reserved
        </div>
    </div>
</div>
</div>

<script src="<%=path%>/js/view/js/viewer.min.js"></script>
<script>
	/* var viewer = new Viewer(document.getElementById('dowebok'), {
		url: 'data-original'
	}); */
	function showPic(id){
		var viewer = new Viewer(document.getElementById(id), {
			url: 'data-original'
		});
	}
</script>
</body>
</html>