package com.vektorel.assetman.web.view.kisi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.service.kisi.KisiService;


@ManagedBean(name="kisiView")
public class KisiView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6657286050378044309L;
	
	List<Kisi> kisis=new ArrayList<Kisi>();
	KisiService kisiService=null;
	Kisi kisi;
	
	@PostConstruct
	private void init() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(false);
		if(session == null && facesContext.getExternalContext() !=null)
		   session = (HttpSession)facesContext.getExternalContext().getSession(true);
		
		kisi=new Kisi();
		kisiService=new KisiService();
		kisis=kisiService.getAll();
	}
	
	public void kaydet() {
		kisiService.save(kisi);
		kisi=new Kisi();
		kisis=kisiService.getAll();
	}
	
	public void sil(Long id) {
		kisiService.delete(id);
		kisi=new Kisi();
		kisis=kisiService.getAll();
	}
	
	public List<Kisi> getKisis() {
		return kisis;
	}
	
	public Kisi getKisi() {
		return kisi;
	}
	
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
	
}
