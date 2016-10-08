package com.vektorel.assetman.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 
 * @author hsn196
 *
 */
@Entity
@Table(name="gnl_firma")
public class Firma extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2304485445772172406L;
	Long id;
	String adi;	
	Kisi kisi;
	String vergiNo;	
	FirmaTip firmaTip;
	Adres adres;
	
	@Id
	@SequenceGenerator(sequenceName="seq_gnl_firma_id",name="seq_gnl_firma_id",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="seq_gnl_firma_id",strategy=GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="adi")
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

	@ManyToOne
	@JoinColumn(name="kisi_id")
	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	@Column(name="vergi_no")
	public String getVergiNo() {
		return vergiNo;
	}

	public void setVergiNo(String vergiNo) {
		this.vergiNo = vergiNo;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="firma_tip")	
	public FirmaTip getFirmaTip() {
		return firmaTip;
	}

	public void setFirmaTip(FirmaTip firmaTip) {
		this.firmaTip = firmaTip;
	}

}
