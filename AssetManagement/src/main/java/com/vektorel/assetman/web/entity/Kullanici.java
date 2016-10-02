package com.vektorel.assetman.web.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author ttemel
 *
 */
@Entity
@Table(name="yet_kullanici")
public class Kullanici extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9141283999805149784L;
	private Long id;
	private String username;
	private String password;
	private Kisi kisi;
	
	@Id
	@SequenceGenerator(allocationSize=1,name="seq_kullanici",sequenceName="seq_kullanici")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_kullanici")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JoinColumn(name="kisi_id")
	@ManyToOne(optional=true)
	public Kisi getKisi() {
		return kisi;
	}
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
	
	
}
