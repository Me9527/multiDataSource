package com.cpst.framework.base.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.cpst.framework.base.Page;
import com.cpst.framework.base.ReqContextHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;

public class JsonUtil {
//	private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	public static void toStringShortDateFormat(Object obj) throws IOException {
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println(json);
//		out.flush();
		String json = GSON.toJson(obj);
		response.getWriter().write(json);
	}
	
	public static void toPageStringShortDateFormat() throws IOException {
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		
		Page page = ReqContextHolder.getContext().getPage();
		String json = GSON.toJson(page.getRs());
		json = "{\"total\":" + page.getTotal() + ",\"rows\":" + json + "}";
		
		response.getWriter().write(json);
	}
	//带合计的方法
	public static void toPageStringShortDateFormatTotal() throws IOException {
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		
		Page page = ReqContextHolder.getContext().getPage();
		
		String json = GSON.toJson(page.getRs());

		String json2 = GSON.toJson(page.getSumrs());
		json = "{\"total\":" + page.getTotal() + ",\"rows\":" + json + ",\"footer\":"+json2+"}";
		
		response.getWriter().write(json);
	}
}
