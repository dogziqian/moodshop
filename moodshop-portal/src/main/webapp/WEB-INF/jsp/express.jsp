<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.json.*"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>心情杂货铺-物流</title>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<style>
body {
	background:#F5F5F5;
}

.pingjia {
	width: 800px;
	background-color: #FFFFFF;
	position: absolute;
	left: 270px;
	top: 50px;
	/*padding-top: 20px;
        		padding-left: 20px;*/
}

#express tr {
	height: 40px;
}

#express .time {
	width: 40%;
}

#tab {
	margin-left: 20px;
}

#top {
	height: 50px;
	background-color: #E33B3E;
	line-height: 50px;
	padding-left: 30px;
}

#top span {
	font-size: 18px;
	font-weight: 545;
}
</style>

</head>
<body>
	<div class="pingjia">
		<div id="top">
			<span>您的物流信息如下：</span>
		</div>
		<div class="p-details-table table-responsive">
			<table id="tab">
				<thead>
					<tr><td>快递公司：${express.shipperCode}</td><td>快递单号：${express.logisticCode}</td></tr>
				</thead>
				<tbody id="express">
					<c:forEach items="${express.traces}" var="trace">
						<tr>
							<td class="time">${trace.acceptTime}</td>
							<td>${trace.acceptStation}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>