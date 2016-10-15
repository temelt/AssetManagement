package com.vektorel.assetman.web.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
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
 * @author sefa
 * 
 */
@Entity
@Table(name="gnl_personel")
public class Personel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 224892664674659023L;
	Long id;
	String sicilNo;
	Kisi kisi;
	BigDecimal maas;

	@Id
	@SequenceGenerator(sequenceName = "seq_gnl_personel", name = "seq_gnl_personel", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_gnl_personel", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "sicil_no", unique = true, length = 30)
	public String getSicilNo() {
		return sicilNo;
	}

	public void setSicilNo(String sicilNo) {
		this.sicilNo = sicilNo;
	}

	@JoinColumn(name="kisi_id")
	@ManyToOne(optional=true)
	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	@Column(name = "maas", precision = 10, scale = 2)
	public BigDecimal getMaas() {
		return maas;
	}

	public void setMaas(BigDecimal maas) {
		this.maas = maas;
	}

}
