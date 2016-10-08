package com.vektorel.assetman.web.entity;

import java.math.BigDecimal;
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
 * @author emre
 * 
 */
@Entity
@Table(name = "gnl_yazilim")
public class Yazilim extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7697681559611184044L;

	public Yazilim() {
		super();
	}

	public Yazilim(Long id, String tanim, String lisansKod,
			BigDecimal lisansFiyati, Date lisansBaslamaTarihi,
			Date lisansBitisTarihi, Firma firma, String aciklama) {
		super();
		this.id = id;
		this.tanim = tanim;
		this.lisansKod = lisansKod;
		this.lisansFiyati = lisansFiyati;
		this.lisansBaslamaTarihi = lisansBaslamaTarihi;
		this.lisansBitisTarihi = lisansBitisTarihi;
		this.firma = firma;
		this.aciklama = aciklama;
	}

	Long id;
	String tanim;
	String lisansKod;
	BigDecimal lisansFiyati;
	Date lisansBaslamaTarihi;
	Date lisansBitisTarihi;
	Firma firma;
	String aciklama;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "seq_yazilim", sequenceName = "seq_yazilim")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_yazilim")
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

	public String getLisansKod() {
		return lisansKod;
	}

	public void setLisansKod(String lisansKod) {
		this.lisansKod = lisansKod;
	}

	public BigDecimal getLisansFiyati() {
		return lisansFiyati;
	}

	public void setLisansFiyati(BigDecimal lisansFiyati) {
		this.lisansFiyati = lisansFiyati;
	}

	@Temporal(TemporalType.DATE)
	public Date getLisansBaslamaTarihi() {
		return lisansBaslamaTarihi;
	}

	public void setLisansBaslamaTarihi(Date lisansBaslamaTarihi) {
		this.lisansBaslamaTarihi = lisansBaslamaTarihi;
	}

	@Temporal(TemporalType.DATE)
	public Date getLisansBitisTarihi() {
		return lisansBitisTarihi;
	}

	public void setLisansBitisTarihi(Date lisansBitisTarihi) {
		this.lisansBitisTarihi = lisansBitisTarihi;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="firma_id")	
	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
