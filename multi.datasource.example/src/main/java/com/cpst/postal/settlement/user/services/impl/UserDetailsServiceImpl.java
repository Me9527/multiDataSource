package com.cpst.postal.settlement.user.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cpst.framework.base.Constants;
import com.cpst.framework.base.exception.DAOLayerException;
import com.cpst.postal.settlement.user.dao.ICommonDAO;
import com.cpst.postal.settlement.user.model.BPermExchangeOffice;
import com.cpst.postal.settlement.user.model.BResourcePermission;
import com.cpst.postal.settlement.user.model.BRole;
import com.cpst.postal.settlement.user.model.BUser;
import com.cpst.postal.settlement.user.vo.BUserVO;

public class UserDetailsServiceImpl implements UserDetailsService {
//	private IBUsersDAO bUsersDAO;
	private ICommonDAO commonDAO;
	
    private String rolePrefix = "perm_";
    
    
	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

//	public IBUsersDAO getbUsersDAO() {
//		return bUsersDAO;
//	}
//
//	public void setbUsersDAO(IBUsersDAO bUsersDAO) {
//		this.bUsersDAO = bUsersDAO;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		String hql = "from " + BUser.class.getSimpleName() + " m where m.delFlag = '1' and m.loginName = ?";
		List<BUser> rs = (List<BUser>) commonDAO.find(hql, username.trim());
		if (null == rs || rs.size() != 1)
			throw new UsernameNotFoundException("UsernameNotFoundException");

		BUser u = rs.get(0);
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		handlePerms(u, authorities);
		Map<Integer, Set<String>> dataPerms = handleDataACL(u);
		dataPerms = Collections.unmodifiableMap(dataPerms);
		BUserVO user = new BUserVO(u.getLoginName(), u.getPassword(), 
				authorities, dataPerms, u.getDataEntryStaffFlag(), u.getRealName(), u.getEmsOrCommon(),
				u.getSeqId(), u.getCode(), u.getPositionsLevel());
		return user;
	}

	private void handlePerms(BUser u, Set<GrantedAuthority> authorities) {
		for(BRole role: u.getRoles())
			for(BResourcePermission perm: role.getResourcePermissions()){
				if(perm.getRType() == 10){	//==10 表示url形式的资源权限,而不是业务层的(service层)
					SimpleGrantedAuthority author = new SimpleGrantedAuthority(rolePrefix+perm.getSeqId());
					authorities.add(author);
				}
			}
	}
	
	@SuppressWarnings("unchecked")
	private Map<Integer, Set<String>> handleDataACL(BUser u) {
		Map<Integer, Set<String>> dataPerms = new HashMap<Integer, Set<String>>();
		dataPerms.put(new Integer(Constants.DATA_READ), new HashSet<String>());
		dataPerms.put(new Integer(Constants.DATA_ADD), new HashSet<String>());
		dataPerms.put(new Integer(Constants.DATA_DELETE), new HashSet<String>());
		dataPerms.put(new Integer(Constants.DATA_UPDATE), new HashSet<String>());
		
		if(u.getPositionsLevel() == Constants.HEAD_OFFICE_RANK ){	//总公司管理员,所有数据只能看不能改.
			handleDataPerms(Constants.DATA_READ, Constants.HEAD_OFFICE_DATA_READ_ALL, dataPerms);
			
			if(u.getLoginName().equals("ie_data_Supervisor") 
					&& u.getDataEntryStaffFlag() == Constants.DATA_ENTRY_STAFF){
				handleDataPerms(Constants.DATA_ALL, u.getCode(), dataPerms);
				return dataPerms;
			}
		}
		
		if(u.getPositionsLevel() == Constants.PROVINCE_RANK ) {
			String hql = "select new BPermExchangeOffice(m.seqId, m.BCode) from BPermExchangeOffice m where m.BProvcode = '";
			hql = hql + u.getCode() + "'";
			
			List<BPermExchangeOffice> rs = (List<BPermExchangeOffice>) commonDAO.find(hql);
			if(null != rs){
				for(BPermExchangeOffice beo: rs)
					handleDataPerms(Constants.DATA_READ, beo.getBCode(), dataPerms);
			}
		}
		
		if(u.getPositionsLevel() == Constants.CITY_RANK ){
			String hql = "select new BPermExchangeOffice(m.seqId, m.BCode) from BPermExchangeOffice m where m.BCitycode = '";
			hql = hql + u.getCode() + "'";
			
			List<BPermExchangeOffice> rs = (List<BPermExchangeOffice>) commonDAO.find(hql);
			if(null != rs){
				for(BPermExchangeOffice beo: rs)
					handleDataPerms(Constants.DATA_READ, beo.getBCode(), dataPerms);
			}
		}
		
		
		if(u.getPositionsLevel() == Constants.EXCHANGE_OFFICE_RANK 
				&& u.getDataEntryStaffFlag() == Constants.DATA_ENTRY_STAFF){
			handleDataPerms(Constants.DATA_ALL, u.getCode(), dataPerms);
			return dataPerms;
		}
		
		if(u.getPositionsLevel() == Constants.EXCHANGE_OFFICE_RANK 
				&& u.getDataEntryStaffFlag() != Constants.DATA_ENTRY_STAFF){
			handleDataPerms(Constants.DATA_READ, u.getCode(), dataPerms);
			return dataPerms;
		}

		return dataPerms;
	}
	
	private void handleDataPerms(int dataPerm, String permIdf, Map<Integer, Set<String>> dataPerms) {
		switch(dataPerm){
		
		case Constants.DATA_READ :
			dataPerms.get(Constants.DATA_READ).add(permIdf);
			break;
		case Constants.DATA_ADD :
			dataPerms.get(Constants.DATA_ADD).add(permIdf);
			break;
		case Constants.DATA_DELETE :
			dataPerms.get(Constants.DATA_DELETE).add(permIdf);
			break;
		case Constants.DATA_UPDATE :
			dataPerms.get(Constants.DATA_UPDATE).add(permIdf);
			break;
		case Constants.DATA_ALL :
			dataPerms.get(Constants.DATA_READ).add(permIdf);
			dataPerms.get(Constants.DATA_ADD).add(permIdf);
			dataPerms.get(Constants.DATA_DELETE).add(permIdf);
			dataPerms.get(Constants.DATA_UPDATE).add(permIdf);
			break;
		default:
			throw new DAOLayerException("Data ACL config load error.");
		}
	}
}
