package com.vektorel.assetman.web.view.stokkart;

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

import com.vektorel.assetman.web.entity.Firma;
import com.vektorel.assetman.web.entity.StokKart;
import com.vektorel.assetman.web.service.StokKartService;
import com.vektorel.assetman.web.utilities.PagingResult;

@Controller("stokKartView")
@Scope("view")
public class StokKartView implements Serializable{
	
	private static final long serialVersionUID = -6657286050378044309L;
	
	@Autowired
	private transient StokKartService stokKartService;
	
	StokKart stokkart;
	Firma ureticiFirma;
	
	

	public Firma getUreticiFirma() {
		return ureticiFirma;
	}



	public void setUreticiFirma(Firma ureticiFirma) {
		this.ureticiFirma = ureticiFirma;
	}

	LazyDataModel<StokKart> lazyModel;
	
	public StokKartView() {
		super();
		System.out.println("StokKartView Oluþturuldu.");
	}
	
	
	
	@PostConstruct
	private void init() {
		stokkart=new StokKart();
		listele();
	}
	
	public void kaydet() {
		try {
			if(stokkart.getId()!=null){
				stokKartService.update(stokkart);
			}else{
				stokKartService.save(stokkart);
			}
			stokkart=new StokKart();
			listele();
			
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		}

	}
	
	public void guncelle(Long id) {
		stokkart=stokKartService.getById(id);
	}
	
	public void sil(Long id) {
		listele();
	}
	
	public void listele(){
		lazyModel=new LazyDataModel<StokKart>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8620783067167021310L;

			@SuppressWarnings("unchecked")
			@Override
			public List<StokKart> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
				PagingResult result = stokKartService.getAllByPaging(first,pageSize,sortOrder , filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			
			 @Override
			    public StokKart getRowData(String rowKey) {
			        for(StokKart k : lazyModel) {
			            if(k.getId().equals(rowKey))
			                return k;
			        }
			 
			        return null;
			    }
			
		};
	}

	
	public StokKart getStokkart() {
		return stokkart;
	}
	
	public void setStokkart(StokKart stokkart) {
		this.stokkart = stokkart;
	}
	
	public LazyDataModel<StokKart> getLazyModel() {
		return lazyModel;
	}

}