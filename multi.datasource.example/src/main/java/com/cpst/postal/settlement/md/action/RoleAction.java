package com.cpst.postal.settlement.md.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.cpst.framework.base.BaseAction;
import com.cpst.framework.base.Page;
import com.cpst.framework.base.util.JsonUtil;
import com.cpst.postal.settlement.user.model.BResourcePermission;
import com.cpst.postal.settlement.user.model.BRole;
import com.cpst.postal.settlement.user.services.IUserService;

public class RoleAction  extends BaseAction{

	private Integer seqId;
	private String name;
	private Integer usepermissions[];
 public Integer[] getUsepermissions() {
		return usepermissions;
	}

	public void setUsepermissions(Integer[] usepermissions) {
		this.usepermissions = usepermissions;
	}

String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	private Integer orders;
	private String memo;
	
	private IUserService userService;


    private String funcPermId;

	public String getFuncPermId() {
		return funcPermId;
	}

	public void setFuncPermId(String funcPermId) {
		this.funcPermId = funcPermId;
	}

	
	private String msg = "please input password";
	

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Integer getSeqId() {
		return seqId;
	}

	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void findJsonByFields() {
		Map<String,Object> fields = new HashMap<String,Object>();	
		Page page   =  userService.findByFieldsToPage(BRole.class, fields);
		try {
			JsonUtil.toPageStringShortDateFormat();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addJson() throws Exception {
		System.out.println("addJson==1=="+this.getName());
		System.out.println("addJson==2=="+this.getMemo());
		
		Integer[]   usepermissions=this.getUsepermissions();
		String usepermissionString="";
		for(int i=0;i<usepermissions.length;i++){
			if(i==usepermissions.length-1){
				usepermissionString+=usepermissions[i];
			}else{
			usepermissionString+=usepermissions[i]+",";
			}
		}
		List<BResourcePermission> resourceList=userService.bResourcePermissionInputer(usepermissionString);
		BRole brole = new BRole();
		BeanUtils.copyProperties(brole, this);
		brole.setName(this.name);
		Set<BResourcePermission> resourcePermissions= new HashSet<BResourcePermission>();
		resourcePermissions.addAll(resourceList);  
		brole.setResourcePermissions(resourcePermissions);
		userService.addData(brole);
		
		JsonUtil.toStringShortDateFormat("addJson");
	}
	 
	public void bResourcePermissionJson() throws Exception {
		
		
		List<BResourcePermission> rs = userService.bResourcePermissionInputer();
		
		JsonUtil.toStringShortDateFormat(rs);
	}

	
}
