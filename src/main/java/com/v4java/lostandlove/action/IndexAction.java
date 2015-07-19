package com.v4java.lostandlove.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.v4java.lal.service.IAdminRolePrivilegeService;
import com.v4java.lal.view.admin.AdminPrivilegeVO;
import com.v4java.lal.view.admin.AdminUserVO;
import com.v4java.lostandlove.common.BaseAction;
import com.v4java.lostandlove.common.Const;
import com.v4java.lostandlove.constant.SessionConst;


@Controller
@Scope("prototype")
public class IndexAction  extends BaseAction{

	@Autowired
	private IAdminRolePrivilegeService adminRolePrivilegeService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index() throws Exception{
		AdminUserVO  adminUserVO=(AdminUserVO) request.getSession().getAttribute(SessionConst.ADMIN_USER);
		StringBuffer adminPrivilegeHTML = null;
		List<String> adminUserPermissions = null;
				//adminUserVO = getAdminUser();
		if (null!=adminUserVO) {
			adminPrivilegeHTML = new StringBuffer();
			List<AdminPrivilegeVO> adminPrivileges= adminRolePrivilegeService.selectAdminRolePrivilegeByRoleId(adminUserVO.getAdminRoleId());
			if (null!=adminPrivileges&&adminPrivileges.size()>0) {
				adminUserPermissions = new ArrayList<String>();
				for (AdminPrivilegeVO adminPrivilegeVO : adminPrivileges) {
					if (adminPrivilegeVO.getParentId()==0) {
						adminPrivilegeHTML.append("<li class=\"treeview\"><a href=\"#\"><i class=\"fa fa-link\"></i> <span>"+adminPrivilegeVO.getName()+"</span> <i class=\"fa fa-angle-left pull-right\"></i></a><ul class=\"treeview-menu\" style=\"display: none;\">");
						for (AdminPrivilegeVO adminPrivilegeson : adminPrivilegeVO.getAdminPrivilegeVOs()) {
							if (adminPrivilegeVO.getId().compareTo(adminPrivilegeson.getId())!=0) {
								adminPrivilegeHTML.append("<li><a  href=\"");
								adminPrivilegeHTML.append(Const.LAL);
								adminUserPermissions.add(adminPrivilegeson.getUrl());
								adminPrivilegeHTML.append(adminPrivilegeson.getUrl());
								adminPrivilegeHTML.append("\">");
								adminPrivilegeHTML.append(adminPrivilegeson.getName());
								adminPrivilegeHTML.append("</a></li>");
							}else {
								adminPrivilegeson = null;
							}
						}
					}
					adminPrivilegeHTML.append("</ul></li>");
				}
				
			}
		}
		session.setAttribute("adminPrivilegeHTML", adminPrivilegeHTML.toString());
		session.setAttribute(SessionConst.ADMIN_USER_PERMISSIONS, adminUserPermissions);
		return "index";
	}
}
