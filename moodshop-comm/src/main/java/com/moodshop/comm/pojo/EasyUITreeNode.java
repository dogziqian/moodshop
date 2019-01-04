package com.moodshop.comm.pojo;

/**
 * EasyUI树节点实体类
 * @author Administrator
 *
 */
public class EasyUITreeNode {
	private long id;//节点ID
	private String text;//节点名称
	private String state;//节点状态（用于判断是否为父节点  closed为父节点，open为子节点）
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
