package com.cpst.framework.base.util;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@SuppressWarnings("rawtypes")
@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "root")
public class JAXBMarshalWrapper {
//    @XmlElementWrapper(name = "channels")
//    @XmlElement(name = "channel")
	private List wrapper;

	public JAXBMarshalWrapper() {
	}

	public JAXBMarshalWrapper(List data) {
		super();
		this.wrapper = data;
	}
	
	public List getWrapper() {
		return wrapper;
	}

	public void setWrapper(List wrapper) {
		this.wrapper = wrapper;
	}
	


}
