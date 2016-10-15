package com.vektorel.assetman.web.view.yazilim;

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
import com.vektorel.assetman.web.entity.Yazilim;
import com.vektorel.assetman.web.service.firma.FirmaService;
import com.vektorel.assetman.web.service.yazilim.YazilimService;
import com.vektorel.assetman.web.utilities.PagingResult;

@Controller("yazilimView")
@Scope("view")
public class YazilimView implements Serializable {
	/**
* 
*/
	private static final long serialVersionUID = 5911808317913971586L;

	@Autowired
	private transient FirmaService firmaService;
	Firma firma;
	LazyDataModel<Yazilim> lazyModel;
	@Autowired
	private transient YazilimService yazilimService;
	Yazilim yazilim;

	@PostConstruct
	private void init() {
		yazilim = new Yazilim();
		firma = new Firma();
		listele();
	}

	public void kaydet() {
		try {
			if (yazilim.getId() != null) {
				yazilimService.update(yazilim);
			} else {
				yazilimService.save(yazilim);
			}
			yazilim = new Yazilim();
			listele();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Hata Oluþtu", e.getMessage()));
		}
		;
	}

	public void guncelle(Long id) {
		yazilim = yazilimService.getById(id);
	}

	public List<Firma> acompFirma(String term) {
		return firmaService.acompFirma(term);
	}

	public void sil(Long id) {
		yazilimService.delete(id);
		yazilim = new Yazilim();
		listele();
	}

	public void listele() {
		lazyModel = new LazyDataModel<Yazilim>() {
			/**
* 
*/
			private static final long serialVersionUID = -8620783067167021310L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Yazilim> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				PagingResult result = yazilimService.getAllByPaging(first,
						pageSize, sortOrder, filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}

			@Override
			public Yazilim getRowData(String rowKey) {
				for (Yazilim y : lazyModel) {
					if (y.getId().equals(rowKey))
						return y;
				}

				return null;
			}
		};
	}

	public Yazilim getYazilim() {
		return yazilim;
	}

	public void setYazilim(Yazilim yazilim) {
		this.yazilim = yazilim;
	}

	public LazyDataModel<Yazilim> getLazyModel() {
		return lazyModel;
	}
}