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
    <jsp:include page="../header.jsp" flush="true"/>
    <script type="text/javascript" src="<%=path%>/js/ymPrompt.js" ></script>
	<link type="text/css" title="www"  rel="stylesheet" href="<%=path%>/css/ymPrompt.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    	<script language="javascript" type="text/javascript">
	    function deleteRole(){
			var roleId = [];
			$("input[name='checkbox2']").each(function() {
				if ($(this).attr("checked")) {
					roleId.push($(this).val());
				}
			});
			if (roleId == "") {
				ymPrompt.alert("请选择需要删除的角色!");
			} else {
				ymPrompt.confirmInfo("确定要删除选择的角色信息吗？",null,null,"删除提示",function(tp) {
					if (tp == "ok") {
						location.href = "deleteRoleById?sub=5&roleId="+roleId;
					}
				});
			}
	    }
	    
	    //修改数据
	    function updateRole(uuid, roleId, roleName, roleDesc){
	    	$("#uuid").val(uuid);
	    	$("#roleId").val(roleId);
	    	$("#roleName").val(roleName);
	    	$("#roleDesc").val(roleDesc);
	    }
	    var cancel = function() {
	    	$("#roleId1").val("");
	    	$("#roleName1").val("");
	    	$("#roleDesc1").val("");
	    }
	    var submitForm = function() {
	    	if (changeNo('js-role-id', 'countRoleById')) {
	    		return true;
	    	} else {
	    		return false;
	    	}
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
    <a href="#" class="current">角色管理</a>
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
                    <div class="widget-content">可以定义系统的角色。</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
<div class="row-fluid">
	<div class="btn-box">
    <button class="btn btn-info" href="#myAlert" data-toggle="modal"><i class="icon-add"></i>添加角色</button>
    <button class="btn btn-danger margin-l5" onclick="deleteRole();"><i class="icon-delete"></i>删除</button>
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
                          <th>角色ID</th>
                          <th>角色名称</th>
                          <th>角色描述</th>
                          <th class="center">操作</th>
                      </tr>
                    </thead>
                    <tbody>
	                    <c:forEach items="${list}" var="role">
	                      <tr>
	                          <td><span class="icon"><input type="checkbox" name="checkbox2" id="checkbox2" value="${role.uuid}"/></span></td>
	                          <td>${role.roleId}</td>
	                          <td>${role.roleName}</td>
	                          <td>${role.roleDesc}</td>
	                          <td class="center">
	                            <div class="btn-group">
	                                <button data-toggle="dropdown" class="btn min-btn dropdown-toggle"><i class="icon-wrench"></i>操作<span class="caret"></span></button>
	                                <ul class="dropdown-menu dropdown-menuL">
	                                    <li><a href="#myAlertedit" data-toggle="modal" onclick="updateRole('${role.uuid}', '${role.roleId}', '${role.roleName}', '${role.roleDesc}');">编辑</a></li>
	                                </ul>
	                            </div>
	                        	</td>
	                      </tr>
	                    </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/role/getAllRoles">
                		<input type="hidden" value="5" name="sub"/>
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
<!--添加角色 start-->
<div id="myAlert" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>添加角色</h3>
    </div>
    <form class="form-horizontal" method="post" action="addRole" name="createKeyPair" id="createKeyPair" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">角色ID:</label>
                        <div class="controls control-s">
                            <input type="text" class="js-role-id" onblur="changeNo('js-role-id', 'countRoleById')" name="roleId" id="roleId1" placeholder="可输入中文,字母,数字,'-','_','.'，请输入一个长度最多是 25 的字符串"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色名称:</label>
                        <div class="controls control-s">
                            <input type="text" class="v-zhlettersnum-r--25" name="roleName" id="roleName1" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色描述:</label>
                        <div class="controls control-s">
                            <input type="text" class="v-zhlettersnum-r--200" name="roleDesc" id="roleDesc1" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <input type="submit" value="确定" class="btn btn-primary" onclick="return submitForm();"/>
            <a data-dismiss="modal" class="btn" onclick="cancel();">取消</a>
        </div>
    </form>
</div>
<!--添加角色 end-->
<!--编辑角色 start-->
<div id="myAlertedit" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>编辑角色</h3>
    </div>
    <form class="form-horizontal" method="post" action="updateRoleById" name="createKeyPair" id="createKeyPair" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">角色ID:</label>
                        <div class="controls control-s">
                            <input type="hidden" name="uuid" id="uuid">
                            <input type="text" class="v-zhlettersnum-r--25" name="roleId" id="roleId" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色名称:</label>
                        <div class="controls control-s">
                            <input type="text" class="v-zhlettersnum-r--25" name="roleName" id="roleName" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色描述:</label>
                        <div class="controls control-s">
                            <input type="text" class="v-zhlettersnum-r--200" name="roleDesc" id="roleDesc" />
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
<!--编辑角色 end-->

<script type="text/javascript">
    page_data = {
        'sidebar' : { 'title': 'Table1', 'link':'#', 'clazzs': 'visible-phone', 'icon': 'picture' },
        'pages': { "picture": { "status": "active open" } }
    };
</script>

</body>
</html>
