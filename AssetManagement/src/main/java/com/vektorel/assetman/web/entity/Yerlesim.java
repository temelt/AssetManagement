package com.vektorel.assetman.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author eda
 *
 */
@Entity
@Table(name="gnl_yerlesim")
public class Yerlesim extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6801468833533161885L;
	
	
	Long id;
	String tanim;
	String kod;
	Yerlesim ustYerlesim;
	YerlesimTip yerlesimTip;
	
	@Id
	@SequenceGenerator(allocationSize=1,name="seq_yerlesim",sequenceName="seq_yerlesim")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_yerlesim")
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
	
	@JoinColumn(name="ust_yerlesim_id")
	@ManyToOne(optional=true)
	public Yerlesim getUstYerlesim() {
		return ustYerlesim;
	}
	public void setUstYerlesim(Yerlesim ustYerlesim) {
		this.ustYerlesim = ustYerlesim;
	}
	
	@Enumerated()
	@Column(name="yerlesim_tip")
	public YerlesimTip getYerlesimTip() {
		return yerlesimTip;
	}
	public void setYerlesimTip(YerlesimTip yerlesimTip) {
		this.yerlesimTip = yerlesimTip;
	}
	
	
}
