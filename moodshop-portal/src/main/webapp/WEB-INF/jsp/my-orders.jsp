<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的订单 - 心情杂货铺</title>
<meta name="Keywords" content="java,心情杂货铺java" />
<meta name="description"
	content="在心情杂货铺中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.common.css"
	media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.order.css"
	media="all" />
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript">
	function dele(v){
		var c=confirm("确定删除订单？");
		if(c==true){
			$.ajax({
				url:"/order/delOrder/"+v,
				success:function(data){
					if(data.status==200){
						alert("删除成功！");
					}
				}
			})
		}
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
						<div class="mod-main mod-comm" id="order01">
							<div class="m m3" id="safeinfo" style="display: none"></div>
							<div class="mt">
								<h3>我的订单</h3>
							</div>
						</div>
						<div class="mod-main mod-comm lefta-box" id="order02">
							<div class="mt">
								<ul class="taborder">
									<li>
										<div class="tyies-t">
											<strong class="ftx-01">全部订单</strong><b></b>
										</div>
									</li>
								</ul>
								<div class="extra-r">
									<div class="search-01">
										<input id="ip_keyword" name="" type="text" class="s-itxt" value="商品名称、商品编号、订单编号" onfocus="if (this.value==this.defaultValue) this.value=''" onblur="if (this.value=='') this.value=this.defaultValue" onkeydown="javascript:if(event.keyCode==13) OrderSearch('ip_keyword');"/>
											<a href="javascript:;" class="btn-13" onclick="OrderSearch('ip_keyword')" clstag="click|keycount|orderinfo|search">查 询</a>
									</div>
								</div>
							</div>
							<div class="mc">
								<table class="tb-void">
									<thead>
										<tr>
											<th>订单信息</th>
											<th>收货人</th>
											<th>订单金额</th>
											<th>数量</th>
											<th>交易状态</th>
											<th>操作</th>
										</tr>
									</thead>
										<tbody id="tb-2538292730">
											<c:forEach items="${orderList}" var="order">
												<tr class="tr-th">
													<td colspan="6"><span class="tcol1"> 订单编号: <a
															name="orderIdLinks" id="idUrl2538292730" target="_blank"
															href="http://order.jd.com/normal/item.action?orderid=2538292730&amp;PassKey=769448C6BA99F1ADA8244BAE7BC60580"
															clstag="click|keycount|orderinfo|order_num">${order.orderId}</a>
	
													</span> <span class="tcol2"> 心情杂货铺 </span> <span class="tcol3">
															<a class="btn-im" onclick="getPamsForChat()" href="#none"
															title="联系客服"></a>
													</span></td>
												</tr>
												<c:forEach items="${order.orderItems}" var="orderItem">
													<tr id="track2538292730" oty="0,1,70" class="tr-td">
												<td>
													<div class="img-list">
														<a href="item/${orderItem.itemId}.html" class="img-box"clstag="click|keycount|orderinfo|order_product" target="_blank"> 
															<img title="${orderItem.title }" width="50" height="50" src="${orderItem.picPath }" class="err-product"/></a> 
													</div>
												</td>
												<td><div class="u-name">${order.orderShipping.receiverName}</div></td>
												<td>${order.payment}<br/>
												<c:if test="${order.paymentType==1}">
													在线支付
												</c:if>
												<c:if test="${order.paymentType==2}">
													货到付款
												</c:if>
												<br/></td>
												<td><span class="ftx-03">${orderItem.num}</span> 
												<input type="hidden" id="paydate" value="${order.paymentTime}"/></td>

												<td><span class="ftx-03">
													<c:if test="${order.status==1}">
														未付款<br/>
														<a href="/order/pay/${order.payment}/${order.orderId}">去结账</a>
													</c:if>
													<c:if test="${order.status==2}">
														已付款<br/>
														等待卖家发货
													</c:if>
													<c:if test="${order.status==3}">
														已付款<br/>
														等待卖家发货
													</c:if>
													<c:if test="${order.status==4}">
														卖家已发货<br/>
														<a href="/order/express/${order.shippingCode}/${order.shippingName}">点击查看物流</a>
													</c:if>
												</span></td>

												<td id="operate2538292730" class="order-doi" width="100">
													<span id="pay-button-2538292730" state=""></span> 
													<a href="javascript:dele(${order.orderId})" clstag="click|keycount|orderinfo|order_del">删除</a></span><span
													id="doi2538292730"><br/>
													<a href="" target="_blank" clstag="click|keycount|orderinfo|order_comment">评价晒单</a><br/></span>
													<a href="" target="_blank" clstag="click|keycount|orderinfo|order_repair">申请返修/退换货</a>
													<a class="btn-again" clstag="click|keycount|orderlist|buy"
													href="/item/${orderItem.itemId}.html"
													target="_blank">还要买</a>
												</td>
											</tr>
												</c:forEach>
											</c:forEach>
											
										</tbody>
								</table>
							</div>
							<div class="mt10">
								<div class="pagin fr">
									<!--  <span class="text">共20条记录</span>    <span class="text">共1页</span> -->
									<span class="prev-disabled">上一页<b></b></span>

									<!-- <span class="prev-disabled">首页</span> -->
									<a class="current">1</a>
									<!-- <span class="next-disabled">末页</span>  -->
									<span class="next-disabled">下一页<b></b></span>

								</div>
								<div class="clr"></div>
							</div>
						</div>
					</div>
				</div>
				<div id="left" class="g-3-5 c-0">
					<!--js 加载异步加载的左侧菜单 -->
					<div id="menu">
						<h3>我的交易</h3>
						<dl class="fore1">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hdd"
									id="_MYJD_ordercenter"
									href="/order/my-orders" class="curr">我的订单</a>
							</dt>
						</dl>
						<dl class="fore2">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hyushou"
									id="_MYJD_yushou"
									href="/my-info.html">我的信息</a>
							</dt>
						</dl>
						<dl class="fore3">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hbdsh"
									id="_MYJD_locallife"
									href="/cart/cart.html">我的购物车</a>
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