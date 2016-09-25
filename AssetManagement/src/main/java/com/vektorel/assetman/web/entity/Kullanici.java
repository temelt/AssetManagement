package com.vektorel.assetman.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	Long id;
	String username;
	String password;
	
	@Id
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
	
	
}
