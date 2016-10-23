package com.vektorel.assetman.web.ws;

import java.util.Date;

public class WsKisi {

	public WsKisi() {
	}
	
	
	public WsKisi(Long id, String ad, String soyad, Date dogumTarihi) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.dogumTarihi = dogumTarihi;
	}


	private Long id;
	private String ad;
	private String soyad;
	private Date dogumTarihi;
	private boolean cinsiyet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}


	public boolean isCinsiyet() {
		return cinsiyet;
	}


	public void setCinsiyet(boolean cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	
}
