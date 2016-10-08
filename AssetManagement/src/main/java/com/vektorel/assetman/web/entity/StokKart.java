package com.vektorel.assetman.web.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author nadide
 * 
 */
@Entity
@Table(name="stk_skart")
public class StokKart extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1850765680460506656L;
	Long id;
	String tanim;
	BigDecimal birimFiyat;
	String seriNo;
	String barkod;
	Firma ureticiFirma;

	@Id
	@SequenceGenerator(sequenceName = "seq_stk_skart", name = "seq_stk_skart", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_stk_skart", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTanim() {
		return tanim;
	}

	public void setTanim(String tanim) {
		this.tanim = tanim;
	}

	public BigDecimal getBirimFiyat() {
		return birimFiyat;
	}

	public void setBirimFiyat(BigDecimal birimFiyat) {
		this.birimFiyat = birimFiyat;
	}

	public String getSeriNo() {
		return seriNo;
	}

	public void setSeriNo(String seriNo) {
		this.seriNo = seriNo;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="uretici_firma_id")	
	public Firma getUreticiFirma() {
		return ureticiFirma;
	}

	public void setUreticiFirma(Firma ureticiFirma) {
		this.ureticiFirma = ureticiFirma;
	}

}
