package com.v4java.lostandlove.common;

import java.io.Serializable;

public class UpdateStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6983483988449652863L;

	private Integer opStatus ;
	
	private Integer status ;
	
	private String opStatusName ;
	
	private String statusName ;

	private String target ;

	private Integer isSuccess;

	

	public Integer getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(Integer opStatus) {
		this.opStatus = opStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOpStatusName() {
		return opStatusName;
	}

	public void setOpStatusName(String opStatusName) {
		this.opStatusName = opStatusName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	
}
