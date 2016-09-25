package com.vektorel.assetman.web.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author emre
 *
 */
public class Yazilim extends BaseEntity{

	Long id;
	String tanim;
	String lisansKod;
	BigDecimal lisansFiyati;
	Date lisansBaslamaTarihi;
	Date lisansBitisTarihi;
	Firma firma;
	String aciklama;
	
}
