package com.v4java.lostandlove.constant;

import java.util.ArrayList;
import java.util.List;

public class ServletPathConst {
	
	/**
	 * 系统后台无限制路径
	 */
	public static final List<String> ADMIN_MAPPING_URLS = new ArrayList<String>();
	
	
	static{
		if(ADMIN_MAPPING_URLS.size()<=0){
			ADMIN_MAPPING_URLS.add("/login.do");
		}

	}

}
