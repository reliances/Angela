  (function($) {

    $.validator.addMethod("inputName", function(value, element) {
      var reg = /[^\a-zA-Z0-9\-\.\_]/g;
      return this.optional(element) || !reg.test(value);
    }, "支持字母,数字,\"-\",\"_\",\".\"");

    $.validator.addMethod("inputName2", function(value, element) {
      var reg = /[^\a-zA-Z0-9\u4E00-\u9FA5\-\.\_]/g;
      return this.optional(element) || !reg.test(value);
    }, "支持中文,字母,数字,\"-\",\"_\",\".\"");

    $.validator.addMethod("inputName3", function(value, element) {
      var reg = /[^\a-zA-Z0-9\-\.\_\$\{\}]/g;
      return this.optional(element) || !reg.test(value);
    }, "支持字母,数字,\"-\",\".\",\"$\",\"{\",\"}\"");

    $.validator.addMethod("inputName4", function(value, element) {
      var reg = /[^\a-zA-Z]/g;
      return this.optional(element) || !reg.test(value);
    }, "支持字母");

    $.validator.addMethod("inputName5", function(value, element) {
      var reg = /[^\a-zA-Z0-9]/g;
      return this.optional(element) || !reg.test(value);
    }, "支持字母,数字,\"-\",\".\"");

    $.validator.addMethod("alphahead", function(value, element) {
      var reg = /^[a-zA-Z]+[\w\.\-]+$/g;
      return this.optional(element) || reg.test(value);
    }, "支持字母,数字并且以字母开头,\"-\",\".\"");

    $.validator.addMethod("insName", function(value, element) {
      var reg = /[^\a-zA-Z0-9\-\.]/g;
      // console.log('insName', this.optional(element) );
      return this.optional(element) || !reg.test(value);
    }, "支持字母,数字,\"-\",\".\"");

    $.validator.addMethod("desc", function(value, element) {
      var reg = /[\<\>]/g;
      return this.optional(element) || !reg.test(value);
    }, "支持任意字符(除'<'和'>')");

    $.validator.addMethod("ip", function(value, element) {
      var reg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
      return this.optional(element) || reg.test(value);
    }, "请输入正确的IP地址");

    $.validator.addMethod("netSegment", function(value, element) {
      var reg = /^(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])\/(\d{1}|[0-2]{1}\d{1}|3[0-2])$/;
      return this.optional(element) || reg.test(value);
    }, "请输入正确的网段，例如：192.168.100.1/24");

    $.validator.addMethod("alphalettersnum", function(value, element) {
      var reg = /^[a-zA-Z]+[a-zA-Z\d\.\-]*$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入字母,数字,\'-\',\'.\'，并且以字母开头");

    $.validator.addMethod("lettersnumeric", function(value, element) {
      var reg = /^[a-zA-Z\d\.\-]+$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入字母,数字,\'-\',\'.\'");

    $.validator.addMethod("alphalettersnumus", function(value, element) {
      var reg = /^[a-zA-Z]+[\w\.\-]*$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入字母,数字,\'-\',\'.\',\'_\'，并且以字母开头");

    $.validator.addMethod("lettersnumericus", function(value, element) {
      var reg = /^[\w\.\-]+$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入字母,数字,\'-\',\'.\',\'_\'");

     $.validator.addMethod("lettersnumericustwo", function(value, element) {
      var reg = /^[\w\.\-\:]+$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入字母,数字,\'-\',\'.\',\'_\':\'");

    $.validator.addMethod("zhlettersnum", function(value, element) {
      var reg = /^[\w\u4E00-\u9FA5\-\.]+$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入中文,字母,数字,\'-\',\'_\',\'.\'");

    $.validator.addMethod("lettersnumpunc", function(value, element) {
      var reg = /^[\w\-\.\$\{\}]+$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入字母,数字,\'-\',\'.\',\'_\',\'$\',\'{\',\'}\'");

    $.validator.addMethod("lettersonly", function(value, element) {
      var reg = /^[a-zA-Z]+$/g;
      return this.optional(element) || reg.test(value);
    }, "必须为字母");

    $.validator.addMethod("integer", function(value, element) {
      var reg = /^-?\d+$/g;
      return this.optional(element) || reg.test(value);
    }, "必须为数字");
    
    $.validator.addMethod("integerPoint", function(value, element) {
    	var reg = /^\d+(\.\d+)?$/g;
    	return this.optional(element) || reg.test(value);
    }, "允许数字和小数点");
    
    $.validator.addMethod("notNull", function(value, element) {
    	var reg = /^\s+$/g;
    	return this.optional(element) || reg.test(value);
    }, "非空");
   
    $.validator.addMethod("lettersnumericonly", function(value, element) {
      var reg = /^[a-zA-Z0-9]+$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入字母，数字");

    $.validator.addMethod("describe", function(value, element) {
      var reg = /^[^\<\>]+$/g;
      return this.optional(element) || reg.test(value);
    }, "可输入任意字符(除'<'和'>')");

    $.validator.addMethod("ipadress", function(value, element) {
      var reg = /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/;
      return this.optional(element) || reg.test(value);
    }, "请输入正确的IP地址，例如：192.168.0.254");

    $.validator.addMethod("ippool", function(value, element) {
      var reg = /^(((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?),((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\n)*(((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?),((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\n*)?$/g;
      return this.optional(element) || reg.test(value);
    }, "应遵循正确格式：start_ip_address,end_ip_address(例如:192.168.0.0,192.168.0.254)，每个条目占用一行");

    $.validator.addMethod("dnsservice", function(value, element) {
      var reg = /^(((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\n)*(((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\n*)?$/g;
      return this.optional(element) || reg.test(value);
    }, "名称服务器的IP地址列表(例如:192.168.0.254)，每个条目占用一行");

    $.validator.addMethod("hostroute", function(value, element) {
      var reg = /^(((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\/(\d{1}|[0-2]{1}\d{1}|3[0-2]),((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\n)*(((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\/(\d{1}|[0-2]{1}\d{1}|3[0-2]),((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)\n*)?$/g;
      return this.optional(element) || reg.test(value);
    }, "应遵循正确格式：destination_cidr,nexthop(例如:192.168.200.0/24,192.10.56.254)，每个条目占用一行");

    /*
    $.extend( $.validator.messages, {
      inputName: "支持字母,数字,\"-\",\"_\",\".\"",
      inputName2:"支持中文,字母,数字,\"-\",\"_\",\".\"",
      inputName3:"支持字母,数字,\"-\",\".\",\"$\",\"{\",\"}\"",
      inputName4:"支持字母",
      inputName5:"支持字母,数字",
      insName: "支持字母,数字,\"-\",\".\"",
      desc: "支持任意字符(除'<'和'>')",
      ip: "请输入正确的IP地址",
      netSegment: "请输入正确的网段，例如：192.168.100.1/24"
    } );

    $.extend( $.validator.methods, {

      inputName: function(value, element) {
          var reg = /[^\a-zA-Z0-9\-\.\_]/g;
          return this.optional(element) || !reg.test(value);
      },
      inputName2: function(value, element) {
          var reg = /[^\a-zA-Z0-9\u4E00-\u9FA5\-\.\_]/g;
          return this.optional(element) || !reg.test(value);
      },
      inputName3: function(value, element) {
          var reg = /[^\a-zA-Z0-9\-\.\_\$\{\}]/g;
          return this.optional(element) || !reg.test(value);
      },
      inputName4: function(value, element) {
          var reg = /[^\a-zA-Z]/g;
          return this.optional(element) || !reg.test(value);
      },
      inputName5: function(value, element) {
          var reg = /[^\a-zA-Z0-9]/g;
          return this.optional(element) || !reg.test(value);
      },
      insName: function(value, element) {
          var reg = /[^\a-zA-Z0-9\-\.]/g;
          console.log(['insName', this, element]);
          return this.optional(element) || !reg.test(value);
      },
      desc: function(value, element) {
          var reg = /[\<\>]/g;
          return this.optional(element) || !reg.test(value);
      },
      ip: function(value, element) {
          var reg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
          return this.optional(element) || reg.test(value);
      },
      netSegment: function(value, element) {
          var reg = /^(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])\/(\d{1}|[0-2]{1}\d{1}|3[0-2])$/;
          return this.optional(element) || reg.test(value);
      }
    } );
    $(function() { 
      _.debounce(function() {
        $.gritter.add({ image: '/img/icons/common/succ48.png', title: 'Success', text: 'See my notification' });
      },750)();
      _.debounce(function() {
        $.gritter.add({ image: '/img/icons/common/fail48.png', title: 'Failure', text: 'See my notification' });
      },1750)();
      _.debounce(function() {
        $.gritter.add({ image: '/img/icons/common/warn48.png', title: 'Warning', text: 'See my notification' });
      },2750)();
    });
*/
  })($);