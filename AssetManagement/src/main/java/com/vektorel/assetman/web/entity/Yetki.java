package com.vektorel.assetman.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author heroglu
 * 
 */
@Entity
@Table(name = "gnl_yetki")
public class Yetki extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long id;
	String adi;
	Boolean ins;
	Boolean upd;
	Boolean del;
	Boolean slct;

	@Id
	@SequenceGenerator(sequenceName = "seq_gnl_yetki", name = "seq_gnl_yetki", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_gnl_yetki", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ad")
	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public Boolean getIns() {
		return ins;
	}

	public void setIns(Boolean ins) {
		this.ins = ins;
	}

	public Boolean getUpd() {
		return upd;
	}

	public void setUpd(Boolean upd) {
		this.upd = upd;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public Boolean getSlct() {
		return slct;
	}

	public void setSlct(Boolean slct) {
		this.slct = slct;
	}

}
