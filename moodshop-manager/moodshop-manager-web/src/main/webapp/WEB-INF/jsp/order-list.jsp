<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="orderList" title="订单列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'orderId',width:60">订单id</th>
            <th data-options="field:'payment',width:200">实付金额</th>
            <th data-options="field:'paymentType',width:100,formatter:MoodShop.formatPayType">支付类型</th>
            <th data-options="field:'postFee',width:100">邮费</th>
            <th data-options="field:'status',width:70,formatter:MoodShop.formatOrderStatus">状态</th>
            <th data-options="field:'paymentTime',width:70,align:'right',formatter:MoodShop.formatDateTime">付款时间</th>
            <th data-options="field:'shippingName',width:100">物流名称</th>
            <th data-options="field:'shippingCode',width:60,align:'center'">物流编号</th>
            <th data-options="field:'userId',width:130,align:'center'">用户id</th>
        </tr>
    </thead>
</table>
<div id="orderEditWindow" class="easyui-window" title="编辑订单" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/order-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var orderList = $("#orderList");
    	var sels = orderList.datagrid("getSelections");
    	
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	
        	if(ids.indexOf(',') >= 0){
        		$.messager.alert('提示','只能选择一个商品!');
        		return ;
        	}
        	
        	MS.createWindow({
    			url : "/order-edit",
    			onLoad : function(){
    				var data = $("#orderList").datagrid("getSelections")[0];
    				//alert(data.orderId);
    				$("#orderEditForm").form("load",data);
    			}
    		});
        }
    }];
</script>