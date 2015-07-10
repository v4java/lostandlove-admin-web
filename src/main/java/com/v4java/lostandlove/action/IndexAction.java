package com.v4java.lostandlove.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.v4java.lal.pojo.AdminPrivilege;
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
		if (adminUserVO==null) {
			adminUserVO = new AdminUserVO();
			adminUserVO.setAccount("admin");
			adminUserVO.setAdminRoleId(1);
			adminUserVO.setAdminRoleName("超级管理员");
		}
		
		StringBuffer adminPrivilegeHTML = null;
		//adminUserVO = getAdminUser();
		if (null!=adminUserVO) {
			adminPrivilegeHTML = new StringBuffer();
			List<AdminPrivilegeVO> adminPrivileges= adminRolePrivilegeService.selectAdminRolePrivilegeByRoleId(adminUserVO.getAdminRoleId());
			if (null!=adminPrivileges&&adminPrivileges.size()>0) {
				
				for (AdminPrivilegeVO adminPrivilegeVO : adminPrivileges) {
					if (adminPrivilegeVO.getParentId()==0) {
						adminPrivilegeHTML.append("<li class=\"treeview\"><a href=\"#\"><i class=\"fa fa-link\"></i> <span>"+adminPrivilegeVO.getName()+"</span> <i class=\"fa fa-angle-left pull-right\"></i></a><ul class=\"treeview-menu\" style=\"display: none;\">");
						for (AdminPrivilegeVO adminPrivilegeson : adminPrivilegeVO.getAdminPrivilegeVOs()) {
							if (adminPrivilegeVO.getId().compareTo(adminPrivilegeson.getId())!=0) {
								adminPrivilegeHTML.append("<li><a  href=\"");
								adminPrivilegeHTML.append(Const.LAL);
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
		return "index";
	}
	
/*	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index() throws Exception{
		AdminUserVO  adminUserVO=(AdminUserVO) request.getSession().getAttribute(SessionConst.ADMIN_USER);
		if (adminUserVO==null) {
			adminUserVO = new AdminUserVO();
			adminUserVO.setAccount("admin");
			adminUserVO.setAdminRoleId(1);
			adminUserVO.setAdminRoleName("超级管理员");
		}
		
		StringBuffer adminPrivilegeHTML = null;
		//adminUserVO = getAdminUser();
		if (null!=adminUserVO) {
			adminPrivilegeHTML = new StringBuffer();
			List<AdminPrivilegeVO> adminPrivileges= adminRolePrivilegeService.selectAdminRolePrivilegeByRoleId(adminUserVO.getAdminRoleId());
			if (null!=adminPrivileges&&adminPrivileges.size()>0) {
				
				List<AdminPrivilegeVO> adminPrivilegesParents = new ArrayList<AdminPrivilegeVO>();
				for (AdminPrivilegeVO adminPrivilegeVO : adminPrivileges) {
					if (adminPrivilegeVO.getParentId()==0) {
						adminPrivilegeHTML.append("<li class=\"treeview\"><a href=\"#\"><i class=\"fa fa-link\"></i> <span>"+adminPrivilegeVO.getName()+"</span> <i class=\"fa fa-angle-left pull-right\"></i></a><ul class=\"treeview-menu\" style=\"display: none;\">");
						List<AdminPrivilege> adminPrivilegesSons = new ArrayList<AdminPrivilege>();
						int parentId= adminPrivilegeVO.getId();
						for (AdminPrivilege adminPrivilegeson : adminPrivileges) {
							if (parentId == adminPrivilegeson.getParentId()) {
								adminPrivilegesSons.add(adminPrivilegeson);
								adminPrivilegeHTML.append("<li><a  href=\"");
								adminPrivilegeHTML.append(Const.LAL);
								adminPrivilegeHTML.append(adminPrivilegeson.getUrl());
								adminPrivilegeHTML.append("\">");
								adminPrivilegeHTML.append(adminPrivilegeson.getName());
								adminPrivilegeHTML.append("</a></li>");
							}
						}
					}
					adminPrivilegeHTML.append("</ul></li>");
					adminPrivilegesParents.add(adminPrivilegeVO);
				}
				
			}
		}
		session.setAttribute("adminPrivilegeHTML", adminPrivilegeHTML.toString());
		return "index";
	}*/
}
