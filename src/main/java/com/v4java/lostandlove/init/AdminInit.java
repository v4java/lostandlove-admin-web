package com.v4java.lostandlove.init;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.v4java.lal.pojo.AdminPrivilege;
import com.v4java.lal.pojo.AdminRole;
import com.v4java.lal.pojo.AdminRolePrivilege;
import com.v4java.lal.pojo.AdminUser;
import com.v4java.lal.service.IAdminPrivilegeService;
import com.v4java.lal.service.IAdminRolePrivilegeService;
import com.v4java.lal.service.IAdminRoleService;
import com.v4java.lal.service.IAdminUserService;
import com.v4java.lostandlove.constant.AdminConst;



public class AdminInit {
	
	
	private static Element root = null;
	private static ApplicationContext context;
	private static List<Integer> adminPrivilegeIds = new ArrayList<Integer>();
	
	static{
		SAXReader sr = new SAXReader();
		try {
			InputStream in = AdminInit.class.getClassLoader().getResourceAsStream("config/admin.xml");
			Document doc = sr.read(in);
			root = doc.getRootElement();
			in.close();
			doc.clearContent();
		} catch (Exception e) {
		}
	}
	
	
	public static void init() throws Exception{
		context = new  ClassPathXmlApplicationContext("applicationContext.xml");
		initAdminPrivilege();
		initAdminUser();
	}

	@SuppressWarnings("unchecked")
	private static void initAdminPrivilege() throws Exception{
		IAdminPrivilegeService adminPrivilegeService = (IAdminPrivilegeService) context.getBean("adminPrivilegeService");
		
		//adminPrivilegeServiceImpl.deleteAdminPrivilegeAll();
		Element privileges = root.element("privilege");
		List<Element> admin_privileges_one = privileges.elements("privilege");
		List<Element> admin_privileges_two = null;
		List<Element> admin_privileges_three = null;
		
		int i = 0;
		int k = 0;
		int j = 0;
		
		List<AdminPrivilege> adminPrivileges = new ArrayList<AdminPrivilege>();
		AdminPrivilege adminPrivilege1 = null;
		AdminPrivilege adminPrivilege2 = null;
		AdminPrivilege adminPrivilege3 = null;
		//根权限
		for(Element admin_privilege_one : admin_privileges_one){
			adminPrivilege1 = new AdminPrivilege();
			adminPrivilege1.setName(admin_privilege_one.attributeValue("name"));
			adminPrivilege1.setUrl(admin_privilege_one.attributeValue("url"));
			adminPrivilege1.setCode(admin_privilege_one.attributeValue("code"));
			adminPrivilege1.setParentId(0);
			adminPrivilege1.setIsShow(1);
			adminPrivilege1.setSort(i);
			adminPrivilege1.setIsDelete(0);
			adminPrivilegeService.insertAdminPrivilegeAnduseGeneratedKeys(adminPrivilege1);;
			//adminPrivileges.add(adminPrivilege1);
			adminPrivilegeIds.add(adminPrivilege1.getId());
			admin_privileges_two = admin_privilege_one.elements("privilege");
			if(null!=admin_privileges_two&&admin_privileges_two.size()>0){
				//二级权限
				for(Element admin_privilege_two : admin_privileges_two){
					adminPrivilege2 = new AdminPrivilege();
					adminPrivilege2.setParentId(adminPrivilege1.getId());
					adminPrivilege2.setName(admin_privilege_two.attributeValue("name"));
					adminPrivilege2.setUrl(admin_privilege_two.attributeValue("url"));
					adminPrivilege2.setCode(admin_privilege_two.attributeValue("code"));
					adminPrivilege2.setIsShow(Integer.valueOf(admin_privilege_two.attributeValue("is_show")));
					adminPrivilege2.setSort(k);
					adminPrivilege2.setIsDelete(0);
					adminPrivilegeService.insertAdminPrivilegeAnduseGeneratedKeys(adminPrivilege2);;
					//adminPrivileges.add(adminPrivilege2);
					adminPrivilegeIds.add(adminPrivilege2.getId());
					admin_privileges_three = admin_privilege_two.elements("privilege");
					if(null!=admin_privileges_three&&admin_privileges_three.size()>0){
						//三级权限
						for(Element admin_privilege_three : admin_privileges_three){
							adminPrivilege3 = new AdminPrivilege();
							adminPrivilege3.setParentId(adminPrivilege2.getId());
							adminPrivilege3.setName(admin_privilege_three.attributeValue("name"));
							adminPrivilege3.setUrl(admin_privilege_three.attributeValue("url"));
							adminPrivilege3.setCode(admin_privilege_three.attributeValue("code"));
							adminPrivilege3.setIsShow(Integer.valueOf(admin_privilege_two.attributeValue("is_show")));
							adminPrivilege3.setSort(j);
							adminPrivilege3.setIsDelete(0);
							adminPrivileges.add(adminPrivilege3);
							adminPrivilegeIds.add(adminPrivilege3.getId());
							j++;
						}
						admin_privileges_three.clear();
						admin_privileges_three = null;
					}
					k++;
					j=0;
				}
				admin_privileges_two.clear();
				admin_privileges_two = null;
			}
			i++;
			k=0;
		}
		adminPrivilegeService.batchInsertAdminPrivilege(adminPrivileges);
		adminPrivileges.clear();
		adminPrivileges = null;
		
	}
	
	private static void initAdminUser() throws Exception{
		IAdminRoleService adminRoleServiceImpl = (IAdminRoleService) context.getBean("adminRoleService");
		IAdminUserService adminUserServiceImpl = (IAdminUserService) context.getBean("adminUserService");
		IAdminRolePrivilegeService adminRolePrivilegeServiceImpl = (IAdminRolePrivilegeService) context.getBean("adminRolePrivilegeService");
		
		//adminRoleServiceImpl.deleteAdminRoleAll();
		//adminUserServiceImpl.deleteAdminUserAll();
		
		Element role = root.element("role");
		Element admin_role = role.element("admin_role");
		AdminRole adminRole = new AdminRole();
		adminRole.setName(admin_role.attributeValue("name"));
		adminRole.setIsAdmin(AdminConst.IS_ADMIN_TRUE);
		adminRole.setIsDelete(AdminConst.DELETE_TRUE);
		adminRoleServiceImpl.insertAdminRoleAnduseGeneratedKeys(adminRole);
		
		
		Element user = root.element("user");
		Element admin_user = user.element("admin_user");
		AdminUser adminUser = new AdminUser();
		adminUser.setAdminRoleId(adminRole.getId());
		adminUser.setAccount(admin_user.attributeValue("account"));
		adminUser.setPassword(admin_user.attributeValue("password"));
		adminUser.setStatus(AdminConst.STATUS_TRUE);
		adminUser.setIsDelete(AdminConst.DELETE_TRUE);
		adminUserServiceImpl.insertAdminUserAnduseGeneratedKeys(adminUser);
		
		List<AdminRolePrivilege> adminRolePrivileges = new ArrayList<AdminRolePrivilege>();
		AdminRolePrivilege adminRolePrivilege = null;
		for(Integer pid : adminPrivilegeIds){
			adminRolePrivilege = new AdminRolePrivilege();
			adminRolePrivilege.setAdminRoleId(adminRole.getId());
			adminRolePrivilege.setAdminPrivilegeId(pid);
			adminRolePrivileges.add(adminRolePrivilege);
		}
		adminRolePrivilegeServiceImpl.batchInsertAdminRolePrivilege(adminRolePrivileges);
		adminRolePrivileges.clear();
		adminRolePrivileges = null;
		adminPrivilegeIds.clear();
		adminPrivilegeIds = null;
		adminUserServiceImpl = null;
		adminRoleServiceImpl = null;
	}
	
	public static void main(String[] args) throws Exception {
		init();
	}
	
}
