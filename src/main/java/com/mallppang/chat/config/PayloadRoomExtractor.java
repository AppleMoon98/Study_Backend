package com.mallppang.chat.config;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class PayloadRoomExtractor {
	static String tryExtractRoomId(Object bean) {
		if (bean == null)
			return null;
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors())
				if ("roomId".equals(pd.getName()) && pd.getReadMethod() != null) {
					Object value = pd.getReadMethod().invoke(bean);
					return value != null ? value.toString() : null;
				}
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	
	private PayloadRoomExtractor() {
		// null
	}
}
