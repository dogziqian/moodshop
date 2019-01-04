package com.moodshop.comm.pojo;
/**
 * 图片上传返回结果
 * @author Administrator
 *
 */
public class PictureResult {
	private int error;//返回结果 0/1:0成功1失败
	private String url;//成功时返回的url
	private String message;//失败时返回的错误信息
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
