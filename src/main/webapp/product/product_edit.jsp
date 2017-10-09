<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="page" uri="../WEB-INF/tld/pager.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Angela后台管理平台</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="../header.jsp" flush="true"/>
    <script type="text/javascript" src="<%=path%>/js/ymPrompt.js" ></script>
    <link type="text/css" href="<%=path%>/css/webuploader/0.1.5/webuploader.css" rel="stylesheet" />
	<link type="text/css" href="<%=path%>/css/ymPrompt.css" title="www"  rel="stylesheet" >
	<script type="text/javascript" src="<%=path%>/css/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="<%=path%>/css/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" src="<%=path%>/js/pace.min.js"> </script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"> </script>
	<script type="text/javascript" src="<%=path%>/css/ueditor/lang/zh-cn/zh-cn.js"></script>
	<link type="text/css" href="<%=path%>/js/validation/base.css" title="www"  rel="stylesheet" >
	<script type="text/javascript" src="<%=path%>/js/validation/validate-methods.js"></script> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    $(function(){
	    	var ue = UE.getEditor('editor');
	    });
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
	    .form-horizontal .conts-4[type=text]{
	        width: 6.6%;
	    }
	    .controls-label label{ 
			display:inline-block !important;
		}
		.addImg{
			margin: 50px;
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
    <a href="#" class="current">商品编辑</a>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="icon-align-justify"></i>                                  
                    </span>
                    <h5>产品信息</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="updateProduct" id="form-product-edit" enctype="multipart/form-data" method="post" class="form-horizontal" style="padding-top: 10px">
                    	<input type="hidden" id="proId" name="id" value="${product.id}">
                    	<input type="hidden" id="imgIds" name="imgIds">
                        <!-- <div class="control-group">
                            <label class="control-label">中文名称</label>
                            <div class="controls">
                                <input type="text" name="productName">
                            </div>
                        </div> -->
                        <!-- <div class="control-group">
                            <label class="control-label">商品主键ID</label>
                            <div class="controls">
                                <input type="password">
                            </div>
                        </div> -->
                        <div class="control-group">
                            <label class="control-label"> 商品名称：</label>
                            <div class="controls">
                                <input type="text" name="productName" placeholder="商品名称" value="${product.productName}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">商品SN码：</label>
                            <div class="controls">
                                <input type="text" name="productSn" placeholder="商品的条形码" value="${product.productSn}">
                                <input type="hidden" id="cateIds" value="${product.categoryId}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 商品分类：</label>
                            <div class="controls controls-label">
                                <%-- <select class="w82" name="categoryId" class="selectpicker show-tick form-control" multiple data-live-search="true">
			                        <c:forEach items="${category}" var="cate">
		                              	<option value="${cate.id}" <c:if test="${fn:contains(product.categoryId, cate.id)}">selected</c:if>>${cate.cateName}</option>
	                              	</c:forEach>
                                </select> --%>
                                <c:forEach items="${category}" var="cate">
                                	<label><input type="checkbox" name="categoryId" value="${cate.id }" <c:if test="${fn:contains(product.categoryId, cate.id)}">checked</c:if>/>${cate.cateName}</label>
                              	</c:forEach>
                            </div>
                        </div>
                        <!-- <div class="control-group">
                            <label class="control-label">点击数</label>
                            <div class="controls">
                                <input type="text" placeholder="点击数" name="clickCount" value="${product.clickCount}">
                            </div>
                        </div> -->
                        <div class="control-group">
                            <label class="control-label"> 商品售价：</label>
                            <div class="controls">
                                <input type="text" name="productPrice" placeholder="实际售价" value="${product.productPrice}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 样品价格：</label>
                            <div class="controls">
                                <input type="text" name="marketPrice" placeholder="市场价格" value="${product.marketPrice}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 批发价格：</label>
                            <div class="controls controls-flex">
                                <input type="text" name="countA" value="${product.countA}" class="conts-4" placeholder="批发数量">:<input type="text" name="priceA" value="${product.priceA}" class="conts-4" placeholder="价格">
                                <input type="text" name="countB" value="${product.countB}" class="conts-4" placeholder="批发数量">:<input type="text" name="priceB" value="${product.priceB}" class="conts-4" placeholder="价格">
                                <input type="text" name="countC" value="${product.countC}" class="conts-4" placeholder="批发数量">:<input type="text" name="priceC" value="${product.priceC}" class="conts-4" placeholder="价格">
                                <input type="text" name="countD" value="${product.countD}" class="conts-4" placeholder="批发数量">:<input type="text" name="priceD" value="${product.priceD}" class="conts-4" placeholder="价格">
                                <input type="text" name="countE" value="${product.countE}" class="conts-4" placeholder="批发数量">:<input type="text" name="priceE" value="${product.priceE}" class="conts-4" placeholder="价格">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 价格包含：</label>
                            <div class="controls">
                                <input type="text" name="priceIncludes" placeholder="价格包含的具体内容,可以为空" value="${product.priceIncludes}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 是否上架：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="isOnSale">
                                    <option value="0" <c:if test="${product.isOnSale eq 0}">selected</c:if>>是</option>
                                    <option value="1" <c:if test="${product.isOnSale eq 1}">selected</c:if>>否</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 是否热销：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="isHot">
                                    <option value="0" <c:if test="${product.isHot eq 0}">selected</c:if>>是</option>
                                    <option value="1" <c:if test="${product.isHot eq 1}">selected</c:if>>否</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> Weekly Discount：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="weeklyDiscount">
                                    <option value="0" <c:if test="${product.weeklyDiscount eq 0}">selected</c:if>>是</option>
                                    <option value="1" <c:if test="${product.weeklyDiscount eq 1}">selected</c:if>>否</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">排序号：</label>
                            <div class="controls">
                                <input type="text" name="sortOrder" placeholder="排序号" value="${product.sortOrder}">
                            </div>
                        </div>
                        <div class="control-group">
                       		<label class="control-label"> 计量单位：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="productUnit">
                                    <c:forEach items="${dictionary}" var="dic">
	                                	<c:if test="${dic.dicKey == 'product_unit' }">
		                              		<option value="${dic.dicId}" <c:if test="${dic.dicId eq product.productUnit}">selected</c:if>>${dic.dicVal}</option>
		                              	</c:if>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 库存：</label>
                            <div class="controls">
                                <input type="text" name="stock" placeholder="库存" value="${product.stock}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 颜色：</label>
                            <div class="controls">
                                <select class="w82" name="productColor">
	                                <c:forEach items="${dictionary}" var="dic">
	                                	<c:if test="${dic.dicKey == 'product_color' }">
		                              		<option value="${dic.dicId}" <c:if test="${dic.dicId eq product.productColor}">selected</c:if>>${dic.dicVal}</option>
		                              	</c:if>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                        	<label class="control-label"> 产地：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="productArea">
                                    <c:forEach items="${dictionary}" var="dic">
	                                	<c:if test="${dic.dicKey == 'product_area' }">
		                              		<option value="${dic.dicId}" <c:if test="${dic.dicId eq product.productArea}">selected</c:if>>${dic.dicVal}</option>
		                              	</c:if>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- <div class="control-group">
                        	<label class="control-label"> 材质：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="material">
                                    <c:forEach items="${dictionary}" var="dic">
	                                	<c:if test="${dic.dicKey == 'product_material' }">
		                              		<option value="${dic.dicId}" <c:if test="${dic.dicId eq product.material}">selected</c:if>>${dic.dicVal}</option>
		                              	</c:if>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div> --%>
                        <div class="control-group">
                            <label class="control-label"> 材质：</label>
                            <div class="controls controls-label">
                                <c:forEach items="${dictionary}" var="dic">
                                	<c:if test="${dic.dicKey eq 'product_material' }">
                                		<label><input type="checkbox" name="material" value="${dic.dicId }" <c:if test="${fn:contains(product.material , dic.dicId)}">checked</c:if>/>${dic.dicVal}</label>
	                              	</c:if>
                              	</c:forEach>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 标签：</label>
                            <div class="controls controls-label">
                                <%-- <select class="w82" name="proTag">
	                                <c:forEach items="${dictionary}" var="dic">
	                                	<c:if test="${dic.dicKey == 'product_tag' }">
		                              		<option value="${dic.dicId}" <c:if test="${dic.dicId eq product.proTag}">selected</c:if>>${dic.dicVal}</option>
		                              	</c:if>
	                              	</c:forEach>
                                </select> --%>
                                <c:forEach items="${dictionary}" var="dic">
                                	<c:if test="${dic.dicKey eq 'product_tag' }">
                                		<label><input type="checkbox" name="proTag" value="${dic.dicId }" <c:if test="${fn:contains(product.proTag , dic.dicId)}">checked</c:if>/>${dic.dicVal}</label>
	                              	</c:if>
                              	</c:forEach>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> 尺寸：</label>
                            <div class="controls controls-flex">
                                <input type="text" name="sizeL" class="conts-3" placeholder="长度" value="${product.sizeL}">&nbsp;&nbsp;&nbsp;
                                <input type="text" name="sizeW" class="conts-3" placeholder="宽度" value="${product.sizeW}">&nbsp;&nbsp;&nbsp;
                                <input type="text" name="sizeH" class="conts-3" placeholder="高度" value="${product.sizeH}">&nbsp;&nbsp;
                            </div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">Shipping Estimate：</label>
                            <div class="controls">
                                <input type="text" name="shippingEstimate" value="${product.shippingEstimate}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> Detail Color：</label>
                            <div class="controls">
                                <input type="text" name="detailColor" value="${product.detailColor}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Imprint Method：</label>
                            <div class="controls">
                                <input type="text" name="imprintMethod" value="${product.imprintMethod}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Imprint Color：</label>
                            <div class="controls">
                                <input type="text" name="imprintColor" value="${product.imprintColor}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Imprint Size：</label>
                            <div class="controls">
                                <input type="text" name="imprintSize" value="${product.imprintSize}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Full Color Process：</label>
                            <div class="controls">
                                <input type="text" name="fullColorProcess" value="${product.fullColorProcess}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Personalization：</label>
                            <div class="controls">
                                <input type="text" name="personalization" value="${product.personalization}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Sold Unimprinted：</label>
                            <div class="controls">
                                <input type="text" name="soldUnimprinted" value="${product.soldUnimprinted}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Artwork & Proofs：</label>
                            <div class="controls controls-label">
                            	<c:forEach items="${dictionary}" var="dic">
                                	<c:if test="${dic.dicKey eq 'product_proofs' }">
                                		<label><input type="checkbox" name="productProofs" value="${dic.dicId }" <c:if test="${fn:contains(product.productProofs , dic.dicId)}">checked</c:if>/>${dic.dicVal}</label>
	                              	</c:if>
                              	</c:forEach>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Production Time：</label>
                            <div class="controls">
                                <input type="text" name="productionTime" value="${product.productionTime}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Rush Service：</label>
                            <div class="controls">
                                <input type="text" name="rushService" value="${product.rushService}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">FOB Point：</label>
                            <div class="controls">
                                <input type="text" name="fobPoint" value="${product.fobPoint}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Shipping Weight：</label>
                            <div class="controls">
                                <input type="text" name="productWeight" value="${product.productWeight}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Packaging：</label>
                            <div class="controls">
                                <input type="text" name="packaging" value="${product.packaging}">
                            </div>
                        </div>
                        <!-- <div class="control-group">
                            <label class="control-label">卖出数量</label>
                            <div class="controls">
                                <input type="text" name="sellCount" placeholder="卖出数量">
                            </div>
                        </div> -->
                        <!-- <div class="control-group">
                            <label class="control-label">创建者</label>
                            <div class="controls">
                                <input type="text" name="createUser" placeholder="创建者">
                            </div>
                        </div> -->
                        <!-- <div class="control-group">
                            <label class="control-label">创建时间</label>
                            <div class="controls">
                                <div class="input-append date" data-form="datepicker" data-date="12-02-2014" data-date-format="dd-mm-yyyy">
                                 <input id="inputDate" name="createDate" class="grd-white" data-form="datepicker" size="16" type="text" value="12-02-2014" />
                                 <span class="add-on"><i class="icon-th"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">最后修改时间</label>
                            <div class="controls">
                                <div class="input-append date" data-form="datepicker" data-date="12-02-2014" data-date-format="dd-mm-yyyy">
                                 <input id="inputDate" name="lastUpdate" class="grd-white" data-form="datepicker" size="16" type="text" value="12-02-2014" />
                                 <span class="add-on"><i class="icon-th"></i></span>
                                </div>
                            </div>
                        </div> 
                        <div class="control-group">
                            <label class="control-label">产品状态</label>
                            <div class="controls">
                                <select class="w82" name="productStatus">
                                    <option>是</option>
                                    <option>否</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否删除</label>
                            <div class="controls">
                                <select class="w82" name="isDelete">
                                    <option>是</option>
                                    <option>否</option>
                                </select>
                            </div>
                        </div>-->
                        <div class="control-group">
                            <label class="control-label"> 简短描述：</label>
                            <div class="controls">
                                <input type="text" name="brief" placeholder="简短描述" value="${product.brief}">
                            </div>
                        </div>
                        <div class="control-group">
							<label class="control-label">商品图片：</label>
							<div class="controls">
								<c:forEach items="${picList}" var="pic" varStatus="status">
                        			<c:if test="${pic.productId eq product.id && pic.imageType == 1}">
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
                         	  	<img class="addImg" src="<%=path%>/img/plus.jpg" onclick="doc.click()"/>
                         	  	<div style="display:none">
									<input type="file" name="myFiles" id="doc" multiple="multiple" onchange="javascript:setImagePreviews();" accept="image/*" >
								</div>
								<div id="dd"></div>
							</div>
						</div>
                        <div class="control-group">
                            <label class="control-label"> 详细描述：</label>
                            <div class="controls">
								<script id="editor" name="productDetails" type="text/plain" style="width:90%; height:300px;">${product.productDetails}</script> 
							</div>
                            <!-- <div class="controls">
                                <textarea name="productDetails" placeholder="详细描述"></textarea>
                            </div> -->
                        </div>
                        <div class="control-group">
							<label class="control-label">商品详情图片：</label>
							<div class="controls">
								<c:forEach items="${picList}" var="pic" varStatus="status">
                        			<c:if test="${pic.productId eq product.id && pic.imageType == 4}">
                         	  	  		<a class="img-wrap" href="javascript:void(0)">
	                         	  	  		<img id="upload2${status.index}" src="<%=path%>/upload/${pic.imageUrl}" style="width:100px;height:100px; margin: 10px;" onclick="file2${status.index}.click()"/>
											<i class="img-close-icon" onclick="setImage('${pic.imageId}',this);"></i>
										</a>
										<div style="display:none">
											<input type="file" name="file" id="file2${status.index}" onchange="uploadImage2('${status.index}')">
											<input id="imgId2${status.index}" type="hidden" value="${pic.imageId}" />
										</div>
                         	  	  	</c:if>
                         	  	</c:forEach>
                         	  	<img class="addImg" src="<%=path%>/img/plus.jpg" onclick="doc2.click()"/>
                         	  	<div style="display:none">
									<input type="file" name="myFiles2" id="doc2" multiple="multiple" onchange="javascript:setImagePreviews2();" accept="image/*" >
								</div>
								<div id="dd2"></div>
							</div>
						</div>
                        <div class="control-group">
                            <label class="control-label"></label>
                            <div class="controls">
								<input type="submit" value="保存" class="btn btn-primary"/>
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
	//表单验证
	$(function(){
		$("#form-product-edit").validate({
			rules:{
				productName:{
					required:false,
					maxlength:150
				},
				marketPrice:{
					required:false,
					number:true
				},
				productPrice:{
					required:false,
					number:true
				},
				stock:{
					required:false,
					digits:true
				},
				sizeL:{
					required:false,
					digits:false
				},
				sizeW:{
					required:false,
					digits:false
				},
				sizeH:{
					required:false,
					digits:false
				},
				brief:{
					required:false,
					maxlength:255
				},
				productDetails:{
					required:false,
					maxlength:255
				}
			},
			onkeyup:false,
			focusCleanup:true,
			success:"valid",
			submitHandler:function(form){
				$(form).ajaxSubmit();
			}
		});
	});
	
	var imgIds = [];
	function setImage(imgId,obj){
		$(obj).parent().hide();
		imgIds.push(imgId);
		$("#imgIds").val(imgIds); 
	}
	
	function uploadImage(n){
		var productId = $("#proId").val();
 		var imgId = $("#imgId"+n).val();
		$.ajaxFileUpload({  
        	url:'../picture/update/image',  
        	secureuri:false,                       //是否启用安全提交,默认为false   
        	fileElementId:'file'+n,
 			data:{"productId":productId,"imgId":imgId,"imgType":1},          
        	dataType:'json',                       //服务器返回的格式,可以是json或xml等  
        	success:function(data, status){ //本例中设定上传文件完毕后,服务端会返回给前台[filepath]
            	var url = data.imageUrl;
               	$("#upload"+n).attr("src", "<%=path%>/upload/"+url);
        	},  
        	error:function(data, status, e){ //服务器响应失败时的处理函数  
            	alert("图片上传失败");  
        	}  
    	}); 
	};
	function uploadImage2(n){
		var productId = $("#proId").val();
 		var imgId = $("#imgId2"+n).val();
		$.ajaxFileUpload({  
        	url:'../picture/update/image',  
        	secureuri:false,                       //是否启用安全提交,默认为false   
        	fileElementId:'file2'+n,
 			data:{"productId":productId,"imgId":imgId,"imgType":4},          
        	dataType:'json',                       //服务器返回的格式,可以是json或xml等  
        	success:function(data, status){ //本例中设定上传文件完毕后,服务端会返回给前台[filepath]
            	var url = data.imageUrl;
               	$("#upload2"+n).attr("src", "<%=path%>/upload/"+url);
        	},  
        	error:function(data, status, e){ //服务器响应失败时的处理函数  
            	alert("图片上传失败");  
        	}  
    	}); 
	};
    //下面用于多图片上传预览功能
    function setImagePreviews(avalue) {
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

	//下面用于多图片上传预览功能
    function setImagePreviews2(avalue) {
        var docObj = document.getElementById("doc2");
        var dd = document.getElementById("dd2");
        dd.innerHTML = "";
        var fileList = docObj.files;
        for (var i = 0; i < fileList.length; i++) {            
            dd.innerHTML += "<div style='float:left' > <img id='img2" + i + "'  /> </div>";
            var imgObjPreview = document.getElementById("img2"+i); 
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
                var localImagId = document.getElementById("img2" + i);
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