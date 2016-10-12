package com.vektorel.assetman.web.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author saliha
 * 
 */
@Entity
@Table(name="gnl_proje")
public class Proje extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -122538802206133956L;
	Long id;
	String adi;
	String kod;
	Date baslamaTarihi;

	@Id
	@SequenceGenerator(sequenceName = "seq_gnl_proje", name = "seq_gnl_proje", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_gnl_proje", strategy = GenerationType.SEQUENCE)
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

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public Date getBaslamaTarihi() {
		return baslamaTarihi;
	}

	public void setBaslamaTarihi(Date baslamaTarihi) {
		this.baslamaTarihi = baslamaTarihi;
	}

}
