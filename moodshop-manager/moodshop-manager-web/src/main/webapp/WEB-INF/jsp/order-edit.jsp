<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="orderEditForm" class="orderForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>订单id:</td>
	            <td><input class="easyui-textbox" type="text" name="orderId" data-options="required:true" style="width: 280px;" readonly="readonly"></input></td>
	        </tr>
	        <tr>
	            <td>实付金额:</td>
	            <td><input class="easyui-textbox" type="text" name="payment" style="width: 280px;"></input></td>
	        </tr>
	         <tr>
	            <td>邮费:</td>
	            <td><input class="easyui-textbox" type="text" name="postFee" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>状态:</td>
	            <td>
	            	<select class="easyui-select" name="status" style="width: 280px;">
	            		<option value="3" selected="selected">未发货</option>
	            		<option value="4">已发货</option>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <td>物流名称:</td>
	            <td>
		            <select class="easyui-selec"  name="shippingName" style="width: 280px;">
		            	<option value="ZTO">中通</option>
		            	<option value="STO">申通</option>
		            	<option value="YTO">圆通</option>
		            </select>
	            </td>
	        </tr>
	        <tr>
	            <td>物流编号:</td>
	            <td><input class="easyui-textbox" type="text" name="shippingCode" style="width: 280px;"></input></td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="orderEditPage.submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="orderEditPage.clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

var orderEditPage = {
		submitForm : function(){
			if(!$('#orderEditForm').form('validate')){
				$.messager.alert('提示','表单还未填写完成!');
				return ;
			}
			
			$.post("/order/edit",$("#orderEditForm").serialize(), function(data){
				//alert(data.status);
				if(data.status == 200){
					$.messager.alert('提示','修改内容成功!');
					$("#orderList").datagrid("reload");
					MS.closeCurrentWindow();
				}
			});
		},
		clearForm : function(){
			
		}
};

</script>