package com.vektorel.assetman.web.view.kisi;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.assetman.web.entity.Adres;
import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.service.kisi.KisiService;
import com.vektorel.assetman.web.utilities.PagingResult;

//@ManagedBean
//@ViewScoped
//@Component
@Controller("kisiView")
@Scope("view")
public class KisiView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6657286050378044309L;
	
	@Autowired
	private transient KisiService kisiService;
	
	Kisi kisi;
	LazyDataModel<Kisi> lazyModel;
	
	public KisiView() {
		super();
		System.out.println("KisiView Oluþturuldu.");
	}
	
	@PostConstruct
	private void init() {
		
		kisi=new Kisi();
		listele();
	}
	
	public void kaydet() {
		try {
			if(kisi.getId()!=null){
				kisiService.update(kisi);
			}else{
				kisiService.save(kisi);
			}
			kisi=new Kisi();
			listele();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		}

	}
	
	public void guncelle(Long id) {
		kisi=kisiService.getById(id);
		if(kisi.getAdres()==null){
			kisi.setAdres(new Adres());
		}
	}
	
	public void sil(Long id) {
		kisiService.delete(id);
		kisi=new Kisi();
		listele();
	}
	
	public void listele(){
		lazyModel=new LazyDataModel<Kisi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8620783067167021310L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Kisi> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
			
				PagingResult result = kisiService.getAllByPaging(first,pageSize,sortOrder , filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			
			 @Override
			    public Kisi getRowData(String rowKey) {
			        for(Kisi k : lazyModel) {
			            if(k.getId().equals(rowKey))
			                return k;
			        }
			 
			        return null;
			    }
			
		};
	}

	
	public Kisi getKisi() {
		return kisi;
	}
	
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
	
	public LazyDataModel<Kisi> getLazyModel() {
		return lazyModel;
	}
	
}
