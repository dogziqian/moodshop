$(function() {
	// 将省份信息加入到下拉列表中	
	$.ajax({
		url : "/queryProvence",
		type : "GET",
		success : function(msg){
			var provence = eval("(" + msg + ")");
			for (var i = 0; i < provence.length; i++) {
				$("#province").append(
						"<option value='" + provence[i].provence_id + "'>"
								+ provence[i].provence_name + "</option>");
			}
		}
	});	
});
// 获取城市信息
function getCity(v) {
	$.get("/queryCity/" + v, function(
			msg) {
		// 用evel解析后台传的json格式数据
		var city = eval("(" + msg + ")");
		// 解析完成是object数组
		// 遍历object数组并给省存入信息
		$("#city")[0].length = 1;
		for (var i = 0; i < city.length; i++) {

			// 获取市下拉框并赋值
			$("#city").append(
					"<option value='" + city[i].city_id + "'>"
							+ city[i].city_name + "</option>");
		}
	});
};
function updateUserInfo(){
	//取商品的规格
	var paramJson = [];
	$("#userForm .item").each(function(i,e){
		var trs = $(e).find(".fl");
		var ps = [];
		for(var i = 1;i<trs.length;i++){
			var tr = trs.eq(i);
			paramJson.push({
				"params": $.trim(tr.find("input").val())
			});
		}
	});
	//把json对象转换成字符串
	paramJson = JSON.stringify(paramJson);
	$("#userForm [name=userParams]").val(paramJson);
	
	//ajax的post方式提交表单
	//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
	$.post("/update/userinfo",$("#userForm").serialize(), function(data){
		if(data.status == 200){
			$.messager.alert('提示','修改个人信息成功!');
		}
	});
}