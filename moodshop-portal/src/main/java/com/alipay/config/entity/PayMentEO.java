package com.alipay.config.entity;

/**
 * 描述：支付宝，付款对象；<BR/>
 * 
 * @author zl；<BR/>
 *
 */
public class PayMentEO {

	private String out_trade_no;
	private String total_amount;
	private String subject;
	private String body;
	private String product_code;

	public PayMentEO() {
		super();
	}

	/**
	 * @param out_trade_no：唯一订单号；
	 * @param total_amount：当前付款金额；
	 */
	public PayMentEO(String out_trade_no, String total_amount) {
		super();
		this.out_trade_no = out_trade_no;
		this.total_amount = total_amount;
		subject = "测试";
		body = "";
		product_code = "FAST_INSTANT_TRADE_PAY";
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

}
