package com.cpst.framework.base;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{

	private static final long serialVersionUID = 6262507728283263361L;
	//每页显示记录
	private int pageSize = 10;
	//总行数
	private long total = 0;	// totalPage = total/pageSize
	//当前页
	private int currPage = 1;	//currOffset = pageSize*(currPage-1)
	//查询结果
	private List<?> rs;
	
	//查询结果汇总
	private List<?> sumrs;
	//currOffset = pageSize*(currPage-1)

	public List<?> getSumrs() {
		return sumrs;
	}

	public void setSumrs(List<?> sumrs) {
		this.sumrs = sumrs;
	}

	public int getOffset() {
		int t = pageSize * (currPage-1);
		if(t < 0) return 0;
		else return t;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
		if(0 > this.total)
			this.total = 0;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<?> getRs() {
		return rs;
	}

	public void setRs(List<?> rs) {
		this.rs = rs;
	}

}
