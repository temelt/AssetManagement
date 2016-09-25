package com.vektorel.assetman.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1345673455552L;

	String ekleyen;
    Date eklemeTarihi;
    String guncelleyen;
    Date guncellemeTarihi;
    Boolean durum;

    @Column(name = "crtr")
    public String getEkleyen() {
        return ekleyen;
    }

    public void setEkleyen(String ekleyen) {
        this.ekleyen = ekleyen;
    }

    @Column(name = "crtm")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEklemeTarihi() {
        return eklemeTarihi;
    }

    public void setEklemeTarihi(Date eklemeTarihi) {
        this.eklemeTarihi = eklemeTarihi;
    }

    @Column(name = "updtr")
    public String getGuncelleyen() {
        return guncelleyen;
    }

    public void setGuncelleyen(String guncelleyen) {
        this.guncelleyen = guncelleyen;
    }

    @Column(name = "updtm")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getGuncellemeTarihi() {
        return guncellemeTarihi;
    }

    public void setGuncellemeTarihi(Date guncellemeTarihi) {
        this.guncellemeTarihi = guncellemeTarihi;
    }

    @Column(name = "state")
    public Boolean getDurum() {
        return durum;
    }

    public void setDurum(Boolean durum) {
        this.durum = durum;
    }
	
}
