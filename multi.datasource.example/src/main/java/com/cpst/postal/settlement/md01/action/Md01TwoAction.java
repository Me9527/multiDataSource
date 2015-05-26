package com.cpst.postal.settlement.md01.action;

import com.cpst.framework.base.BaseAction;
import com.cpst.framework.base.util.JsonUtil;
import com.cpst.postal.settlement.md01.services.IMd01Service;

public class Md01TwoAction extends BaseAction {
	
	private Integer id;
	private String loginName;
	private String password;
	private IMd01Service md01Service;
	
	public void testAJson() throws Exception {
		
		JsonUtil.toStringShortDateFormat("success");
	}
	
	public IMd01Service getMd01Service() {
		return md01Service;
	}
	
	public void setMd01Service(IMd01Service md01Service) {
		this.md01Service = md01Service;
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