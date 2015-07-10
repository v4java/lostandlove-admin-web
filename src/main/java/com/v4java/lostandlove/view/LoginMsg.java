package com.v4java.lostandlove.view;

import java.io.Serializable;

public class LoginMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	
	private Integer failCount;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}
	
	
}
