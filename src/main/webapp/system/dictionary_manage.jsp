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
	    function deleteDictionary(){
			var dicId = [];
			$("input[name='checkbox2']").each(function() {
				if ($(this).attr("checked")) {
					dicId.push($(this).val());
				}
			});
			if (dicId == "") {
				ymPrompt.alert("请选择需要删除的字典!");
			} else {
				ymPrompt.confirmInfo("确定要删除选择的字典信息吗？",null,null,"删除提示",function(tp) {
					if (tp == "ok") {
						location.href = "deleteDictionaryById?sub=1&dicId="+dicId;
					}
				});
			}
	    }
	    //修改数据
	    function updateDictionary(dicId, dicName, dicKey, dicVal, dicDes){
	    	$("#dicId").val(dicId);
	    	$("#dicName").val(dicName);
	    	$("#dicKey").val(dicKey);
	    	$("#dicVal").val(dicVal);
	    	$("#dicDes").html(dicDes);
	    }
	    
	    var cancel = function() {
	    	$("#dicName1").val("");
	    	$("#dicKey1").val("");
	    	$("#dicVal1").val("");
	    	$("#dicDes1").val("");
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
    <a href="#" title="系统管理" class="tip-bottom"><i class="icon-home"></i>系统管理</a>
    <a href="#" class="current">流程字典表</a>
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
                    <div class="widget-content">进行数据字典的添加修改操作。</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
<div class="row-fluid">
	<div class="btn-box">
    <button class="btn btn-info" href="#myAlertCreate" data-toggle="modal"><i class="icon-add"></i>新增字典</button>
    <button class="btn btn-danger margin-l5" onclick="deleteDictionary();" data-toggle="modal"><i class="icon-delete"></i>删除</button>
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
                          <th>字典名称</th>
                          <th>字典键</th>
                          <th>字典值</th>
                          <th>说明</th>
                          <th>创建时间</th>
                          <th class="center">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${list}" var="dic">
	                      <tr>
	                          <td><span class="icon"><input type="checkbox" id="checkbox2" name="checkbox2" value="${dic.dicId}"/></span></td>
	                          <td>${dic.dicName}</td>
	                          <td>${dic.dicKey}</td>
	                          <td>${dic.dicVal}</td>
	                          <td>${dic.dicDes}</td>
	                          <td>${dic.createDate}</td>
	                          <td>
	                              <div class="btn-group">
	                                <button data-toggle="dropdown" class="btn min-btn dropdown-toggle"><i class="icon-wrench"></i>操作<span class="caret"></span></button>
	                                <ul class="dropdown-menu">
	                                	<li><a href="#myAlertEdit" data-toggle="modal" onclick="updateDictionary('${dic.dicId}','${dic.dicName}','${dic.dicKey}','${dic.dicVal}', '${dic.dicDes}');">编辑</a></li>
	                                    <!-- <li><a href="#myAlertDelete" data-toggle="modal">删除</a></li> -->
	                                </ul>
	                              </div>
	                          </td>
	                      </tr>
                      </c:forEach>
                    </tbody>
                </table>
                <div>
                	<form method="post" id="requestForm" action="<%=path%>/dictionary/getAllDictionary">
                		<input type="hidden" value="1" name="sub"/>
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
<!--新增字典 start-->
<div id="myAlertCreate" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>新增字典</h3>
    </div>
    <form class="form-horizontal" method="post" action="addDictionary" name="createKeyPair" id="createKeyPair" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">字典名称:</label>
                        <div class="controls control-s">
                            <input type="text" name="dicName" id="dicName1"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">字典键:</label>
                        <div class="controls control-s">
                            <input type="text" name="dicKey" id="dicKey1"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">字典值:</label>
                        <div class="controls control-s">
                            <input type="text" name="dicVal" id="dicVal1"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">说明:</label>
                        <div class="controls control-s">
                            <textarea name="dicDes" id="dicDes1" class="v-describe---100"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <!--<a data-dismiss="modal" class="btn btn-primary" href="#">确定</a>-->
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
          <p>你确定要对字典进行删除操作吗？</p>
      </div>
       <div class="modal-footer">
          <a class="btn btn-primary" href="#" data-dismiss="modal">确定</a>
          <a class="btn" href="#" data-dismiss="modal">取消</a>
      </div>
  </div>
  <!--删除end-->
<!--编辑字典 start-->
<div id="myAlertEdit" class="modal modal-mid hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3><i></i>编辑</h3>
    </div>
    <form class="form-horizontal" method="post" action="updateDictionaryById" name="createKeyPair" id="createKeyPair" novalidate="novalidate">
        <div class="modal-body">
            <div class="widget-box">
                <div class="poplump">
                    <div class="control-group">
                        <label class="control-label control-slabel">字典名称:</label>
                        <div class="controls control-s">
                        	<input type="hidden" name="dicId" id="dicId">
                            <input type="text" name="dicName" id="dicName"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">字典键:</label>
                        <div class="controls control-s">
                            <input type="text" name="dicKey" id="dicKey"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">字典值:</label>
                        <div class="controls control-s">
                            <input type="text" name="dicVal" id="dicVal"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label control-slabel">说明:</label>
                        <div class="controls control-s">
                            <textarea name="dicDes" id="dicDes" class="v-describe---100"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <!--<a data-dismiss="modal" class="btn btn-primary" href="#">确定</a>-->
            <input type="submit" value="确定" class="btn btn-primary" />
            <a data-dismiss="modal" class="btn" href="#">取消</a>
        </div>
    </form>
</div>
<!--编辑字典 end-->
</body>
</html>