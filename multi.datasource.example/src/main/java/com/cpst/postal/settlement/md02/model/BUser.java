package com.cpst.postal.settlement.md02.model;

import java.util.Date;

public class BUser implements java.io.Serializable {

	private static final long serialVersionUID = -6005232084209889838L;
	private String delFlag;
	private Date createDate;
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
