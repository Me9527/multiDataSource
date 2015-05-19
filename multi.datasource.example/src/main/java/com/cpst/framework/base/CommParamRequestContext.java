package com.cpst.framework.base;

import java.util.HashMap;
import java.util.Map;

public final class CommParamRequestContext {
	private final Page page = new Page();
	//commParam保存通用的请求参数，例如查询时的 order 参数，即desc或asc参数。
	private final Map<String, String> commParam = new HashMap<String, String>();
	
	
	public Map<String, String> getCommParam() {
		return commParam;
	}

	public Page getPage() {
		return page;
	}
	
}
