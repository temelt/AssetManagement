package com.vektorel.assetman.web.view.rol;

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

import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.entity.RolYetki;
import com.vektorel.assetman.web.entity.Yetki;
import com.vektorel.assetman.web.service.rol.RolService;
import com.vektorel.assetman.web.service.yetki.RolYetkiService;
import com.vektorel.assetman.web.service.yetki.YetkiService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;


@Controller("rolYetkiView")
@Scope("view")
public class RolYetkiView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2458955553269023922L;

	RolYetki rolYetki ;
	
	@Autowired
	private transient RolYetkiService rolYetkiService;
	
	@Autowired
	private transient YetkiService yetkiService ;
	
	@Autowired	
	private transient RolService rolService;
	LazyDataModel<RolYetki> lazyDataModel;

	@PostConstruct
	private void init() {
		rolYetki = new RolYetki();
		listele();
	}

	public void listele() {
		lazyDataModel = new LazyDataModel<RolYetki>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3338650643514358924L;

			@SuppressWarnings("unchecked")
			@Override
			public List<RolYetki> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				PagingResult result = rolYetkiService.getAllByPaging(first,pageSize, sortOrder, filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}


		};

	}
	public void kaydet() {
		try {
			if(rolYetki.getRol().getKod()!=null){
				if (rolYetki.getId()==null) {
					rolYetkiService.save(rolYetki);
					rolYetki = new RolYetki();
					listele();
			        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Kaydedildi."));
					
				} else {
					rolYetkiService.update(rolYetki);
					rolYetki = new RolYetki();
					listele();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Güncellendi."));	
					}
			}
		} catch (DbException d) {		
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu . " + d.getMessage()));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu."));
		}
		
	}
	public void sil(Long rolId) {
		try {
			rolYetkiService.delete(rolId);
			rolYetki=new RolYetki();
			listele();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Silindi."));

		} catch (DbException d) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu . " + d.getMessage()));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Hata Oluþtu."));

		}
	}
	public void getById(Long rolId) {
		rolYetki=rolYetkiService.getById(rolId);
		}
	public List<Yetki> acompYetki(String term) {		
		return yetkiService.acompYetki(term);
	}
	public List<Rol> acompRol(String term) {		
		return rolService.acompRol(term);
	}
	public LazyDataModel<RolYetki> getLazyDataModel() {
		return lazyDataModel;
	}

	public RolYetki getRolYetki() {
		return rolYetki;
	}

	public void setRolYetki(RolYetki rolYetki) {
		this.rolYetki = rolYetki;
	}
}