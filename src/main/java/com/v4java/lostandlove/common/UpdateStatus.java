package com.v4java.lostandlove.common;

import java.io.Serializable;

public class UpdateStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6983483988449652863L;

	private String opStatus ;
	
	private String status ;
	
	private String opStatusName ;
	
	private String statusName ;

	private String target ;

	private Integer isSuccess;

	public String getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
