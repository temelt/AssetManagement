package com.vektorel.assetman.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author heroglu
 * 
 */
@Entity
@Table(name = "yet_kullanici_rol")
public class KullaniciRol extends BaseEntity {

	Long id;
	Rol rol;
	Kullanici kullanici;

	public KullaniciRol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KullaniciRol(Rol rol, Kullanici kullanici) {
		super();
		this.rol = rol;
		this.kullanici = kullanici;
	}

	@Id
	@SequenceGenerator(sequenceName="seq_gnl_kullanici_rol",name="seq_gnl_kullanici_rol",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="seq_gnl_kullanici_rol",strategy=GenerationType.SEQUENCE)
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

	@ManyToOne()
	@JoinColumn(name="kullanici_id")
	public Kullanici getKullanici() {
		return kullanici;
	}
	
	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	@Override
	public String toString() {
		return "KullaniciRol [rol=" + rol + ", kullanici=" + kullanici + "]";
	}
	
}
