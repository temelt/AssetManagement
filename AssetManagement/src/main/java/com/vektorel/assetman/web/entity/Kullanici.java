package com.vektorel.assetman.web.entity;

import javax.persistence.Table;

/**
 * 
 * @author ttemel
 *
 */
@Table(name="yet_kullanici")
public class Kullanici extends BaseEntity{

	Long id;
	String username;
	String password;
}
