package com.cpst.postal.settlement.md01.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cpst.framework.base.Constants;
import com.cpst.framework.base.Page;
import com.cpst.framework.base.ReqContextHolder;
import com.cpst.framework.base.exception.DAOLayerException;
import com.cpst.postal.settlement.md01.dao.IMd01CommonDAO;
import com.cpst.postal.settlement.md01.services.IMd01Service;
import com.cpst.postal.settlement.user.dao.IBUsersDAO;
import com.cpst.postal.settlement.user.model.BPermExchangeOffice;
import com.cpst.postal.settlement.user.model.BPermProvCity;
import com.cpst.postal.settlement.user.model.BResourcePermission;
import com.cpst.postal.settlement.user.model.BRole;
import com.cpst.postal.settlement.user.model.BUser;
import com.cpst.postal.settlement.user.util.PasswordEncoderUtil;

public class Md01ServiceImpl implements IMd01Service {
	private IMd01CommonDAO md01CommonDAO;
	private IBUsersDAO bUsersDAO;
	private JdbcTemplate jdbcTemplate;
	
    private  String defulPassword;

	public String getDefulPassword() {
		return defulPassword;
	}

	public void setDefulPassword(String defulPassword) {
		this.defulPassword = defulPassword;
	}

	public IMd01CommonDAO getMd01CommonDAO() {
		return md01CommonDAO;
	}

	public void setMd01CommonDAO(IMd01CommonDAO md01CommonDAO) {
		this.md01CommonDAO = md01CommonDAO;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addData(Object data) {
		md01CommonDAO.addData(data);

	}

	@Override
	public void update(Object data) {
		md01CommonDAO.update(data);

	}

	@Override
	public void delByIds(Class<?> clazz, Integer[] seqIds) {
		String in = "";
		for(Integer i: seqIds){
			in = in + i + ",";
		}
		in = in.substring(0, in.length()-1);
		in = " ( " + in +" ) ";
		StringBuffer sb = new StringBuffer(" update " + clazz.getSimpleName() + " m " +" set m.delFlag='2' " + " where m.seqId in " + in );	//2 表示删除
		md01CommonDAO.bulkUpdate(sb.toString());
		
//		for(Integer id: seqIds){
//			commonDAO.delById(clazz, id);
//		}

	}

	public void updatePasswd(Integer userId, String newPs){
//		String hql = "from BUser m where m.seqId = " + userId;
//		List<BUser> rs = (List<BUser>) commonDAO.find(hql);
//		BUser user = rs.get(0);
//		
		BUser user = (BUser)md01CommonDAO.load(BUser.class, userId);
		String np = PasswordEncoderUtil.md5Encode(newPs);
		user.setPassword(np);
		md01CommonDAO.update(user);
	}
	
	@Override
	public Page getPage(Class<?> clazz, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addAAAA(String s, Object data){
		if(s.equals("addEmsDataInputer")){
			addEmsDataInputer();
		}
		if(s.equals("delEmsDataInputer")){
			delEmsDataInputer();
		}
		
		if(s.equals("addEmsDataManager")){
			addEmsDataManager();
		}
		if(s.equals("delEmsDataManager")){
			delEmsDataManager();
		}
		
		if(s.equals("addEmsDataPronvinceManager")){
			addEmsDataPronvinceManager();
		}
		if(s.equals("delEmsDataPronvinceManager")){
			delEmsDataPronvinceManager();
		}
		
		
		if(s.equals("saveResourcePermission")){
			saveResourcePermission();
		}
		if(s.equals("saveRoles")){
			saveRoles();
		}
		
		if(s.equals("updateRoles")){
			updateRoles();
		}
	}
	
	private void updateRoles(){
		String hql = "from BRole m where m.seqId = 8";
		@SuppressWarnings("unchecked")
		List<BRole> roles = (List<BRole>) md01CommonDAO.find(hql);
		BRole role = (BRole)roles.get(0);
		hql = "from BResourcePermission ";
		@SuppressWarnings("unchecked")
		List<BResourcePermission> rps = (List<BResourcePermission>) md01CommonDAO.find(hql);
		Set<BResourcePermission> resourcePermissions = new HashSet<BResourcePermission>();
		resourcePermissions.addAll(rps);
		role.setResourcePermissions(resourcePermissions);
		md01CommonDAO.update(role);
	}
	
	private void saveRoles(){
//		String hql = "from BRole ";
//		List<BRole> roles = (List<BRole>) commonDAO.find(hql);
//		try {
//			Util.saveRoles(roles);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	private void saveResourcePermission(){
		String hql = "from BResourcePermission ";
//		List<BResourcePermission> rps = (List<BResourcePermission>) commonDAO.find(hql);
//		try {
//			Util.savePerms(rps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
	private void addResourcePermission(){
		BResourcePermission rp = new BResourcePermission();
		rp.setFuncdesc("");
		rp.setFuncname("");
		rp.setModulename("");
//		rp.setOrders(orders);
		rp.setPermission("perm_");
		rp.setResources("");
		rp.setRType(10);
		md01CommonDAO.addData(rp);
	}
	
	@SuppressWarnings("unchecked")
	private void addEmsDataPronvinceManager(){
		String passwd = PasswordEncoderUtil.md5Encode("123");
		
		String hql = "from BRole m where m.name = '" + "速递管理员" + "'";
		List<BRole> role = (List<BRole>) md01CommonDAO.find(hql);
		if(role.size() > 1) 
			throw new DAOLayerException("addEmsDataInputer");
		
		hql = "from BPermProvCity";
		List<BPermProvCity> provs = (List<BPermProvCity>) md01CommonDAO.find(hql);
		Set<String> t = new HashSet<String>();
		int i = 1;
		for(BPermProvCity prov: provs){
			if(prov.getDistrictCode().substring(2).equals("0000")){
				BUser user = new BUser();
				if(t.contains(prov.getProvinceSim())){
					i++;
					user.setLoginName(prov.getProvinceSim().toLowerCase()+"-ems-prov-gly" + i);
				}
				else{
					t.add(prov.getProvinceSim());
					user.setLoginName(prov.getProvinceSim().toLowerCase()+"-ems-prov-gly");
				}
				user.setCode(prov.getDistrictCode().substring(0, 2));
				user.setDataEntryStaffFlag(0);
				user.setEMail(user.getLoginName()+"@chinapost.cn");
				user.setEmsOrCommon(1);
				user.setPassword(passwd);
				user.setPositionsLevel(Constants.PROVINCE_RANK);
				user.setRealName(prov.getProvinceName()+"省管理员");
				Set<BRole> roles = new HashSet<BRole>();
				roles.add(role.get(0));
				user.setRoles(roles);
				user.setTel("010-66668888");
				md01CommonDAO.addData(user);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void delEmsDataPronvinceManager(){
		
		String hql = "from BUser";
		List<BUser> users = (List<BUser>) md01CommonDAO.find(hql);

		for(BUser u: users){
			if(u.getRoles().size() == 1)
				if(u.getRoles().iterator().next().getName().equals("速递管理员")
						&& u.getPositionsLevel() == Constants.PROVINCE_RANK)
					md01CommonDAO.delById(BUser.class, u.getSeqId());
					
		}
	}
	
	@SuppressWarnings("unchecked")
	private void addEmsDataInputer(){
		String passwd = PasswordEncoderUtil.md5Encode("123");
		
		String hql = "from BRole m where m.name = '" + "速递录入员" + "'";
		List<BRole> role = (List<BRole>) md01CommonDAO.find(hql);
		if(role.size() > 1) 
			throw new DAOLayerException("addEmsDataInputer");
		
		hql = "from BPermExchangeOffice";
		List<BPermExchangeOffice> exchanges = (List<BPermExchangeOffice>) md01CommonDAO.find(hql);

		for(BPermExchangeOffice off: exchanges){
			BUser user = new BUser();
			user.setLoginName(off.getBCode().toLowerCase()+"-ems-lr");
			user.setCode(off.getBCode());
			user.setDataEntryStaffFlag(1);
			user.setEMail(user.getLoginName()+"@chinapost.com.cn");
			user.setEmsOrCommon(1);
			user.setPassword(passwd);
			user.setPositionsLevel(Constants.EXCHANGE_OFFICE_RANK);
			user.setRealName(off.getBCname()+"局录入员");
			Set<BRole> roles = new HashSet<BRole>();
			roles.add(role.get(0));
			user.setRoles(roles);
			user.setTel("010-66668888");
			md01CommonDAO.addData(user);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void delEmsDataInputer(){
		
		String hql = "from BUser";
		List<BUser> users = (List<BUser>) md01CommonDAO.find(hql);

		for(BUser u: users){
			if(u.getRoles().size() == 1)
				if(u.getRoles().iterator().next().getName().equals("速递录入员")
						&& u.getPositionsLevel() == Constants.EXCHANGE_OFFICE_RANK)
					md01CommonDAO.delById(BUser.class, u.getSeqId());
					
		}
	}
	
	@SuppressWarnings("unchecked")
	private void addEmsDataManager(){
		String passwd = PasswordEncoderUtil.md5Encode("123");
		
		String hql = "from BRole m where m.name = '" + "速递管理员" + "'";
		List<BRole> role = (List<BRole>) md01CommonDAO.find(hql);
		if(role.size() > 1) 
			throw new DAOLayerException("addEmsDataInputer");
		
		hql = "from BPermExchangeOffice";
		List<BPermExchangeOffice> exchanges = (List<BPermExchangeOffice>) md01CommonDAO.find(hql);

		for(BPermExchangeOffice off: exchanges){
			BUser user = new BUser();
			user.setLoginName(off.getBCode().toLowerCase()+"-ems-gly");
			user.setCode(off.getBCode());
			user.setDataEntryStaffFlag(0);
			user.setEMail(user.getLoginName()+"@chinapost.com.cn");
			user.setEmsOrCommon(1);
			user.setPassword(passwd);
			user.setPositionsLevel(Constants.EXCHANGE_OFFICE_RANK);
			user.setRealName(off.getBCname()+"局管理员");
			Set<BRole> roles = new HashSet<BRole>();
			roles.add(role.get(0));
			user.setRoles(roles);
			user.setTel("010-66668888");
			md01CommonDAO.addData(user);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void delEmsDataManager(){
		
		String hql = "from BUser";
		List<BUser> users = (List<BUser>) md01CommonDAO.find(hql);

		for(BUser u: users){
			if(u.getRoles().size() == 1)
				if(u.getRoles().iterator().next().getName().equals("速递管理员")
						&& u.getPositionsLevel() == Constants.EXCHANGE_OFFICE_RANK)
					md01CommonDAO.delById(BUser.class, u.getSeqId());
					
		}
	}
	
	private void addCommonDataInputer(String s, Object data){
		String hql = "from BRole m where m.name = '" + "普邮录入员 " + "'";
		List<BRole> rs = (List<BRole>) md01CommonDAO.find(hql);
		
		
	}
	
	public Page  findByFieldsToPage(Class<?> clazz,Map<String, ?> fields){
		
		Page page = ReqContextHolder.getContext().getPage();
		
		String loginName = (String) fields.get("loginName");
		String realName = (String) fields.get("realName");
		String provinceid = (String) fields.get("provinceid");
		String roleId = (String) fields.get("roleId");
		String code = (String) fields.get("code");
		String seqid = (String) fields.get("seqid");
		String zjseqid = (String) fields.get("zjseqid");
		String delFlagquery = (String) fields.get("delFlagquery");
		String cityCode = (String) fields.get("cityCode");
		String 	postionLevel=(String) fields.get("postionLevel");
		String 	provinceidquery=(String) fields.get("provinceidquery");
		
		
		StringBuffer hql = new StringBuffer("from " + clazz.getSimpleName() +" t where 1=1  ");  
	if(!(seqid==null) && !"".equals(seqid)&&!"0".equals(seqid)){
			
			hql.append(" and seqId="+seqid) ;
		}
		if(!(loginName==null) && !"".equals(loginName)){
			hql.append(" and   loginName like '" + loginName + "%'");
		}
		if(!(realName==null) && !"".equals(realName)){
			hql.append(" and   realName='" + realName + "'");
		}
		if(!(realName==null) && !"".equals(realName)){
			hql.append(" and   realName='" + realName + "'");
		}
		if(!(provinceid==null) && !"".equals(provinceid)){//省登陆
			//
			
			hql.append(" and ( code in (select distinct   substr(districtCode,0,4) as districtCode from BPermProvCity where degree='2' and districtCode like '"+provinceid+"%')") ;//地市的员工
			hql.append("  or code in (select BCode  from BPermExchangeOffice where BProvcode='"+provinceid+"')" ) ;//互换局的员工
			hql.append("  or ( code='"+provinceid+"' and dataEntryStaffFlag!=0 ) )) " ) ;//省普通员工
		}
		if(!(provinceidquery==null) && !"".equals(provinceidquery)){//总部查询
			//
			
			hql.append(" and ( code in (select distinct   substr(districtCode,0,4) as districtCode from BPermProvCity where degree='2' and districtCode like '"+provinceidquery+"%')") ;//地市的员工
			hql.append("  or code in (select BCode  from BPermExchangeOffice where BProvcode='"+provinceidquery+"')" ) ;//互换局的员工
			hql.append("  or ( code='"+provinceidquery+"'  ) )) " ) ;//省普通员工
		}
		if(!(roleId==null) && !"".equals(roleId)&&!"0".equals(roleId)){
			
			hql.append(" and seqId in (select userId from BPermUserRole where roleId="+roleId+")") ;
		}
		if(!(code==null) && !"".equals(code)){
			hql.append(" and  code='" + code + "'") ;
			//hql.append("  or code in (select BCode  from BPermExchangeOffice where BProvcode='"+provinceid+"'))" ) ;
		}
	
        if(!(zjseqid==null) && !"".equals(zjseqid)&&!"0".equals(zjseqid)){
			
			hql.append(" and seqId!="+zjseqid) ;
		}
        if(!(delFlagquery==null) && !"".equals(delFlagquery)){
			hql.append(" and  delFlag='" + delFlagquery + "'") ;
			//hql.append("  or code in (select BCode  from BPermExchangeOffice where BProvcode='"+provinceid+"'))" ) ;
		}
        if(!(cityCode==null) && !"".equals(cityCode)){
        	hql.append(" and  ( code in (select BCode  from BPermExchangeOffice where BCitycode='"+cityCode+"')" ) ;//互换局员工
        	hql.append("  or ( code='"+cityCode+"' and dataEntryStaffFlag!=0 ) )) " ) ;//市普通员工
			//hql.append(" and  delFlag='" + delFlagquery + "'") ;
			//hql.append("  or code in (select BCode  from BPermExchangeOffice where BProvcode='"+provinceid+"'))" ) ;
		}
        if(postionLevel.equals("40")){
        	hql.append(" and dataEntryStaffFlag=1 ") ;
        }
        
		List<Object> orderbyList=new ArrayList<Object>();
		String countHql = "select count(*) " + hql;
		long  total = bUsersDAO.findTotal(countHql); 

		List<?> rs = bUsersDAO.findByPage(hql.toString(), page.getOffset(), page.getPageSize(),orderbyList);
		page.setTotal(total); 
		page.setRs(rs);

		return page;
	}
	
	public List<BPermExchangeOffice>  bpermexchangeoffice(String  bcode){
	
		String 	hql = "from BPermExchangeOffice";
		if(bcode!=null&&!bcode.equals("")){
			hql+=" where BProvcode="+bcode;
		}
		List<BPermExchangeOffice> rs = (List<BPermExchangeOffice>) md01CommonDAO.find(hql);
		return rs;
		
	}

	/**
	 * 
	 */
	public List<BPermExchangeOffice>  bpermexchangeofficebyCityCode(String  citycode){
		
		String 	hql = "from BPermExchangeOffice";
		if(citycode!=null&&!citycode.equals("")){
			hql+=" where BCitycode="+citycode;
		}
		List<BPermExchangeOffice> rs = (List<BPermExchangeOffice>) md01CommonDAO.find(hql);
		return rs;
		
	}
	public List<BPermProvCity>  provinceInputer(){
		String hql = "select distinct   substr(districtCode,0,2) as districtCode,provinceName as provinceName from BPermProvCity  order by districtCode ";
		List<Object[]> rs = (List<Object[]>) md01CommonDAO.find(hql);
		List<BPermProvCity> list=new ArrayList();
		for(Object[] bj: rs){
			BPermProvCity bppc= new   BPermProvCity();
			if(bj[0]!=null&!bj[0].equals("")){
				bppc.setDistrictCode((String)bj[0]);
			}
			if(bj[1]!=null&!bj[1].equals("")){
				bppc.setProvinceName((String)bj[1]);
			}
			list.add(bppc);
		}
		
		return list;
		
	} 
	
	public List<BPermProvCity>  provinceInputerprovinceid(String provinceid){
		String hql = "select distinct   substr(districtCode,0,2) as districtCode,provinceName as provinceName from BPermProvCity where 1=1  ";
		if(provinceid!=null&&!provinceid.equals("")){
			hql+=" and  districtCode  like '" + provinceid + "%'  ";
			
		}
		List<Object[]> rs = (List<Object[]>) md01CommonDAO.find(hql);
		List<BPermProvCity> list=new ArrayList();
		for(Object[] bj: rs){
			BPermProvCity bppc= new   BPermProvCity();
			if(bj[0]!=null&!bj[0].equals("")){
				bppc.setDistrictCode((String)bj[0]);
			}
			if(bj[1]!=null&!bj[1].equals("")){
				bppc.setProvinceName((String)bj[1]);
			}
			list.add(bppc);
		}
		
		return list;
		
	} 
	public List<BPermProvCity>  cityInputer(){
		String hql = "select distinct   substr(districtCode,0,4) as districtCode,cityName as cityName from BPermProvCity where degree='2'";
		List<Object[]> rs = (List<Object[]>) md01CommonDAO.find(hql);
		List<BPermProvCity> list=new ArrayList();
		for(Object[] bj: rs){
			BPermProvCity bppc= new   BPermProvCity();
			if(bj[0]!=null&!bj[0].equals("")){
				bppc.setDistrictCode((String)bj[0]);
			}
			if(bj[1]!=null&!bj[1].equals("")){
				bppc.setCityName((String)bj[1]);
			}
			list.add(bppc);
		}
		return list;
	} 
	public List<BPermProvCity>  cityInputer(String provinceid){
		String hql = "select distinct   substr(districtCode,0,4) as districtCode,cityName as cityName from BPermProvCity where degree='2' and districtCode like '"+provinceid+"%' ";
		List<Object[]> rs = (List<Object[]>) md01CommonDAO.find(hql);
		List<BPermProvCity> list=new ArrayList();
		
		for(Object[] bj: rs){
			BPermProvCity bppc= new   BPermProvCity();
			if(bj[0]!=null&!bj[0].equals("")){
				bppc.setDistrictCode((String)bj[0]);
			}
			if(bj[1]!=null&!bj[1].equals("")){
				bppc.setCityName((String)bj[1]);
			}
			list.add(bppc);
		}
		
		return list;
		
	} 
	
	public List<BPermProvCity>  cityInputerbycityCode(String cityCode){
		String hql = "select distinct   substr(districtCode,0,4) as districtCode,cityName as cityName from BPermProvCity where degree='2' and districtCode like '"+cityCode+"%' ";
		List<Object[]> rs = (List<Object[]>) md01CommonDAO.find(hql);
		List<BPermProvCity> list=new ArrayList();
		
		for(Object[] bj: rs){
			BPermProvCity bppc= new   BPermProvCity();
			if(bj[0]!=null&!bj[0].equals("")){
				bppc.setDistrictCode((String)bj[0]);
			}
			if(bj[1]!=null&!bj[1].equals("")){
				bppc.setCityName((String)bj[1]);
			}
			list.add(bppc);
		}
		
		return list;
		
	}
	public List<BPermProvCity>  provinceInputeronseclect(){
		String hql = "select distinct   substr(districtCode,0,2) as districtCode,provinceName as provinceName from BPermProvCity order by districtCode ";
		List<Object[]> rs = (List<Object[]>) md01CommonDAO.find(hql);
		List<BPermProvCity> list=new ArrayList();
		
		BPermProvCity bppc2= new   BPermProvCity();
		bppc2.setDistrictCode("");
		bppc2.setProvinceName("全部");
		list.add(bppc2);
		for(Object[] bj: rs){
			BPermProvCity bppc= new   BPermProvCity();
			if(bj[0]!=null&!bj[0].equals("")){
				bppc.setDistrictCode((String)bj[0]);
			}
			if(bj[1]!=null&!bj[1].equals("")){
				bppc.setProvinceName((String)bj[1]);
			}
			list.add(bppc);
		}
		
		return list;
		
	} 
	public List<BRole>  roleInputer(){
		String hql = "from BRole m ";
		List<BRole> rs = (List<BRole>) md01CommonDAO.find(hql);
		return rs;
		
	}
	public List<BResourcePermission>  bResourcePermissionInputer(){
		String hql = "from BResourcePermission m ";
		List<BResourcePermission> rs = (List<BResourcePermission>) md01CommonDAO.find(hql);
		return rs;
		
	}
	public List<BResourcePermission>  bResourcePermissionInputer(String seqId){
		String hql = "from BResourcePermission m where seqId in ("+seqId+")";
		List<BResourcePermission> rs = (List<BResourcePermission>) md01CommonDAO.find(hql);
		return rs;
		
	}
	public List<BRole>  rolefindroleid(String roleid ){
	    String rolefirst=roleid.substring(0,1);
	    if(rolefirst.equals(",")){
	    	roleid=roleid.substring(1);
	    }
		String hql = "from BRole m  where seqId in ("+roleid+")";
		List<BRole> rs = (List<BRole>) md01CommonDAO.find(hql);
		return rs;
		
	}
	public IBUsersDAO getbUsersDAO() {
		return bUsersDAO;
	}

	public void setbUsersDAO(IBUsersDAO bUsersDAO) {
		this.bUsersDAO = bUsersDAO;
	}
	
	public void delByIds(Class<?> clazz, Serializable ids[]){
		for(Serializable id: ids)
			bUsersDAO.delById(clazz, id);
	}
	
	public 	List<BPermExchangeOffice>  loadExchange(String bcode ){
		String 	hql = "from BPermExchangeOffice";
		if(bcode!=null&&!bcode.equals("")){
			hql+=" where BCode='"+bcode+"'";
		}
		List<BPermExchangeOffice> rs = (List<BPermExchangeOffice>) md01CommonDAO.find(hql);
		return rs;
		
	}
	public 	List<BRole>  findroles(String userId ){
		String 	hql = "from BRole m  where seqId in (select roleId from BPermUserRole where userId="+userId+")";
		List<BRole> rs = (List<BRole>) md01CommonDAO.find(hql);
		return rs;
	}
	/**
	 * 省分公司以下数据
	 */
	public List<BRole>  findroleprovunder(){
		String hql = "from BRole m where seqId not in (4,5)";
		List<BRole> rs = (List<BRole>) md01CommonDAO.find(hql);
		return rs;		
	}
	public List<BUser>  findbyUserLoginName(String loginName){
		String hql = "from BUser m where loginName='"+loginName+"'";
		List<BUser> rs = (List<BUser>) md01CommonDAO.find(hql);
		return rs;		
	}
	public List<BUser>  findbyUpdateUserLoginName(String loginName,Integer loginSeqId){
		String hql = "from BUser m where loginName='"+loginName+"' and seqId not in ("+loginSeqId+")";
		List<BUser> rs = (List<BUser>) md01CommonDAO.find(hql);
		return rs;		
	}
	public BUser loadBUSer(Integer userId){
//		String hql = "from BUser m where m.seqId = " + userId;
//		List<BUser> rs = (List<BUser>) commonDAO.find(hql);
//		BUser user = rs.get(0);
//		
		BUser user = (BUser)md01CommonDAO.load(BUser.class, userId);
		//String np = PasswordEncoderUtil.md5Encode(newPs);
		//user.setPassword(np);
		//commonDAO.update(user);
		return user;
	}
	public void addUser(BUser buser) {
		String np = PasswordEncoderUtil.md5Encode(this.defulPassword);
		buser.setPassword(np);
		md01CommonDAO.addData(buser);

	}
	
	public void updateBefulPasswordByIds(Class<?> clazz, Integer[] seqIds) {
		String in = "";
		for(Integer i: seqIds){
			in = in + i + ",";
		}
		in = in.substring(0, in.length()-1);
		in = " ( " + in +" ) ";
		String np = PasswordEncoderUtil.md5Encode(this.defulPassword);
		StringBuffer sb = new StringBuffer(" update " + clazz.getSimpleName() + " m " +" set m.password='"+np+"' " + " where m.seqId in " + in );	//2 表示删除
		md01CommonDAO.bulkUpdate(sb.toString());
		
//		for(Integer id: seqIds){
//			commonDAO.delById(clazz, id);
//		}

	}
	
	/**
	 * 根据员工级别找到相应的权限
	 */
	public List<BRole>  findrolebyLevelId(Integer levelId){
		String hql = "from BRole m where seqId  in (select roleId from BPermRolesLevel where positionsLevel>"+levelId+"  or (positionsLevel="+levelId+" and roleTypeId!=0) )";
		List<BRole> rs = (List<BRole>) md01CommonDAO.find(hql);
		return rs;		
	}
}
