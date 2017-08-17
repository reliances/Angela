var unicorn_data_config = {
  "sidebar": {
    "menus": [ 
      { "title": "首页", "icon": "home", "link": "../index/index.html"},
      { "title": "云主机管理", "icon": "th-list", "link": "#","submenus": [
        { "title": "入门向导", "link": "../instance/example_page1.html" },
				{ "title": "平台概况汇总", "link": "../instance/instanceSurvey.html" },
				{ "title": "虚拟主机", "link": "../instance/instanceKvm_1.html" },
				{ "title": "镜像模板", "link": "../image/image_list.html" },
				{ "title": "虚拟网络", "link": "../network/network_list.html" },
				{ "title": "安全策略", "link": "../network/securityGroups_list.html" },
				{ "title": "数据卷盘", "link": "../blockstorage/volume_list.html" },
				{ "title": "实例类型", "link": "../instance/example_page6.html" },
				/*{ "title": "实例同步本地库", "link": "../instance/InstanceSynchronization.html" },
				{ "title": "数据盘同步本地库", "link": "../instance/dataDiskSynchronous.html" },*/
				{ "title": "访问证书", "link": "../instance/example_page7.html" },
			]},
			{ "title": "负载均衡管理", "icon": "file", "link": "#", "submenus": [
				{ "title": "入门向导", "link": "../lbaas/ibaas_guide.html" },
				{ "title": "负载均衡器", "link": "../lbaas/loadbalancers.html" },
				{ "title": "健康监控", "link": "../lbaas/healthMonitor_list.html" },
				{ "title": "资源池", "link": "../lbaas/pool_list.html" },
			] },
			{ "title": "中间件管理", "icon": "wrench", "link": "#", "submenus": [
				{ "title": "数据库(mysql)", "link": "../middleware/mysql_list_1.html" },
				{ "title": "应用服务器(tomcat)", "link": "../middleware/tomcat_list_1.html" }
			] },
			{ "title": "弹性伸缩管理", "icon": "chevron-right", "link": "#", "submenus": [
				{ "title": "入门向导", "link": "../elasticexpansion/elastic_guide.html" },
				{ "title": "弹性伸缩", "link": "../elasticexpansion/elasticexpansion.html" },
				{ "title": "报警策略(Alarm)", "link": "../elasticexpansion/alarmstrategy.html" }
			] },
			{ "title": "云流程支撑", "icon": "barcode", "link": "#", "submenus": [
				{ "title": "实例模板", "link": "../cloudflow/instanceTemplate_list.html" },
				{ "title": "创建实例审批", "link": "../cloudflow/approveInstance_create.html" },
				{ "title": "查看申请进度", "link": "../cloudflow/approveInstance_view.html" },
			] },
			{ "title": "云服务编排", "icon": "picture", "link": "#", "submenus": [
				{ "title": "服务编排设计", "link": "../cloudservice/taskStack_list.html" },
				{ "title": "服务编排进度", "link": "../cloudservice/taskStackRunRecord_list_1.html" },
			] },
			{ "title": "云运维支撑", "icon": "retweet", "link": "#", "submenus": [
			    { "title": "主机概况报表", "link": "../cloudoperation/surveyreport.html" },
					{ "title": "运维注册管理", "link": "../cloudoperation/agentInstances_list.html" },
					{ "title": "操作日志管理", "link": "../cloudoperation/logInfo_list.html" },
					{ "title": "配置变更管理(全局)", "link": "../cloudoperation/config_list.html" },
					{ "title": "健康巡检项目", "link": "../cloudoperation/healthInspectionItem_list_1.html" },
					{ "title": "健康巡检", "link": "../cloudoperation/healthInspection_list.html" }
			] },
			{ "title": "云业务支撑", "icon": "tasks", "link": "#", "submenus": [
					{ "title": "计量计费及服务目录", "link": "../cloudbusiness/ceilometer_list.html" }
			] },
			{ "title": "云报表管理", "icon": "sheet", "link": "#", "submenus": [
					{ "title": "By主机", "link": "../cloudsheet/byhost.html" },
					{ "title": "By主机1", "link": "../cloudsheet/byhost2.html" },
					{ "title": "By主机2", "link": "../cloudsheet/byhost3.html" },
					{ "title": "By租户", "link": "../cloudsheet/bytenant.html" },
					{ "title": "By租户1", "link": "../cloudsheet/bytenant2.html" },
					{ "title": "By租户2", "link": "../cloudsheet/bytenant3.html" },
					{ "title": "By网络", "link": "../cloudsheet/bynetwork.html" },
					{ "title": "By网络1", "link": "../cloudsheet/bynetwork2.html" },
					{ "title": "By网络2", "link": "../cloudsheet/bynetwork3.html" },
					{ "title": "By虚拟机时间", "link": "../cloudsheet/bytime.html" }
			] },
			{ "title": "系统管理", "icon": "globe", "link": "#", "submenus": [
				{ "title": "租户管理", "link": "../identity/tenant_list.html" },
				{ "title": "用户管理", "link": "../identity/user_list.html" },
				{ "title": "字典管理", "link": "../identity/dictionary_list.html" },
			] }
    ]
  },
  "pages": {
     "home": { "link": "../index/index.html" }
  }
};

var unicorn_data_render = function(cfg) {

  _.templateSettings.interpolate = /{{([\s\S]+?)}}/g;
  
  var renders = {}
  renders['submenus'] = function(cfg) {
    var lis = '';
    var tpl = _.template('<li><a href="{{link}}">{{title}}</a></li>');
    _.each(cfg, function(v) {
       lis += tpl(v);
    });
    return lis ? '<ul>' + lis + '</ul>' : '';
  }
  renders['menus'] = function(cfg) {
    var subs = '';
    var tpl = _.template('<li class="{{clazzs}} {{status}}"><a href="{{link}}"><i class="icon icon-{{icon}}"></i><span>{{title}}</span>{{label}}</a>{{subs}}</li>');
	var lbl = '<span class="label"></span>';
    _.each(cfg, function(v) {
       v['subs'] = v['submenus'] ? renders['submenus']( v['submenus'] ) : '';
	   v['label'] = v['subs'] ? lbl : '';
       v['clazzs'] = v['submenus'] ? 'submenu' : '';
       subs += tpl(v);
    });
    return subs ? '<ul>' + subs + '</ul>' : '';
  }
  renders['sidebar'] = function(cfg) {
    var seg = '';
    var v = cfg;
    var subs = v['menus'] ? renders['menus']( v['menus'] ) : '';
    var tpl = _.template('<a href="{{link}}" class="{{clazzs}}">{{title}}<i class="icon icon-{{icon}}"></i></a> {{subs}}');
    
    cfg['subs'] = subs;
    return tpl(cfg);
  }
  
  if(cfg && cfg.pages) {
    var find = null;
    var findv = null;
    _.each(cfg.pages, function(v, k) {
      if(v && v.status && v.status.match('active')) {
        find = k;
        findv = v.status;
      }
    });
    _.each(cfg.sidebar.menus, function(v) {
      if(find && v && v.icon == find) {
        v['status'] = findv;
      }
    });
  }	
      
  _.each(cfg, function(v, k) {
    if(renders[k]) {
      var html = renders[k](v);
      $('#' + k).html(html);
    }
  });
};

var unicorn_popwin_render = function(){
  //下一页
  var _popWin = $('.pop-window');
  $(".btn-next", _popWin).click(function(){
    $(this).parents(".aublock").css("display","none");
    $(this).parents(".aublock").next(".aublock").css("display","block");
  })	
  //上一页
  $(".btn-up", _popWin).click(function(){
    $(this).parents(".aublock").css("display","none");
    $(this).parents(".aublock").prev(".aublock").css("display","block");
  })	
  //弹层内部选项卡
  $(".ptab li", _popWin).each( function(i){ 
    $(this).click(function(){
      $(this).addClass("active").siblings("li").removeClass("active");
      $(".p-content").eq(i).addClass("tabnow").siblings(".p-content").removeClass("tabnow");
    });
  });
  //page页面选项卡
  $(".tabselect a").each( function(i){ 
    $(this).click(function(){
      $(this).addClass("active").siblings("a").removeClass("active");
      $(this).parents(".tabselect").siblings(".p-content").eq(i).addClass("tabnow").siblings(".p-content").removeClass("tabnow");
    });
  });
  //镜像点击
  $(".mirror li a,.mirrormin li a,.mirrorBig li a", _popWin).click(function(){
    $(this).addClass("mirrorSayOn").parent("li").siblings().find("a").removeClass("mirrorSayOn"); 
  })
  //选择网络
  $(".mirrorImg li a,.mirrorAuto li a", _popWin).click(function(){
    $(this).toggleClass("mirrorSayOn");
  })
  //查看更多
  $(".moreIcon,.moreShow").click(function(){
    $(this).parent().siblings(".more-content").slideToggle(500);
  })
}

var page_data = null;


//根据下拉框选中的值,切换div 
//1.在jsp页面需要定义一个select id, 然后select 调用onchange事件 onchange=changeDivShow('selectId')
//2.在div的class中添加 "sel_selectvalue" selectvalue 是选中的select下拉的值
//样例 安全组详情页面 添加安全组规则(securityGroups_detail.html)
var changeDivShow = function(selectId){
	var selval = $('#'+selectId).val();//已经选中的select option value
	$("#"+selectId+" option").each(function(){ //遍历全部option
		var value = $(this).val(); //获取option的内容
		if(selval == value){ 
			$('.sel_'+value).show();
		}else{
			$('.sel_'+value).hide();
		}
	});
  }
	

/*圆形百分比*/
	function loadImg (){
	$(".cirprogress").each(function() {
		var i = 0;
		var object =$(this) ;
		var data=object.attr("title");
		//alert(data);
		setInterval(function(){
			i++
			if(i>data){
				i=data
			}
			var imgLeft = -(i*44+(i*10))+'px'
			object.css("background-position",imgLeft+'\t'+'0px');
			object.html( i+'%');
		},50)
		
	});
}
loadImg();
/*云主机管理*/
$(".instanadm").click(function(){
  $(".instanbox").slideToggle();
});

//选择不同的复选按钮显示不同的内容			 	 
		function sortCheckbox(val){
				$(".sortCheck").toggle();
		}
		
		function ignoreCheckbox(){
				$(".ignoreGroup").toggle();
		}
//租户配额使用情况的显示与隐藏
$(function(){
		$(".tenanttit").click(function(){
			$(this).find(".icon-chevron-down").toggleClass("icon-chevron-right");
			$(this).siblings(".tenantcon").toggle();
		});
})
