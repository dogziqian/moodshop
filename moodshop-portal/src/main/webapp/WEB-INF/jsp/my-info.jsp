<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的信息 - 心情杂货铺</title>
<meta name="Keywords" content="java,心情杂货铺java" />
<meta name="description"
	content="在心情杂货铺中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.common.css"
	media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.info.css"
	media="all" />
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<!--  <script type="text/javascript" src="/js/provence.js"></script>-->
<script type="text/javascript">
$(function() {
	// 将省份信息加入到下拉列表中	<input type="hidden" name="city_data"
	$.ajax({
		url : "/queryProvence.action",
		type : "GET",
		success : function(msg){
			var provence = eval("("+msg.data+")");
			var pros=provence.data;
			for (var i = 0; i < pros.length; i++) {
				//alert(provence);
				if($("#provence_data").val()==pros[i].provenceId){
					$("#province").append(
							"<option value='" + pros[i].provenceId + "' selected>"
							+ pros[i].provenceName + "</option>");
					getCity(pros[i].provenceId);
					}else{
						$("#province").append(
								"<option value='" + pros[i].provenceId + "'>"
								+ pros[i].provenceName + "</option>");
						}
					}
		}
	});	
});
// 获取城市信息
function getCity(v) {
	$.ajax({
		url : "/queryCity.action",
		type : "GET",
		data:{"pid":v},
		success : function(msg){
			// 用evel解析后台传的json格式数据
			var city = eval("(" + msg.data + ")");
			var citys=city.data;
			// 解析完成是object数组
			// 遍历object数组并给省存入信息
			$("#city")[0].length = 1;
			
			for (var i = 0; i < citys.length; i++) {
				// 获取市下拉框并赋值
				if($("#city_data").val()==citys[i].cityId){
					$("#city").append(
							"<option value='" + citys[i].cityId + "' selected>"
							+ citys[i].cityName + "</option>");
				}else{
					$("#city").append(
							"<option value='" + citys[i].cityId + "'>"
							+ citys[i].cityName + "</option>");
				}
			}
		}
	});
};
function updateUserInfo(){
	//取商品的规格
	

	var nickname=$("#nickname").val();
	var sex=$(".item input[name='sex']:checked").val();
	var birthday=$("#birthday").val();
	var email=$("#email").val();
	var realname=$("#realname").val();
	var province=$("#province option:selected").val();
	var city=$("#city option:selected").val();
	var address=$("#address").val();
	
	var paramJson =
		{
			"nickname": nickname,
			"sex":sex,
			"birthday":birthday,
			"email":email,
			"realname":realname,
			"province":province,
			"city":city,
			"address":address
		};
	paramJson = JSON.stringify(paramJson);
	//alert(paramJson);
	$.ajax({
		url:"/update/userinfo",
		type:"POST",
		contentType:'application/json;charset=UTF-8',
		data:paramJson,
		success:function(data){
			if(data.status==200){
				alert('修改个人信息成功!');
			}
		}
	});
}
</script>
</head>
<body>
	<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
	<!-- header start -->
	<jsp:include page="commons/header.jsp" />
	<!-- header end -->

	<div id="container">
		<div class="w">

			<div id="main">
				<div class="g-0">
					<div id="content" class="c-3-5">
						<div class="mod-main">
							<div class="mt">
								<ul class="extra-l">
									<li class="fore-1"><a class="curr"
										href="http://i.jd.com/user/info">基本信息</a></li>
									<li class="fore-2"><a
										href="http://i.jd.com/user/userinfo/showImg.html">头像照片</a></li>
									<li class="fore-3"><a
										href="http://i.jd.com/user/userinfo/more.html">更多个人信息</a></li>

								</ul>
							</div>
							<div class="mc">
								<div class="user-set userset-lcol">
									<form class="form" name="infoForm" id="infoForm">
										<div class="item">
											<span class="label"><em>*</em>昵称：</span>
											<div class="fl">
												<input type="text" class="itxt" maxlength="20" id="nickname"
													name="nickname" value="${userDetail.nickname}">
												<div class="clr"></div>
												<div class="prompt-06">
													<span id="nickName_msg"></span>
												</div>
											</div>
										</div>
										<div class="item">
											<span class="label"><em>*</em>性别：</span>
											<div class="fl">
												<input type="radio" name="sex" class="jdradio" value="0" /><label
													class="mr10"
													<c:if test="${userDetail.sex=='0'}">checked</c:if>>男</label>
												<input type="radio" name="sex" class="jdradio" value="1"
													<c:if test="${userDetail.sex=='1'}">checked</c:if> /><label
													class="mr10">女</label>
											</div>
										</div>
										<div class="item">
											<span class="label">生日：</span>
											<div class="fl">
												<input type="date" name="birthday" id="birthday"
													class="itxt" value="${userDetail.birthday}" /> <span
													class="ftx03">填生日有惊喜哦~</span>
											</div>
										</div>

										<div class="item">
											<span class="label">兴趣爱好：</span>
											<div class="fl interest-list">
												<p>请选择您感兴趣的分类，给您最精准的推荐</p>
												<ul class="hobul">
													<li name="hobbyType" value="1">图书/音像/数字商品<s></s></li>
													<li name="hobbyType" value="2">家用电器<s></s></li>
													<li name="hobbyType" value="3">手机/数码<s></s></li>
													<li name="hobbyType" value="4">电脑/办公<s></s></li>
													<li name="hobbyType" value="5">家居/家具/家装/厨具<s></s></li>
													<li name="hobbyType" value="6">服饰内衣/珠宝首饰<s></s></li>
													<li name="hobbyType" value="7">个护化妆<s></s></li>
													<li name="hobbyType" value="8">鞋靴/箱包/钟表/奢侈品<s></s></li>
													<li name="hobbyType" value="9">运动健康<s></s></li>
													<li name="hobbyType" value="10">汽车用品<s></s></li>
													<li name="hobbyType" value="11">母婴/玩具乐器<s></s></li>
													<li name="hobbyType" value="12">食品饮料/保健食品<s></s></li>
													<li name="hobbyType" value="13">彩票/旅行/充值/票务<s></s></li>
												</ul>
											</div>
										</div>
										<div class="item">
											<span class="label">邮箱：</span>
											<div class="fl">
												<div>
													<input type="email" name="email" id="email" class="itxt"
														value="${userDetail.email}" />
												</div>
											</div>

											<div class="clr"></div>
										</div>

										<div class="item">
											<span class="label">真实姓名：</span>
											<div class="fl">
												<input type="text" class="itxt" maxlength="20"
													name="realname" id="realname"
													value="${userDetail.realname}" />
												<div class="clr"></div>
												<div class="prompt-06">
													<span id="realName_msg"></span>
												</div>
											</div>
										</div>
										<div class="item">
											<span class="label">所在地：</span>
											<div class="fl">
												<input type="hidden" name="provence_data" id="provence_data"
													value="${userDetail.province }" /> <input type="hidden"
													name="city_data" id="city_data" value="${userDetail.city}" />
												<select class="selt mr5" id="province"
													onchange="getCity(this.value)">
													<option value="">--请选择--</option>
												</select> <select name="city" class="selt mr5" id="city">
													<option value="">--请选择--</option>
												</select>
												<div class="clr"></div>
												<div class="prompt-06">
													<span id="city_msg"></span>
												</div>
											</div>
										</div>
										<div class="item">
											<div class="fl">
												<span class="label">具体地址：</span> <input type="text"
													class="itxt itxt1" name="address" id="address"
													value="${userDetail.address}" />
												<div class="clr"></div>
												<div class="prompt-06">
													<span id="address_msg"></span>
												</div>
											</div>
										</div>
										<div class="item">
											<span class="label">&nbsp;</span>
											<div class="fl">
												<a href="javascript:void(0)" class="btn-5"
													onclick="updateUserInfo()">提交</a>
											</div>
										</div>
									</form>
								</div>
								<div id="user-info">
									<div class="u-pic">
										<img alt="用户头像" src="/images/defaultImgs/1.jpg" />
										<div class="mask"></div>
										<div class="face-link-box"></div>
										<a href="http://i.jd.com/user/userinfo/showImg.html"
											class="face-link">修改头像</a>
									</div>
									<div class="info-m">
										<div>
											<b>用户名：${userDetail.nickname }</b>
										</div>
										<div class="u-level">
											<span class="rank r4"> <s></s><a
												href="http://usergrade.jd.com/user/grade" target="_blank">金牌会员</a>
											</span>
										</div>
										<div class="shop-level">
											购物行为评级：<span><a target="_blank"
												href="http://help.jd.com/help/question-57.html#help2173">
													<s id="userCredit" class="rank-sh rank-sh01 rank-sh02"></s>
											</a></span>
										</div>
										<div>会员类型：个人用户</div>
									</div>
								</div>
								<div class="fr ac" style="width: 280px;">
									注：修改手机和邮箱请到<a class="ml5 ftx05"
										href="http://safe.jd.com/user/paymentpassword/safetyCenter.action">账户安全</a>
								</div>
								<div class="clr"></div>

							</div>
						</div>
					</div>
				</div>
				<div id="left" class="g-3-5 c-0">
					<!--js 加载异步加载的左侧菜单 -->
					<div id="menu">
						<h3>我的信息</h3>
						<dl class="fore1">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hdd"
									id="_MYJD_ordercenter" href="/my-info.html" class="curr">我的信息</a>
							</dt>
						</dl>
						<dl class="fore2">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hyushou"
									id="_MYJD_yushou" href="/order/my-orders">我的交易</a>
							</dt>
						</dl>
						<dl class="fore3">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hbdsh"
									id="_MYJD_locallife" href="/cart/cart.html">我的购物车</a>
							</dt>
						</dl>
					</div>
				</div>
				<span class="clr"></span>
			</div>
		</div>
	</div>

	<!-- footer start -->
	<jsp:include page="commons/footer.jsp" />
	<!-- footer end -->
</body>
</html>