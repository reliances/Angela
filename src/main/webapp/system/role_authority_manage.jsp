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
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <jsp:include page="../header.jsp" flush="true"/>
	<link rel="stylesheet" href="<%=path%>/css/ymPrompt.css"  type="text/css">
	<link rel="stylesheet" href="<%=path%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/ymPrompt.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.excheck-3.5.js"></script>
    
    <script type="text/javascript">
        var treeObj = "";
	    function deleteRoleAuth(){
			var roleAuthId = [];
			$("input[name='checkbox2']").each(function() {
				if ($(this).attr("checked")) {
					roleAuthId.push($(this).val());
				}
			});
			if (roleAuthId == "") {
				ymPrompt.alert("请选择需要删除的角色权限!");
			} else {
				ymPrompt.confirmInfo("确定要删除选择的角色权限信息吗？",null,null,"删除提示",function(tp) {
					if (tp == "ok") {
						location.href = "deleteRoleAuthById?roleAuthId="+roleAuthId;
					}
				});
			}
	    }
	    
	    //修改数据
	    function updateRoleAuth(uuid, roleId, roleName, roleDesc){
	    	$("#uuid").val(uuid);
	    	//$("#roleId").val(roleId);普通用户（000000101）
	    	$("#roleName").html(roleName + "(" + roleId + ")");
	    	$("#roleDesc").html(roleDesc);
	    	zNodes = [];
			$.get(
                "getMenuTree",
                {   roleNo: $("#uuid").val(),
                    nocache: new Date().getTime()
                },
                function(data){   
                	var authNos = [];
                	if (data.roleAuth != null && data.roleAuth.authNo != null) {
               			authNos = data.roleAuth.authNo.split(',');
               		 }
                	 $.each(data.list, function(index, item) {
               			 var subItem = {};
                   		 subItem['id'] = item.mId;
                   		 subItem['pId'] = item.parentId;
                   		 subItem['name'] = item.mName;
                   		 if (item.parentId === null) {
                   			 subItem['open'] = true;
                   		 }
                   		 if (data.roleAuth != null) {
                   			if (authNos.indexOf("" + item.mId) != -1) {
                       			subItem['checked'] = true; 
                       		 }
                   		 }
                   		 zNodes.push(subItem);
                	 });
                	 treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
               }
            );
	    }
	    
	    $(function () {
	    	$("#js-role-name").change(function () {
	    		$.get(
                    "role/getRoleById",
                    {
                    	roleId: $(this).val(),
                        nocache: new Date().getTime()
                    },
                   function(data){   	
                     	$("#js-role-desc").html(data.roleDesc);
                     	$("#js-role-id").val(data.roleId);
                   }
                );
	    	})
	    })
	  
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	    
		/* var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"}
		]; */
	
		$(document).ready(function(){
			//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$(".js-submit-form").click(function(){
				var ids = [];
				var nodes = treeObj.getCheckedNodes(true);
				$.each(nodes, function(index, item){
					ids.push(item.id);
				});
				var roleNo = $("#uuid").val();
				window.location.href = "editRoleAuth?roleNo=" + roleNo + "&authNo=" + ids;
			});
		});
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
    <a href="#" title="系统管理" class="tip-bottom"><i class="icon-home"></i>系统管理</a>
    <a href="#" class="current">角色资源分配</a>
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
                    <div class="widget-content">可以为不同的角色定制权限。</div>
                </div>
            </div>
        </div>
    </div>
 
	<div class="row-fluid">
    <div class="span12">
        <div class="widget-box widtable-box">
            <div class="outer-box">
                <table class="table table-bordered table-hover no-search no-select">
                    <thead>
                      <tr>
                          <th>角色ID</th>
                          <th>角色名称</th>
                          <th>角色描述</th>
                          <th>权限状态</th>
                          <th class="center">操作</th>
                      </tr>
                    </thead>
                    <tbody>
	                    <c:forEach items="${list}" var="role">
	                      <tr>
	                          <td>${role.roleId}</td>
	                          <td>${role.roleName}</td>
	                          <td>${role.roleDesc}</td>
	                          <td>${role.roleStatus eq 0 ? "未设定" : "已设定"}</td>
	                          <td class="center">
	                            <div class="btn-group">
	                                <button data-toggle="dropdown" class="btn min-btn dropdown-toggle"><i class="icon-wrench"></i>操作<span class="caret"></span></button>
	                                <c:if test="${role.roleName ne '管理员'}">
		                                <ul class="dropdown-menu dropdown-menuL">
		                                    <li><a href="#myAlertedit" data-toggle="modal" onclick="updateRoleAuth('${role.uuid}', '${role.roleId}', '${role.roleName}', '${role.roleDesc}');">编辑</a></li>
		                                </ul>
	                                </c:if>
	                            </div>
	                        	</td>
	                      </tr>
	                    </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/roleAuth/getAllRoleAuths">
                		<input type="hidden" name="sub" value="5"/>
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

<!--添加角色资源分配 start-->
<div id="myAlert" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>添加角色资源分配</h3>
    </div>
    <form class="form-horizontal" method="post" action="addRoleAuth" name="createKeyPair" id="createKeyPair" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">角色名称:</label>
                        <div class="controls control-s">
                            <select class="w83" name="roleName">
                              <c:forEach items="${roleList}" var="role">
                              	<option value="${role.uuid}">${role.roleName}（${role.roleId}）</option>
                              </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色描述:</label>
                        <div class="controls control-s">
                            <input type="hidden" value="${role.roleId}" id="js-role-id" name="roleId">
                            <textarea class="high100" id="js-role-desc" name="roleDesc">${role.roleDesc}</textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色选择:</label>
                        <div class="controls control-s">
							<div class="clearfix"></div>
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
<!--添加角色资源分配 end-->
<!--编辑角色资源分配 start-->
<div id="myAlertedit" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>编辑角色资源分配</h3>
    </div>
    <form class="form-horizontal" method="post" action="#" name="createKeyPair" id="createKeyPair" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">角色名称:</label>
                        <div class="controls control-stxt">
                            <input type="hidden" name="uuid" id="uuid">
                            <span id="roleName">普通用户（000000101）</span>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色描述:</label>
                        <div class="controls control-stxt">
                            <span id="roleDesc">ssdddddddddddddddddd</span>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色选择:</label>
                        <div class="controls control-s">
	                        <div class="content_wrap">
								<div class="zTreeDemoBackground left">
									<ul id="treeDemo" class="ztree"></ul>
								</div>
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <input type="button" value="确定" class="btn btn-primary js-submit-form" />
            <a data-dismiss="modal" class="btn" href="#">取消</a>
        </div>
    </form>
</div>
</body>
</html>

