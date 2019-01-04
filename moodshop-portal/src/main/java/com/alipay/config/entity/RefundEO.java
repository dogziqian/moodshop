package com.alipay.config.entity;

/**
 * 描述：支付宝，《退款》对象；<BR/>
 * 
 * @author zl；<BR/>
 *
 */
public class RefundEO {

	/**
	 * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no；
	 */
	private String out_trade_no;// 订单ID

	/**
	 * 支付宝交易号，和商户订单号不能同时为空
	 */
	private String trade_no;

	/** 本次退款请求，对应的退款金额 */
	private String refund_amount;

	/**
	 * 发起退款时，传入的退款原因
	 */
	private String refund_reason;

	/**
	 * 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
	 */
	private String out_request_no;

	public RefundEO() {
		super();
	}

	/**
	 * 
	 * @param out_trade_no：订单ID，
	 * @param trade_no：支付宝交易号；
	 * @param refund_amount：退款金额；
	 * @param refund_reason：退款原因；
	 * @param out_request_no：订单ID；
	 */
	public RefundEO(String out_trade_no, String trade_no, String refund_amount, String refund_reason, String out_request_no) {
		super();
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
		this.refund_amount = refund_amount;
		this.refund_reason = refund_reason;
		this.out_request_no = out_request_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getRefund_reason() {
		return refund_reason;
	}

	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}

}
