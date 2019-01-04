package com.moodshop.comm.pojo;

import java.util.List;
/**
 * EasyUI分页结果集
 * @author Administrator
 *
 */
public class EasyUIDataGridResult {
	private long total;//总记录数
	private List<?> rows;//结果集
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
