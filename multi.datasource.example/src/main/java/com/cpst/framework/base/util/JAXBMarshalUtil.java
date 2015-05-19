package com.cpst.framework.base.util;

import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

@SuppressWarnings("rawtypes")
public class JAXBMarshalUtil {

	private boolean formattedOutput = true;
	
	public boolean isFormattedOutput() {
		return formattedOutput;
	}

	public void setFormattedOutput(boolean formattedOutput) {
		this.formattedOutput = formattedOutput;
	}

	/**
	 * 使用时必须保证 list 中的对象必须是同一种类型.
	 */
	public void marshalList(List list, OutputStream output) throws Exception {
		if (null == list || !(list instanceof List))
			return;
		
		Class clazz[] = {JAXBMarshalWrapper.class, JAXBMarshalWrapper.class};
		if(list.size() > 0)
			clazz[1] = list.get(0).getClass();
		
		JAXBMarshalWrapper wrapper = new JAXBMarshalWrapper(list);
		doMarshal(wrapper, output, clazz);
	}

	public void manualMarshal(Object data, OutputStream output, Class...list) throws Exception {
		doMarshal(data, output, list);
		
	}
	
	private void doMarshal(Object data, OutputStream output, Class...list) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(list);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);
		
		jaxbMarshaller.marshal(data, output);
	}
	
}
