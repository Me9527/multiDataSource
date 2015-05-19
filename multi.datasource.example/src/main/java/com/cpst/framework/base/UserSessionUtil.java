package com.cpst.framework.base;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cpst.framework.base.exception.DAOLayerException;
import com.cpst.postal.settlement.user.vo.BUserVO;

public class UserSessionUtil {
	protected static final Log logger = LogFactory.getLog(UserSessionUtil.class);
	/* 得到当前登录用户的数据访问权限,用互换局名列表表示. 例如BJS，CTU*/
	/* 参数 dataAccessType 表示当前操作需要的数据访问权限类型(add,del,update,find四种)*/
	/* dataAccessType 类型必须为Constants中定义的四种类型之一， 例如Constants.DATA_READ */
	public static Set<String> getDataAccessPerm(Integer dataAccessType){
		BUserVO user = null;
		try {
			user = (BUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.info("Get user error from session.");
			throw new DAOLayerException("No Permissino to access data.");
		}
		if(null == user)
			throw new DAOLayerException("No Permissino to access data.");
		return user.getPerms(dataAccessType);
	}
	
	/* 得到当前登录用户的详细信息  */
	public static BUserVO getUser() {
		BUserVO user = null;
		try {
			user = (BUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.info("Get user error from session.");
		}
		return user;
	}
	
	/* select in 特供 */
	public static String getDataAccessPermForIn(Integer dataAccessType){
		
		BUserVO user = (BUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(null == user)
			throw new DAOLayerException("No Permissino to access data.");
		
		StringBuffer sb = new StringBuffer(" ( ");
		for(String s: user.getPerms(dataAccessType)){
			sb.append("'" + s + "',");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" ) ");
		
		return sb.toString();
	}
	
	public static String getCurrentUserName(){
		BUserVO user = null;
		try {
			user = (BUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.info("Get user error from session.");
		}
		if(null == user)
			return "";
		return user.getLoginName()+"-"+user.getRealName();
	}
	
	public static String getLoginName(){
		BUserVO user = null;
		try {
			user = (BUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.info("Get user error from session.");
		}
		if(null == user)
			return "";
		return user.getLoginName();
	}
	
}
