package com.v4java.lostandlove.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.v4java.lal.view.admin.AdminUserVO;


public class BaseAction {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

//	protected HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

//	protected HttpServletResponse response =  ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	protected AdminUserVO getAdminUser() throws Exception{
		AdminUserVO adminUserVO = null;
		adminUserVO = (AdminUserVO) session.getAttribute("admin_user");
		return adminUserVO;
	}
	

/*	protected AdminUserVO getAdminUser() throws Exception {
		String id = JSONObject.fromObject(getCookieValue()).getString("id");
		String key = AdminRedisConst.ADMIN_USER + id;
		AdminUserVO adminUser  = null;
		if(!JedisUtil.getInstance().exists(key)){
			IAdminUserService adminUserServiceImpl = (IAdminUserService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminUserServiceImpl");
			adminUser =  adminUserServiceImpl.findAdminUserVOById(id);
			if(null!=adminUser){
				JedisUtil.getInstance().set(key, JSONObject.fromObject(adminUser).toString(),TimeConst.ONE_HOUR);
			}
		} else{
			adminUser = (AdminUserVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),AdminUserVO.class);
		}
		return adminUser;
	}
	
	public String getAdminUserId() throws Exception{
		return JSONObject.fromObject(getCookieValue()).getString("id");
	}
	
	protected void setAdminUser(AdminUserVO adminUser) throws Exception {
		String key = AdminRedisConst.ADMIN_USER + adminUser.getId();
		if(null!=adminUser){
			if(JedisUtil.getInstance().exists(key)){
				JedisUtil.getInstance().del(key);
			}
			JedisUtil.getInstance().set(key, JSONObject.fromObject(adminUser).toString());
		}
	}
	
	protected Map<String,AdminPrivilege> getPrivilege() throws Exception{
		return PrivilegeUtil.getAdminPrivilegeCode(getAdminUser().getAdmin_role_id()).get(getCurrentPrivilegeId());
	}
	
	protected String getCurrentPrivilegeId(){
		return request.getAttribute("privilegeId").toString();
	}

	protected String getCookieValue() throws Exception {
		return YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), AdminCookieConst.ADMINUSER_COOKIE_NAME));
	}
	
	protected void outputResult(String text) throws Exception{
		response.setContentType(CodeConst.TEXT_HTML);
		response.setCharacterEncoding(CodeConst.UTF_8);
		PrintWriter out = null;
		try{
			out = response.getWriter();
			out.println(text);
		} catch (Exception e){
			throw e;
		} finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
	}*/

	
}