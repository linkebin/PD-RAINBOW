/*解析 ConextPath*/
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    
    var protocol = window.location.protocol ;
    if( protocol == "file:" ){
    	result = pathName.split("manPower")[0]+"manPower" ;//直接浏览器查看文件路径
    	return window.location.protocol+"//"+result;
    }
    return result ;
}

window.Config = window.Config || {
	contextPath:getContextPath(),
	serverPath:window.location.protocol+"//"+window.location.host+getContextPath()
} ;
window.Global = window.Global || window.Config ;



/********************************
 *************jQuery.utils*******
 *********************************/
jQuery.utils = {
	//解析URL
	parseUrl: function (url,params) {
        params = params || {};
        url = jQuery.trim(url);
        if (url.startWith("~")) {
            url = url.substring(1);
            url = Config.contextPath + url;
        }

        //url = url.replace("~",Config.contextPath) ;
        url = url.replace("{host}", getHost());
        url = url.replace("{port}", getPort());

        for (var o in params) {
            url = url.replace("{" + o + "}", params[o]);
        }

        if (new RegExp("^(http|https)://").test(url)) {
        	try{
	        	var urlObject = parseUrlInternal(url);
	            if ((urlObject.host + ":" + urlObject.port == window.location.host) || urlObject.host == window.location.host) {
	                url = url.replace(new RegExp("^(http|https)://[^/]+/"), window.location.protocol + "//" + window.location.host + "/");
	            }
        	}catch(e){
        	}
        }

		return url ;

		function getHost(){
			var host = window.location.host ;
			return host.split(":")[0] ;
		}

		function getPort(){
			return window.location.port ;
		}

		 function parseUrlInternal(url) {
            var a = document.createElement('a');
            a.href = url;

            return {
                source: url,
                protocol: a.protocol.replace(':', ''),
                host: a.hostname,
                port: a.port,
                query: a.search,
                params: (function () {
                    var ret = {},
         			seg = a.search.replace(/^\?/, '').split('&'),
         			len = seg.length, i = 0, s;
                    for (; i < len; i++) {
                        if (!seg[i]) { continue; }
                        s = seg[i].split('=');
                        ret[s[0]] = s[1];
                    }
                    return ret;
                })(),
                file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
                hash: a.hash.replace('#', ''),
                path: a.pathname.replace(/^([^\/])/, '/$1')
            };
        }
	},scriptPath:function(scriptName){
		var _scriptRoot = window.defaultScriptRoot||"~/statics/scripts" ;
		var _themeRoot  = window.defaultThemeRoot||"~/statics/themes" ;
		var _defaultTheme = window.defaultTheme||"mobile" ;

		if(scriptName == "plugin"||scriptName == "plugins") return jQuery.utils.parseUrl(_scriptRoot+"/plugins/") ;
		if(scriptName == "upload") return jQuery.utils.parseUrl(_scriptRoot+"/plugins/") ;
		if( scriptName == 'jqueryui.css' ) return  jQuery.utils.parseUrl(_themeRoot+"/"+_defaultTheme+"/jquery-ui.css") ;
		var path = "" ;
		$("script,link").each(function(){
			if(path) return ;
			var src = this.src||this.href ;
			if(src &&  src.toLowerCase().indexOf(scriptName.toLowerCase())!=-1 ){
				path = src.substring(0, src.toLowerCase().indexOf(scriptName.toLowerCase()));
				var A = path.lastIndexOf("/");
				if (A > 0)
					path = path.substring(0, A + 1);
				return ;
			}
		}) ;
		return path ;
	}
};


/**
 * 统一获取数据入口
 * 参数格式：
 * 	1、 params
 *       type: 'post',
         url: 'demo-data.html' ,
         data: req.term ,
         async: true ,
         dataType:'json'

         返回数据格式
         returnCode:       --  int
         returnDesc:        -- string
         error:                  --  string
         returnValue:      --  json object
 *
 */
jQuery.support.cors = true ;
jQuery.request = function(params){
	var _url     = null ;
	var _data    = null ;
	var _success = null ;
	var _error   = null ;
	var target   = params.target||window.document.body ;

	 if( jQuery.block && !params.noblock ) jQuery(target).block() ;

 	 var dataType 	= 'text' ;
 	 var async 		= typeof params.async == 'undefined' ? true : params.async ;
 	 var type 		= params.type||'post' ;
 	 var error 		= params.error||_error|| jQuery.request.defaultErrorHandler;
 	 var success 	= params.success||_success ;
 	 var url 		= params.url ||_url ;
 	 var data 		= params.data || _data ;

	 if(jQuery.utils) url = jQuery.utils.parseUrl(url) ;
	 if (url.indexOf("?") > 0) {
        url = url + "&sb=" + Math.random();
     } else {
        url = url + "?sb=" + Math.random();
     }

     var ajaxOptions = {
        type: type,
        url: url ,
        data: data ,
        async: async ,
        dataType:dataType ,
        success: function(response){
        	if( jQuery.unblock && !params.noblock ) jQuery(target).unblock() ;
        	var json = response ;

        	if(!response){
        		success(response,params.custom||{}) ;
        		return ;
        	}

        	if(typeof(response) == 'string'){
        		try{
        			eval("response = "+ response ) ;
        		}catch(e){
        			success(response,params.custom||{}) ;
        			return ;
        		}
        	}

        	if( typeof response.returnCode != 'undefined' && response.returnCode != 200 ){
        		error(null , response.returnCode , response.error,url) ;
        	}else{
        		if( !response.returnValue ||  typeof  response.returnValue == 'string')
        			success(response.returnValue===false?false:(response.returnValue||response),params.custom||{}) ;
        		else{
        			if( response.returnValue.Rows ){
        				var items = [] ;
        				var columnNames = response.returnValue.ColumnNames ;
        				jQuery(response.returnValue.Rows).each(function(){
        					var item = this ;
        					var temp = {} ;
        					jQuery(item).each(function(index,record){
        						var colName = columnNames[index] ;
        						temp[colName] = record ;
        					}) ;
        					items.push(temp) ;
        				}) ;
        				success(items) ;
        			}else{
        				success( response.returnValue || response,params.custom||{}) ;
        			}
        		}
        	}
        } ,
        error: function(xhr, textStatus, errorThrown){
        	if( jQuery.unblock && !params.noblock ) jQuery(target).unblock() ;
        	error(xhr, textStatus, errorThrown,url) ;
        	params._error && params._error(xhr, textStatus, errorThrown,url) ;
        }
     } ;

     if( jQuery.request._events['beforeSend'] || params.beforeSend){
     	ajaxOptions.beforeSend = function(xhr){
     		jQuery( jQuery.request._events['beforeSend']||[] ).each(function(index,func){
     			func.call(ajaxOptions,xhr) ;
     		}) ;
     		if( params.beforeSend ){
     			params.beforeSend.call(ajaxOptions,xhr) ;
     		}
     	}
     }
     var isCrossDomain = false ;
     var url = ajaxOptions.url ;
     if( url.startWith("http://") || url.startWith("https://") ){
     	 var urlParser = new Poly9.URLParser(url) ;
     	 var  proto = urlParser.getProtocol() ;
     	 var  host = urlParser.getHost() ;
     	 var  port = urlParser.getPort() ;

     	 var requestUrl = proto+"://"+host+":"+port ;
     	 var localUrl   = jQuery.utils.parseUrl("http://{host}:{port}") ;

     	 isCrossDomain = requestUrl!=localUrl ;
     }

     if(isCrossDomain){
     	ajaxOptions.type = "get" ;
     	ajaxOptions.dataType = 'jsonp' ;
     	ajaxOptions.async = false ;
        ajaxOptions.data = ajaxOptions.data||{} ;
		ajaxOptions.data.jsonp = true ;
     }

 	 jQuery.ajax(ajaxOptions);
 }
jQuery.request._events = {} ;
jQuery.request.addEvent = function( type , func ){
	 jQuery.request._events[type] == jQuery.request._events[type]||[] ;
	 jQuery.request._events[type].push(func) ;
}

jQuery.request.defaultErrorHandler = function(xhr, textStatus, errorThrown,url){
	 jQuery.open(Global.contextPath+"/common/error/report500.jsp",570,410,{errorThrown:errorThrown} ) ;
}


/**
 * 数据服务统一调用接口
 * @param {} commandName
 * @param {} params
 * @param {} callback   {success:function(){},error:function(){}} or function(){}//success
 */
jQuery.dataservice = function(commandName , params , callback , reqParams ){
	callback 			= callback||{} ;
	params  			= params||{} ;

	for(var o in params){
		if( typeof params[o]  == 'object'){
			params[o] =  $.json.encode(params[o])  ;
		}
	}

	params.CommandName 	= commandName ;

	reqParams 			= reqParams||{} ;
	reqParams.data 		= params ;
	reqParams.type		= 'post' ;
	reqParams.noblock 	= reqParams.noblock === false?false:true ;
	reqParams.url 		= commandName? (window.dataServiceUrl||"~/dataservice") :reqParams.url ;
	reqParams.dataType 	= commandName?'json':"text" ;
	//alert(reqParams.url);
	//process callback
	if( callback.success ){
		reqParams.success = callback.success ;
	}

	if( callback.error ){
		reqParams.error = callback.error ;
	}

	if( jQuery.isFunction(callback) ){
		reqParams.success = callback ;
	}
	jQuery.request(reqParams) ;
}


/*********************
 * common
 * */
String.prototype.startWith=function(str){
      var reg=new RegExp("^"+str);
      return reg.test(this);
}

String.prototype.endWith=function(str){
      var reg=new RegExp(str+"$");
      return reg.test(this);
}

String.prototype.getQueryString = function(name){ //name 是URL的参数名字
	var reg = new RegExp("(^|&|\\?)"+ name +"=([^&]*)(&|$)"), r;
	if (r=this.match(reg)) return (unescape(r[2])||"").split("#")[0]; return null;
};

if (typeof Poly9 == 'undefined')
{
    var Poly9 = {};
}
  
/**
 * Creates an URLParser instance
 *
 * @classDescription    Creates an URLParser instance
 * @return {Object} return an URLParser object
 * @param {String} url  The url to parse
 * @constructor
 * @exception {String}  Throws an exception if the specified url is invalid
 */


/* fix 表单点击回车提交问题 */
jQuery(function(){
   jQuery(document).find("form").keydown(function(e){
	  var kc = e.keyCode ;
	  if(kc == 13){
		 	var $tgt = jQuery(e.target);

		 	if (!$tgt.is('input'))return true ;

	 		 if (e && e.preventDefault) {
	 			e.preventDefault();
	 		 } else {
				window.event.returnValue = false;
			 }
			 return false;
		}
		return true ;
      }) ;
}) ;



/* widget-common */
(function($){
	/**
	 * 控件初始化
	 */

	/**
	 * 浏览器兼容
	 */
	var browserFix_map = {} ;
	$.browserFix = function(el){
		if ($.browser.msie){
			var bowser = "ie" ;
			var version = parseInt($.browser.version, 10) ;
			for(var type in browserFix_map[bowser+"_"+version]||{} ){
				(browserFix_map[bowser+"_"+version]||{})[type]( el ) ;
			}
		}
	}

	$.pageLoad = {before:[],after:[]} ;
	$.pageLoad.register = function(type , func){
		$.pageLoad[type].push(func) ;
	} ;

	$(function(){
		$( $.pageLoad.before ).each(function(index,func){
			func() ;
		}) ;

		$( $.pageLoad.after ).each(function(index,func){
			func() ;
		}) ;
	});


})(jQuery)







