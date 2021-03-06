package com.v4java.lostandlove.action.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.v4java.lal.pojo.AdminUser;
import com.v4java.lal.query.admin.AdminUserQuery;
import com.v4java.lal.service.IAdminUserService;
import com.v4java.lal.view.admin.AdminUserVO;
import com.v4java.lostandlove.common.BTables;
import com.v4java.lostandlove.common.DateUtil;
import com.v4java.lostandlove.common.UpdateStatus;
import com.v4java.lostandlove.constant.AdminConst;

@Controller
@Scope("prototype")
@RequestMapping("/adminUser")
public class AdminUserAction {

	@Autowired
	private IAdminUserService adminUserService;
	
	private static final Logger logger = Logger.getLogger(AdminUserAction.class);
	
	@RequestMapping(value = "/findAdminUser",method = RequestMethod.GET)
	public String findAdminUser(){
		return "page/admin/user/index";
		
	}

	@RequestMapping(value = "/findAdminUserJson",method = RequestMethod.POST)
	public @ResponseBody BTables<AdminUserVO> findAdminUserJson(@RequestBody AdminUserQuery adminUserQuery){
		BTables<AdminUserVO> bTables = new BTables<AdminUserVO>();
		try {
			List<AdminUserVO> adminUserVOs = adminUserService.selectAdminUserVO(adminUserQuery);
			Integer total = adminUserService.selectAdminUserVOCount(adminUserQuery);
			StringBuffer op = null;
			for (AdminUserVO adminUserVO : adminUserVOs) {
				op = new StringBuffer();
				adminUserVO.setIsDeleteName(AdminConst.DELETE_NAME[adminUserVO.getIsDelete()]);
				adminUserVO.setStatusName(AdminConst.STATUS_NAME[adminUserVO.getStatus()]);
				adminUserVO.setCreateTimeName(DateUtil.datetimeToStr(adminUserVO.getCreateTime()));
				//冻结/解冻 按钮
				op.append("<button name=\"updateStatus\"");
				//data-id
				op.append("data-name=\"status\" data-id=\"");
				op.append(adminUserVO.getId());
				op.append("\" ");
				//data-val
				op.append("data-status=\"");
				op.append(AdminConst.OP_STATUS[adminUserVO.getStatus()]);
				op.append("\" ");
				op.append("type=\"button\" op-url=\"updateAdminStatus.do\" class=\"btn btn-warning btn-flat\">");
				op.append(AdminConst.OP_STATUS_NAME[adminUserVO.getStatus()]);
				op.append("</button>");
				
				//删除/恢复 按钮
				op.append("<button name=\"updateStatus\" ");
				//data-id
				op.append("data-name=\"isDelete\"  data-id=\"");
				op.append(adminUserVO.getId());
				op.append("\" ");
				//data-val
				op.append("data-status=\"");
				op.append(AdminConst.OP_DELETE[adminUserVO.getIsDelete()]);
				op.append("\" ");
				
				op.append("type=\"button\"  op-url=\"updateAdminIsDlete.do\" class=\"btn btn-danger btn-flat\">");
				op.append(AdminConst.OP_DELETE_NAME[adminUserVO.getIsDelete()]);
				op.append("</button>");
				adminUserVO.setOperation(op.toString());
				op = null;
			}
			bTables.setRows(adminUserVOs);
			bTables.setTotal(total);
		} catch (Exception e) {
			logger.error("查询管理用户错误", e);
		}
		return bTables;
	}
	
	/**
	 * 更改管理员用户状态
	 * @return
	 */
	@RequestMapping(value = "/updateAdminStatus",method = RequestMethod.POST)
	public @ResponseBody UpdateStatus updateAdminStatus(@RequestBody AdminUser adminUser){
		UpdateStatus updateStatus = new UpdateStatus();
		try {
			int n  = adminUserService.updateAdminUserStatus(adminUser);
			updateStatus.setIsSuccess(n);
			if (n==1) {
				int x =adminUser.getStatus();
				updateStatus.setTarget("status");
				updateStatus.setStatus(x);
				updateStatus.setStatusName(AdminConst.STATUS_NAME[x]);
				updateStatus.setOpStatus(AdminConst.OP_STATUS[x]);
				updateStatus.setOpStatusName(AdminConst.OP_STATUS_NAME[x]);
			}
			updateStatus.setIsSuccess(n);
		} catch (Exception e) {
			logger.error("更改管理员用户状态错误", e);
		}
		
		return updateStatus;
	}
	
	
	/**
	 * 更改管理员用户是否删除
	 * @return
	 */
	@RequestMapping(value = "/updateAdminIsDlete",method = RequestMethod.POST)
	public @ResponseBody UpdateStatus updateAdminIsDlete(@RequestBody AdminUser adminUser){
		UpdateStatus updateStatus = new UpdateStatus();
		try {
			int n = adminUserService.updateAdminUserIsDelete(adminUser);
			updateStatus.setIsSuccess(n);
			if (n==1) {
				int x =adminUser.getIsDelete();
				updateStatus.setTarget("is_delete");
				updateStatus.setStatus(x);
				updateStatus.setStatusName(AdminConst.DELETE_NAME[x]);
				updateStatus.setOpStatus(AdminConst.OP_DELETE[x]);
				updateStatus.setOpStatusName(AdminConst.OP_DELETE_NAME[x]);
			}
		} catch (Exception e) {
			logger.error("更改管理员用户是否删除错误", e);
		}
		return updateStatus;
	}
}
