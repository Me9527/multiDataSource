package com.cpst.postal.settlement.md.vo;

import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cpst.framework.base.exception.DAOLayerException;

public class BUserVO implements UserDetails, java.io.Serializable {

	private static final long serialVersionUID = 6286319915027760094L;
	private final Integer seqId;
	private final String loginName;
	private final String description;
	private final String password;
	private final String username;
	private final Set<GrantedAuthority> authorities;
	private final Map<Integer, Set<String>> dataPerms;
	private final int dataEntryStaffFlag;
	private final String realName;
	private final Integer emsOrCommon;
	private final String code;
	private final Integer positionsLevel;
	// private Set<BAccountDataAcl> accountDataAcl;
	// private List<String> dataPermissions;
	// private String identify;
	
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;
	
	public Set<String> getPerms(Integer dataAccessType) {
		Set<String> t = dataPerms.get(dataAccessType);
		if (null == t || t.size() == 0)
			throw new DAOLayerException("NO data access permissions.");
		return t;
	}

	public BUserVO(String loginName, String password,
			Set<GrantedAuthority> authorities,
			Map<Integer, Set<String>> dataPerms,
			int dataEntryStaffFlag,
			String realName, Integer emsOrCommon,
			Integer seqId, String code,
			Integer positionsLevel) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.username = loginName;
		this.authorities = authorities;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
		this.description = "";
		this.seqId = seqId;
		this.dataPerms = dataPerms;
		this.dataEntryStaffFlag = dataEntryStaffFlag;
		this.realName = realName;
		this.emsOrCommon = emsOrCommon;
		this.code = code;
		this.positionsLevel = positionsLevel;
	}

	public Integer getSeqId() {
		return seqId;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getDescription() {
		return description;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Map<Integer, Set<String>> getDataPerms() {
		return dataPerms;
	}

	public int getDataEntryStaffFlag() {
		return dataEntryStaffFlag;
	}

	public String getRealName() {
		return realName;
	}

	public Integer getEmsOrCommon() {
		return emsOrCommon;
	}

	public String getCode() {
		return code;
	}

	public Integer getPositionsLevel() {
		return positionsLevel;
	}

	
	// public Set<BAccountDataAcl> getAccountDataAcl() {
	// return accountDataAcl;
	// }
	//
	// public void setAccountDataAcl(Set<BAccountDataAcl> accountDataAcl) {
	// this.accountDataAcl = accountDataAcl;
	// }

}
