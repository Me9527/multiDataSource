package com.cpst.framework.base.sqlutil;

public class SqlSelectModel {
	
	private String sql_allstr;
	private String sql_count;
	private int sql_page;
	private int sql_rows;

	public SqlSelectModel(){
		sql_allstr = " ";
		sql_count = " ";
		sql_page = 1;
		sql_rows = 10;
	}

	
	public String getSql_count() {
		return sql_count;
	}


	public void setSql_count(String sql_count) {
		this.sql_count = sql_count;
	}


	public String getSql_allstr() {
		return sql_allstr;
	}


	public void setSql_allstr(String sql_allstr) {
		this.sql_allstr = sql_allstr;
	}


	public int getSql_page() {
		return sql_page;
	}
	public void setSql_page(int sql_page) {
		if(sql_page<1){sql_page =1;}
		this.sql_page = sql_page;
	}
	public int getSql_rows() {
		return sql_rows;
	}
	public void setSql_rows(int sql_rows) {
		this.sql_rows = sql_rows;
	}
	
	public String getSql_fullcount(String fullcount){
		if(fullcount==null){
			StringBuffer paginationSQL = new StringBuffer(" ");
			paginationSQL.append(" select count(*) from ( ");
			paginationSQL.append(sql_count);
			paginationSQL.append(" ) temp ");
			fullcount = paginationSQL.toString();
		}
		printstr(fullcount);
		return fullcount;
	}
	
	public String getSql_fullstr(String fullstr){
		if(fullstr==null){
			fullstr = sql_allstr;
		}
		printstr(fullstr);
		return fullstr;
	}
	
	public String[] getSql_fullstrs(String fullstr) {
		
		if(fullstr==null){
			fullstr = sql_allstr;
		}
		
		//分割符
		String[] sql_sqls = fullstr.split("@@--##--@@");
		
		for(int i=0;i<sql_sqls.length;i++){
			printstr(sql_sqls[i]);
		}
		
		return sql_sqls;
	}
	
	public String getSql_fulllist(String fulllist){
		if(fulllist==null){
			StringBuffer paginationSQL = new StringBuffer(" ");
			paginationSQL.append(sql_allstr);
			fulllist = paginationSQL.toString();
		}
		printstr(fulllist);
		return fulllist;
	}
	
	public String getSql_fullfy(String fullfy){
		if(fullfy==null){
			StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
			paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
			paginationSQL.append(sql_allstr);
			paginationSQL.append("　) temp where ROWNUM <= " + sql_page*sql_rows);
			paginationSQL.append(" ) WHERE　num > " + (sql_page-1)*sql_rows);
			fullfy = paginationSQL.toString();
		}
		printstr(fullfy);
		return fullfy;
	}
	
	public void printstr(String str){
		
		System.out.println("JdbcTemplate SQL:" + str);
		
	}
	
}
