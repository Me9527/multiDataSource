package com.cpst.postal.settlement.user.services;

import java.util.List;

import com.cpst.postal.settlement.user.model.BPermExchangeOffice;
import com.cpst.postal.settlement.user.model.BPermProvCity;
import com.cpst.postal.settlement.user.model.BPermUserRole;
import com.cpst.postal.settlement.user.model.BRole;

public interface IStartLoadService {
	public List<BRole>  findAllRole();
	public List<BPermExchangeOffice>  findAllpermexchangeoffice();
	public List<BPermProvCity>  findAllprovince();
	public List<BPermProvCity>  findAllcity();

}
