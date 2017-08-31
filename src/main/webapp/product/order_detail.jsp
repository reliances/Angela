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
	<script type="text/javascript" src="<%=path%>/js/pace.min.js"> </script>
	<link type="text/css" rel="stylesheet" href="<%=path%>/js/view/css/viewer.min.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" type="text/javascript">
	    
	</script>
	<style type="text/css">
	    .form-horizontal .row-fluid .span6{
	        margin-left:0!important;
	    }
	    .form-horizontal .row-fluid{
	        margin-top:0!important;
	    }
	    .form-horizontal .control-label{
	        padding-top: 12px;
	        width:130px;
	    }
	    .form-horizontal .controls{
	    	margin-left:150px;
	    }
	    .controls .cs-dw{
	        margin-left: 5px;
	    }
	    .uploader-list-container{
	        width: 80%;
	    }
	    .form-horizontal .conts-3[type=text]{
	        width: 24%;
	    }
	    .w82 {
		    width: 81.2%;
		}
		.widget-content nopadding{ 
			display:inline-block !important;
		}
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
    <a href="#" title="订单管理" class="tip-bottom"><i class="icon-home"></i>订单管理</a>
    <a href="#" class="current">订单详情</a>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="icon-align-justify"></i>                                  
                    </span>
                    <h5>订单信息</h5>
                </div>
                <div class="form-horizontal widget-content nopadding ">
                   <div class="row-fluid">
                       <div class="span6">
                           <div class="control-group">
                               <label class="control-label">公司名称：</label>
                               <div class="controls">
                                   <input type="text" name="companyName" value="${orderInfo.companyName }" readonly="readonly"> 
                               </div>
                           </div>
                       </div>
                       <div class="span6">
                        <div class="control-group">
                            <label class="control-label">用户名称：</label>
                            <div class="controls">
                                <input type="text" name="customName" value="${orderInfo.customName }" readonly="readonly">
                            </div>
                        </div>
                       </div>
                       <div class="span6">
                           <div class="control-group">
                               <label class="control-label"> E-Mail：</label>
                               <div class="controls">
                                   <input type="text" name="email" value="${orderInfo.email }" readonly="readonly">
                               </div>
                           </div>
                       </div>
                       <div class="span6">
                           <div class="control-group">
                               <label class="control-label"> PhoneNo：</label>
                               <div class="controls">
                                   <input type="text" name="phoneNumber" value="${orderInfo.phoneNumber }" readonly="readonly">
                               </div>
                           </div>
                       </div>
                       <div class="span6">
                           <div class="control-group">
                               <label class="control-label"> ASI：</label>
                               <div class="controls">
                                   <input type="text" name="asi" value="${orderInfo.asi }" readonly="readonly">
                               </div>
                           </div>
                       </div>
                       <div class="span6">
                           <div class="control-group">
                               <label class="control-label"> FAX：</label>
                               <div class="controls">
                                   <input type="text" name="fax" value="${orderInfo.fax }" readonly="readonly">
                               </div>
                           </div>
                       </div>
                       <div class="span6">
                           <div class="control-group">
                               <label class="control-label"> 订单时间：</label>
                               <div class="controls">
                                   <input type="text" name="createDate" value="${orderInfo.createDate }" readonly="readonly">
                               </div>
                           </div>
                       </div>
                       <div class="span6">
                           <div class="control-group">
                               <label class="control-label"> 备注信息：</label>
                               <div class="controls">
                                   <input type="text" name="remarks" value="${orderInfo.remarks }" readonly="readonly">
                               </div>
                           </div>
                       </div>
                    </div>
                </div>
            </div>                      
        </div>
    </div>
    
    
    <div class="row-fluid">
	    <div class="span12">
	        <div class="widget-box widtable-box">
	        	<div class="outer-box">
	          		<h5 class="settit" style="max-width: 100%; font-size:13px;">订单产品列表</h5>
	            	<table width="100%" class="setttable" style="max-width: 100%;">
	                <tr>
	                  <th scope="col">产品名称</th>
	                  <th scope="col">图片</th>
	                  <th scope="col">数量</th>
	                  <th scope="col">价格</th>
	                  <th scope="col">备注</th>
	                  <th scope="col">下单时间</th>
	                </tr>
	                <c:forEach items="${orderInfo.orderProduct }" var="prod">
	                <tr>
	                  <td>${prod.product.productName }</td>
	                  <td id="${prod.product.id}" class="pictures">
                        <ul>
                         	<c:forEach items="${prod.product.pictures }" var="pic">
                         	   <c:if test="${pic.productId eq prod.product.id && pic.imageType == 1}">
                         	  	  <li><img data-original="<%=path%>/upload/${pic.imageUrl}" onclick="showPic('${prod.product.id}')" src="<%=path%>/upload/${pic.imageUrl}"/></li>
                         	   </c:if>
                         	</c:forEach>
					 	</ul>
					  </td>
	                  <td>${prod.quantity }</td>
	                  <td>${prod.targetPrice }</td>
	                  <td>${prod.message }</td>
	                  <td>${prod.deliveryTime }</td>
	                </tr>
	                </c:forEach>
	              </table>
	            </div>
	        </div>
	    </div>
	</div>
</div>
</div>
<script src="<%=path%>/js/view/js/viewer.min.js"></script>
<script>
	function showPic(id){
		var viewer = new Viewer(document.getElementById(id), {
			url: 'data-original'
		});
	}
</script>
</body>
</html>