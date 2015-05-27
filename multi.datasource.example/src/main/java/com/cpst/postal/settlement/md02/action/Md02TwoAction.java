package com.cpst.postal.settlement.md02.action;

import com.cpst.framework.base.BaseAction;
import com.cpst.framework.base.util.JsonUtil;
import com.cpst.postal.settlement.md02.services.IMd02Service;

public class Md02TwoAction extends BaseAction {
	
	private Integer id;
	private String loginName;
	private String password;
	private IMd02Service md02Service;
	
	public void testAJson() throws Exception {
		md02Service.addData(null);
		JsonUtil.toStringShortDateFormat("success");
	}
	
	public IMd02Service getMd02Service() {
		return md02Service;
	}
	
	public void setMd02Service(IMd02Service md02Service) {
		this.md02Service = md02Service;
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