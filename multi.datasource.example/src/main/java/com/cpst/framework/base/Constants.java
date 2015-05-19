package com.cpst.framework.base;

public class Constants {

//	public static final String USER_SESSION_KEY = "ps-user-key";
//	public static final String CURR_USER_NAME = "currUserName";
	
	public static final String PAGE = "page";
	
	
	public static final String CURR_PAGE = "page";
	public static final String PAGE_SIZE = "rows";
	public static final String SORT_ORDER = "sortOrder";

	public static final String EXPORT_FILE_NAME = "exportFileName";
	public static final String XLS_CAPTION = "sheet1";
	public static final String EXPORT_TYPE = "exportType";
	public static final String COLUMN_TITLE = "columnTitle";
	public static final String COLUMN_FIELD_MAP = "columnFieldMap";
	
	public static final Integer UNKNOW_ZB_RELATION= Integer.MIN_VALUE;
	public static final String ORG = "org";
	public static final String DEST = "dest";
	
	/* 出口还是进口,用于快速输入辅助功能中对调原寄局与寄达局 */
	public static final String IN_OUT = "inOrOut";
	public static final String POSTAL_IN = "pin";
	public static final String POSTAL_OUT = "pout";
	
	/* 用户属于的行政级别,用于数据层权限控制  */
	public static final int HEAD_OFFICE_RANK = 10;
	public static final int PROVINCE_RANK = 20;
	public static final int CITY_RANK= 30;
	public static final int EXCHANGE_OFFICE_RANK = 40;
	

	/* 下面4个常量用户数据层权限控制，表示对一条数据的4种操作权限。 */
	public static final int DATA_READ = 1;
	public static final int DATA_ADD = 3;
	public static final int DATA_DELETE = 5;
	public static final int DATA_UPDATE = 7;
	
	public static final int DATA_ALL = 16;
	
	/* 总公司管理员,所有数据只能看不能改,为了防止产生多个where条件。 */
	public static final String HEAD_OFFICE_DATA_READ_ALL = "READ_ALL";
	
	/* 互换局下面负责录入数据的用户的标识,用之用数据的录入者才能对相应的录入数据作增删改查操作.  */
	public static final int DATA_ENTRY_STAFF = 1;
	
	/*速递用户类型 */
	public static final int EMS = 1;
	/*普邮用户类型 */
	public static final int COMMON = 2;

	/* 县级别 */
	public static final int COUNTY = 40;
	/* 错误行政级别 */
	public static final int IRREGULAR = 50;	
	
}
