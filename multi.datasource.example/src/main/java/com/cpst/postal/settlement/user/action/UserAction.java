package com.cpst.postal.settlement.user.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.cpst.framework.base.BaseAction;
import com.cpst.framework.base.Page;
import com.cpst.framework.base.UserSessionUtil;
import com.cpst.framework.base.util.JsonUtil;
import com.cpst.postal.settlement.user.model.BPermExchangeOffice;
import com.cpst.postal.settlement.user.model.BPermProvCity;
import com.cpst.postal.settlement.user.model.BRole;
import com.cpst.postal.settlement.user.model.BUser;
import com.cpst.postal.settlement.user.model.Bselect;
import com.cpst.postal.settlement.user.services.IUserService;
import com.cpst.postal.settlement.user.util.PasswordEncoderUtil;
import com.cpst.postal.settlement.user.vo.BUserVO;

public class UserAction extends BaseAction {

	private Integer seqId;
	private Integer version;
	private String loginName;
	private String password;
	private String realName;
	private String tel;
	private String EMail;
	private Integer emsOrCommon;
	private Integer dataEntryStaffFlag;
	private Integer positionsLevel;
	private String code;
	private String description;

	private String oper;

	private IUserService userService;

	private String oldPs;
	private String newPs;
	private String newPs2;
	private String bCode;
    private String roleid;
    private String exchangeid;
    private Integer userseqId[];
    
    private String  provinceid;
   
    private String roleList[];
    
    private String rolecheckbox;
    
    private String delFlag;
    
    private  String delFlagquery;
    private String  positionsLevelSelect;

    
	public String getPositionsLevelSelect() {
		return positionsLevelSelect;
	}

	public void setPositionsLevelSelect(String positionsLevelSelect) {
		this.positionsLevelSelect = positionsLevelSelect;
	}

	public String getDelFlagquery() {
		return delFlagquery;
	}

	public void setDelFlagquery(String delFlagquery) {
		this.delFlagquery = delFlagquery;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRolecheckbox() {
		return rolecheckbox;
	}

	public void setRolecheckbox(String rolecheckbox) {
		this.rolecheckbox = rolecheckbox;
	}

	public String[] getRoleList() {
		return roleList;
	}

	public void setRoleList(String[] roleList) {
		this.roleList = roleList;
	}

	private String loginNamequery;
    private String realNamequery;
    private Integer provinceidquery;
    private String roleIdquery;
    
    public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	public String getRoleIdquery() {
		return roleIdquery;
	}

	public void setRoleIdquery(String roleIdquery) {
		this.roleIdquery = roleIdquery;
	}

	public Integer getProvinceidquery() {
		return provinceidquery;
	}

	public void setProvinceidquery(Integer provinceidquery) {
		this.provinceidquery = provinceidquery;
	}

	public String getLoginNamequery() {
		return loginNamequery;
	}

	public void setLoginNamequery(String loginNamequery) {
		this.loginNamequery = loginNamequery;
	}

	public String getRealNamequery() {
		return realNamequery;
	}

	public void setRealNamequery(String realNamequery) {
		this.realNamequery = realNamequery;
	}



	public Integer[] getUserseqId() {
		return userseqId;
	}

	public void setUserseqId(Integer[] userseqId) {
		this.userseqId = userseqId;
	}

	public String getExchangeid() {
		return exchangeid;
	}

	public void setExchangeid(String exchangeid) {
		this.exchangeid = exchangeid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getBCode() {
		return bCode;
	}

	public void setBCode(String bCode) {
		this.bCode = bCode;
	}

	private String msg = "please input password";
	
	public String changePasswd() throws IOException {
		if((null == oldPs ||oldPs.equals("") ) || null == newPs||newPs.equals("") || null == newPs2||newPs2.equals("")){
			msg = "密码不能为空。";
			return "userEdit";
		}
		
		if(newPs.trim().length() > 8){
			msg = "密码最大不能超过8个字符。";
			return "userEdit";
		}
		
		if(!newPs.equals(newPs2)){
			msg = "前后输入密码不一致。";
			return "userEdit";
		}
		
		BUserVO user = UserSessionUtil.getUser();
		if(!PasswordEncoderUtil.md5Encode(oldPs).equals(user.getPassword())){
			msg = "密码输入错误。";
			return "userEdit";
		}
		userService.updatePasswd(user.getSeqId(), newPs.trim());
		msg = "修改密码成功，重新登录将使用新密码。";
		
		return "userEdit";
	}
	
	public void abcdJson() throws IOException {

		JsonUtil.toStringShortDateFormat("abcdJson");
	}

	private void addRole() throws IOException {

	}

	private void addSupervisor() throws IOException {

	}

	public void createDataInputer() throws IOException {
		if (null == oper)
			return;
		userService.addAAAA(oper, null);

	}

	private void createProvinceManager() throws IOException {

	}

	private void createExchangeManager() throws IOException {

	}

	private void addExchangeOff() throws IOException {

	}

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public Integer getEmsOrCommon() {
		return emsOrCommon;
	}

	public void setEmsOrCommon(Integer emsOrCommon) {
		this.emsOrCommon = emsOrCommon;
	}

	public Integer getDataEntryStaffFlag() {
		return dataEntryStaffFlag;
	}

	public void setDataEntryStaffFlag(Integer dataEntryStaffFlag) {
		this.dataEntryStaffFlag = dataEntryStaffFlag;
	}

	public Integer getPositionsLevel() {
		return positionsLevel;
	}

	public void setPositionsLevel(Integer positionsLevel) {
		this.positionsLevel = positionsLevel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getOldPs() {
		return oldPs;
	}

	public void setOldPs(String oldPs) {
		this.oldPs = oldPs;
	}

	public String getNewPs() {
		return newPs;
	}

	public void setNewPs(String newPs) {
		this.newPs = newPs;
	}

	public String getNewPs2() {
		return newPs2;
	}

	public void setNewPs2(String newPs2) {
		this.newPs2 = newPs2;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void findJsonByFields() {
		
		
		Map<String,Object> fields = new HashMap<String,Object>();
		if(null != this.loginNamequery){
			fields.put("loginName",this.loginNamequery.toString().trim());
		}
		if(null != this.realNamequery){
			fields.put("realName",this.realNamequery.toString().trim());
		}
		if(null != this.provinceidquery){
			fields.put("provinceidquery",this.provinceidquery.toString().trim());
		}
		if(null != this.roleIdquery){
			fields.put("roleId",this.roleIdquery.toString().trim());
		}
		if(null != this.delFlagquery){
			fields.put("delFlagquery",this.delFlagquery.toString().trim());
		}
		BUserVO user = UserSessionUtil.getUser();
		Integer  postionLevel=user.getPositionsLevel();
		int     dataEntryStaffFlag=user.getDataEntryStaffFlag();//如果是录入员只能看见自己
		fields.put("postionLevel",postionLevel+"");
		if(dataEntryStaffFlag!=0){
			fields.put("seqid",user.getSeqId().toString().trim());
		}else {
			fields.put("zjseqid",user.getSeqId().toString().trim());
		}
			
		if(postionLevel==20){
			fields.put("provinceid",user.getCode().trim());
		}
		if(postionLevel==40){
			fields.put("code",user.getCode().trim());	
		}
		if(postionLevel==30){
			fields.put("cityCode",user.getCode().trim());		
		}
		
		Page page   =  userService.findByFieldsToPage(BUser.class, fields);
		try {
			JsonUtil.toPageStringShortDateFormat();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addJson() {
		try {
	     String rolecheckbox=this.rolecheckbox;
	     
	     String rolecheckbox2=rolecheckbox.substring(0, rolecheckbox.length()-1);//去掉最后一位，
	    
		List<BRole> roleList=userService.rolefindroleid(rolecheckbox2);
		
		//System.out.println("ssssssss====="+roleList.size());
		BUser buser = new BUser();
		//System.out.println(this.getEmsOrCommon());
		
		BeanUtils.copyProperties(buser, this);
		//buser.setDelFlag("1");	//非删除状态
		if(this.positionsLevel==10){//总部的citycode是10
			buser.setCode("0");
		}else if (this.positionsLevel==40){//40 是互换局，code 为互换局编码
			buser.setCode(this.exchangeid);
		}else if (this.positionsLevel==20){
		    buser.setCode(this.provinceid);//20省份编码
		}else {
			 buser.setCode(this.code);//30地市编码
		}
		buser.setCreateDate(new Date());
		//String np = PasswordEncoderUtil.md5Encode("123");
		//buser.setPassword(np);
		Set<BRole> roles = new HashSet<BRole>();
          roles.addAll(roleList);  
		  buser.setRoles(roles);
		userService.addUser(buser);
		
		JsonUtil.toStringShortDateFormat("addJson");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateJson()   {
		try {
			 String rolecheckbox=this.rolecheckbox;
		     String rolecheckbox2=rolecheckbox.substring(0, rolecheckbox.length()-1);//去掉最后一位，
		    
		//System.out.println("aaaaaaaaaaaaaa222222");
       List<BRole> roleList=userService.rolefindroleid(rolecheckbox2);
		
		//System.out.println("ssssssss====="+roleList.size());
		BUser buser =userService.loadBUSer(this.seqId);
		buser.setRealName(this.realName);
		buser.setTel(this.tel);
		buser.setEMail(this.EMail);
		buser.setDescription(this.description);
		buser.setDelFlag(this.delFlag);
		//System.out.println(this.getEmsOrCommon());
		//buser=userService.loadBUSer(this.seqId);
		//BeanUtils.copyProperties(buser, this);
		//buser.setDelFlag("1");	//非删除状态(启用)
		//if(this.positionsLevel==10){//总部的citycode是10
		//	buser.setCode("0");
		//}else if (this.positionsLevel==40){//40 是互换局，code 为互换局编码
			//buser.setCode(this.exchangeid);
		//}else if (this.positionsLevel==20){
		  //  buser.setCode(this.provinceid);//20省份编码
		//}else {
			// buser.setCode(this.code);//30地市编码
		//}	
		
		Set<BRole> roles = new HashSet<BRole>();
          roles.addAll(roleList);  
		  buser.setRoles(roles);
		userService.update(buser);
		//ioCn38.setIo343Zbh(0);
		//ioCn38.setIo343Zbno(0);
		//dataInputService.update(ioCn38);
		JsonUtil.toStringShortDateFormat("update");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void bRoleJson() throws Exception {
		//List<Bselect> rs =new ArrayList<Bselect>();
		BUserVO user = UserSessionUtil.getUser();
		List<BRole> rs=new ArrayList<BRole>();
		Integer  postionLevel=user.getPositionsLevel();
		int     dataEntryStaffFlag=user.getDataEntryStaffFlag();//如果是录入员
		if(dataEntryStaffFlag!=0){//不等于管理员
			//BRole bselect1=new BRole();
			 rs = userService.findroles(user.getSeqId()+"");
			//bselect1.setPosCode("1");
			//sbselect1.setPosName("录入员");
		//	rs.add(bselect1);
		}else {//if(postionLevel==10){
		
		// rs = userService.roleInputer();
		//}else
		//{
		rs=userService.findrolebyLevelId(postionLevel);
			
		}
			
		
		
		JsonUtil.toStringShortDateFormat(rs);
	}

	public void bRoleJsononselect() throws Exception {
		
		
		//List<BRole> rs = userService.roleInputer();
		BUserVO user = UserSessionUtil.getUser();
		List<BRole> rs=new ArrayList<BRole>();
		Integer  postionLevel=user.getPositionsLevel();
		int     dataEntryStaffFlag=user.getDataEntryStaffFlag();//如果是录入员
		if(dataEntryStaffFlag!=0){//不等于管理员
			//BRole bselect1=new BRole();
			 rs = userService.findroles(user.getSeqId()+"");
			//bselect1.setPosCode("1");
			//sbselect1.setPosName("录入员");
		//	rs.add(bselect1);
		}else {//if(postionLevel==10){
		
		// rs = userService.roleInputer();
		//}else
		//{
		rs=userService.findrolebyLevelId(postionLevel);
			
		}
		List<BRole> rs2=new ArrayList<BRole>();
		BRole brole=new BRole();
		brole.setSeqId(0);
		brole.setName("全部");
		rs2.add(brole);
		rs2.addAll(rs);
		JsonUtil.toStringShortDateFormat(rs2);
	}
	
	public void bCityInputerJson() throws Exception {
		List<BPermProvCity> rs=new ArrayList<BPermProvCity>();
		
		if(this.provinceid==null||this.provinceid.equals("")){			
		 rs = userService.cityInputer();		
		}else{
		 rs = userService.cityInputer(this.provinceid);	
		}
		BUserVO user = UserSessionUtil.getUser();
		Integer  postionLevel=user.getPositionsLevel();
		if(postionLevel==40){
			//rs=userService.loadExchange(user.getCode());
			//rs.add(exchange40);
		
		}else if(postionLevel==20){
			rs = userService.cityInputer(user.getCode());			
			//	}
		}else if(postionLevel==30){
			rs = userService.cityInputerbycityCode(user.getCode());		
		}
		else{
			if(this.provinceid==null||this.provinceid.equals("")){			
				 rs = userService.cityInputer();		
				}else{
				 rs = userService.cityInputer(this.provinceid);	
				}
			//rs = userService.bpermexchangeoffice(this.bCode);		
			//	}
		}
		JsonUtil.toStringShortDateFormat(rs);
	}
	
	public void bProvinceInputerJson() throws Exception {
		BUserVO user = UserSessionUtil.getUser();
		Integer  postionLevel=user.getPositionsLevel();
		List<BPermProvCity> rs=new ArrayList<BPermProvCity>();
		if(postionLevel==20){
			rs=userService.provinceInputerprovinceid(user.getCode());
			
		}else if(postionLevel==10){
			  rs = userService.provinceInputer();					
		}else if(postionLevel==30){
			  rs = userService.provinceInputerprovinceid(user.getCode().substring(0,2));					
		}//else if(postionLevel==40){
			//  rs = userService.provinceInputerprovinceid(user.getCode().substring(0,2));					
		//}
	  
		JsonUtil.toStringShortDateFormat(rs);
	}
	
	public void bProvinceInputerJsonSelect() throws Exception {
		BUserVO user = UserSessionUtil.getUser();
		Integer  postionLevel=user.getPositionsLevel();
		List<BPermProvCity> rs=new ArrayList<BPermProvCity>();
		if(postionLevel==20){
			rs=userService.provinceInputerprovinceid(user.getCode());
			
		}else if(postionLevel==10){
			  rs = userService.provinceInputeronseclect();	;					
		}else if(postionLevel==30){
			  rs = userService.provinceInputerprovinceid(user.getCode().substring(0,2));
		}		  
			  
			 
		//if(postionLevel==20){
		//	BPermProvCity exchange20=userService.provinceInputeronseclect(user.getCode());
		//	rs.add(exchange20);
		//}
		
		 //rs = userService.provinceInputeronseclect();		
		JsonUtil.toStringShortDateFormat(rs);
	}
	public void bpermExchangeInputerJson() throws Exception {
		List<BPermExchangeOffice> rs=new ArrayList<BPermExchangeOffice>();
		BUserVO user = UserSessionUtil.getUser();
		Integer  postionLevel=user.getPositionsLevel();
		if(postionLevel==40){
			rs=userService.loadExchange(user.getCode());
			//rs.add(exchange40);
		}else if(postionLevel==20){
			rs = userService.bpermexchangeoffice(user.getCode());		
			//	}
		}else if (postionLevel==30){
			rs = userService.bpermexchangeofficebyCityCode(user.getCode());
		}
			else{
			rs = userService.bpermexchangeoffice(this.bCode);		
			//	}
		}
	//if(postionLevel==40){
			
		//}
	    //if(postionLevel==10){
			
		
		JsonUtil.toStringShortDateFormat(rs);
	}
	public void dispatch()  {
		if("delete".equals(methodName)){
			this.deleteByIdJson();
		}else if("add".equals(methodName)){
			this.addJson();
		}else if("update".equals(methodName)){
			this.updateJson();
		}else{
			try {
				JsonUtil.toStringShortDateFormat("Can not find mehtod:" + methodName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void deleteByIdJson()   {
		//System.out.print("bbbbb====123=="+this.getUserseqId()[0]);
		userService.delByIds(BUser.class, this.getUserseqId());
		try {
			JsonUtil.toStringShortDateFormat("delete");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBefulPasswordByIdsById()   {
		//System.out.print("bbbbb====123=="+this.getUserseqId()[0]);
		userService.updateBefulPasswordByIds(BUser.class, this.getUserseqId());
		try {
			JsonUtil.toStringShortDateFormat("updateBefulPassword");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void bInputerpositionsLevelJson() throws Exception {
		List<Bselect> rs =new ArrayList<Bselect>();
		BUserVO user = UserSessionUtil.getUser();
		Integer  postionLevel=user.getPositionsLevel();
		if(postionLevel==20){
			Bselect bselect20=new Bselect();
			bselect20.setPosCode("20");
			bselect20.setPosName("省级");
			rs.add(bselect20);
			Bselect bselect30=new Bselect();
			bselect30.setPosCode("30");
			bselect30.setPosName("市级");
			rs.add(bselect30);
			Bselect bselect40=new Bselect();
			bselect40.setPosCode("40");
			bselect40.setPosName("互换局");
			rs.add(bselect40);
		}else if(postionLevel==30){
			Bselect bselect30=new Bselect();
			bselect30.setPosCode("30");
			bselect30.setPosName("市级");
			rs.add(bselect30);
			Bselect bselect40=new Bselect();
			bselect40.setPosCode("40");
			bselect40.setPosName("互换局");
			rs.add(bselect40);
			//fields.put("code",user.getCode().trim());	
		}else if(postionLevel==40){
			Bselect bselect40=new Bselect();
			bselect40.setPosCode("40");
			bselect40.setPosName("互换局");
			rs.add(bselect40);
			//fields.put("code",user.getCode().trim());	
		}else{
			Bselect bselect10=new Bselect();
			bselect10.setPosCode("10");
			bselect10.setPosName("总部");
			rs.add(bselect10);
			Bselect bselect20=new Bselect();
			bselect20.setPosCode("20");
			bselect20.setPosName("省级");
			rs.add(bselect20);
			Bselect bselect30=new Bselect();
			bselect30.setPosCode("30");
			bselect30.setPosName("市级");
			rs.add(bselect30);
			Bselect bselect40=new Bselect();
			bselect40.setPosCode("40");
			bselect40.setPosName("互换局");
			rs.add(bselect40);
		}
		JsonUtil.toStringShortDateFormat(rs);
	}
	public void bDataEntryStaffFlagJson() throws Exception {
		List<Bselect> rs =new ArrayList<Bselect>();
		BUserVO user = UserSessionUtil.getUser();
		Integer  postionLevel=user.getPositionsLevel();
		int     dataEntryStaffFlag=user.getDataEntryStaffFlag();//如果是录入员
		if(dataEntryStaffFlag==1||postionLevel==40){
			Bselect bselect1=new Bselect();
			bselect1.setPosCode("1");
			bselect1.setPosName("录入员");
			rs.add(bselect1);
		}else if(dataEntryStaffFlag==2){
			Bselect bselect2=new Bselect();
			bselect2.setPosCode("2");
			bselect2.setPosName("普通员工");
			rs.add(bselect2);
		}
		else
		{
			if(this.positionsLevelSelect!=null&&!this.positionsLevelSelect.equals("")){
	            if(this.positionsLevelSelect.equals("40")){
	            	Bselect bselect0=new Bselect();
					bselect0.setPosCode("0");
					bselect0.setPosName("管理员");
					rs.add(bselect0);
					Bselect bselect1=new Bselect();
					bselect1.setPosCode("1");
					bselect1.setPosName("录入员");
					rs.add(bselect1);
	            }else if (this.positionsLevelSelect.equals("10")||(this.positionsLevelSelect.equals("20")&&postionLevel==20)||(this.positionsLevelSelect.equals("30")&&postionLevel==30)){
	            	Bselect bselect2=new Bselect();
	    			bselect2.setPosCode("2");
	    			bselect2.setPosName("普通员工");
	    			rs.add(bselect2);
	            }
	            else{//positionsLevelSelect=20,30时
	            	Bselect bselect2=new Bselect();
	    			bselect2.setPosCode("2");
	    			bselect2.setPosName("普通员工");
	    			rs.add(bselect2);
	            	Bselect bselect0=new Bselect();
	    			bselect0.setPosCode("0");
	    			bselect0.setPosName("管理员");
	    			rs.add(bselect0);
	            }
				
		
				// rs = userService.cityInputer();		
			}else{
			Bselect bselect0=new Bselect();
			bselect0.setPosCode("0");
			bselect0.setPosName("管理员");
			rs.add(bselect0);
			Bselect bselect1=new Bselect();
			bselect1.setPosCode("1");
			bselect1.setPosName("录入员");
			rs.add(bselect1);
			Bselect bselect2=new Bselect();
			bselect2.setPosCode("2");
			bselect2.setPosName("普通员工");
			rs.add(bselect2);
				}
		}
		JsonUtil.toStringShortDateFormat(rs);
	}
	public void checkLoginNameRelationJson() throws Exception {
	String   loginName=this.getLoginName();
	Integer   seqid=this.getSeqId();
		List<BUser>   buserList=new ArrayList<BUser>();
		if(this.getMethodName().equals("update")){
		 buserList=userService.findbyUpdateUserLoginName(loginName,seqid);
		}else
		{
		 buserList=userService.findbyUserLoginName(this.loginName);
		}
		if(buserList.size()>0){
			JsonUtil.toStringShortDateFormat("false");
			return;
	    }else{
	    	JsonUtil.toStringShortDateFormat("true");
			return;
	   }
		
}
	public void bdelflag() throws Exception {
		List<Bselect> rs =new ArrayList<Bselect>();
		BUserVO user = UserSessionUtil.getUser();
		//Integer  postionLevel=user.getPositionsLevel();
	
			Bselect bselect=new Bselect();
			bselect.setPosCode("");
			bselect.setPosName("全部");
			rs.add(bselect);
			Bselect bselect0=new Bselect();
			bselect0.setPosCode("1");
			bselect0.setPosName("启用");
			rs.add(bselect0);
			Bselect bselect1=new Bselect();
			bselect1.setPosCode("2");
			bselect1.setPosName("未启用");
			rs.add(bselect1);
			
			
			
		JsonUtil.toStringShortDateFormat(rs);
	}
	
	public void bdelflagnoall() throws Exception {
		List<Bselect> rs =new ArrayList<Bselect>();
		BUserVO user = UserSessionUtil.getUser();
		//Integer  postionLevel=user.getPositionsLevel();
	
		
			Bselect bselect0=new Bselect();
			bselect0.setPosCode("1");
			bselect0.setPosName("启用");
			rs.add(bselect0);
			Bselect bselect1=new Bselect();
			bselect1.setPosCode("2");
			bselect1.setPosName("未启用");
			rs.add(bselect1);
			
			
			
		JsonUtil.toStringShortDateFormat(rs);
	}
}