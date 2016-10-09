package com.vektorel.assetman.web.view.rol;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.service.rol.RolService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Controller("rolView")
@Scope("view")
public class RolView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LazyDataModel<Rol> lazyModel;
	Rol rol ;
	
	@Autowired
	private transient RolService rolService ;

	@PostConstruct
	private void init() {
		rol = new Rol();
		listele();
	}

	public void kaydet() {
		try {
				if (rol.getId() == null) {
					rol.setEklemeTarihi(new Date());
					rolService.save(rol);
					rol = new Rol();
			        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Kaydedildi."));
				} else {
					rol.setGuncellemeTarihi(new Date());
					rolService.update(rol);
					rol = new Rol();
			        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Güncellendi."));
				}		
			} catch (DbException d) {		
				   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu . " + d.getMessage()));
			}catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu."));
			}
			
		listele();
	}

	public LazyDataModel<Rol> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Rol> lazyModel) {
		this.lazyModel = lazyModel;
	}

	private void listele() {
		lazyModel = new LazyDataModel<Rol>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public List<Rol> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				PagingResult result = rolService.getAllByPaging(first,
						pageSize, sortOrder, filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			 @Override
			    public Rol getRowData(String rowKey) {
			        for(Rol r : lazyModel) {
			            if(r.getId().equals(rowKey))
			                return r;
			        }
			 
			        return null;
			    }
		};

	}

	public void sil(Long rolId) {
		try {
			rolService.delete(rolId);
			rol = new Rol();
			listele();
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Silindi."));
		}catch (DbException d) {		
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu . " + d.getMessage()));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu."));
		}
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public void getById(Long id) {
		rol = rolService.getById(id);
	}

}
