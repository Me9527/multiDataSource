package com.cpst.postal.settlement.md1.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cpst.framework.base.Page;
import com.cpst.postal.settlement.user.model.BPermExchangeOffice;
import com.cpst.postal.settlement.user.model.BPermProvCity;
import com.cpst.postal.settlement.user.model.BResourcePermission;
import com.cpst.postal.settlement.user.model.BRole;
import com.cpst.postal.settlement.user.model.BUser;


public interface IUserService {

	void addData(Object data);
	
	void update(Object data);
	
	void delByIds(Class<?> clazz, Integer seqIds[]);
	
	Page getPage(Class<?> clazz, Map<String, Object> params);
	
	void updatePasswd(Integer userId, String newPs);
	
	public Page  findByFieldsToPage(Class<?> clazz,Map<String, ?> fields);
	void addAAAA(String s, Object data);
	
	public List<BRole>  roleInputer();
	public List<BPermProvCity>  cityInputer();
	public List<BPermProvCity>  cityInputer(String provinceid);
	public List<BPermExchangeOffice>  bpermexchangeoffice(String  bcode);
	
	public List<BRole>  rolefindroleid(String roleid);
	public List<BResourcePermission>  bResourcePermissionInputer(String seqId);
	
	public List<BResourcePermission>  bResourcePermissionInputer();
	
	public void delByIds(Class<?> clazz, Serializable ids[]);
	
	
	public List<BPermProvCity>  provinceInputeronseclect();
	public List<BPermProvCity>  provinceInputer();
	
	public List<BPermExchangeOffice>  loadExchange(String bcode);
	public List<BPermProvCity>  provinceInputerprovinceid(String provinceid);
	
	public 	List<BRole>  findroles(String userId );
	
	/**
	 * 省分公司以下数据
	 */
	public List<BRole>  findroleprovunder();
	
	public List<BUser>  findbyUserLoginName(String loginName);
	
	public List<BUser>  findbyUpdateUserLoginName(String loginName,Integer loginSeqId);
	
	public List<BPermExchangeOffice>  bpermexchangeofficebyCityCode(String  citycode);
	
	public List<BPermProvCity>  cityInputerbycityCode(String cityCode);
	
	public BUser loadBUSer(Integer userId);
	
	public void addUser(BUser buser);
	
	public void updateBefulPasswordByIds(Class<?> clazz, Integer[] seqIds);
	
	/**
	 * 根据员工级别找到相应的权限
	 */
	public List<BRole>  findrolebyLevelId(Integer levelId);
}
