package com.senoops.utils;

/**
 * 通过此对象封装控制层返回的JSON结果
 * 便于对控制层返回数据进行统一格式化
 */
public class JsonResult {
    public static final int SUCCESS=1;
    public static final int ERROR=0;
	/**服务端的响应状态  0:错误 1：成功*/
	private int state; 
	/**信息(给用户的提示)*/
	private String message;
	/**具体业务数据*/
	private Object data;
	
	public JsonResult() {
		this.state=SUCCESS;
		this.message="success";
	}
	
	public JsonResult(Object data) {
		this();
		this.data=data;
	}
	public JsonResult(Throwable e){
		this.state=ERROR;
		this.message=e.getMessage();
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
