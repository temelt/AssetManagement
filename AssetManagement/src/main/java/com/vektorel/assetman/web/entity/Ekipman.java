package com.vektorel.assetman.web.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author recep
 *
 */
@Entity
 @Table(name="STK_EKIPMAN")
public class Ekipman extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String tanim;
	private String seriNo;
	private String kod;
	private StokKart stokKart;
	private Lokasyon lokasyon;
	private Yerlesim yerlesim;
	private Date ekipmanTarihi;
	private BigDecimal maliyet;
	private Personel personel;
	private Date kurulumTarihi;
	private EkipmanTip ekipmanTip;

	@Id
	@SequenceGenerator(allocationSize=1,name="seq_ekipman",sequenceName="seq_ekipman")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_ekipman")
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
	
	public String getSeriNo() {
		return seriNo;
	}

	public void setSeriNo(String seriNo) {
		this.seriNo = seriNo;
	}

	public StokKart getStokKart() {
		return stokKart;
	}

	public void setStokKart(StokKart stokKart) {
		this.stokKart = stokKart;
	}

	@JoinColumn(name="lokasyon_id")
	@ManyToOne(optional=true)
	public Lokasyon getLokasyon() {
		return lokasyon;
	}

	public void setLokasyon(Lokasyon lokasyon) {
		this.lokasyon = lokasyon;
	}

	@JoinColumn(name="yerlesim_id")
	@ManyToOne(optional=true)
	public Yerlesim getYerlesim() {
		return yerlesim;
	}

	public void setYerlesim(Yerlesim yerlesim) {
		this.yerlesim = yerlesim;
	}

	public Date getEkipmanTarihi() {
		return ekipmanTarihi;
	}

	public void setEkipmanTarihi(Date ekipmanTarihi) {
		this.ekipmanTarihi = ekipmanTarihi;
	}

	public BigDecimal getMaliyet() {
		return maliyet;
	}

	public void setMaliyet(BigDecimal maliyet) {
		this.maliyet = maliyet;
	}

	@JoinColumn(name="personel_id")
	@ManyToOne(optional=true)
	public Personel getPersonel() {
		return personel;
	}

	public void setPersonel(Personel personel) {
		this.personel = personel;
	}

	public Date getKurulumTarihi() {
		return kurulumTarihi;
	}

	public void setKurulumTarihi(Date kurulumTarihi) {
		this.kurulumTarihi = kurulumTarihi;
	}

	@Enumerated(EnumType.ORDINAL)
	public EkipmanTip getEkipmanTip() {
		return ekipmanTip;
	}
	public void setEkipmanTip(EkipmanTip ekipmanTip) {
		this.ekipmanTip = ekipmanTip;
	}
}
