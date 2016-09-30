package com.vektorel.assetman.web.view.yazilim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.vektorel.assetman.web.entity.Yazilim;
import com.vektorel.assetman.web.service.yazilim.YazilimService;

public class YazilimView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5911808317913971586L;
	List<Yazilim> yazilims=new ArrayList<Yazilim>();
	YazilimService yazilimService=null;
	Yazilim yazilim;
	
	@PostConstruct
	private void init() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(false);
		if(session == null && facesContext.getExternalContext() !=null)
		   session = (HttpSession)facesContext.getExternalContext().getSession(true);
		
		yazilim=new Yazilim();
		yazilimService=new YazilimService();
		yazilims=yazilimService.getAll();
	}
	
	public void kaydet() {
		yazilimService.save(yazilim);
		yazilim=new Yazilim();
		yazilims=yazilimService.getAll();
	}
	
	public void sil(Long id) {
		yazilimService.delete(id);
		yazilim=new Yazilim();
		yazilims=yazilimService.getAll();
	}
	
	public List<Yazilim> getYazilims() {
		return yazilims;
	}
	
	public Yazilim getYazilim() {
		return yazilim;
	}
	
	public void setYazilim(Yazilim yazilim) {
		this.yazilim = yazilim;
	}
	
}
