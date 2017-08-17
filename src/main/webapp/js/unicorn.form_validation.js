/**
 * Unicorn Admin Template
 * Diablo9983 -> diablo9983@gmail.com
**/
$(document).ready(function(){
	
	// $('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	// $('select').select2();
  
    var validatePage = function( ) {
        var seq = 0;
    
        $('body form').each(function() {
            var form = $(this);
            var opts = { "rules": null, "errorClass": "help-inline", "errorElement": "span"};

            opts["highlight"] = function(element, errorClass, validClass) {
            	$(element).parents('.control-group').removeClass('success');
                $(element).parents('.control-group').addClass('error');
            };
            opts["unhighlight"] = function(element, errorClass, validClass) {
                $(element).parents('.control-group').removeClass('error');
                $(element).parents('.control-group').addClass('success');
            };

            var textVali = function() {
                var clazzs  = $(this).attr('class') ? $(this).attr('class').split(/\s+/) : [];
                var matches = null;
                var maxlength = null;
                var range = false;
                // input name, rule name, required?, min, max
                // e.g. matches = [ 'names', 'inputName2', 'r', '', '' ]
                for(var i = 0; i < clazzs.length; i++) {
                    if(clazzs[i].match(/^v-/)) {
                        matches = clazzs[i].split(/-/);
                        //console.log(matches);
                        // matches[0] = 'vf-' + ((new Date().getTime()) + seq);
                        matches[0] = $(this).attr('name');
                        if(matches && matches[0]) {
                            // $(this).addClass(matches[0]);
                            var rule = {};
                            if(matches[1]) {
                                if(matches[1].match('range')) {
                                    if(matches[2] == 'r') {
                                        rule['required'] = true;
                                    }
                                    if(matches[3] && matches[4]) {
                                        rule[matches[1]] = [parseInt(matches[3]),parseInt(matches[4])];
                                        range = true
                                    }
                                } else if(matches[1].match('equalTo')) {
                                    if(matches[2] == 'r') {
                                        rule['required'] = true;
                                    }
                                    if(matches[3]) {
                                        rule[matches[1]] = matches[3];
                                    }
                                } else {
                                    rule[matches[1]] = true;
                                    if(matches[2] == 'r') {
                                        rule['required'] = true;
                                    }
                                    if(matches[3]) {
                                        rule['minlength'] = parseInt(matches[3]);
                                    }
                                    if(matches[4]) {
                                        rule['maxlength'] = parseInt(matches[4]);
                                        maxlength = parseInt(matches[4]);
                                    }
                                }
                            }
                            opts.rules = opts.rules || {};
                            opts.rules[matches[0]] = rule;
                            if($.validator.messages && $.validator.messages[matches[1]]) {
                                if(maxlength) {
                                    $(this).attr('placeholder', $.validator.messages[matches[1]]+"，"+$.validator.messages.maxlength(maxlength));
                                } else if(range) {
                                    $(this).attr('placeholder', $.validator.messages.range([parseInt(matches[3]),parseInt(matches[4])]));
                                } else {
                                    $(this).attr('placeholder', $.validator.messages[matches[1]]);
                                }

                            }
                        }
                    }
                }
            };
      
            $("input[type='text'], input[type='password'], input[type='file'], textarea, select", $(form)).each(textVali);
      
            if(opts && opts.rules) {
                //console.log(opts);
                //console.log('#' + form.attr('id'));
                // $('#' + form.attr('id')).validate(opts);
                $(form).validate(opts);
            }
    
        });
    
    }
  
    validatePage();
/*	
	// Form Validation
    $("#basic_validate").validate({
		rules:{
			required:{
				required:true
			},
			email:{
				required:true,
				email: true
			},
			date:{
				required:true,
				date: true
			},
			url:{
				required:true,
				url: true
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	
	$("#number_validate").validate({
		rules:{
			min:{
				required: true,
				min:10
			},
			max:{
				required:true,
				max:24
			},
			number:{
				required:true,
				number:true
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	
	$("#password_validate").validate({
		rules:{
			pwd:{
				required: true,
				minlength:6,
				maxlength:20
			},
			pwd2:{
				required:true,
				minlength:6,
				maxlength:20,
				equalTo:"#pwd"
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});

    //创建实例
    $("#createInstance").validate({
        rules:{
            names:{
                required: true,
                insName: true,
                maxlength: 25
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //创建实例New
    $("#createInstanceNew").validate({
        rules:{
            names:{
                required: true,
                insName: true,
                maxlength: 25
            },
            describe:{
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //创建实例类型
    $("#createFlavor").validate({
        rules:{
            name:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            vcpus:{
                required: true,
                digits: true,
                maxlength: 4
            },
            ram:{
                required: true,
                digits: true,
                maxlength: 5
            },
            disk:{
                required: true,
                digits: true,
                maxlength: 4
            },
            ephemeral:{
                required: true,
                digits: true,
                maxlength: 4
            },
            swap:{
                required: true,
                digits: true,
                maxlength: 6
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //创建密钥对
    $("#createKeyPair").validate({
        rules:{
            keyName:{
                required: true,
                inputName: true,
                maxlength: 25
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //导入密钥对
    $("#importKeyPair").validate({
        rules:{
            keyName:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            publicKey:{
                required: true,
                desc: true,
                maxlength: 500
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //创建健康监控
    $("#createHealthMonitor").validate({
        rules:{
            delay:{
                required: true,
                digits: true,
                maxlength: 4
            },
            timeout:{
                required: true,
                digits: true,
                maxlength: 4
            },
            max_retries:{
                required: true,
                range: [1, 9]
            },
            url_path:{
                url: true,
                maxlength: 50
            },
            expected_codes:{
                range: [100, 999]
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //编辑健康监控
    $("#updateHealthMonitor").validate({
        rules: {
            delay: {
                required: true,
                digits: true,
                maxlength: 4
            },
            timeout: {
                required: true,
                digits: true,
                maxlength: 4
            },
            max_retries: {
                required: true,
                range: [1, 9]
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
	});

    //创建资源池
    $("#createPool").validate({
        rules:{
            name:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            description:{
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //编辑资源池
    $("#updatePool").validate({
        rules:{
            name:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            description:{
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //创建VIP
    $("#createVip").validate({
        rules:{
            vipName:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            vipDescription:{
                desc: true,
                maxlength: 100
            },
            vip_protocol_port:{
                required: true,
                range: [0, 65535]
            },
            vip_persistence_cookie_name:{
                required: true,
                inputName: true,
                maxlength: 10
            },
            vip_connection_limit:{
                digits: true,
                maxlength: 2
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //编辑VIP
    $("#updateVip").validate({
        rules:{
            vipName:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            vipDescription:{
                desc: true,
                maxlength: 100
            },
            vip_persistence_cookie_name:{
                required: true,
                inputName: true,
                maxlength: 10
            },
            vip_connection_limit:{
                digits: true,
                maxlength: 2
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //创建成员
    $("#createMember").validate({
        rules:{
            memberWeight:{
                range: [0, 256]
            },
            member_protocol_port:{
                required: true,
                range: [0, 65535]
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //编辑成员
    $("#updateMember").validate({
        rules:{
            memberWeight:{
                range: [0, 256]
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	
	  // 镜像管理 编辑镜像的input提示
    $("#image_Edit").validate({
        rules: {
            updateImgName: {
                required: true,
                inputName: true,
                maxlength: 25
            },
            updateImgOsName: {
                required: true,
                inputName: true,
                maxlength: 25
            },
            updateImgDescribe: {
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    // 网络及安全管理 虚拟安全组 创建安全组input提示
    $('#sgForm').validate({
        rules: {
            sgName: {
                required: true,
                inputName: true,
                maxlength: 25
            },
            sgDescription: {
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    $('#copy_Clear').validate({
        rules: {
            sgName: {
                required: true,
                inputName: true,
                maxlength: 25
            },
            sgDescription: {
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //创建安全组规则
    $("#createSgRule").validate({
        rules: {
            port: {
                required: true,
                digits: true,
                maxlength: 5
            },
            cird: {
                required: true,
                netSegment: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //复制安全组规则
    $('#copySgRule').validate({
        rules: {
            port: {
                required: true,
                digits: true,
                maxlength: 5
            },
            cird: {
                required: true,
                netSegment: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //中间件管理 新建关系型数据的input验证

    $('#create_sql').validate({
        rules: {
            mysql_instanceName: {
                required: true,
                insName: true,
                maxlength: 25
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
          $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    $('#create_sql2').validate({
        rules: {
            nodeNum: {
                required: true,
                digits: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    $('#create_sql3').validate({
        rules: {
            mysql_database: {
                required: true,
                inputName: true,
                maxlength: 25
            },
            mysql_port: {
                required: true,
                digits: true,
                maxlength: 4
            },
            mysql_username: {
                required: true,
                inputName: true,
                minlength: 4,
                maxlength: 20
            },
            mysql_password: {
                required: true,
                minlength: 6,
                maxlength: 20
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //修改数据库
    $('#repire_sql').validate({
        rules: {
            name: {
                required: true,
                inputName: true,
                maxlength: 25
            },
            description: {
                desc: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });


    //中间件管理 应用服务器的 input验证
    $('#create_appsql').validate({
        rules: {
            name: {
                required: true,
                insName: true,
                maxlength: 25
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    $('#create_appsql2').validate({
        rules: {
            node: {
                required: true,
                digits: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });


    $('#create_appsql3').validate({
        rules: {
            mysql_port: {
                required: true,
                digits: true,
                maxlength: 4
            },
            mysql_username: {
                required: true,
                inputName: true,
                minlength: 4,
                maxlength: 20
            },
            mysql_password: {
                required: true,
                minlength: 6,
                maxlength: 20
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //修改应用服务器
    $('#repire_appsql').validate({
        rules: {
            name: {
                required: true,
                inputName: true,
                maxlength: 25
            },
            description: {
                desc: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });



    //liwj
    //系统管理: 创建租户
    $("#createTenant").validate({
        rules:{
            project_name:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            project_description:{
                desc: true,
                maxlength: 100
            },
            metadata_items:{
                digits: true,
                maxlength: 9
            },
            instances:{
                digits: true,
                maxlength: 9
            },
            file_content_bytes:{
                digits: true,
                maxlength: 9
            },
            volume_snapshots:{
                digits: true,
                maxlength: 9
            },
            ram:{
                digits: true,
                maxlength: 9
            },
            security_group_rules:{
                digits: true,
                maxlength: 9
            },
            networks:{
                digits: true,
                maxlength: 9
            },
            routers:{
                digits: true,
                maxlength: 9
            },
            vcpus:{
                digits: true,
                maxlength: 9
            },
            injected_files:{
                digits: true,
                maxlength: 9
            },
            volumes:{
                digits: true,
                maxlength: 9
            },
            volume_snapshots_size:{
                digits: true,
                maxlength: 9
            },
            security_groups:{
                digits: true,
                maxlength: 9
            },
            floating_ips:{
                digits: true,
                maxlength: 9
            },
            ports:{
                digits: true,
                maxlength: 9
            },
            subnets:{
                digits: true,
                maxlength: 9
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	
    //系统管理: 编辑租户
    $("#updateTenant").validate({
        rules:{
            project_name:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            project_description:{
                desc: true,
                maxlength: 100
            },
            metadata_items:{
                digits: true,
                maxlength: 9
            },
            instances:{
                digits: true,
                maxlength: 9
            },
            file_content_bytes:{
                digits: true,
                maxlength: 9
            },
            volume_snapshots:{
                digits: true,
                maxlength: 9
            },
            ram:{
                digits: true,
                maxlength: 9
            },
            security_group_rules:{
                digits: true,
                maxlength: 9
            },
            networks:{
                digits: true,
                maxlength: 9
            },
            routers:{
                digits: true,
                maxlength: 9
            },
            vcpus:{
                digits: true,
                maxlength: 9
            },
            injected_files:{
                digits: true,
                maxlength: 9
            },
            volumes:{
                digits: true,
                maxlength: 9
            },
            volume_snapshots_size:{
                digits: true,
                maxlength: 9
            },
            security_groups:{
                digits: true,
                maxlength: 9
            },
            floating_ips:{
                digits: true,
                maxlength: 9
            },
            ports:{
                digits: true,
                maxlength: 9
            },
            subnets:{
                digits: true,
                maxlength: 9
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	
    //系统管理: 创建用户
    $("#createUser").validate({
        rules:{
            userName:{
                required: true,
                inputName: true,
                minlength: 4,
                maxlength: 20
            },
            email:{
                email: true
            },
            password:{
                required: true,
                minlength: 6,
                maxlength: 20
            },
            confirmPwd:{
                required:true,
                minlength: 6,
                maxlength: 20,
                equalTo:"#password"
            },
            tenantId:{
                required: true
            },
            roleId:{
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	
    //系统管理: 编辑用户
    $("#updateUser").validate({
        rules:{
            userName:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            email:{
                email: true
            },
            password:{
                minlength:6,
                maxlength:15
            },
            confirmPwd:{
                minlength:6,
                maxlength:15,
                equalTo:"#password"
            },
            tenantId:{
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //实例模板:部署/流程部署
    $("#createInstanceByTemple").validate({
        rules:{
            names:{
                required: true,
                insName: true,
                maxlength: 25
            },
            useDes:{
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
    //实例模板: 修改实例模板名称
    $("#updateInstanceTempleName").validate({
        rules:{
            tempName:{
                required: true,
                inputName: true,
                maxlength: 25
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });

    //服务编排设计: 创建服务编排TaskStack
    $("#createTaskStack").validate({
        rules:{
            name:{
                required: true,
                inputName: true,
                maxlength: 25
            },
            desc:{
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
    //服务编排设计: 创建实例添加创建实例TaskBox, 虚拟机部署
    $("#createInstanceTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            instanceName:{
                required: true,
                insName: true,
                maxlength: 25
            },
            useDes:{
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
    //服务编排设计: 修改实例添加创建实例TaskBox, 虚拟机部署
    $("#updateInstanceTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            instanceName:{
                required: true,
                insName: true,
                maxlength: 25
            },
            useDes:{
                desc: true,
                maxlength: 100
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
    //服务编排设计: 创建负载均衡TaskBox, 创建虚机成员
    $("#createLbaasTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            poolId:{
                required: true
            },
            healthMonitorPoolId:{
                required: true
            },
            memberWeight:{
                required: true,
                range: [0,256]
            },
            member_protocol_port:{
                required: true,
                range: [0,65535]
            },
            instanceId:{

            },
            memberAdderss:{
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
    //服务编排设计: 修改负载均衡TaskBox, 创建虚机成员
    $("#updateLbaasTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            poolId:{
                required: true
            },
            healthMonitorPoolId:{
                required: true
            },
            memberWeight:{
                required: true,
                range: [0,256]
            },
            member_protocol_port:{
                required: true,
                range: [0,65535]
            },
            instanceId:{
                inputName3:true
            },
            memberAdderss:{
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
    //服务编排设计: 创建数据盘TaskBox
    $("#createVolumeTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            volumeName:{
                required: true,
                insName: true,
                maxlength: 25
            },
            volumeSize:{
                required: true,
                number: true,
                maxlength: 4
            },
            volumeType:{
                required: true
            },
            volumeDes:{
                desc: true,
                maxlength: 100
            },
            volumeInstance:{
                required: true,
                inputName3: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
    //服务编排设计: 修改数据盘TaskBox
    $("#updateVolumeTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            volumeName:{
                required: true,
                insName: true,
                maxlength: 25
            },
            volumeSize:{
                required: true,
                number: true,
                maxlength: 4
            },
            volumeType:{
                required: true
            },
            volumeDes:{
                desc: true,
                maxlength: 100
            },
            volumeInstance:{
                required: true,
                inputName3: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	
    //服务编排设计:  创建挂载数据盘TaskBox,挂载卷部署
    $("#createVolumeMountTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            returnVolumeId:{
                required: true,
                inputName3: true
            },
            returnInstanceId:{
                required: true,
                inputName3: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	
    //服务编排设计: 修改挂载数据盘TaskBox,挂载卷部署
    $("#updateVolumeMountTaskBox").validate({
        rules:{
            taskBoxName:{
                required: true,
                inputName2: true,
                maxlength: 25
            },
            returnVolumeId:{
                required: true,
                inputName3: true
            },
            returnInstanceId:{
                required: true,
                inputName3: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
  
    //添加健康巡检项目
    $("#createHealthInspectionItem").validate({
        rules:{
            inspectionName:{
                required: true,
                inputName: true,
                maxlength: 25
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	
    //添加健康巡检 :巡检登记
    $("#createHealthInspection").validate({
        rules:{
            nameSelect:{
                required: true
            },
            targetSelect:{
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight:function(element, errorClass, validClass) {
            $(element).parents('.control-group').addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.control-group').removeClass('error');
            $(element).parents('.control-group').addClass('success');
        }
    });
	*/
});








