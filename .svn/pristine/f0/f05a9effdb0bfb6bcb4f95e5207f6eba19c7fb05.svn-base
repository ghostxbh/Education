package com.senoops.utils;
/**
 * 通过此对象封装控制层返回的结果
 * @author 天大java
 *
 */
public class JsonResult {
	
	
	/**表示成功*/
	public static final int SUCCESS=1;
	/**表示失败*/
	public static final int ERROR=0;
	/**表示超时*/
	public static final int TIMEOUT=-1;
	
	/**状态 */
	private int state;
	/**信息(给用户提示的信息)*/
	private String message;
	/**具体业务数据*/
	private Object data;
	
	
	public JsonResult(){
		this.state=SUCCESS;
		this.message="OK";
	}
	
	public JsonResult(Object data){
		this();//调用当前类的构造方法
		this.data=data;
	}
	
	public JsonResult(Throwable e){
		this.state=ERROR;
		this.message=e.getMessage();
	}
	//设置状态码和提示信息
	public JsonResult(int state, String message) {
		super();
		this.state = state;
		this.message = message;
	}

	//设置状态码和数据
	public JsonResult(int state, Object data) {
		super();
		this.state = state;
		this.data = data;
	}

	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public static int getSuccess() {
		return SUCCESS;
	}


	public static int getError() {
		return ERROR;
	}
	
	
	
	
}
