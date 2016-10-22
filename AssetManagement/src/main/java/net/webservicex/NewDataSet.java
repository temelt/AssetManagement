package net.webservicex;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewDataSet",namespace="")
public class NewDataSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Table> data;
	
	@XmlElement(name="Table")
	public List<Table> getData() {
		return data;
	}
	public void setData(List<Table> data) {
		this.data = data;
	}
}
