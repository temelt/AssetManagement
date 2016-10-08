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
@Table(name = "gnl_rol")
public class Rol extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123432432L;

	Long id;
	String tanim;
	String kod;

	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(String tanim, String kod) {
		super();
		this.tanim = tanim;
		this.kod = kod;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@SequenceGenerator(sequenceName = "seq_gnl_rol", name = "seq_gnl_rol", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_gnl_rol", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	@Column(name = "tanim")
	public String getTanim() {
		return tanim;
	}

	public void setTanim(String tanim) {
		this.tanim = tanim;
	}

	@Column(name = "kod")
	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	@Override
	public String toString() {
		return "Rol [tanim=" + tanim + ", kod=" + kod + "]";
	}
}
