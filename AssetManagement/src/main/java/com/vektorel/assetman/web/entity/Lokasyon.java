package com.vektorel.assetman.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author ege
 * 
 */
@Entity
@Table(name="gnl_lokasyon")
public class Lokasyon extends BaseEntity {

	Long id;
	String tanim;
	String kod;

	@Id
	@SequenceGenerator(sequenceName="seq_gnl_lokasyon",name="seq_gnl_lokasyon",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="seq_gnl_lokasyon",strategy=GenerationType.SEQUENCE)
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

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

}
