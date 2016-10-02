package com.vektorel.assetman.web.entity;

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
 * @author heroglu
 *
 */
@Entity
@Table(name="gnl_rol_yetki")
public class RolYetki extends BaseEntity{

	Long id;
	Rol rol;
	Yetki yetki;
	
	
	public RolYetki() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolYetki(Rol rol, Yetki yetki) {
		super();
		this.rol = rol;
		this.yetki = yetki;
	}

	@Id
	@SequenceGenerator(sequenceName="seq_gnl_rol_yetki_id",name="seq_gnl_rol_yetki_id",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="seq_gnl_rol_yetki_id",strategy=GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(optional=true)
	@JoinColumn(name="rol_id")	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	@Column(name="yetki_id")
	public Yetki getYetki() {
		return yetki;
	}
	public void setYetki(Yetki yetki) {
		this.yetki = yetki;
	}

	@Override
	public String toString() {
		return "RolYetki [rol=" + rol + ", yetki=" + yetki + "]";
	}

}
