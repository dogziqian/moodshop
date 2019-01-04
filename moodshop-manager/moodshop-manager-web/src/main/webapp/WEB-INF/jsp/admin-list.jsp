<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="adminList" title="用户列表"
	data-options="singleSelect:false,collapsible:true,pagination:true,url:'/admin/list',method:'get',pageSize:30,toolbar:toolbar">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:60">管理员ID</th>
			<th data-options="field:'username',width:200">管理员名称</th>
			<th data-options="field:'rank',width:100">管理员等级</th>
			<th
				data-options="field:'status',width:100,formatter:MoodShop.formatAdminStatus">状态</th>
		</tr>
	</thead>
</table>
<script>

    function getSelectionsIds(){
    	var adminList = $("#adminList");
    	var sels = adminList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增管理员')").parent().click();
        	MS.createWindow({
    			url : "/admin-add"
    		});
        }
    	
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个用户才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个用户!');
        		return ;
        	}
        	
        	MS.createWindow({
    			url : "/admin-edit",
    			onLoad : function(){
    				var data = $("#adminList").datagrid("getSelections")[0];
    				$("#adminEditForm").form("load",data);
    			}
    		});
        }
    }];
</script>