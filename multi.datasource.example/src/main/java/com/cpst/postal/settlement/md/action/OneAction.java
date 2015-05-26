package com.cpst.postal.settlement.md.action;

import com.cpst.framework.base.BaseAction;
import com.cpst.framework.base.util.JsonUtil;

public class OneAction extends BaseAction {
	
	private Integer id;
	private String loginName;
	private String password;
	
	public void testAJson() throws Exception {
		
		JsonUtil.toStringShortDateFormat("success");
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}