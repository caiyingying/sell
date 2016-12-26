package com.daoliuhe.sell.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Collections;
import java.util.Set;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private Set<String> encryptedProps = Collections.emptySet();

	public void setEncryptedProps(Set<String> encryptedProps) {
		this.encryptedProps = encryptedProps;
	}
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (encryptedProps.contains(propertyName)) {
			return EncryptUtil.decode(propertyValue);
		}
		return super.convertProperty(propertyName, propertyValue);
	}

}
