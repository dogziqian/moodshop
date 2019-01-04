package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016080800194083";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCm/nTaL37RzOycqmt1raFa0MXRh62JEc9/DqNxTlUtk4d3+g5hMK+4SD1cosUf18hKz6NmzvVxbTPMSDXNl7p4+kS9gTTv6MA7+vto8tSwVhwwsuGwlYGEeuLTKdlrxwxBU5Q3GrskCln3NrJmoC0r8/7HH9/+tfOYIwDiSTG5DaNaqZoifw5/rqrciZEi6hNPrpPSDPB39n2OMru0gP4D2mHFnOhR78iwU30e0eVvDfaeU+u7q1ZAHBAdibwRDpNzv5fueUNWwkrLzcudWcx43K0mcbHttvKyA4ewxYBrkZr4D980ewKeskGpTVIryKCNVli0kP4vSPZdYGPV+vgpAgMBAAECggEAYpNGKx1qQTI0Ye5/bIHIA7HFdAAhQg5Pa/MaK+JPiY3So9Hp9xkkQSXzguFad4eK5g5XqHJ2lDQgJXBiwi/UlWE2fp7svsmn4EAxdCwTvNu33E9bxj8aqzdW+ct0Wpiu9Uj+7uBKo+WwYavZ95aJ064+MRDxyothdecDuI/4WAFu0wDZvEcNuIWmD5zCKznlDJcwUKnswbMYC80jNQARokSIXhKri+7nWi2Iis6zp6nLLW9Nshnz2QWZ0+woyV8JOCibERh3ZOQuqLxrJN0JBgPGF1QRJQpH4PFRTpOFeIgkxsIewKjD17tY3gAXYEmOaI+sYAU0g3K1SV0zyyb22QKBgQDRTKtGh9XN/lgzeeaXOLIMt+MlqpWNU7vx5jS0MgolF7l8ViBsxnO6RCvZp7hEVtJ6awtjcKPofXqG5k7rJTHOGbe5xWbILVBRZJxzRnpUF2SUZ7kTDoBCiKU2r/hFL542/b0hSWKmpDbIcrXIHIYkeeyqws6X0NRhWlyR0EYoxwKBgQDMQURfj5d25yTK3KEVyZTdpDGtEr03u+Zp8PzHUfNNiBlch/a1SL0cI8gDOs67DxB3dENIsNVIVtMD4PBumnhDIIAY9ub9CfMEIWKvYDqNeFH+On3a6SS8V7wwXe3hmEQI1lhesGb1DGTxp2YSZtCRh1KDL1SMPhacQ5Ijra1HjwKBgD6L++9C7qj1pmQTvM5aOhNE7NO3y6OFb3gVxJBJF/EHyv2WO3BmSfVTJAuSbKW3kITW8ntQERuMTA5+Sv1Iiz69PsN8c3/E0ujP+VUVrb7tkrgq2wsP3VKExWK3aak7Vru2TkvhQ2LrVhfG9/4KUCgca5aISvbhiGZje6BzrlPHAoGAGXjmOvigTf7Dct0S5bGu8/kfDNNyb3nqYTnhfEX3DqszZnu6Q59fnhvEPMrfrypQxmTkYzNSVeQmCqzJrzFqYAy0jOh1pAbSnKhBM1fP1Ya0yvymZ1996lMSsAF2whSIH4YDnUNog5TZHd3fd9oElrGGk7+aTqcLkM9IcVSecHcCgYEAz+3glJ0y6zTSltLueHYMbwEDogqXuoIGK3zJwQ2hIE1EKODA801gCYlqbphs2s40ujTewAz/kFr63PjEQxdr5Bc9wLr4GPBORXuf77gT5iXjeTOYk8OsTzC3pcCtok2SyplBEr5sv8lGX7VEQ5ZPjiMRh0pdo8EcoQDUMmiQCa8=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyxw96HmYw6tj+C3WWmzmkHOTBw1O/7zR8v1b193MBcHtqo+4qdyMkSXc2bPhfrzxM0j81dLULIgh6yVVwks3M0m0nV1HU++In76r6F53sFr5EXx2qJaoy2E45NzZlIxZGp5RcWI/FkAcY8GdFw5J981783WLy5JEEa+q1uipHq+GiQP/6/fAGG8qrDHqMpKejorVkMdm7XccSDGZw5VCsJarX3yIfN7P1T9DosbzCPnvNToEDoqmEDo2FLmkPvR3MHtwYb7/exov4Z6pGP5FE7VsSgfZlROrcfL4dDrlV8nH1P8IcAU5NkmkKHmX890ZInZHL8wLY9XNhQEFNwlG7wIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://4qxu4f.natappfree.cc/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://www.moodshop.xyz/order/alipay/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.moodshop.xyz/order/aliPayMentCallback";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
}

