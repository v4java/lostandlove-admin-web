package com.v4java.lostandlove.action.workFlow;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.v4java.lal.service.IWorkFlowService;
import com.v4java.lostandlove.action.admin.AdminUserAction;

@Controller
@Scope("prototype")
@RequestMapping("/workFlow")
public class WorkFlowAction {

	

	@Autowired
	private IWorkFlowService workFlowService;
	
	private static final Logger logger = Logger.getLogger(AdminUserAction.class);
	
	@RequestMapping(value = "/findWorkFlow  ",method = RequestMethod.GET)
	public String findWorkFlow(){
		return "page/workFlow/index";
	}
	
}
