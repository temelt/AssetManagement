package com.vektorel.assetman.web.view.rol;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.service.rol.RolService;
import com.vektorel.assetman.web.utilities.PagingResult;

@ManagedBean(name = "rolView")
@ViewScoped
public class RolView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LazyDataModel<Rol> lazyModel;
	Rol rol = null;
	RolService rolService = null;

	@PostConstruct
	private void init() {
		rolService = new RolService();
		rol = new Rol();
		listele();
	}

	public void kaydet() {
		if (rol.getId() == null) {
			rol.setEklemeTarihi(new Date());
			rolService.save(rol);
		} else {
			rol.setGuncellemeTarihi(new Date());
			rolService.update(rol);
		}
		rol = new Rol();
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
		};

	}

	public void sil(Long rolId) {
		rolService.delete(rolId);
		rol = new Rol();
		listele();
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
