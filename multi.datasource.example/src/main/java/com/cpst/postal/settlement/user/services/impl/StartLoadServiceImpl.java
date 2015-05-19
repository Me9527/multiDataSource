package com.cpst.postal.settlement.user.services.impl;

import java.util.ArrayList;
import java.util.List;


import com.cpst.postal.settlement.user.dao.ICommonDAO;
import com.cpst.postal.settlement.user.model.BPermExchangeOffice;
import com.cpst.postal.settlement.user.model.BPermProvCity;
import com.cpst.postal.settlement.user.model.BPermUserRole;
import com.cpst.postal.settlement.user.model.BRole;
import com.cpst.postal.settlement.user.services.IStartLoadService;

public class StartLoadServiceImpl implements IStartLoadService{
	private ICommonDAO commonDAO;

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	public List<BRole>  findAllRole(){
		String hql = "from BRole m ";
		List<BRole> rs = (List<BRole>) commonDAO.find(hql);
		return rs;
		
	}
	public List<BPermExchangeOffice>  findAllpermexchangeoffice(){
		
		String 	hql = "from BPermExchangeOffice";
		List<BPermExchangeOffice> rs = (List<BPermExchangeOffice>) commonDAO.find(hql);
		return rs;
	}
	
	public List<BPermProvCity>  findAllprovince(){
		String hql = "select distinct   substr(districtCode,0,2) as districtCode,provinceName as provinceName from BPermProvCity  order by districtCode ";
		List<Object[]> rs = (List<Object[]>) commonDAO.find(hql);
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
	public List<BPermProvCity>  findAllcity(){
		String hql = "select distinct   substr(districtCode,0,4) as districtCode,cityName as cityName from BPermProvCity where degree='2'";
		List<Object[]> rs = (List<Object[]>) commonDAO.find(hql);
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
	
	
}
