package com.cpst.postal.settlement.user.model;

// Generated 2012-12-6 15:33:40 by Hibernate Tools 3.4.0.CR1


/**
 * BPermFuncPermissions generated by hbm2java
 */
public class BResourcePermission implements java.io.Serializable {

	private static final long serialVersionUID = -9116802216966046943L;
	private Integer seqId;
	private Integer RType;
	private String resources;
	private String permission;
	private String modulename;
	private String funcname;
//	private BigDecimal funcCode;
	private String funcdesc;
	private Integer orders;

	public BResourcePermission() {
	}

	/**  
	 */
	public Integer getSeqId() {
		return this.seqId;
	}

	/**
	 */
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}

	/**  
	 */
	public Integer getRType() {
		return this.RType;
	}

	/**
	 */
	public void setRType(Integer RType) {
		this.RType = RType;
	}

	/**  
	 */
	public String getResources() {
		return this.resources;
	}

	/**
	 */
	public void setResources(String resources) {
		this.resources = resources;
	}

	/**  
	 */
	public String getPermission() {
		return this.permission;
	}

	/**
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**  
	 */
	public String getModulename() {
		return this.modulename;
	}

	/**
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	/**  
	 */
	public String getFuncname() {
		return this.funcname;
	}

	/**
	 */
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

//	/**  
//	 */
//	public BigDecimal getFuncCode() {
//		return this.funcCode;
//	}
//
//	/**
//	 */
//	public void setFuncCode(BigDecimal funcCode) {
//		this.funcCode = funcCode;
//	}

	/**  
	 */
	public String getFuncdesc() {
		return this.funcdesc;
	}

	/**
	 */
	public void setFuncdesc(String funcdesc) {
		this.funcdesc = funcdesc;
	}

	/**  
	 */
	public Integer getOrders() {
		return this.orders;
	}

	/**
	 */
	public void setOrders(Integer orders) {
		this.orders = orders;
	}

}
