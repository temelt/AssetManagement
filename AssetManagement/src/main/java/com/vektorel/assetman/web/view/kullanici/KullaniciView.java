package com.vektorel.assetman.web.view.kullanici;

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
import com.vektorel.assetman.web.entity.Kullanici;
import com.vektorel.assetman.web.service.kisi.KisiService;
import com.vektorel.assetman.web.service.kullanici.KullaniciService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;


@Controller("kullaniciView")
@Scope("view")
public class KullaniciView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6657286050378044309L;
	
	@Autowired
	private transient KullaniciService kullaniciService;
	
	@Autowired
	private transient KisiService kisiService ;
	Kullanici kullanici;
	LazyDataModel<Kullanici> lazyModel;
	
	@PostConstruct
	private void init() {
		kullanici=new Kullanici();
		listele();
	}
	
	public void kaydet() {
		try {
			if(kullanici.getId()!=null){
				kullaniciService.update(kullanici);
			}else{
				kullaniciService.save(kullanici);
			}
			kullanici=new Kullanici();
			listele();
		} catch (DbException e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Kisi> acompKisi(String term) {
		return kisiService.acompKisi(term);
	}
	
	public void guncelle(Long id) {
		kullanici=kullaniciService.getById(id);
	}
	
	public void yeni() {
		kullanici=new Kullanici();
	}
	
	public void sil(Long id) {
		kullaniciService.delete(id);
		kullanici=new Kullanici();
		listele();
	}
	
	public void listele(){
		lazyModel=new LazyDataModel<Kullanici>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8620783067167021310L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Kullanici> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
			
				PagingResult result = kullaniciService.getAllByPaging(first,pageSize,sortOrder , filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			
			 @Override
			    public Kullanici getRowData(String rowKey) {
			        for(Kullanici k : lazyModel) {
			            if(k.getId().equals(rowKey))
			                return k;
			        }
			 
			        return null;
			    }
			
		};
	}

	
	public Kullanici getKullanici() {
		return kullanici;
	}
	
	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}
	
	public LazyDataModel<Kullanici> getLazyModel() {
		return lazyModel;
	}
	
}
