package com.vektorel.assetman.web.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "indexView")
@ViewScoped
public class IndexView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1152435119548646166L;

	private String message = "Merhaba Mehmet";
	

	@PostConstruct
	private void init(){
	}
	
	public void kaydet() {
		System.out.println(message );
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
