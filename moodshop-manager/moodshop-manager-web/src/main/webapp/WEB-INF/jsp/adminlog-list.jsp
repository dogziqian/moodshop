<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="adminLogList" title="用户列表"
	data-options="singleSelect:false,collapsible:true,pagination:true,url:'/admin/adminLog/list',method:'get',pageSize:30,toolbar:toolbar">
	<thead>
		<tr>
			<th data-options="field:'username',width:100">管理员名称</th>
			<th data-options="field:'opration',width:200">操作</th>
			<th data-options="field:'optime',width:150,formatter:MoodShop.formatDateTime">操作时间</th>
		</tr>
	</thead>
</table>
<script>
var toolbar = []
</script>