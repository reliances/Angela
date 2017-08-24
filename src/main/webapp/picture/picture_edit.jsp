<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="page" uri="../WEB-INF/tld/pager.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Angela后台管理平台</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include page="../header.jsp" flush="true" />
<script type="text/javascript" src="<%=path%>/js/ymPrompt.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"> </script>
<link type="text/css" href="<%=path%>/css/ymPrompt.css" title="www"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" type="text/javascript">
	var cancel = function() {
		$("#catName1").val("");
	}
</script>
<style type="text/css">
.form-horizontal .control-label {
	padding-top: 12px;
}

.controls .cs-dw {
	margin-left: 5px;
}

.uploader-list-container {
	width: 80%;
}

.form-horizontal .conts-3[type=text] {
	width: 25%;
}

.addImg{
	margin: 25px;
	width:	30px;
	height: 30px;
}
.img-wrap{
	  display: block;
	  position: relative;
	  display:inline-block;
	}
	.img-wrap .img{
	  display:block;
	}
	.img-wrap:hover .img-close-icon{
	  display:block;
	}
	.img-wrap .img-close-icon{
	  display:none;
	  position: absolute;
	  left: 95px;
	  top: 10px;
	  display: block;
	  background: url(../img/cancel.png);
	  width: 16px;
	  height: 16px;
	}
</style>
</head>
<body>
	<div id="header">
		<h1>
			<a href="#">Angela后台管理平台</a>
		</h1>
	</div>
	<div id="user-nav" class="navbar navbar-inverse">
		<jsp:include page="../top.jsp" flush="true" />
	</div>

	<!--左侧菜单 start-->
	<div id="sidebar">
		<jsp:include page="../left.jsp" flush="true" />
	</div>
	<!--左侧菜单 end-->
	<div id="content">
		<div id="breadcrumb">
			<a href="#" title="图片管理" class="tip-bottom"><i class="icon-home"></i>图片管理</a>
			<a href="#" class="current">图片编辑</a>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>图片信息</h5>
						</div>
						<div class="widget-content nopadding">
							<form action="updatePictures" enctype="multipart/form-data" method="post" class="form-horizontal" style="padding-top: 10px">
								<input type="hidden" id="pId" name="productId" value="${picInfo.productId}"> 
								<input type="hidden" name="imageId" value="${picInfo.imageId}">
								<input type="hidden" id="imgIds" name="imgIds">
								<div class="control-group">
									<label class="control-label">商品分类：</label>
									<div class="controls">
										<select class="w82" name="categoryId">
											<c:forEach items="${category}" var="cate">
												<option value="${cate.id}"
													<c:if test="${picInfo.productId eq cate.id}">selected</c:if>>${cate.cateName}</option>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">图片类型</label>
                            <div class="controls">
                            	<select class="w82" name="imageType">
                            		<option value="1" <c:if test="${picInfo.imageType == 1}">selected</c:if>>产品</option>
                                    <option value="2" <c:if test="${picInfo.imageType == 2}">selected</c:if>>案例</option>
                                    <option value="3" <c:if test="${picInfo.imageType == 3}">selected</c:if>>广告</option>
                                    <option value="4" <c:if test="${picInfo.imageType == 4}">selected</c:if>>其它</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">产品图片</label>
                            <div class="controls">
								<c:forEach items="${allPict}" var="pic" varStatus="status">
                        			<c:if test="${pic.productId eq picInfo.productId}">
                        				<a class="img-wrap" href="javascript:void(0)">
	                         	  	  		<img id="upload${status.index}" src="<%=path%>/upload/${pic.imageUrl}" style="width:100px;height:100px; margin: 10px;" onclick="file${status.index}.click()"/>
											<i class="img-close-icon" onclick="setImage('${pic.imageId}',this);"></i>
										</a>
										<div style="display:none">
											<input type="file" name="file" id="file${status.index}" onchange="uploadImage('${status.index}')">
											<input id="imgId${status.index}" type="hidden" value="${pic.imageId}" />
										</div>
                         	  	  	</c:if>
                         	  	</c:forEach>
								<img src="<%=path%>/img/plus.jpg" class="addImg" onclick="doc.click()"/>
                         	  	<div style="display:none">
									<input type="file" name="myFiles" id="doc" multiple="multiple" onchange="javascript:setImagePreviews();" accept="image/*" >
								</div>
								<div id="dd"></div>
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
	var imgIds = [];
	function setImage(imgId,obj){
		$(obj).parent().hide();
		imgIds.push(imgId);
		$("#imgIds").val(imgIds); 
	}

	var submitForm = function() {
		if (changeNo('js-productId', 'getProductId')) {
	 		return true;
	 	} else {
	 		return false;
	 	}
	}
	//修改图片
	function uploadImage(n){
		var productId = $("#pId").val();
		var imgId = $("#imgId"+n).val();
		var imgType = ${picInfo.imageType};
		$.ajaxFileUpload({  
	    	url:'update/image',  
	    	secureuri:false,                       //是否启用安全提交,默认为false   
	    	fileElementId:'file'+n,
			data:{"productId":productId,"imgId":imgId,"imgType":imgType},          
	    	dataType:'json',                       //服务器返回的格式,可以是json或xml等  
	    	success:function(data, status){ //本例中设定上传文件完毕后,服务端会返回给前台[filepath]
	        	var url = data.imageUrl;
	           	$("#upload"+n).attr("src", "<%=path%>/upload/"+url);
	    	},  
	    	error:function(data, status, e){ //服务器响应失败时的处理函数  
	        	alert("图片上传失败");  
	    	}  
		}); 
	}
	//下面用于多图片上传预览功能
    function setImagePreviews() {
        var docObj = document.getElementById("doc");
        var dd = document.getElementById("dd");
        dd.innerHTML = "";
        var fileList = docObj.files;
        for (var i = 0; i < fileList.length; i++) {            
            dd.innerHTML += "<div style='float:left' > <img id='img" + i + "'  /> </div>";
            var imgObjPreview = document.getElementById("img"+i); 
            if (docObj.files && docObj.files[i]) {
                //火狐下，直接设img属性
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = '100px';
                imgObjPreview.style.height = '100px';
				imgObjPreview.style.margin = "10px";
                //imgObjPreview.src = docObj.files[0].getAsDataURL();
                //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
            }else {
                //IE下，使用滤镜
                docObj.select();
                var imgSrc = document.selection.createRange().text;
                alert(imgSrc)
                var localImagId = document.getElementById("img" + i);
                //必须设置初始大小
                localImagId.style.width = "100px";
                localImagId.style.height = "100px";
				localImagId.style.margin = "10px";
                //图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                }catch (e) {
                    alert("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
        }  
        return true;
    }
</script>
</body>
</html>