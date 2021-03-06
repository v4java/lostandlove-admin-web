package com.v4java.lostandlove.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.v4java.lal.service.IAdminUserService;
import com.v4java.lal.view.admin.AdminUserVO;
import com.v4java.lostandlove.common.BaseAction;
import com.v4java.lostandlove.constant.LoginMsgConst;
import com.v4java.lostandlove.constant.SessionConst;
import com.v4java.lostandlove.view.LoginMsg;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(LoginAction.class);
	@Autowired
	private IAdminUserService adminUserService;

	public String goLogin(){
		return "login";
		
	}
	
	@ResponseBody 
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public LoginMsg login(@RequestParam("account") String account,@RequestParam("userPwd") String userPwd,@RequestParam("code") String code){
		LoginMsg loginMsg =  (LoginMsg) request.getSession().getAttribute(SessionConst.LOGIN_MSG);
		if (loginMsg == null) {
			loginMsg = new LoginMsg();
			loginMsg.setFailCount(0);
		}
		int count  = loginMsg.getFailCount();
		loginMsg.setFlag(false);
		if (count <=2) {
			String eCode = (String) request.getSession().getAttribute(SessionConst.CODE);
			
			try {
				AdminUserVO adminUserVO = adminUserService.selectAdminUserVOByAccount(account);
				if (adminUserVO!=null) {
					if (userPwd.equals(adminUserVO.getPassword())) {
						loginMsg.setFlag(true);
						loginMsg.setFailCount(0);
						loginMsg.setMsg(LoginMsgConst.ACCOUNT_SUCCESS);
						session.setAttribute(SessionConst.ADMIN_USER, adminUserVO);
					}else {
						loginMsg.setMsg(LoginMsgConst.PWD_ERROR);
					}
				}else{
					loginMsg.setMsg(LoginMsgConst.ACCOUNT_NO);
				}
			} catch (Exception e) {
				logger.error("用户登录查询错误", e);
			}
		}else{
			loginMsg.setMsg(LoginMsgConst.ACCOUNT_SUCCESS);
		}
		session.setAttribute(SessionConst.LOGIN_MSG, loginMsg);
		return loginMsg;
	}
	
	
}
