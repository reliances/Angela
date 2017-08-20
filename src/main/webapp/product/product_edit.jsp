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
    <link type="text/css" href="<%=path%>/css/webuploader/0.1.5/webuploader.css" rel="stylesheet" />
	<link type="text/css" href="<%=path%>/css/ymPrompt.css" title="www"  rel="stylesheet" >
	<script type="text/javascript" src="<%=path%>/css/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="<%=path%>/css/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" src="<%=path%>/js/pace.min.js"> </script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"> </script>
	<script type="text/javascript" src="<%=path%>/css/ueditor/lang/zh-cn/zh-cn.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    $(function(){
	    	var ue = UE.getEditor('editor');
	    });
    
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
                    <form action="updateProduct" enctype="multipart/form-data" method="post" class="form-horizontal" style="padding-top: 10px">
                    	<input type="hidden" name="id" value="${product.id}">
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
                            <label class="control-label">商品名称：</label>
                            <div class="controls">
                                <input type="text" name="productName" class="js-productName" onblur="changeNo('js-productName', 'getProductName');" placeholder="商品名称" value="${product.productName}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">商品SN码：</label>
                            <div class="controls">
                                <input type="text" name="productSn" class="js-productSn" onblur="changeNo('js-productSn', 'getProductSn');" placeholder="商品的条形码" value="${product.productSn}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">商品分类：</label>
                            <div class="controls">
                                <select class="w82" name="categoryId">
	                                <c:forEach items="${category}" var="cate">
		                              	<%-- <option value="${cate.id}">${cate.catName}</option> --%>
		                              	<option value="${cate.id}" <c:if test="${cate.id eq product.categoryId}">selected</c:if>>${cate.catName}</option>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div>
                        <!-- <div class="control-group">
                            <label class="control-label">点击数</label>
                            <div class="controls">
                                <input type="text" placeholder="点击数" name="clickCount" value="${product.clickCount}">
                            </div>
                        </div> -->
                       <div class="control-group">
                            <label class="control-label">市场价：</label>
                            <div class="controls">
                                <input type="text" name="marketPrice" class="js-marketPrice" onblur="changeNo('js-marketPrice', 'getMarketPrice');" placeholder="市场价格" value="${product.marketPrice}"><span class="cs-dw">元</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">本店售价：</label>
                            <div class="controls">
                                <input type="text" name="productPrice" class="js-productPrice" onblur="changeNo('js-productPrice', 'getProductPrice');" placeholder="实际售价" value="${product.productPrice}"><span class="cs-dw">元</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否上架：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="isOnSale">
                                    <option value="0" <c:if test="${product.isOnSale eq 0}">selected</c:if>>是</option>
                                    <option value="1" <c:if test="${product.isOnSale eq 1}">selected</c:if>>否</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否热销：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="isHot">
                                    <option value="0" <c:if test="${product.isHot eq 0}">selected</c:if>>是</option>
                                    <option value="1" <c:if test="${product.isHot eq 1}">selected</c:if>>否</option>
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
                       		<label class="control-label">计量单位：</label>
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
                            <label class="control-label">库存：</label>
                            <div class="controls">
                                <input type="text" name="stock" class="js-stock" onblur="changeNo('js-stock', 'getStock');" placeholder="库存" value="${product.stock}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">颜色：</label>
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
                        	<label class="control-label">产地：</label>
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
                        <div class="control-group">
                        	<label class="control-label">材质：</label>
                            <div class="controls" class="w82">
                                <select class="w82" name="material">
                                    <c:forEach items="${dictionary}" var="dic">
	                                	<c:if test="${dic.dicKey == 'product_material' }">
		                              		<option value="${dic.dicId}" <c:if test="${dic.dicId eq product.material}">selected</c:if>>${dic.dicVal}</option>
		                              	</c:if>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">标签：</label>
                            <div class="controls">
                                <select class="w82" name="proTag">
	                                <c:forEach items="${dictionary}" var="dic">
	                                	<c:if test="${dic.dicKey == 'product_tag' }">
		                              		<option value="${dic.dicId}" <c:if test="${dic.dicId eq product.proTag}">selected</c:if>>${dic.dicVal}</option>
		                              	</c:if>
	                              	</c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">尺寸：</label>
                            <div class="controls controls-flex">
                                <input type="text" name="sizeL" class="conts-3" placeholder="长度" value="${product.sizeL}">&nbsp;&nbsp;&nbsp;
                                <input type="text" name="sizeW" class="conts-3" placeholder="宽度" value="${product.sizeW}">&nbsp;&nbsp;&nbsp;
                                <input type="text" name="sizeH" class="conts-3" placeholder="高度" value="${product.sizeH}">&nbsp;&nbsp;
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
                        </div> -->
                        <!-- <div class="control-group">
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
                        </div> -->
                        <div class="control-group">
                            <label class="control-label">图片上传：</label>
                            <div class="controls">
                            	<div style="margin-left:1px; width:990px;">
									<input type="file" name="file" id="doc" multiple="multiple" onchange="javascript:setImagePreviews();" accept="image/*" />
									<div id="dd"></div>
								</div>
                            </div>
                        </div>
                        <div class="control-group">
							<label class="control-label">商品图片：</label>
							<div class="controls">
								<c:forEach items="${picList}" var="pic" varStatus="status">
                        			<c:if test="${pic.productId eq product.id && pic.imageType == 1}">
                         	  	  		<img id="upload${status.index}" src="<%=path%>/upload/${pic.imageUrl}" width="100" height="75" onclick="file${status.index}.click()"/>
										<div style="display:none">
											<input type="file" name="imageUrl" id="file${status.index}" onchange="uploadImage('${status.index}')">
										</div>
                         	  	  	</c:if>
                         	  	</c:forEach>
							</div>
						</div>
                        <div class="control-group">
                            <label class="control-label">简短描述：</label>
                            <div class="controls">
                                <input type="text" name="brief" class="js-brief" onblur="changeNo('js-brief', 'getBrief');" placeholder="简短描述" value="${product.brief}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">详细描述：</label>
                            <div class="controls">
								<script id="editor" name="productDetails" type="text/plain" style="width:90%; height:300px;">${product.productDetails}</script> 
							</div>
                            <!-- <div class="controls">
                                <textarea name="productDetails" placeholder="详细描述"></textarea>
                            </div> -->
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
	function uploadImage(n){
		$.ajaxFileUpload({  
        	url:'/product/update/image',  
        	secureuri:false,                       //是否启用安全提交,默认为false   
        	fileElementId:'file'+n, 
        	dataType:'json',                       //服务器返回的格式,可以是json或xml等  
        	success:function(data, status){ //本例中设定上传文件完毕后,服务端会返回给前台[filepath]  
            	var url = data.url;
               	var img = document.getElementById("img"+n);
               	$("#img"+n).attr("src", url.substring(1));
        	},  
        	error:function(data, status, e){ //服务器响应失败时的处理函数  
            	alert("图片上传失败");  
        	}  
    	}); 
	}

	var submitForm = function() {
		if (changeNo('js-productName', 'getProductName')&& changeNo('js-productSn', 'getProductSn') && changeNo('js-marketPrice', 'getMarketPrice')
				&& changeNo('js-productPrice', 'getProductPrice') && changeNo('js-stock', 'getStock') && changeNo('js-brief', 'getBrief') ) {
	 		return true;
	 	} else {
	 		return false;
	 	}
	}
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
                imgObjPreview.style.width = '150px';
                imgObjPreview.style.height = '180px';
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
                localImagId.style.width = "150px";
                localImagId.style.height = "180px";
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