package com.cpst.framework.base.sqlutil;

import java.io.UnsupportedEncodingException;

@SuppressWarnings("serial")
public class BaseUtilModel  implements java.io.Serializable {
	
	private int page;
	private int rows ;
	private String sort;
	private String order;
	private String exportType;
	private String exportFileName;
	private String exportTitle;
	private String exportHeader;
	private String exportBody;
	
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public String getExportFileName() {
		return exportFileName;
	}
	public void setExportFileName(String exportFileName) {
		try {
			this.exportFileName = java.net.URLDecoder.decode(exportFileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getExportTitle() {
		return exportTitle;
	}
	public void setExportTitle(String exportTitle) {
		try {
			this.exportTitle = java.net.URLDecoder.decode(exportTitle,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getExportHeader() {
		return exportHeader;
	}
	public void setExportHeader(String exportHeader) {
		try {
			this.exportHeader = java.net.URLDecoder.decode(exportHeader,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getExportBody() {
		return exportBody;
	}
	public void setExportBody(String exportBody) {
		try {
			this.exportBody = java.net.URLDecoder.decode(exportBody,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
