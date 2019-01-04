package com.moodshop.comm.pojo;
/**
 * 物流节点实体类
 * @author Administrator
 *
 */
public class Trace {
	private String acceptTime;
	
	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getAcceptStation() {
		return acceptStation;
	}

	public void setAcceptStation(String acceptStation) {
		this.acceptStation = acceptStation;
	}

	private String acceptStation;
	
	
}
