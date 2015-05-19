package com.cpst.framework.base.sqlutil;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class BaseCommonUtil {
	
	public static void log_str(String str){
		System.out.println("Sqlutil LOG:" + str);
	}
	
	public static String objec_json(Object map){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(map);
		return json;
	}
	
	public static String sql_inj(String str){
		if(str!=null){
			/*
			String inj_str = "'#--#;#exec#select#delete#update#truncate#declare";
			String inj_stra[] = inj_str.split("#");
			for (int i=0;i<inj_stra.length;i++ )
			{
				str = str.replaceAll(inj_stra[i],"");
			}
			*/
			str = str.replaceAll(".*([';]+|(--)+).*", "");
		}
		return str;
	}
	
	public static boolean str_val(String str){
		if(str != null && str.length()>0 && !str.equals("null")){
			return true;
		}else{
			return false;
		}
	}
	
	public static String sql_inj_str(String str){
		if(str_val(str)){
			String tstr = sql_inj(str);
			if(tstr.length()>0){
				return tstr;
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	
	public static Map<String, Object> getExportParm(BaseUtilModel baseUtilModel){
		
		Map<String, Object> result = new HashMap<String, Object>(20);
		
		result.put("exportType", baseUtilModel.getExportType());
		
		result.put("exportFileName", baseUtilModel.getExportFileName());
		
		result.put("exportHeader", baseUtilModel.getExportHeader());
		
		result.put("exportBody", baseUtilModel.getExportBody());
		
		result.put("exportTitle", baseUtilModel.getExportTitle());

		return result;
	}
	
	public static boolean isRowEven(int rowcount) {
        if (rowcount != 0 && (rowcount % 2) == 0) {
            return true;
        }

        return false;
    }

    public static boolean isRowOdd(int rowcount) {
        if (rowcount != 0 && (rowcount % 2) == 0) {
            return false;
        }

        return true;
    }

}
