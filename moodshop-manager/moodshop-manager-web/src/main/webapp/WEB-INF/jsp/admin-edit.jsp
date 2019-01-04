<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="adminEditForm" class="adminForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>管理员ID:</td>
	            <td><input class="easyui-textbox" type="text" name="id" data-options="required:true" style="width: 280px;" readonly="readonly"></input></td>
	        </tr>
	        <tr>
	            <td>管理员名称:</td>
	            <td><input class="easyui-textbox" name="username" style="width: 280px; readonly="readonly="readonly"></input></td>
	        </tr>
	        <tr>
	            <td>管理员等级:</td>
	            <td><input class="easyui-numberbox" type="text" name="rank" style="width: 280px;" readonly="readonly" />
	            </td>
	        </tr>
	       <tr>
	            <td>管理员等级:</td>
	            <td>
	            <select class="easyui-select"  name="status" style="width: 280px;">
	            	<option value="1">正常</option>
	            	<option value="2">锁定</option>
	            </select>
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">

	function submitForm(){
		if(!$('#adminEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("/admin/update",$("#adminEditForm").serialize(), function(data){
			//alert(data.status);
			if(data.status == 200){
				alert('修改管理员成功!');
				$("#adminList").datagrid("reload");
				MS.closeCurrentWindow();
			}
		});
	}
</script>
