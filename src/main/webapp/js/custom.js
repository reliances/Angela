// JavaScript Document
$(function(){
//tabnav选项卡
  $(".tabnav li").each( function(i){ 
    $(this).click(function(){
      $(this).addClass("active").siblings("li").removeClass("active");
      $(".tabcontent").eq(i).addClass("tabnow").siblings(".tabcontent").removeClass("tabnow");
    });
  });		 
//D地图视图的导航hover效果
	$(".mapnav > a").click(function(){
		$(this).parent(".mapnav").find(".subnav").slideToggle();
		$(this).parent(".mapnav").siblings().find(".subnav").hide();
	});
	//treenav主菜单效果	
	$(".treemenu > span").click(function(){
			if($(this).find("i").hasClass("changeicon")){
					$(this).siblings(".treecolumn").hide().height(0);
				}
			else{
				$(this).siblings(".treecolumn").show().height( $(window).height()-(50 - 10) - treeNavSum*30 -50 - $('.hismeasures').height());
				}
			$(this).find("i").toggleClass("changeicon");
			$(this).parents(".treemenu").siblings(".treemenu").find("i").removeClass();
			$(this).parents(".treemenu").siblings(".treemenu").find(".treecolumn").hide().height(0);
		});
		//窗口计算	
		$('.treeindex').height( $(window).height() - (50 - 10) - 30 - $('.hismeasures').height());
		$('.treeinav').height( $(window).height() - (50 - 10) - 30 - $('.hismeasures').height() );
		var treeNavSum = $(".treenav").find(".treemenu").size();
		$('.hosttree').height( $(window).height() - 140 );
		$('.treenav').height( $(window).height() - (50 - 10) - 90 );
		$(".currcolumn").show().height( $(window).height()-(50 - 10) - treeNavSum*30 -50 - $('.hismeasures').height() );
		$(".currcolumn").siblings("span").find("i").addClass("changeicon")
		$(window).resize(function(){
			$('.treenav').height( $(window).height() - (50 - 10) - 30 );
			$('.treeindex').height( $(window).height() - (50 - 10) - 30 - $('.hismeasures').height());
			$('.treeinav').height( $(window).height() - (50 - 10) - 30 - $('.hismeasures').height() );
			$('.hosttree').height( $(window).height() - 140 );
			$(".treecolumn").each(function(){
				if($(this).css('display')=="block")
				{$(this).height( $(window).height()-(50 - 10) - treeNavSum*30 -50 - $('.hismeasures').height() );}
				})		
		});	 
	//交行数据指标菜单
	$(".treeimenu > span").live("click",function(){
			if($(this).find("i").hasClass("changeicon")){
					$(this).siblings(".treeicolumn").hide();
				}
			else{
				$(this).siblings(".treeicolumn").show();
				}			
			$(this).find("i").toggleClass("changeicon");
			$(this).parents(".treeimenu").siblings(".treeimenu").find("i").removeClass();
			$(this).parents(".treeimenu").siblings(".treeimenu").find(".treeicolumn").hide();
		});
		$(".curricolumn").siblings("span").find("i").addClass("changeicon");
//点击左侧显示不同菜单
		$("#applybtn").click(function(){
			$("#treeinav").toggle();
			$("#treeindex").hide();
			$(this).addClass("btn_sel");
			$(this).siblings("a").removeClass("btn_sel");
			$(this).find("i").toggleClass("foldicon");
      $(this).siblings("a").find("i").removeClass("foldicon");
			if($("#treeinav").is(":hidden") && $("#treeindex").is(":hidden")){
				$(".main").css("marginLeft",0);
				}
			else{$(".main").css("marginLeft",220);}
		});
		$("#icobtn").click(function(){
			$("#treeindex").toggle();
			$("#treeinav").hide();
			$(this).addClass("btn_sel");
			$(this).siblings("a").removeClass("btn_sel");
			$(this).find("i").toggleClass("foldicon");
			$(this).siblings("a").find("i").removeClass("foldicon");
			if($("#treeinav").is(":hidden") && $("#treeindex").is(":hidden")){
				$(".main").css("marginLeft",0);
				}
			else{
				$(".main").css("marginLeft",220);
			}
		});
	//table的隔行换色				 
	/*$(".table tr").mouseover(function(){    
       //如果鼠标移到class为stripe的表格的tr上时，执行函数    
      $(this).addClass("over");}).mouseout(function(){    
       //给这行添加class值为over，并且当鼠标一出该行时执行函数    
      $(this).removeClass("over");}) //移除该行的class    
  $(".table tr:even").addClass("selcolor"); */   
    //给class为stripe的表格的偶数行添加class值为alt 				 
  //顶部点击展示效果			 
	$(".viewbox > li > a").live("click",function(){
　　　$(this).toggleClass("currentview");
			$(this).siblings(".viewcolumn").toggle();
　　});
		
		
		
	//上一页和下一页		 
	 //下一页
  $(".btn-next").click(function(){
    $(this).parents(".columnblock").css("display","none");
    $(this).parents(".columnblock").next(".columnblock").css("display","block");
  })	
  //上一页
  $(".btn-up").click(function(){
    $(this).parents(".columnblock").css("display","none");
    $(this).parents(".columnblock").prev(".columnblock").css("display","block");
  })	
	//tale行文字滚动
	var scrtime;
	$(".scrollBox").hover(function(){
		clearInterval(scrtime);
	
	},function(){
	
	scrtime = setInterval(function(){
		var $ulTable = $(".scrollBox tbody");
		var liTrHeight = $ulTable.find("tr:last").height();
		$ulTable.animate({marginTop : liTrHeight + 35 + "px"},2000,function(){
		$ulTable.find("tr:last").prependTo($ulTable)
		$ulTable.find("tr:first").hide();
		$ulTable.css({marginTop:35 + "px"});
		$ulTable.find("tr:first").fadeIn(2000);
		});
	},2000);
	
	}).trigger("mouseleave");
	
	//点击显示展开类效果			 
		$(".classify").click(function(){
			$(this).siblings(".sorts").slideToggle();
		});					 
	//右侧菜单的显示隐藏
	$(".unfold").click(function(){
			if($(this).attr('show') == 1){
			   $(this).removeAttr('show');
				 $(this).parents(".span12").siblings(".span4").show();
				 $(this).parents(".span12").removeClass("span12").addClass("span8");
			   $(this).css({"right":"-5px","background-image": "url(../../img/icons/common/icon_zr.png)"});
			}else{
				$(this).attr('show', 1);
				$(this).parents(".span8").removeClass("span8").addClass("span12");
				$(this).parents(".span12").siblings(".span4").hide();
				$(this).css({"right":"-5px","background-image": "url(../../img/icons/common/icon_zl.png)"});
			}
		}); 
					 
		//首页应用页面切换
		$(".switchsppl").click(function(){
			if($(this).attr('show') == 1){
			   $(this).removeAttr('show');
				 $(this).parents(".span4").show();
				 $(this).parents(".span4").siblings(".span4").hide();
			}else{
				$(this).attr('show', 1);
				$(this).parents(".span4").hide();
				$(this).parents(".span4").siblings(".span4").show();
			}
		}); 			 
		//自动化部署对应内容切换			 
		$(".viewlist li").each( function(i){ 
			$(this).click(function(){
				$(this).addClass("addselect").siblings("li").removeClass("addselect");
				$(".overall").eq(i).addClass("overNow").siblings(".overall").removeClass("overNow");
			});
	  });	
		//交行大数据的主机选择选卡			 
		function tabs(tabTit,on,tabCon){
			/*$(tabCon).each(function(){
				$(this).children().eq(0).show();
				});*/
				/*$(tabTit).each(function(){
				$(this).children().eq(0).addClass(on).append("<i class=\"caret\"></i>");
				});*/
			 $(tabTit).children().click(function(){
					$(this).addClass(on).siblings().removeClass(on);
					$(this).addClass(on).append("<i class=\"caret\"></i>");
					$(this).siblings().find("i").remove();
					 var index = $(tabTit).children().index(this);
					 $(tabCon).children().eq(index).show().siblings().hide();
				});
			 }
			tabs(".cm_valueList","active",".cm_values");
			//点击当前栏目 其它栏目隐藏
			$(".cm_valueList li").live("click",function(){
					$(this).parent(".cm_valueList").siblings(".cm_values").show();																				
					if($(this).parents(".c_column").siblings(".c_column").find("a").hasClass("f-check")){
						$(this).parents(".c_column").siblings(".c_column").find(".cm_values").show();
						}
					else{
						$(this).parents(".c_column").siblings(".c_column").find(".cm_values").hide();
						$(this).parents(".c_column").siblings(".c_column").find(".active").removeClass();
						}
			});
			
			
			$(".sm_valueList li").each( function(i){ 
				$(this).click(function(){
					$(this).addClass("active").siblings("li").removeClass("active");
					$(this).append("<i class=\"caret\"></i>");
					$(this).siblings().find("i").remove();
					$(this).parent().parent(".cm_value").find(".sm_content").eq(i).addClass("sm_show").siblings().removeClass("sm_show");
				});
			});	
			
			$(".sm_vlist li").each( function(i){ 
				$(this).click(function(){
					$(this).addClass("active").siblings("li").removeClass("active");
					$(this).append("<i class=\"caret\"></i>");
					$(this).siblings().find("i").remove();
					$(this).parent().parent(".cm_value").find(".sm_content").eq(i).addClass("sm_show").siblings().removeClass("sm_show");
				});
			});	
				
			//交行大数据更多列显示
			$(".cm_ext .more").click(function(){
				$(this).parent(".cm_ext").siblings(".cm_value").find(".hidecon").toggle();
			});
			//交换文字效果
	 	 $(".cm_ext .more").toggle(function(){
				$(this).attr("id","widthmin");
				$(this).html("收起");
			},function(){
				$(this).attr("id","widthmax");
				$(this).html("更多");
			});
			//交行大数据多选显示效果
			$(".multiple").live("click",function(){
				$(this).parent(".cm_ext").siblings(".cm_value").find(".cm_valueshow li a").addClass("f-check");
				$(this).hide();
				$(this).siblings(".more").hide();
				$(this).parent(".cm_ext").siblings(".cm_value").find(".cm_btns").show();
				$(this).parent(".cm_ext").siblings(".cm_value").find(".btn-info").hide();
			});
			//交行大数据点击选择不同效果
			$(".cm_valueshow li").live("click",function(){
				$(this).find(".f-check").toggleClass("f-checked");
				$(this).parents(".cm_values").siblings(".cm_btns").find(".btn-info").show();
			});
			//所有主机选择
			$(".sm_valueshow li").live("click",function(){
				$(this).find(".f-check").toggleClass("f-checked");
			});
			//单指标多主机事件的选定
			$(".sm_valueshow li label").live("click",function(){
				var smIndex = $(this).parents(".sm_content").index();
				var smTitVal = $(this).parents(".sm_content").siblings(".sm_vlist").find("li").eq(smIndex - 1).text();
				var smValList = $(this).parents(".sm_content").siblings(".sm_valueList").find("li").eq(smIndex - 1).text();
				$(".cmkeyval").html(smTitVal + $(this).text().substr(2));
				$(".cmkeyval").html(smValList + $(this).text());
				$(this).parents(".cm_value").hide();
				//alert(smTitVal);
			});
			$(".cmkeyval").live("click",function(){
				$(this).siblings(".cm_value").show();
			});
			//顶部的显示隐藏
			$(".arrowup").live("click",function(){
				$(this).toggleClass("arrowdown");
				$(this).parents(".incontent").find(".hismeasures").toggle();
				$(".hisempty").toggle();
			});
			//左侧菜单显示隐藏
			$(".arrowl").live("click",function(){
				$(this).toggleClass("arrowr");
				//$(this).parents(".main").siblings(".menubox").toggle();
				//$(this).parents(".main").toggleClass("mainleft0");
				$(this).parents(".main").siblings(".menubox").toggle();
				if($("#menubox").is(":hidden")){
					$(".main").css("marginLeft",0);
					}
				else{
					$(".main").css("marginLeft",220);
				}
				$(".foldmenu").toggle();
			});
			
			
			
			
			$(".delete").live("click",function(){
				$(this).parent(".cm_btns").siblings(".cm_values").find(".cm_valueshow li a").removeClass("f-check");
				$(this).parent(".cm_btns").siblings(".cm_values").find(".cm_valueshow li a").removeClass("f-checked");
				$(this).parents(".cm_value").siblings(".cm_ext").show();
				$(this).parents(".cm_value").siblings(".cm_ext").find(".more").show();
				$(this).parents(".cm_value").siblings(".cm_ext").find(".multiple").show();
				$(this).parents(".cm_btns").hide();
			});
			//大数据的显示隐藏效果
			$(".conceal").click(function(){
				$(this).parents(".c_title").siblings(".c_column").hide();
			});
			$(".spread").click(function(){
				$(this).parents(".c_title").siblings(".c_column").show();
			});
			//大数据的显示隐藏的自动效果功能
			$(".automatic").live("click",function(){
					$(".chooser").hover(function(){ 
					$(this).find(".c_column").show(); 
					}, 
					function(){ 
					$(this).find(".c_column").hide();
					}) 
				
			});
			
			//滚动的表格	
			$(".btable_div").scroll( function () {
			var left = $(this).scrollLeft();
			//alert(left);
			$(".htable_div").scrollLeft(left);
			});			
		//追加资源组RG
		$("#Additional").click(function(){
				$("#resourceGroup").show();
			});
 
		//页面header加载
		$("#header").load("header.html");	
	
	
	
	
	
	
	
	})
//关于IP地址js		 
		function mask(obj){
    obj.value=obj.value.replace(/[^\d]/g,'')
    if(obj.value.length>=3){
        if(parseInt(obj.value)>=256 || parseInt(obj.value)<=0){
            alert(parseInt(obj.value)+"IP地址错误！")
            obj.value=""
            obj.focus()
            return false;
        }else{
            obj.blur();
            nextip=parseInt(obj.id.substr(2,1))+1;
            var _iptNext = document.getElementById('ip'+nextip);
            if(_iptNext){
                _iptNext.select();
            }
        }
    }
    getIP(obj.id.substr(0,2));
		}
		var filter = function(obj){
				clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))
		}
		var paste = function(strId){
				var data = clipboardData.getData('text');
				var _arr = [];
				_arr = data.split('.');
				for(var i=0;i<4;i++){
						document.getElementById(strId+i).value = _arr[i];
				}
				document.getElementById(strId+'3').focus();
		};
		var getIP = function(strId){
				var str = '';
				var v   = '';
				for(var i=0;i<4;i++){
						v = document.getElementById(strId+i).value;
						if(v == ''){
								return;
						} else {
								str += (i<3) ? v + '.': v ;
						}
				}
				document.getElementById('ip').value = str;		 
		};








