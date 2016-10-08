package com.vektorel.assetman.web.view.firma;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Firma;
import com.vektorel.assetman.web.service.firma.FirmaService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@ManagedBean(name="firmaView")
public class FirmaView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -30734031444095449L;
	LazyDataModel<Firma> lazyDataModel;
	Firma firma = null;
	FirmaService firmaService = null;
	
	@PostConstruct
	private void init() {
		firmaService = new FirmaService();
		firma = new Firma();
		listele();
	}

	@SuppressWarnings("unchecked")
	public void kaydet() {
		try {
			if (firma.getId() == null) {
				firma.setEklemeTarihi(new Date());
				firmaService.save(firma);
				firma = new Firma();
				listele();
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Kaydedildi."));
			}else{
				firma.setGuncellemeTarihi(new Date());
				firmaService.update(firma);
				firma = new Firma();
				listele();
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Güncellendi."));
			}
		} catch (DbException e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sil(Long rolId) {
		try {
			firmaService.delete(rolId);
			firma = new Firma();
			listele();
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Silindi."));
		}catch (DbException e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listele() {
		lazyDataModel = new LazyDataModel<Firma>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3966021225522410051L;
			@Override
			public List<Firma> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {
				PagingResult result = firmaService.getAllByPaging(first,pageSize, sortOrder, filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			 @Override
			    public Firma getRowData(String rowKey) {
			        for(Firma y : lazyDataModel) {
			            if(y.getId().equals(rowKey))
			                return y;
			        }
			 
			        return null;
			    }
		};

	}


	public LazyDataModel<Firma> getlazyDataModel() {
		return lazyDataModel;
	}

	public void setlazyDataModel(LazyDataModel<Firma> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public void getById(Long id) {
		firma = (Firma) firmaService.getById(id);
	}
}
