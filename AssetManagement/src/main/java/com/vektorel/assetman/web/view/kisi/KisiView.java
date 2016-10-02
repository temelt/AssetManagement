package com.vektorel.assetman.web.view.kisi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.service.kisi.KisiService;
import com.vektorel.assetman.web.utilities.PagingResult;


@ManagedBean(name="kisiView")
public class KisiView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6657286050378044309L;
	
	KisiService kisiService=null;
	Kisi kisi;
	LazyDataModel<Kisi> lazyModel;
	
	@PostConstruct
	private void init() {
		
		kisi=new Kisi();
		kisiService=new KisiService();
		listele();
	}
	
	public void kaydet() {
		kisiService.save(kisi);
		kisi=new Kisi();
		listele();
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

			@Override
			public List<Kisi> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
			
				PagingResult result = kisiService.getAllByPaging(first,pageSize,sortOrder , filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
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
