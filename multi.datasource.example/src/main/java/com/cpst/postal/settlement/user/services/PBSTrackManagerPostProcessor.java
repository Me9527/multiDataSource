package com.cpst.postal.settlement.user.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class PBSTrackManagerPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		//System.out.println("sssssssssssssssssssssss========="+arg0);
		if(arg0 instanceof StartOnLoadService) {
			//System.out.println("sssssssssssssssssssssss=====222222222====");
			((StartOnLoadService)arg0).loadData(); //调用办法加载数据
		}
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		// TODO Auto-generated method stub
		return arg0;
	}

}
