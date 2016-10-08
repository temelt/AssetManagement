package com.vektorel.assetman.web.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Adres implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4557554633029220551L;
	
	String acikAdres;
	String email;
	String tel;
	Yerlesim il;
	Yerlesim ilce;

	public String getAcikAdres() {
		return acikAdres;
	}

	public void setAcikAdres(String acikAdres) {
		this.acikAdres = acikAdres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@JoinColumn(name="il_id")
	@ManyToOne(optional=true)
	public Yerlesim getIl() {
		return il;
	}

	public void setIl(Yerlesim il) {
		this.il = il;
	}

	@JoinColumn(name="ilce_id")
	@ManyToOne(optional=true)
	public Yerlesim getIlce() {
		return ilce;
	}

	public void setIlce(Yerlesim ilce) {
		this.ilce = ilce;
	}

}
