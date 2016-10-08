package com.vektorel.assetman.web.entity;

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
 * @author recep
 * 
 */
@Entity
@Table(name="STK_EKIPMAN_OZELLIK")
public class EkipmanOzellik extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;
	String ozellik;
	String kod;
	Ekipman ekipman;

	@Id
	@SequenceGenerator(allocationSize=1,name="seq_ekipman_ozellik",sequenceName="seq_ekipman_ozellik")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_ekipman_ozellik")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOzellik() {
		return ozellik;
	}

	public void setOzellik(String ozellik) {
		this.ozellik = ozellik;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	@JoinColumn(name="ekipman_id")
	@ManyToOne(optional=false)
	public Ekipman getEkipman() {
		return ekipman;
	}

	public void setEkipman(Ekipman ekipman) {
		this.ekipman = ekipman;
	}

}
