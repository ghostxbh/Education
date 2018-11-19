package com.senoops.utils.qiniuyun;

/**
 * 参数不合法异常
 * @author Axin
 *
 */
public class ParamIllegalException extends RuntimeException{

	public ParamIllegalException() {
		super();
	}

	public ParamIllegalException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParamIllegalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamIllegalException(String message) {
		super(message);
	}

	public ParamIllegalException(Throwable cause) {
		super(cause);
	}
	
	
	

}
