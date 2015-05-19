package com.cpst.postal.settlement.user.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.cpst.postal.settlement.user.model.BPermExchangeOffice;
import com.cpst.postal.settlement.user.model.BPermProvCity;
import com.cpst.postal.settlement.user.model.BRole;


public class StartOnLoadService {
	//public static Map<String,List> dictionaryInfoMap = new HashMap<String,List>();
	public static Map<Integer,BRole> rolesMap = new HashMap<Integer,BRole>();//角色Map
	public static Map<String,BPermExchangeOffice> exchangeOfficeMap = new HashMap<String,BPermExchangeOffice>();//互换局Map
	public static Map<String,BPermProvCity> provinceMap = new HashMap<String,BPermProvCity>();//省份编码Map
	public static Map<String,BPermProvCity> CityMap = new HashMap<String,BPermProvCity>();//省份编码Map
	//public static Map<String,List> UserRoleInfoMap = new HashMap<String,List>();
	private IStartLoadService startLoadService;//

	public IStartLoadService getStartLoadService() {
		return startLoadService;
	}
	public void setStartLoadService(IStartLoadService startLoadService) {
		this.startLoadService = startLoadService;
	}
	public void loadData() {
		List<BRole>   roles=startLoadService.findAllRole();//所有角色信息
		for(BRole role: roles){
			rolesMap.put(role.getSeqId(), role);
		}
		List<BPermExchangeOffice>   exchangeoffices=startLoadService.findAllpermexchangeoffice();
		for(BPermExchangeOffice ex: exchangeoffices){
			exchangeOfficeMap.put(ex.getBCode(), ex);
		}
		List<BPermProvCity>   provinces=startLoadService.findAllprovince();
		for(BPermProvCity prov: provinces){
			provinceMap.put(prov.getDistrictCode(), prov);
		}
		List<BPermProvCity>   citys=startLoadService.findAllcity();
		for(BPermProvCity city: citys){
			provinceMap.put(city.getDistrictCode(), city);
		}
		//dictionaryInfoMap.put("nationalMap", roles);
	}
	/**
	 * 根据角色编码得到角色名字
	 * @param excode
	 * @return
	 */
	public static String getRoleName(Integer roleid)
	{
		String roleName = "";
		if (rolesMap.containsKey(roleid))
		{
			roleName = ((BRole) rolesMap.get(roleid)).getName();
		}
		return roleName;
	}
	/**
	 * 得到所有的角色类型
	 * @param excode
	 * @return
	 */
	public static List<BRole> findAllRole()
	{
		List<BRole>   roles=new ArrayList<BRole>();
		roles.addAll(rolesMap.values());
		return roles;
	}
	
	/**
	 * 根据要求角色类型
	 * @param excode
	 * @return
	 */
	public static List<BRole> findRole(Integer dataEntryStaffFlag,Integer positionsLevel,Integer userId)
	{
		List<BRole>   rolesList=new ArrayList<BRole>();
		List<BRole>   roles=new ArrayList<BRole>();
		rolesList.addAll(rolesMap.values());
		//if(dataEntryStaffFlag==1){
			//BRole bselect1=new BRole();
			//roles = userService.findroles(userId+"");
			//bselect1.setPosCode("1");
			//sbselect1.setPosName("录入员");
		//	rs.add(bselect1);
		//}else
		if(positionsLevel==30||positionsLevel==40){
			
			 for(BRole role: rolesList){ 
				 //System.out.println("dddddddddddddddddddddddddddddd"+role.getSeqId());
				 if(role.getSeqId()!=4){
					 roles.add(role);
				 }
			 }
			 
			 return roles;
			 //for(BRole role: rolesList){
				//if(role.getSeqId()==4)
				//	rolesList.remove(role);
			//}
			//roles.addAll(broleMap.values());
		}else{
			 return rolesList;
		}
		
	}
	
	/**
	 * 根据互换局简拼得到互换局中文名字
	 * @param excode
	 * @return
	 */
	public static String getCexchangeName(String excode)
	{
		String CexchangeName = "";
		if (exchangeOfficeMap.containsKey(excode))
		{
			CexchangeName = ((BPermExchangeOffice) exchangeOfficeMap.get(excode)).getBCname();
		}
		return CexchangeName;
	}
	/**
	 * 根据互换局简拼得到互换局拼音
	 * @param excode
	 * @return
	 */
	public static String getEexchangeName(String excode)
	{
		String cexchangeName = "";
		if (exchangeOfficeMap.containsKey(excode))
		{
			cexchangeName = ((BPermExchangeOffice) exchangeOfficeMap.get(excode)).getBEname();
		}
		return cexchangeName;
	}
	/**
	 * 根据根据省份编码得到得到省份拼音
	 * @param excode
	 * @return
	 */
	public static String getProvinceName(String provcode)
	{
		String provinceName = "";
		if (provinceMap.containsKey(provcode))
		{
			provinceName = ((BPermProvCity) provinceMap.get(provcode)).getProvinceName();
		}
		return provinceName;
	}
	/**
	 * 根据根据地市编码得到得到省份名称
	 * @param excode
	 * @return
	 */
	public static String getCityName(String citycode)
	{
		String cityName = "";
		if (CityMap.containsKey(citycode))
		{
			cityName = ((BPermProvCity) CityMap.get(citycode)).getCityName();
		}
		return cityName;
	}
	
	
}
