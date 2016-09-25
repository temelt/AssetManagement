package com.vektorel.assetman.web.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * @author ttemel
 *
 */
@Entity
@Table(name="gnl_kisi")
public class Kisi extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long id;
	String ad;
	String soyad;
	String tc;
	Date dogumTarihi;
	Adres adres;
	Kullanici kullanici;

	@Id
	@SequenceGenerator(allocationSize=1,name="seq_kisi",sequenceName="seq_kisi")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_kisi")
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

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	@Temporal(TemporalType.DATE)
	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="kullanici_id")
	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

}
