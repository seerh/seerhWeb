$(function(){
	//全局AJAX
	$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError){
		//shiro返回401
    	if(jqXHR.status == 401) {
    		var url = "http://localhost:8888/seerhWeb/cas";
    		window.location.href="http://172.16.151.30/cas4/login?service=" + url;
    	}
    });
	$(document).ajaxSend(function(event, jqXHR, ajaxSettings, thrownError){
		var location = window.location.href;
		var host = window.location.protocol + "//" + window.location.host
		location = location.replace(host, "");
		alert(location);
		jqXHR.setRequestHeader('casUrl', location);
	});
	
});