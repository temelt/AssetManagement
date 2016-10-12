package com.vektorel.assetman.web.view.proje;

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

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.entity.Proje;
import com.vektorel.assetman.web.service.kisi.KisiService;
import com.vektorel.assetman.web.service.proje.ProjeService;
import com.vektorel.assetman.web.utilities.PagingResult;

@Controller("projeView")
@Scope("view")
public class ProjeView implements Serializable{

private static final long serialVersionUID = -6657286050378044309L;
	
	@Autowired
	private transient ProjeService projeService;
	
	
	Proje proje; 
	LazyDataModel<Proje> lazyModel;
	public ProjeView() {
		super();
		System.out.println("ProjeView Oluþturuldu.");
	}
	
	@PostConstruct
	private void init() {		
		proje=new Proje();
		listele();
	}
	
	public void kaydet() {
		try {
			if(proje.getId()!=null){
				projeService.update(proje);
			}else{
				projeService.save(proje);
			}
			proje=new Proje();
			listele();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		}

	}
	
	public void guncelle(Long id) {
		proje=projeService.getById(id);
	}
	
	public void sil(Long id) {
		projeService.delete(id);
		proje=new Proje();
		listele();
	}
	
	public void listele(){
		lazyModel=new LazyDataModel<Proje>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8620783067167021310L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Proje> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
			
				PagingResult result = projeService.getAllByPaging(first,pageSize,sortOrder , filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			
			 @Override
			    public Proje getRowData(String rowKey) {
			        for(Proje k : lazyModel) {
			            if(k.getId().equals(rowKey))
			                return k;
			        }
			 
			        return null;
			    }
			
		};
	}

	
	public Proje getProje() {
		return proje;
	}
	
	public void setProje(Proje proje) {
		this.proje = proje;
	}
	
	public LazyDataModel<Proje> getLazyModel() {
		return lazyModel;
	}
}
