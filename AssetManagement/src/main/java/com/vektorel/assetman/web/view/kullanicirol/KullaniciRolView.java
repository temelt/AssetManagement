package com.vektorel.assetman.web.view.kullanicirol;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Kullanici;
import com.vektorel.assetman.web.entity.KullaniciRol;
import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.service.kullanici.KullaniciService;
import com.vektorel.assetman.web.service.kullanicirol.KullaniciRolService;
import com.vektorel.assetman.web.service.rol.RolService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@ManagedBean(name="kullaniciRolView")
@ViewScoped
public class KullaniciRolView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4944998596765498275L;
	LazyDataModel<KullaniciRol> lazyDataModel;
	KullaniciRol kullaniciRol = null;
	KullaniciRolService kullaniciRolService = null;
	RolService rolService = null;
	KullaniciService kullaniciService = null;
	
	@PostConstruct
	private void init() {
		kullaniciRolService = new KullaniciRolService();
		kullaniciRol = new KullaniciRol();
		rolService = new RolService();
		kullaniciService=new KullaniciService();
		listele();
	}

	@SuppressWarnings("unchecked")
	public void kaydet() {
		try {
			if (kullaniciRol.getId() == null) {
				kullaniciRol.setEklemeTarihi(new Date());
				kullaniciRolService.save(kullaniciRol);
				kullaniciRol = new KullaniciRol();
				listele();
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Kaydedildi."));
			}else{
				kullaniciRol.setGuncellemeTarihi(new Date());
				kullaniciRolService.update(kullaniciRol);
				kullaniciRol = new KullaniciRol();
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
			kullaniciRolService.delete(rolId);
			kullaniciRol = new KullaniciRol();
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
		lazyDataModel = new LazyDataModel<KullaniciRol>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3966021225522410051L;
			@Override
			public List<KullaniciRol> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {
				PagingResult result = kullaniciRolService.getAllByPaging(first,pageSize, sortOrder, filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			 @Override
			    public KullaniciRol getRowData(String rowKey) {
			        for(KullaniciRol y : lazyDataModel) {
			            if(y.getId().equals(rowKey))
			                return y;
			        }
			 
			        return null;
			    }
		};

	}
	public List<Kullanici> acompKullanici(String term) {		
		return kullaniciService.acompKullanici(term);
	}
	public List<Rol> acompRol(String term) {		
		return rolService.acompRol(term);
	}
	public LazyDataModel<KullaniciRol> getlazyDataModel() {
		return lazyDataModel;
	}

	public void setlazyDataModel(LazyDataModel<KullaniciRol> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public KullaniciRol getKullaniciRol() {
		return kullaniciRol;
	}

	public void setKullaniciRol(KullaniciRol kullaniciRol) {
		this.kullaniciRol = kullaniciRol;
	}

	public void getById(Long id) {
		kullaniciRol = (KullaniciRol) kullaniciRolService.getById(id);
	}
}
