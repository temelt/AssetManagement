package com.vektorel.assetman.web.entity;

public class Firma extends BaseEntity {

	Long id;
	String adi;	
	Kisi kisi;
	String vergiNo;	
	FirmaTip firmaTip;
	Adres adres;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	public String getVergiNo() {
		return vergiNo;
	}

	public void setVergiNo(String vergiNo) {
		this.vergiNo = vergiNo;
	}

	public FirmaTip getFirmaTip() {
		return firmaTip;
	}

	public void setFirmaTip(FirmaTip firmaTip) {
		this.firmaTip = firmaTip;
	}

}
