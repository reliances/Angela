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
	<link type="text/css" href="<%=path%>/css/ymPrompt.css" title="www"  rel="stylesheet" >
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
	<style type="text/css">
    .form-horizontal .control-label{
        padding-top: 12px;
    }
    .controls .cs-dw{
        margin-left: 5px;
    }
    .uploader-list-container{
        width: 80%;
    }
    .form-horizontal .conts-3[type=text]{
        width: 25%;
    }
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
    <a href="#" title="图片管理" class="tip-bottom"><i class="icon-home"></i>图片管理</a>
    <a href="#" class="current">图片添加</a>
</div>


<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="icon-align-justify"></i>                                  
                    </span>
                    <h5>图片信息</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="addPictures" enctype="multipart/form-data" method="post" class="form-horizontal" style="padding-top: 10px">
                        <div class="control-group">
                            <label class="control-label">产品ID</label>
                            <div class="controls">
                                <input type="text" name="productId" class="js-productId" onblur="changeNo('js-productId', 'getProductId');" placeholder="产品ID" >
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">图片类型</label>
                            <div class="controls">
                                <select class="w82" name="imageType">
                                    <option value="1">产品</option>
                                    <option value="2">案例</option>
                                    <option value="3">广告</option>
                                    <option value="4">其它</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">产品图片</label>
                            <div class="controls">
                                <input type="file" name="imageUrl" placeholder="产品图片">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"></label>
                            <div class="controls">
                                <input type="submit" value="保存" class="btn btn-primary" onclick="return submitForm();"/>
                                <a data-dismiss="modal" class="btn" onclick="javascript:window.history.go(-1);">取消</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>                      
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
	var submitForm = function() {
		if (changeNo('js-productId', 'getProductId')) {
	 		return true;
	 	} else {
	 		return false;
	 	}
	}
</script>
</body>
</html>