<style>
	.OnlineService_Bg{  
    width: 30px;  
    height: 100px;  
    background-color: #15b1ea;  
    position: fixed;  
    right: 80px;  
    bottom: 200px;  
	} 
</style>
<body>
 <!--客服功能的开始 -->
	<div class="OnlineService_Bg">
		<div class="OnlineService_Box">
			<div class="Online_QQ">
				<a target="_blank"
					href="http://wpa.qq.com/msgrd?v=3&uin=980569907&site=qq&menu=yes"><img
					border="0" src="http://wpa.qq.com/pa?p=2:980569907:53"
					alt="点击这里给我发消息" title="点击这里给我发消息" /></a>
			</div>
		</div>
	</div>

	<script id="longlian_service_tmpl" type="text/x-jquery-tmpl">
				{{if service != null}}
				{{each(i, svc) service}}
				<li><a target="_blank" rel="nofollow" href="http://wpa.qq.com/msgrd?v=3&uin=${svc.ServiceAccount}&site=qq&menu=yes">${svc.ServiceName}
				</a></li>
				{{/each}}
				{{/if}}
	</script>
	<!-- 客服功能的结束 -->