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
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="../header.jsp" flush="true"/>
    <script type="text/javascript" src="<%=path%>/js/ymPrompt.js" ></script>
	<link type="text/css" title="www"  rel="stylesheet" href="<%=path%>/css/ymPrompt.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    function deleteSector(){
			var userId = [];
			$("input[name='checkbox2']").each(function() {
				if ($(this).attr("checked")) {
					userId.push($(this).val());
				}
			});
			if (userId == "") {
				ymPrompt.alert("请选择需要删除的人员!");
			} else {
				ymPrompt.confirmInfo("确定要删除选择的人员信息吗？",null,null,"删除提示",function(tp) {
					if (tp == "ok") {
						location.href = "deleteUserById?userId="+userId;
					}
				});
			}
	    }
	    
	    function addUser(){
    		$("input[type=radio]").attr("checked",false);
    		$.uniform.update($("input[type=radio]"));
    		$("input[type=radio][value=0]").attr("checked","checked");
	    	$.uniform.update($("input[type=radio][value=0]"));
    	}
	    
	    function updateUser(uuid, userId, userName, userAccount, userPassword, userRole,
	    		userEmail, userDep, userPos){
	    	$("#uuid").val(uuid);
	    	$("#userId").val(userId);
	    	$("#userName").val(userName);
	    	$("#userAccount").val(userAccount);
	    	
	    	$("#userPassword").val(userPassword);
	    	$("#userRole").val(userRole);
	    	$("#userRole").select2();
	    	$("#userEmail").val(userEmail);
	    }
	    
	    var cancel = function() {
	    	$("#userId1").val("");
	    	$("#userName1").val("");
	    	$("#userAccount1").val("");
	    	$("#userPassword1").val("");
	    	$("#userRole1").val("");
	    	$("#userRole1").select2();
	    	$("#userEmail1").val("");
	    }
        
        var submitForm = function() {
        	if (changeNo('js-user-id', 'getUserById') && changeNo('js-account', 'countUserByAccount')) {
        		return true;
        	} else {
        		return false;
        	}
        }
	    
	    $(function () {
	    	$(".js-user-email").change(function () {
	    		$(this).closest('div').find("span").remove();
	    		if ($(this).val() != "") {
	    			var result=$(this).val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
		    		if(result==null) {
		    			$(".js-user-email").closest('div').append("<span class='help-inline' style='display: inline-block; color:#b94a48'>格式错误</span>");
		    		}
	    		}
	    	})
	    })
	    
	    //校验用户名
		function checkFormat(){
	    	var userCount = $("#userAccount1").val();
	    	if(null != userCount && userCount !=""){
		    	$.ajax({
					type:'post',
					url:'getUserByCount',
					data:{
						userCount:userCount
					},
					dataType:'html',
					cache:false,
					global:false,
					success:function(msg){
						if(msg=="success"){
							$("#createForm").submit();
						}else{
							alert("用户账户已经存在,请重新输入!");
							$("#userAccount").focus();
							return;
						}
					}
				}); 
	    	}
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

<div id="sidebar">
	<jsp:include page="../left.jsp" flush="true"/>
</div>
<div id="content">
<div id="breadcrumb">
    <a href="#" title="系统管理" class="tip-bottom"><i class="icon-home"></i> 系统管理</a>
    <a href="#" class="current">人员管理</a>
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
                    <div class="widget-content">可以创建与编辑人员信息，包括：员工编号、账号、密码、角色、所属部门等。</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
<div class="row-fluid">
	<div class="btn-box">
    <button class="btn btn-info" href="#myAlert" onclick="addUser();" data-toggle="modal"><i class="icon-add"></i>添加员工</button>
    <button class="btn btn-danger margin-l5" onclick="deleteSector();"><i class="icon-delete"></i>删除</button>
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
                          <th>员工编号</th>
                          <th>员工姓名</th>
                          <th>账号</th>
                          <th>角色</th>
                          <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${list}" var="user">
	                      <tr>
	                          <td><span class="icon"><input type="checkbox" name="checkbox2" id="checkbox2" value="${user.uuid}"/></span></td>
	                          <td>${user.userId}</td>
	                          <td>${user.userName}</td>
	                          <td>${user.userAccount}</td>
	                          <td>${user.userRole}</td>
	                          <%-- <td>${user.userStatus eq 0 ? "在职" : "否"}</td> --%>
	                          <td class="center">
	                            <div class="btn-group">
	                                <button data-toggle="dropdown" class="btn min-btn dropdown-toggle"><i class="icon-wrench"></i>操作<span class="caret"></span></button>
	                                <ul class="dropdown-menu dropdown-menuL">
	                                    <li><a href="#myAlertedit" onclick="updateUser('${user.uuid }','${user.userId }','${user.userName }','${user.userAccount }',
	                                    '${user.userPassword}','${user.roleId }', '${user.userEmail}');" data-toggle="modal">编辑</a></li>
	                                </ul>
	                            </div>
	                        </td>
	                      </tr>
                      </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/user/getAllUsers">
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
<!--添加人员 start-->
<div id="myAlert" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>添加员工</h3>
    </div>
    <form class="form-horizontal" method="post" action="addUser" name="createKeyPair" id="createForm" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">员工编号:</label>
                        <div class="controls control-s">
                            <input type="text" name="userId" id="userId1" class="js-user-id" onblur="changeNo('js-user-id', 'getUserById');" placeholder="可输入中文,字母,数字,'-','_','.'，请输入一个长度最多是 25 的字符串"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">员工姓名:</label>
                        <div class="controls control-s">
                            <input type="text" name="userName" id="userName1" class="v-zhlettersnum-r--25"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">账号:</label>
                        <div class="controls control-s">
                            <input type="text" class="js-account" id="userAccount1" name="userAccount"  onblur="changeNo('js-account', 'countUserByAccount');" placeholder="可输入中文,字母,数字,'-','_','.'，请输入一个长度最多是 25 的字符串"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">密码:</label>
                        <div class="controls control-s">
                            <input type="text" class="v-zhlettersnum-r--25" id="userPassword1" name="userPassword" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色:</label>
                        <div class="controls control-s">
                            <select class="w83" name="userRole" id="userRole1">
                              <c:forEach items="${roleList}" var="role">
	                              <option value="${role.uuid }">${role.roleName }（${role.roleId }）</option>
                              </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">邮箱地址:</label>
                        <div class="controls control-s">
                            <input type="text" name="userEmail" id="userEmail1" class="v-email-r--25"/>
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
<!--添加人员 end-->


<!--编辑人员 start-->
<div id="myAlertedit" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>编辑员工</h3>
    </div>
    <form class="form-horizontal" method="post" action="updateUserById" name="createKeyPair" id="createKeyPair" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <input type="hidden" id="uuid" name="uuid"/>
                        <label class="control-label control-slabel">员工编号:</label>
                        <div class="controls control-stxt">
                            <input type="text" name="userId" id="userId" class="v-zhlettersnum-r--25">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">员工姓名:</label>
                        <div class="controls control-stxt">
                            <input type="text" name="userName" id="userName" class="v-zhlettersnum-r--25">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">账号:</label>
                        <div class="controls control-s">
                            <input type="text" name="userAccount" id="userAccount" class="v-zhlettersnum-r--25"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">密码:</label>
                        <div class="controls control-s">
                            <input type="password" name="userPassword" id="userPassword" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">角色:</label>
                        <div class="controls control-s">
                            <select class="w83" name="userRole" id="userRole">
                              <c:forEach items="${roleList}" var="role">
	                              <option value="${role.uuid }">${role.roleName }（${role.roleId }）</option>
                              </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">邮箱地址:</label>
                        <div class="controls control-stxt">
                            <input type="text" name="userEmail" id="userEmail" class="v-email-r--25"/>
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
<!--编辑人员 end-->
<script type="text/javascript">
    page_data = {
        'sidebar' : { 'title': 'Table1', 'link':'#', 'clazzs': 'visible-phone', 'icon': 'picture' },
        'pages': { "picture": { "status": "active open" } }
    };
</script>

</body>
</html>
