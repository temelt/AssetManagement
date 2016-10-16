package com.vektorel.assetman.web.utilities;

import java.io.Serializable;

public class Istatistik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7239000016444597673L;
	String key;
	String value;

	public Istatistik() {
	}
	
	public Istatistik(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
