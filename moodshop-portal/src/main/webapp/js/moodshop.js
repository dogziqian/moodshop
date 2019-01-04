var MS = MoodShop = {
	checkLogin : function(){
		var _ticket = $.cookie("MS_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://sso.moodshop.xyz/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到心情杂货铺！<a href=\"http://sso.moodshop.xyz/user/logout/"+_ticket+"\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	MS.checkLogin();
});