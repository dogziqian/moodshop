<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引用的css -->
  <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/default/easyui.css" rel="external nofollow" >  
  <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/default/panel.css" rel="external nofollow" >  
  <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/icon.css" rel="external nofollow" > 
  <link rel="stylesheet" type="text/css" href="/css/index.css" rel="external nofollow" > 
  <script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="/js/common.js"></script>
<title>管理员登录</title>
</head>
<body>
  <div id="header" >
    <div class="logo">
      <strong>管理员登陆系统</strong>
    </div>
  </div>
  <div data-options=" region:'east',split:true,style:{position:'absolute',right:550,top:150}"
 class="easyui-panel " title="管理员登录" style="width:300px;text-align: center;">
      <div style="padding:10px 60px 20px 60px">
        <form id="formlogin" class="easyui-form" method="post" data-options="novalidate:true">
          <table cellpadding="5">
            <tr>
              <td><input id="loginname" class="easyui-textbox" data-options="prompt:'用户名',validType:'name'" iconCls="icon-man" iconAlign=left style="width:100%;height:32px"/></td>
            </tr>
            <tr>
              <td><input id="nloginpwd" type="password" class="easyui-textbox" data-options="prompt:'密码',validType:'password'" iconCls="icon-lock" iconAlign=left style="width:100%;height:32px"></input></td>
            </tr>
          </table>
        </form>
        <div style="text-align:center;padding:5px; ">
          <input type="button" id="loginsubmit"  class="easyui-linkbutton" style="width:45%;height:32px" value="登录">   
        </div>
      </div>
    </div>
    <script type="text/javascript">
    var LOGIN = {
			checkInput:function() {
				if ($("#loginname").val() == "") {
					alert("用户名不能为空");
					$("#loginname").focus();
					return false;
				}
				if ($("#nloginpwd").val() == "") {
					alert("密码不能为空");
					$("#nloginpwd").focus();
					return false;
				}
				return true;
			},
			doLogin:function() {
				
				var username=$("#loginname").val();
				var password=$("#nloginpwd").val();
				//var params=[username,password];
				$.post("/admin/login", {username:username,password:password},function(data){
					var result=JSON.stringify(data.data);
					//alert(result);
					//alert(result=='"index"');
					if(result=='"index"'){
						alert("登录成功！");
						window.location.href="/index";
					}else if(result=='"login"'){
						alert("用户名或密码错误！");
					}else{
						alert("账户异常，请联系超级管理员！");
					}
					
				});
			},
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}
		
	};
	$(function(){
		$("#loginsubmit").click(function(){
			LOGIN.login();
		});
	});
    </script>
</body>
</html>