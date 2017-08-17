<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<style>
	#sidebar{z-index:0;}
	.modal-backdrop{z-index:0 !important;}
</style>
</head>
<body>
   <ul class="nav btn-group">
        <li class="btn btn-help">
        	<a title="" href="#"><i class="icon icon-user"></i> 
        		<span class="text">用户名：<b style="color: orange;">${user.userName }</b></span>
        	</a>
        </li>
        <li class="btn btn-help"><a title="" data-toggle="modal" href="#myPassword"><i class="icon icon-lock"></i> <span class="text">修改密码</span></a></li>
        <li class="btn btn-help"><a title="" href="../user/logout"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
        <!-- <li class="btn btn-help"><a title="" href="../login/getLoginInfo"><i class="icon icon-share-alt"></i> <span class="text">openstackLogin</span></a></li> -->
    </ul>
    
        <!--修改密码start-->
		<div id="myPassword" class="modal modal-mid hide">
	    <div class="modal-header">
	        <button data-dismiss="modal" class="close" type="button">×</button>
	        <h3><i></i>修改密码</h3>
	    </div>
	    <form class="form-horizontal" method="post" action="changePassword" id="submits">
	        <div class="modal-body">
	            <div class="widget-box">
	                <div class="poplump">
	                    
	                    <div class="control-group">
	                        <label class="control-label control-slabel">旧密码:</label>
	                        <div class="controls control-s">
	                            <input type="password" class="js-old-password" onkeyup="change('js-old-password')" name="oldPassword" placeholder="可输入中文,字母,数字,'-','_','.'，请输入一个长度最多是 25 的字符串"/>
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        <label class="control-label control-slabel">新密码:</label>
	                        <div class="controls control-s">
	                            <input type="password" class="js-new-password" onkeyup="change('js-new-password')" name="newPassword" placeholder="可输入中文,字母,数字,'-','_','.'，请输入一个长度最多是 25 的字符串"/>
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        <label class="control-label control-slabel">确认密码:</label>
	                        <div class="controls control-s">
	                            <input type="password" class="js-confirm-password" onkeyup="change('js-confirm-password')" name="nameShort" placeholder="可输入中文,字母,数字,'-','_','.'，请输入一个长度最多是 25 的字符串"/>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="modal-footer">
	            <input type="button" value="确定" class="btn btn-primary" onclick="changePassword();"/>
	            <a data-dismiss="modal" class="btn" onclick="cancelTop();">取消</a>
	        </div>
	    </form>
	</div>

<!--修改密码 end-->
<script>
var cancelTop = function() {
	$(".js-old-password").val("");
	$(".js-new-password").val("");
	$(".js-confirm-password").val("");
}
var change = function(state) {
	var object = $("." + state);
	var password = object.val().trim();
	var regex = /^[\u4E00-\u9FA5 A-Z a-z 0-9 _ - .]{0,25}$/;
	object.closest("div").parent("div").find("label").css("color", "black");
	object.closest("div").find("span").remove();
	object.css("border-color", "#ccc");
	if (password.length === 0) {
		object.css("border-color", "#b94a48");
		object.closest("div").parent("div").find("label").css("color", "#b94a48");
		object.closest("div").append("<span for='applyTitle' class='help-inline' style='color:#b94a48'>必选字段</span>");
		object.focus();
		return false;
	} else if (password.length > 25) {
		object.css("border-color", "#b94a48");
		object.closest("div").parent("div").find("label").css("color", "#b94a48");
		object.closest("div").append("<span for='applyTitle' class='help-inline' style='color:#b94a48'>请输入一个长度最多是 25 的字符串</span>");
		object.focus();
		return false;
	}
	else if(!regex.test(password)) {
		object.css("border-color", "#b94a48");
		object.closest("div").parent("div").find("label").css("color", "#b94a48");
		object.closest("div").append("<span for='applyTitle' class='help-inline' style='color:#b94a48'>可输入中文,字母,数字,'-','_','.'</span>");
		object.focus();
		return false;
	} else {
		object.closest("div").parent("div").find("label").css("color", "#468847");
		object.closest("div").find("span").remove();
		object.css("border-color", "#468847");
		return true;
	}
}


var changePassword = function() {
	var oldPassword = $(".js-old-password").val();
	var newPassword = $(".js-new-password").val();
	var confirmPassword = $(".js-confirm-password").val();
	if (oldPassword === newPassword && oldPassword != null && oldPassword != '') {
		alert("新旧密码不能相同");
		return false;
	} else if (newPassword != confirmPassword) {
		alert("两次输入密码不相同");
		return false;
	} else if (change('js-old-password') && change('js-new-password') && change('js-confirm-password')) {
		$.ajaxSetup({
	        async: false
	    });
		$.post("changePassword",{
	            oldPassword: oldPassword,
	            newPassword: newPassword,
	            nocache: new Date().getTime()
	      	},function(data){
	            if(data.message === "1") {
	            	alert("修改密码成功");
	            	window.location.href="logout";
	            } else {
	            	alert("修改密码失败");
	            	$(".close").click();
	            }
	            $(".js-old-password").val("");
	            $(".js-new-password").val("");
	            $(".js-confirm-password").val("");
	     });
	}
}


var valiall = function() {
	$('form.form1').off('change', 'input.text-ajax');
	$('form.form1').on('change', 'input.text-ajax', vali_ajaxchange);
}


var ajaxchange_ok = function(f) {
	// .....
}

var ajaxchange_no = function(f) {
	// .....
}

var vali_ajaxchange = function(e) {
	var $input = e.target;
	// value ... 
	
    if(ok) {
    	ajaxchange_ok($input);
    	if(e) {
            e.stopPropagation(); 
    	}
    }
}

var changeNo = function(id, url) {
	var object = $("." + id);
	var regex = /^[\u4E00-\u9FA5 A-Z a-z 0-9 _ - .]{0,25}$/;
	var st = true;
	var value = object.val().trim();
	object.closest("div").parent("div").removeClass("success");
	object.closest("div").find("span").remove();
	
	if (value.length === 0) {
		object.closest("div").parent("div").removeClass("success").addClass("error");
		object.closest("div").append("<span for='applyTitle' class='help-inline' style='color:#b94a48'>必选字段</span>");
		object.focus();
		return false;
	} else if (value.length > 25) {
		object.closest("div").parent("div").removeClass("success").addClass("error");
		object.closest("div").append("<span for='applyTitle' class='help-inline' style='color:#b94a48'>请输入一个长度最多是 25 的字符串</span>");
		object.focus();
		return false;
	}
	else if(!regex.test(value)) {
		object.closest("div").parent("div").removeClass("success").addClass("error");
		object.closest("div").append("<span for='applyTitle' class='help-inline' style='color:#b94a48'>可输入中文,字母,数字,'-','_','.'</span>");
		object.focus();
		return false;
	} else {
		$.ajaxSetup({
	        async: true
	    });
		$.get(url,{id: object.val(),nocache: new Date().getTime()},
            function(data){
                 if(data.message === '1') {
             		object.closest('div').append("<span class='help-inline' style='display: inline-block; color:#b94a48'>已存在</span>");
             		object.closest("div").parent("div").removeClass("success").addClass("error");
             		object.focus();
             		st = false;
             		//e.stopPropagation();
             		return false;
                 } else {
             		object.closest("div").find("span").remove();
             		object.closest("div").parent("div").removeClass("error").addClass("success");
                 	st = true;
                 	return false;
                 }
            }
         );
	}
	
	return st;
}
</script>
</body>
</html>
