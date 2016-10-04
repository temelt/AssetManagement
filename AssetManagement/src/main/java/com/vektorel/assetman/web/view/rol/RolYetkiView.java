package com.vektorel.assetman.web.view.rol;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.entity.RolYetki;
import com.vektorel.assetman.web.entity.Yetki;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.service.rol.RolService;
import com.vektorel.assetman.web.service.yetki.RolYetkiService;
import com.vektorel.assetman.web.service.yetki.YetkiService;
import com.vektorel.assetman.web.utilities.PagingResult;

@ManagedBean(name = "rolYetkiView")
@ViewScoped
public class RolYetkiView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2458955553269023922L;

	RolYetki rolYetki ;
	RolYetkiService rolYetkiService = null;
	YetkiService yetkiService = null;
	RolService rolService = null;
	LazyDataModel<RolYetki> lazyDataModel;

	@PostConstruct
	private void init() {

		rolYetkiService = new RolYetkiService();
		rolYetki = new RolYetki();
		yetkiService = new YetkiService();
		rolService = new RolService();
		listele();
	}

	public void listele() {
		lazyDataModel = new LazyDataModel<RolYetki>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3338650643514358924L;

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
		if(rolYetki.getRol().getKod()!=null){
		rolYetkiService.save(rolYetki);

		}
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