package com.moodshop.comm.pojo;

import java.util.List;

/**
 * 物流实体类
 * @author Administrator
 *
 */
public class Express {
	private String eBusinessID;//快递单号
	private String shipperCode;//快递编号（ZTO）
	private Boolean success;//是否成功
	private String logisticCode;
	private String state;//状态
	private List<Trace> traces;//物流节点
	public String geteBusinessID() {
		return eBusinessID;
	}
	public void seteBusinessID(String eBusinessID) {
		this.eBusinessID = eBusinessID;
	}
	public String getShipperCode() {
		return shipperCode;
	}
	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getLogisticCode() {
		return logisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Trace> getTraces() {
		return traces;
	}
	public void setTraces(List<Trace> traces) {
		this.traces = traces;
	}
	
	
}
