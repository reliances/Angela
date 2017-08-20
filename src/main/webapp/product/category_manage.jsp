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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    function deleteCategoryById(){
			var dicId = [];
			$("input[name='checkbox2']").each(function() {
				if ($(this).attr("checked")) {
					dicId.push($(this).val());
				}
			});
			if (dicId == "") {
				ymPrompt.alert("请选择需要删除的分类!");
			} else {
				ymPrompt.confirmInfo("确定要删除选择的分类信息吗？",null,null,"删除提示",function(tp) {
					if (tp == "ok") {
						location.href = "deleteCategoryById?sub=2&dicId="+dicId;
					}
				});
			}
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
    <a href="#" title="商品管理" class="tip-bottom"><i class="icon-home"></i>商品管理</a>
    <a href="#" class="current">商品类别管理</a>
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
                    <div class="widget-content">进行商品类别添加修改操作。</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
<div class="row-fluid">
	<div class="btn-box">
    <button class="btn btn-info" href="#myAlertCreate" data-toggle="modal"><i class="icon-add"></i>新增商品类别</button>
    <button class="btn btn-danger margin-l5" onclick="deleteCategoryById();" data-toggle="modal"><i class="icon-delete"></i>删除</button>
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
                          <th>类别名称</th>
                          <th>父级名称</th>
                          <th>深度</th>
                          <th>优先级</th>
                          <th>状态</th>
                          <th class="center">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${list}" var="cates">
	                      <tr>
	                          <td><span class="icon"><input type="checkbox" id="checkbox2" name="checkbox2" value="${cates.id}"/></span></td>
	                          <td>${cates.catName}</td>
	                          <td>
	                          	<c:forEach items="${cateListAdd }" var="cate">
					       			<c:if test="${cates.parentId eq cate.id }">
										${cate.catName }
									</c:if>
								</c:forEach>
	                          </td>
	                          <td>${cates.depth}</td>
	                          <td>${cates.priority}</td>
	                          <td>${cates.status}</td>
	                          <td>
	                              <div class="btn-group">
	                                <button data-toggle="dropdown" class="btn min-btn dropdown-toggle"><i class="icon-wrench"></i>操作<span class="caret"></span></button>
	                                <ul class="dropdown-menu">
	                                	<li><a href="#myAlertEdit" data-toggle="modal" onclick="updateCategory('${cates.id}', '${cates.catName}','${cates.parentId}','${cates.depth}','${cates.priority}');">编辑</a></li>
	                                </ul>
	                              </div>
	                          </td>
	                      </tr>
                      </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/category/getAllCategory">
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
<!--新增类别 start-->
<div id="myAlertCreate" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>新增类别</h3>
    </div>
    <form class="form-horizontal" method="post" action="addCategory" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">类别名称:</label>
                        <div class="controls control-s">
                            <input type="text" name="catName" id="catName1"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">父级名称:</label>
                        <div class="controls control-s">
                            <select class="w83" name="parentId">
                            <option value=" "></option>
                              <c:forEach items="${cateListAdd}" var="cate">
                              	 <c:if test="${cate.parentId eq ' '}">
                              	 	<option value="${cate.id}">${cate.catName}</option>
                              	 </c:if>
                              </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">深度:</label>
                        <div class="controls control-s">
                            <select class="w83" name="depth">
                              	 <option value="1">1</option>
                              	 <option value="2">2</option>
                              	 <option value="3">3</option>
                              	 <option value="4">4</option>
                              	 <option value="5">5</option>
                              	 <option value="6">6</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">优先级:</label>
                        <div class="controls control-s">
                        	<select class="w83" name="priority">
                              	 <option value="1">1</option>
                              	 <option value="2">2</option>
                              	 <option value="3">3</option>
                              	 <option value="4">4</option>
                              	 <option value="5">5</option>
                              	 <option value="6">6</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <input type="submit" value="确定" class="btn btn-primary" />
            <a data-dismiss="modal" class="btn" onclick="cancel();">取消</a>
        </div>
    </form>
</div>
<!--新增字典 end-->


<!--删除start-->
  <div id="myAlertDelete" class="modal pop-window hide">
      <div class="modal-header">
          <button data-dismiss="modal" class="close" type="button">×</button>
          <h3><i class="icon icon-th-list"></i>删除</h3>
      </div>
      <div class="modal-body">
          <p>你确定要对类别进行删除操作吗？</p>
      </div>
       <div class="modal-footer">
          <!-- <a class="btn btn-primary" href="#" data-dismiss="modal">确定</a> -->
          <a class="btn" href="#" data-dismiss="modal">取消</a>
      </div>
  </div>
  <!--删除end-->
  
  
<!--编辑  start-->
<div id="myAlertEdit" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>编辑</h3>
    </div>
    <form class="form-horizontal" method="post" action="updateCategoryById" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">类别名称:</label>
                        <div class="controls control-s">
                        	<input type="hidden" name="id" id="id">
                            <input type="text" name="catName" id="catName"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">父级名称:</label>
                        <div class="controls control-s">
                            <select class="w83" name="parentId">
                            <option value=" "></option>
                              <c:forEach items="${list}" var="cate">
                              	 <c:if test="${cate.parentId eq ' '}">
                              	 	<option value="${cate.id}" <c:if test="${cate.id eq parentId}">checked</c:if>>${cate.catName}</option>
                              	 </c:if>
                              </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">深度:</label>
                        <div class="controls control-s">
                            <select class="w83" name="depth">
                              	 <option value="1">1</option>
                              	 <option value="2">2</option>
                              	 <option value="3">3</option>
                              	 <option value="4">4</option>
                              	 <option value="5">5</option>
                              	 <option value="6">6</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">优先级:</label>
                        <div class="controls control-s">
                        	<select class="w83" name="priority">
                              	 <option value="1">1</option>
                              	 <option value="2">2</option>
                              	 <option value="3">3</option>
                              	 <option value="4">4</option>
                              	 <option value="5">5</option>
                              	 <option value="6">6</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <input type="submit" value="确定" class="btn btn-primary" />
            <a data-dismiss="modal" class="btn" href="#">取消</a>
        </div>
    </form>
</div>
<!--编辑字典 end-->
</body>
</html>