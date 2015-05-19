package com.cpst.framework.base.util;

import java.util.ArrayList;
import java.util.List;

public class ExportDataModel {
	private final String columnTitle;
	private final String columnFieldMap;
	private final List<String> columnFieldVaule = new ArrayList<String>();

	public ExportDataModel(String columnTitle, String columnFieldMap) {
		super();
		this.columnTitle = columnTitle;
		this.columnFieldMap = columnFieldMap;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public String getColumnFieldMap() {
		return columnFieldMap;
	}

	public List<String> getColumnFieldVaule() {
		return columnFieldVaule;
	}

	// private List<String> columnTitle; //原寄局，寄达局
	// private List<String> columnFieldMap; //org, dest

}
