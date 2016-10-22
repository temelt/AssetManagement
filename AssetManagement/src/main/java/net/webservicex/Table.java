package net.webservicex;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Table",namespace="")
public class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ulke;

	@XmlElement(name="Name")
	public String getUlke() {
		return ulke;
	}
	
	public void setUlke(String ulke) {
		this.ulke = ulke;
	}

}
